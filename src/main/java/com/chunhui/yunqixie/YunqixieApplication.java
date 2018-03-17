package com.chunhui.yunqixie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chunhui.yunqixie.service")
public class YunqixieApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunqixieApplication.class, args);
	}
}
