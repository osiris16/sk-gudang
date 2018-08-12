package org.radot.exceptions;

import org.radot.enums.Errors;

public class ProcessException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6380586068888343297L;

	private final Errors error;
	
	public ProcessException(final Errors error) {
		this.error = error;
	}

	public final Errors getError() {
		return error;
	}

}
