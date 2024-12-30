package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
		@PostMapping
		public ResponseEntity<Map<String,Object>> login(@RequestBody User loginRequest){
			
			try {
				//Serviceにauthenticateメソッド(name,password)
				User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
				
				String token = UUID.randomUUID().toString();
				userService.saveToken(user.getId(), token);
				
				Map<String, Object> response = new HashMap<>();
				response.put("message","ログイン成功");
				response.put("token", token);
				return ResponseEntity.ok(response);				
			}catch(Exception e) {
				
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","認証に失敗しました。"));
			}
			
		}
		
//		@GetMapping("/protected")
//		public ResponseEntity<Map<String,String>> protectedEndpoint(@RequestHeader("Authorization")String token){
//			if(userService.isValidToken(token)) {
//				return ResponseEntity.ok(Map.of("message","認証済みユーザのみがアクセス可能なエンドポイントです。"));
//			}else {
//				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message","トークンが無効です"));
//			}
//		}
	}


