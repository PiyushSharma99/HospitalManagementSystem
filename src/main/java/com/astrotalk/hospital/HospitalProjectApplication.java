package com.astrotalk.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan("com.astrotalk.hospital")
@SpringBootApplication
public class HospitalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalProjectApplication.class, args);
	}

}
