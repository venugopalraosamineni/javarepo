package com.altron.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altron.api.constant.RestMappingConstant;
import com.altron.api.response.BaseApiResponse;
import com.altron.api.response.CommonMessageResponse;
import com.altron.api.response.ResponseBuilder;
import com.altron.api.service.MessageProcessService;

/** Question 05 **/
@RestController
@RequestMapping(path = RestMappingConstant.Test.TEST_BASE_URI)
public class MessageProcessorRestControlled {

	@Autowired
	private MessageProcessService messageProcessService;

	/****************************************
	 * Test End Point handler method
	 *****************************************/

	@GetMapping
	public ResponseEntity<BaseApiResponse> testEndpointHandler() {

		/** calling service layer **/
		CommonMessageResponse commonMessageResponse = messageProcessService.processMessage();

		/** Wrapping Response to BaseApiResponse **/
		BaseApiResponse baseApiResponse = ResponseBuilder.getSuccessResponse(commonMessageResponse);

		return new ResponseEntity<BaseApiResponse>(baseApiResponse, HttpStatus.OK);

	}

}
