package com.altron.api.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.TransactionRolledBackException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.altron.api.config.UtilProperties;
import com.altron.api.service.MessageProcessTaskService;
import com.altron.api.service.PersistenceService;

@Service
public class MessageProcessTaskServiceImpl implements MessageProcessTaskService {
	
	private static final Logger LOGGER = LogManager.getLogger(MessageProcessTaskServiceImpl.class);
	private static final String JDBC = "JDBC";
	@Autowired
	private PersistenceService persistenceService;
	
	@Value("${jmc.message.persistOption}")
	private String persistOption;

	@Override
	@Async
	public void performTaskAsync(List<Integer> integerValueList) {

		LOGGER.info("=====INSIDE THREAD : " + Thread.currentThread().getName() + "========= STARTED at" + new Date());
		long startTimeForThread = System.currentTimeMillis();
		for (int i : integerValueList) {

			try {
				doReallyComplexProcess(i);
			}
			catch(TransactionRolledBackException te){
				LOGGER.error(te);
			}
			catch (Exception e) {
				LOGGER.error(e);
			}

		}

		long endTimeForThread = System.currentTimeMillis();
		LOGGER.info(
				"=====INSIDE THREAD : " + Thread.currentThread().getName() + "========= ENDED at" + new Date());

		LOGGER.info("=====TIME TAKEN BY THREAD : " + Thread.currentThread().getName() + " = "
				+ (endTimeForThread - startTimeForThread) + " milliseconds");

	}

	private void doReallyComplexProcess(int i) throws TransactionRolledBackException, Exception {
		// Thread.sleep(5000); // This sleep represents really complex code that
		// takes 5 seconds to run and cannot be further
		// optimised
		// Comment the above Thread.sleep(5000) and call below for :
		// Question 2: Instead of Thread.sleep(5000), Store the variable “i” to the
		// database using JDBC.
		// Question 3a: Instead of Thread.sleep(5000), Store the variable “i” to the
		// database using JPA.
		LOGGER.info("utilProperties.getPersistOption()------------>" +  persistOption);
		if(JDBC.equals(persistOption)) {
			persistenceService.storeVariableIntoDBUsingJDBC(i);
		} else {
			persistenceService.storeVariableIntoDBUsingJPA(i);
		}
	}
}
