package com.demo.springbootrediscrudwithkeyvalue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootRedisCrudWithKeyValueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisCrudWithKeyValueApplication.class, args);
	}

}
