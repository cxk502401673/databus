package com.zjydt.sustain.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.zjydt.sustain.common", "com.zjydt.sustain.auth"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SustainAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SustainAuthApplication.class, args);
	}
}
