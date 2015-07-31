package com.shz.foundation.shortmessage;

/**
 * 短信发送错误时抛出此异常。
 * @author pc
 *
 */
public class SendMessageException extends Exception {

	private static final long serialVersionUID = 4130482730550961789L;
	
	public SendMessageException(String message, Throwable cause) {
		super(message,cause);
	}

	public SendMessageException(String message) {
		super(message);
	}

	public SendMessageException(Throwable cause) {
		super(cause);
	}
}
