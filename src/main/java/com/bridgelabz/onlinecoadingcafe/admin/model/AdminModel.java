package com.bridgelabz.onlinecoadingcafe.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdminModel {
	private String name;

	@Override
	public String toString() {
		return "AdminModel [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * @param email
	 * @param password
	 */
	public AdminModel(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	/**
	 * 
	 */
	public AdminModel() {

	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String email;
	private String password;

}
