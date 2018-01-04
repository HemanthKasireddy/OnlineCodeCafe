package com.bridgelabz.onlinecoadingcafe.admin.model;

public class Program {

	private String id;
	private String title;
	private String description;
	private String code;
	
	
	
	public Program() {

	}

	/**
	 * @param title
	 * @param description
	 * @param code
	 */
	public Program(String id,String title, String description, String code) {
		this.id=id;
		this.title = title;
		this.description = description;
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
