package org.radot.json.servlet;

import com.google.gson.annotations.Expose;

public class JsonResult {

	@Expose()
	private Integer code;

	@Expose()
	private String message;
	@Expose()
	private String usrType;
	
	public final Integer getCode() {
		return code;
	}
	
	public final void setCode(final Integer code) {
		this.code = code;
	}
	
	public final String getMessage() {
		return message;
	}
	
	public final void setMessage(final String message) {
		this.message = message;
	}

	public String getUsrType() {
		return usrType;
	}

	public void setUsrType(String usrType) {
		this.usrType = usrType;
	}
	
}
