package org.radot.json.beans;

import org.radot.enums.UserType;
import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class UserAddParam extends JsonParam {
	
	@Expose
	private String name;
	
	@Expose
	private String password;
	@Expose
	private String oldPass;
	@Expose
	private String newPass;
	@Expose
	private String confirmNewPass;
	
	@Expose
	private UserType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmNewPass() {
		return confirmNewPass;
	}

	public void setConfirmNewPass(String confirmNewPass) {
		this.confirmNewPass = confirmNewPass;
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
