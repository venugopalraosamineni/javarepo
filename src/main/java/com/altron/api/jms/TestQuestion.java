package com.altron.api.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.altron.api.service.MessageProcessService;

/*@MessageDriven(
        activationConfig = { 
            @ActivationConfigProperty(propertyName = "destination", propertyValue = "tutorialQueue"), 
            @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})*/
public class TestQuestion implements MessageListener {
	
	 private static final Logger LOGGER = LogManager.getLogger(TestQuestion.class);

	@Autowired
	private MessageProcessService messageProcessService;

	public void onMessage(Message message) {
		/**
		 * Calling common service layer on Arrival of a Message
		 * 
		 * The main logic is placed inside a service layer. So that the code can be same
		 * for a message arrival and for the Web service (/AssignmentModule/v1/api/test)
		 * call as well.
		 * 
		 **/

		/** Question 4 **/
		if (message instanceof TextMessage) {
			LOGGER.info("=========The message is of Type TextMessage======Providing 10 seconds Delay====");
			delay(10000); // 10 seconds sleep
		}
		messageProcessService.processMessage();
	}

	private void delay(int msDelay) {
		try {
			Thread.sleep(msDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
