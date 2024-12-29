package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	private final PersonService personService;
	private final ParserUtil parser;

	@Autowired
	public PersonController(PersonService personService, ParserUtil parser) {
		this.personService = personService;
		this.parser = parser;
	}

	@PostMapping("/id")
	public Map<String, Object> receiveId(@RequestBody Map<String, Object> requestData) {

		long id = parser.parseId(requestData.get("id"));

		Person person = personService.getPersonById(id);

		Map<String, Object> response = new HashMap<>();
		response.put("id", person.getId());
		response.put("name", person.getName());
		response.put("age", person.getAge());

		return response;
	}
	
	@PostMapping("/delete")
	public Map<String,String> deleteUser(@RequestBody Map<String, Object> requestData){
		
		long id = parser.parseId(requestData.get("id"));
		
		personService.deletePerson(id);
		
		Map<String,String> response = new HashMap<>();
		response.put("message","指定された"+id+"番のユーザー情報を削除しました。");
		return response;
	}
	
	
	@GetMapping("/allId")
	public Map<String, Object> allReceiveId(){
		try {
		List<Person> persons = personService.getAllPerson();
		
		Map<String, Object> response = persons.stream()
				.collect(Collectors.toMap(
						person -> "id" + person.getId(),
						person -> person
						));
		
		return response;
		}catch(Exception e) {
			log.debug("DEBUG: Preparing data for processing"+ e);
			throw e;
			
		}
	}
	 

	@PostMapping("/add")
	 public ResponseEntity<Map<String, Object>> addUser(@RequestBody Map<String, Object> requestData) {
        try {
            // パラメータをパース
            long id = parser.parseId(requestData.get("id"));
            String name = parser.parseName(requestData.get("name"));
            int age = parser.parseAge(requestData.get("age"));

            // Person オブジェクトを生成
            Person person = new Person(id, name, age);

            // Person オブジェクトが有効か確認
            if (!person.isValid()) {
                return ResponseEntity.badRequest().body(Map.of("message", "無効なユーザーデータです。"));
            }

            // ユーザーを保存
            personService.savePerson(person);

            return ResponseEntity.ok(Map.of("message", name + "さんの登録を完了しました。"));
        } catch (Exception e) {
            // エラー発生時の処理
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "登録中にエラーが発生しました: " + e.getMessage()));
        }
    }
}
