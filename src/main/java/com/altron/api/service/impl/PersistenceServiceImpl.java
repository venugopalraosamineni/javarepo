package com.altron.api.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.TransactionRolledBackException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altron.api.dao.PersistDao;
import com.altron.api.entity.SampleMessageEntity;
import com.altron.api.repository.SampleMessageEntityRepository;
import com.altron.api.service.PersistenceService;

@Service
public class PersistenceServiceImpl implements PersistenceService {
	private static final Logger LOGGER = LogManager.getLogger(PersistenceServiceImpl.class);
	@Autowired
	private SampleMessageEntityRepository sampleMessageEntityRepository;

	@Autowired
	private PersistDao persistDao;

	@Override
	@Transactional(rollbackFor = TransactionRolledBackException.class)
	public int storeVariableIntoDBUsingJDBC(int value) {
		LOGGER.info("in side storeVariableIntoDBUsingJDBC");
		int res = 0;

		/** calling DAO layer to save into database using JDBC Template **/
		res = persistDao.insertIntoSampleMessageTable(value);

		if (value % 2 == 0) {
			throw new TransactionRolledBackException(new javax.jms.TransactionRolledBackException("Value divisible by number two"));
		}

		return res;
	}

	@Override
	@Transactional(rollbackFor = TransactionRolledBackException.class)
	public int storeVariableIntoDBUsingJPA(int value) {
		LOGGER.info("in side storeVariableIntoDBUsingJPA");
		int res = 0;

		/** Creating Entity **/
		SampleMessageEntity sampleMessageEntity = new SampleMessageEntity();
		sampleMessageEntity.setVariableValue(value);

		/** calling DAO layer to save into database using JDBC Template **/
		SampleMessageEntity savedEntity = sampleMessageEntityRepository.save(sampleMessageEntity);

		if (savedEntity.getVariableValue() % 2 == 0) {
			throw new TransactionRolledBackException(new javax.jms.TransactionRolledBackException("Value divisible by number two"));
		}

		return res;
	}

}
