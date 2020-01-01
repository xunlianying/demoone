package com.demoone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.demoone.**.mapper"})
public class DemooneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemooneApplication.class, args);
	}

}
