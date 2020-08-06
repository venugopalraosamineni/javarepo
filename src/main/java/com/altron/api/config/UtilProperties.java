package com.altron.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "jms.message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UtilProperties {

	private String connectionFactoryName;
	private String queueName;
	private String persistOption;
	
}
