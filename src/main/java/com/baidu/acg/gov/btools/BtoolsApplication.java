package com.baidu.acg.gov.btools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class BtoolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtoolsApplication.class, args);
	}

}
