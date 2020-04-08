package com.bw.edu.ctb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class CtbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtbApplication.class, args);
		System.out.println("成功了");
	}

}
