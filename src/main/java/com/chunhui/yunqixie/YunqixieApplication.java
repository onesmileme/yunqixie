package com.chunhui.yunqixie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.chunhui.yunqixie.service","com.chunhui.yunqixie.domain.dao"})
@ComponentScan(basePackages = {"com.chunhui.yunqixie.service"})

public class YunqixieApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunqixieApplication.class, args);
	}
}
