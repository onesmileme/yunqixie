package com.yunqixie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan(basePackages = {"com.yunqixie.service","com.yunqixie.domain.dao"})
//@ComponentScan(basePackages = {"com.yunqixie.service"})

public class YunqixieApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunqixieApplication.class, args);
	}
}
