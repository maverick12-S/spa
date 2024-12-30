package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.User;

@Mapper
public interface UserMapper {
	
	User findById(Long id);
	User findByName(String name);
	void updateUser(User user);

}
