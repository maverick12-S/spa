package com.example.demo.dbRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresTestRepository {
	  private final JdbcTemplate jdbcTemplate;

	    public PostgresTestRepository(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    public int getDatabaseConnectionTest() {
	        return jdbcTemplate.queryForObject("SELECT 1", Integer.class);
	    }
	}


