package com.altron.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.altron.api.config.UtilProperties;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(UtilProperties.class)
public class AtronProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtronProjectApplication.class, args);
	}

}
