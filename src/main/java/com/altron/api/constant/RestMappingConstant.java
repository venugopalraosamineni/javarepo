package com.altron.api.constant;

public interface RestMappingConstant {

	String APP_BASE_URI = "/altron/v1/api";
	
	public interface Test{
		
		String TEST_BASE_URI = APP_BASE_URI+"/message";
		String TEST_ENDPOINT = "/message";
	}

}

