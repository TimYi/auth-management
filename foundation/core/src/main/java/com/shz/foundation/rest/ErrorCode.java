package com.shz.foundation.rest;

/**
 * 错误代码
 * @author pc
 *
 */
public class ErrorCode {
	
	private final Integer code;
	private final String error;
	
	public ErrorCode(Integer code, String error) {
		this.code=code;
		this.error=error;
	}
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}	
}
