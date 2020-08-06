package com.altron.api.dao.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.altron.api.dao.PersistDao;

@Repository
public class PersistDaoImpl implements PersistDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_QUERY = "INSERT INTO SAMPLE_MESSAGE_TABLE (VARIABLE_VALUE) VALUES (:VALUE) ";
	private static final String VARIABLE_VALUE = "VALUE";

	@Override
	public int insertIntoSampleMessageTable(int value) {

		MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue(VARIABLE_VALUE, value);

		return new NamedParameterJdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource())).update(INSERT_QUERY,
				parameterSource);

	}

}
