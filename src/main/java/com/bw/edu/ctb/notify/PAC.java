package com.bw.edu.ctb.notify;

import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

/**
 * local producer and consumer
 *
 * 探活：
 *  访问 /t/ps，获取容量
 *
 * 测试方法：
 *  本地启动项目，访问/t/per?eid=3
 *  consumer中打断点
 */
@Service
public class PAC {
    private final static Logger logger = LoggerFactory.getLogger(PAC.class);
    static final Integer MAX_CAPACITY=1000000;
    //1个long占8byte，百万个占内存8M
    private BlockingQueue<Long> queue = new LinkedBlockingQueue<Long>(MAX_CAPACITY);
    @Autowired
    private ExRecConsumer exRecConsumer;

    /** 探活 */
    public Integer getCapacity(){
        return queue.size();
    }

    public void produce(Long erid){
        if(queue.size()>MAX_CAPACITY){
            logger.error("[fatal] queue exceeded!");
            return;
        }//超过最大容量，这里直接返回，不抛异常，走补偿链路
        try {
            queue.put(erid);
        } catch (InterruptedException e) {
            logger.error("[fatal error] PAC produce interrupted", e);
            promoteException(CtbExceptionEnum.PAC_INTERRUPTED);
        }
    }

    @PostConstruct
    public void init(){
        logger.error("[PAC] consumer initialing...");

        //todo 这里改造成线程池，加快消费
        this.new Consumer().start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consumer();
        }
        private void consumer() {
            while (true) {
                try {
                    Long erid = queue.take();
                    exRecConsumer.consume(erid);
                } catch (InterruptedException e) {
                    logger.error("[fatal error] PAC consumer interrupted", e);
                }//这里不能抛异常，否则会退出while循环
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    logger.error("[fatal error] thread sleep", e);
                }
            }
        }
    }
}
