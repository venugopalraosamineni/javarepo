package com.altron.api.service;

public interface PersistenceService {

	public int storeVariableIntoDBUsingJDBC(int value);

	public int storeVariableIntoDBUsingJPA(int value);

}
