package com.altron.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Message;
import javax.persistence.RollbackException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.TransactionRolledBackException;
import org.springframework.stereotype.Service;

import com.altron.api.config.UtilProperties;
import com.altron.api.jms.TestQuestion;
import com.altron.api.response.CommonMessageResponse;
import com.altron.api.service.MessageProcessTaskService;
import com.altron.api.service.PersistenceService;
import com.altron.api.service.MessageProcessService;

@Service
public class MessageProcessServiceImpl implements MessageProcessService {

	private static final Logger LOGGER = LogManager.getLogger(MessageProcessServiceImpl.class);
	
	private static final String JDBC = "JDBC";
	
	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private MessageProcessTaskService messageProcessTaskService;
	
	@Value("${jmc.message.persistOption}")
	private String persistOption;

	@Override
	public CommonMessageResponse processMessage() {

		Date startDate = new Date();
		LOGGER.info("############# Call Started At : " + startDate + " ############");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 4; i++) {
			try {
				doReallyComplexProcess(i);
			} catch (TransactionRolledBackException ex) {
				LOGGER.error("======Transaction Rolled Back======For Value : " + i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Date endDate = new Date();
		long endTime = System.currentTimeMillis();

		LOGGER.info("############# Call Finished At : " + endDate + " ############");

		printReport(startDate, endDate, startTime, endTime);
		return new CommonMessageResponse("Task Executed Successfully");
	}

	private void doReallyComplexProcess(int i) throws TransactionRolledBackException,Exception {
		// Thread.sleep(5000); // This sleep represents really complex code that
		// takes 5 seconds to run and cannot be further
		// optimised

		// Comment the above Thread.sleep(5000) and call below for :
		// Question 2: Instead of Thread.sleep(5000), Store the variable “i” to the
		// database using JDBC.
		// Question 3a: Instead of Thread.sleep(5000), Store the variable “i” to the
				// database using JPA.
		LOGGER.info("Persistnace Property------------>" +  persistOption);
		if(JDBC.equals(persistOption)) {
			persistenceService.storeVariableIntoDBUsingJDBC(i);
		} else {
			persistenceService.storeVariableIntoDBUsingJPA(i);
		}
	}

	private void printReport(Date startDate, Date endDate, long startTime, long endTime) {
		LOGGER.info("= = = = = = = = = = = = = = = = = ");
		LOGGER.info("====== Execution Report =========");
		LOGGER.info("= = = = = = = = = = = = = = = = = ");
		LOGGER.info("");
		LOGGER.info("TASK START TIME : " + startDate);
		LOGGER.info("");
		LOGGER.info("TASK END TIME  : " + endDate);
		LOGGER.info("");
		LOGGER.info("EXEC TIME (in millis) : " + (endTime - startTime));

	}

	/** Question 1 **/
	/**
	 * This is a more efficient way which uses Multi-threaded approach. For a
	 * particular batch, a separate thread is created
	 **/
	@Override
	public CommonMessageResponse processMessageAsync() {
		Date startDate = new Date();
		LOGGER.info("############# Call Started At : " + startDate + " ############");

		for (int i = 0; i < 1000; i++) { // 0 1 || 2 3
			List<Integer> arrayList = new ArrayList<Integer>();
			arrayList.add(i);
			arrayList.add(i + 1);
			/********* Separate Thread for Each Call ***********/
			messageProcessTaskService.performTaskAsync(arrayList);
		}
		return new CommonMessageResponse("Task Execution Started");
	}

}
