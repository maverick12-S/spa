package com.example.demo.dbTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dbRepository.PostgresTestRepository;

@SpringBootTest
public class PostgresConnectionTest {
	@Autowired
    private PostgresTestRepository postgresTestRepository;

    @Test
    public void testPostgresConnection() {
        int result = postgresTestRepository.getDatabaseConnectionTest();
        assertEquals(1, result);
    }

}
