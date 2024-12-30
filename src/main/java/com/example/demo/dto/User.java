package com.example.demo.dto;


public class User {
	
	private long id;
	private String username;
	private String password;
	private String token;
	
	public User(Long id,String username,String password,String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
		
	}
	
	public User() {}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}	
}