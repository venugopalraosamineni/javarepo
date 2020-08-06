package com.altron.api.service;

import com.altron.api.response.CommonMessageResponse;

public interface MessageProcessService {

	public CommonMessageResponse processMessage();

	public CommonMessageResponse processMessageAsync();
}
