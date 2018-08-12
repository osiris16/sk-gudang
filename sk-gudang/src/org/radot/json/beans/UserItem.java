package org.radot.json.beans;

import org.radot.enums.UserType;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class UserItem {

	@Expose
	private Long id;

	@Expose
	private String name;
	
	@Expose
	private String password;
	
	@Expose
	private UserType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	
	
}
