package com.example.demo.service;

import java.util.Optional;
import java.util.function.Supplier;


import org.hibernate.query.IllegalQueryOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.controller.PersonController;
import com.example.demo.dto.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
	private final UserMapper userMapper;
	
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public User authenticate(String username,String password) throws IllegalAccessException{
		try {
			 User userProfile = isValidUserCredentials(username,password);
			 return userProfile;
			 
		}catch(Exception e) {
			throw new IllegalAccessException();
		}	
	}
	
	private User isValidUserCredentials(String username,String password) {
		User profile = userMapper.findByName(username);
		
	
		if(username.equals(profile.getUsername())) {
			if(password.equals(profile.getPassword())) {
				return profile;
			}else {
				String message = "ユーザーのパスワードが違います。";
				log.error(profile.getId()+"DEBUG: Preparing data for processing"+ message);
				throw new IllegalQueryOperationException(password);
			}
			
		}else {
			String message = "該当するユーザー情報が見つかりませんでした。";
			log.error(profile+"DEBUG: Preparing data for processing"+ message);
			throw new IllegalQueryOperationException(username);
		}
	}
	
	public void saveToken(Long id,String token) {
		User user = userMapper.findById(id);
		user.setToken(token);
		userMapper.updateUser(user);
	}
}
