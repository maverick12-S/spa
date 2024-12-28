package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Person;
import com.example.demo.mapper.PersonMapper;

@Service
public class PersonService {
	private final PersonMapper personMapper;
	
	public PersonService(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}
	
	public long parseId(Object idObject) {
		if(idObject instanceof Number) {
			return ((Number) idObject).longValue();
		}else if (idObject instanceof String) {
			return Long.parseLong((String) idObject);
		}else {
			throw new IllegalArgumentException("Inavalid id type: " + idObject.getClass().getName());
		}
	}
	
	public Person getPersonById(Long id) {
		return personMapper.findById(id);
	}
	
	public List<Person> getAllPerson(){
		return personMapper.findAll();
	}
	
	public void savePerson(Person person) {
		personMapper.insert(person);
	}
	
	public void updatePerson(Person person) {
		personMapper.update(person);
	}
	
	public void deletePerson(Long id) {
		personMapper.delete(id);
	}
	
}
