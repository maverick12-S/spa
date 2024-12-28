package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Person;
import com.example.demo.service.ParserUtil;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/apiData")
public class PersonController {
	private final PersonService personService;
	private final ParserUtil parser;
	
	@Autowired
	public PersonController(PersonService personService,ParserUtil parser) {
		this.personService = personService;
		this.parser= parser;
	}
	
	@PostMapping("/id")
	public Map<String,Object>receiveId(@RequestBody Map<String,Object> requestData){
		
		long id = parser.parseId(requestData.get("id"));
		
		Person person = personService.getPersonById(id);
		
		Map<String,Object> response = new HashMap<>();
		response.put("id",person.getId());
		response.put("name", person.getName());
		response.put("age", person.getAge());
		
		
		return response;
	}
	
//	@PostMapping("/add")
//	public String addUser(@RequestBody Map<String,Object> requestData) {
//		long id = personService.parseId(requestData.get("id"));
//		String name = personService.parseId(requestData.get("name"));
//	}
	
	
	
	
}
