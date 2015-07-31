package com.shz.foundation.validate;

@SuppressWarnings("serial")
public class ValidateException extends Exception {

	public ValidateException(Throwable cause) {
		super(cause);
	}
	
	public ValidateException(String message) {
		super(message);
	}
}
