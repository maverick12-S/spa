package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Person;

@Mapper
public interface PersonMapper {

	    Person findById(Long id);
	    List<Person> findAll();
	    void insert(Person person);
	    void update(Person person);
	    void delete(Long id);
	}

