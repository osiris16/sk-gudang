package org.radot.enums;

public enum Errors {
	
	PARAMETERS_INVALID										(-100, "Invalid parameter"),
	USERNAME_INVALID										(-101, "Invalid username"),
	PASSWORD_INVALID										(-102, "Invalid password"),
	USERNAME_OR_PASSWORD_INVALID							(-103, "Invalid username or password"),
	KAPTCHA_INVALID											(-104, "Invalid kaptcha"),
	
	IMPLEMENTATION_INVALID									(-300, "Invalid implementation"),
	IMPLEMENTATION_TYPE_INVALID								(-301, "Invalid implementation type"),
	
	PRIVILEGE_INVALID										(-501, "Access denied"),
	
	UNKNOWN_ERROR											(-999, "Unexpected error")
	;
	
	
	public final int code;
	public final String description;
	
	private Errors(final int code, final String description) {
		this.code = code;
		this.description = description;
	}


}
