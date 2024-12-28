package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Person;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/apiData")
public class PersonController {
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping("/id")
	public Map<String,Object>receiveId(@RequestBody Map<String,Object> requestData){
		
		long id = personService.parseId(requestData.get("id"));
		
		Person person = personService.getPersonById(id);
		
		Map<String,Object> response = new HashMap<>();
		response.put("id",person.getId());
		response.put("name", person.getName());
		response.put("age", person.getAge());
		
		return response;
	}
}
