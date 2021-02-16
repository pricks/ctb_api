package com.bw.edu.ctb;

import com.bw.edu.ctb.common.util.PropertiesOnStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.URL;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
//@MapperScan({"com.bw.edu.ctb.dao.mapper","com.bw.edu.ctb.dao.mapper.usr"})
@MapperScan({"com.bw.edu.ctb.dao.mapper"})
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
	public static final String ENV = "env";

	public static final String PROTOCOL = "prot";
	public static final String PROTOCOL_HTTPS = "https";
	public static final String PORT = "server.port";

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
		System.out.println("设置系统参数. log = "+ System.getProperty("edu-ctb.log.home.dir"));
		// 在本地启动项目时，未通过jvm参数指定项目名称，则进行默认指定，避免CMS tools等强依赖的工具报错
		PropertiesOnStartup.parse();
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
			System.setProperty("server.ssl.enabled", "false");
		}
		if(System.getProperty(PROTOCOL) == null || !"https".equalsIgnoreCase(System.getProperty(PROTOCOL))){
			String port = System.getProperty("port");
			if(port!=null){
				System.setProperty(PORT, port);
			}
		}
	}
}
