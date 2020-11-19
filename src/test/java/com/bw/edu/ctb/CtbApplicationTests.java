package com.bw.edu.ctb;

import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.mapper.CtbTitleMapper;
import com.bw.edu.ctb.util.PropertiesOnStartup;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CtbApplication.class)
public abstract class CtbApplicationTests {
    static {
        // cmsTool等初始化时会检查jvm参数project.name，如未设置将会导致应用启动失败
        System.setProperty(CtbApplication.PROJECT_NAME, "ctb");

        // 设置log输出的类型为console
        System.setProperty(CtbApplication.LOG_OUTPUT_TYPE, "console");

        PropertiesOnStartup.parse();
    }

    /**
     * 调用sleep方法，并将{@link InterruptedException}异常转换成{@link RuntimeException}
     *
     * @param millis
     *     sleep的毫秒数
     */
    @SuppressWarnings("squid:S2925")
    protected static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
