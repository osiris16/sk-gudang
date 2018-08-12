package org.radot.json.beans;

import org.radot.json.servlet.JsonParam;

import com.google.gson.annotations.Expose;

public class LoginParam extends JsonParam {
	
	@Expose()
	private String username;

	@Expose()
	private String password;

	@Expose()
	private String kaptcha;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}
	
	public String getKaptcha() {
		return kaptcha;
	}
	
	public void setKaptcha(final String kaptcha) {
		this.kaptcha = kaptcha;
	}

}
