package com.senqicloud.senqimediaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.senqicloud.senqimediaserver")
public class SenqiMediaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenqiMediaServerApplication.class, args);
	}

}
