package com.bw.edu.ctb;

import com.bw.edu.ctb.util.PropertiesOnStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.net.URL;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("com.bw.edu.ctb.dao.mapper")
public class CtbApplication {
	/**
	 * 系统变量中指定项目名称的变量key
	 */
	public static final String PROJECT_NAME = "project.name";

	/**
	 * 本地开发环境
	 */
	public static final String ENV_DEV = "dev";

	/**
	 * Spring当前激活的profile
	 */
	public static final String ENV = ENV_DEV;

	/**
	 * 日志输出的类型：file - 文件；console - 控制台。
	 */
	public static final String LOG_OUTPUT_TYPE = "log.output.type";

	public static void main(String[] args) {
		setupDefaultSystemProperties();

		SpringApplication.run(CtbApplication.class, args);
		System.out.println("成功了");
	}

	/**
	 * 设置默认的系统参数
	 */
	private static void setupDefaultSystemProperties() {
		System.out.println("设置系统参数");
		// 在本地启动项目时，未通过jvm参数指定项目名称，则进行默认指定，避免CMS tools等强依赖的工具报错。
		if (System.getProperty(PROJECT_NAME) == null) {
			System.setProperty(PROJECT_NAME, "edu-ctb");
		}

		// 如果未指定profile，默认以dev方式启动（本地开发模式）
		if (System.getProperty(ENV) == null || ENV_DEV.equals(System.getProperty(ENV))) {
			System.out.println("本地dev方式启动");
			URL devPropertyFile = Thread.currentThread().getContextClassLoader().getResource(
					"application-dev.properties");

			// 如果本地没有application-dev.properties配置文件，说明无个性化配置，fallback到日常的配置启动应用进行开发
			if (devPropertyFile == null) {
				System.setProperty(ENV, ENV_DEV);
			} else {
				System.setProperty(ENV, ENV_DEV);
			}

			// 设置log输出的类型为console
			System.setProperty(LOG_OUTPUT_TYPE, "console");
		}

		PropertiesOnStartup.parse();
	}

}
/**


 ReentrantLock.unlock() {
 	sync.release(1);
 }
 aqs.release(int arg) {
	 if (tryRelease(arg)) {
	 	Node h = head;
		 if (h != null && h.waitStatus != 0)
			 unparkSuccessor(h);
 		return true;
	 }
	 return false;
 }
 NonfairSync.tryRelease(int releases) {
	 int c = getState() - releases;
	 if (Thread.currentThread() != getExclusiveOwnerThread())
	 	throw new IllegalMonitorStateException();
	 boolean free = false;
	 if (c == 0) {
		 free = true;
		 setExclusiveOwnerThread(null);
	 }
	 setState(c);
	 return free;
 }
 aqs.unparkSuccessor(Node node) {
		int ws = node.waitStatus;
        if (ws < 0)
			compareAndSetWaitStatus(node, ws, 0);

		Node s = node.next;
		if (s == null || s.waitStatus > 0) {
			s = null;
			for (Node t = tail; t != null && t != node; t = t.prev)
				if (t.waitStatus <= 0)
					s = t;
			}
		if (s != null)
 			LockSupport.unpark(s.thread);
 }

 */
