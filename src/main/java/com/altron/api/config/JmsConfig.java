package com.altron.api.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import com.altron.api.jms.TestQuestion;

//@Configuration
public class JmsConfig {

	
	@Autowired
	private UtilProperties utilProperties;
	
	@Bean
	public JndiTemplate jndiTemplate(){
		return new JndiTemplate();
	}
	
	@Bean
	public JndiObjectFactoryBean queueConnectionFactory(){
		JndiObjectFactoryBean queueConndectionFactory = new JndiObjectFactoryBean();
		queueConndectionFactory.setJndiTemplate(jndiTemplate());
		queueConndectionFactory.setJndiName(utilProperties.getConnectionFactoryName());
		return queueConndectionFactory;
	}
	
	@Bean 
	public JndiDestinationResolver jmsDestinationResolver(){
		JndiDestinationResolver destResolver = new JndiDestinationResolver();
		destResolver.setJndiTemplate(jndiTemplate());
		destResolver.setCache(true);
		return destResolver;
	}
	
	@Bean
	public JndiObjectFactoryBean jmsQueue(){
		JndiObjectFactoryBean jmsQueue = new JndiObjectFactoryBean();
		jmsQueue.setJndiTemplate(jndiTemplate());
		jmsQueue.setJndiName(utilProperties.getQueueName());
		
		return jmsQueue;
	}
		
	
	@Bean
	public TestQuestion queueListener(){
		return new TestQuestion();
	}
		
	
	@Bean 
	public DefaultMessageListenerContainer messageListener(){
		DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
		
		/**Change this value to customize the number of Concurrent Consumers. Currently I have made 5**/
		listener.setConcurrentConsumers(5);
		listener.setConnectionFactory((ConnectionFactory)queueConnectionFactory());
		listener.setDestination((Destination)jmsDestinationResolver());
		listener.setMessageListener(queueListener());
		
		return listener;
		
	}
	
	
}
	

