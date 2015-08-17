package com.shz.foundation.rest;

import java.io.IOException;

import com.shz.foundation.utils.JsonSerializer;
import com.shz.foundation.utils.LogUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求结果，分成功结果和错误结果两种
 * @author pc
 *
 */
public abstract class RequestResult {
	
	private static Logger logger=LoggerFactory.getLogger(RequestResult.class);
	
	public static <T> SuccessResult<T> success(T result) {
		SuccessResult<T> successResult=new SuccessResult<>();
		successResult.result=result;
		return successResult;
	}
	
	public static FailResult error(Integer code, String error, String errorDescription) {
		FailResult failResult=new FailResult();
		failResult.setCode(code);
		failResult.setError(error);
		failResult.setErrorDescription(errorDescription);
		return failResult;
	}
	
	public static FailResult error(ErrorCode code, String errorDescription) {
		return error(code.getCode(), code.getError(), errorDescription);
	}
	
	public String toJson() {
		try {
			return JsonSerializer.Serialize(this);
		} catch (IOException e) {
			logger.debug(e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public abstract RequestStatus getStatus();
	
	public static enum RequestStatus {
		OK,ERROR
	}
	public static class SuccessResult<T> extends RequestResult {
		private SuccessResult(){}
		private T result;
		
		public T getResult() {
			return result;
		}
		public void setResult(T result) {
			this.result = result;
		}
		
		@Override
		public RequestStatus getStatus() {
			return RequestStatus.OK;
		}
	}
	public static class FailResult extends RequestResult {
		private FailResult(){}
		private Integer code;
		private String error;
		private String errorDescription;
		/**
		 * @return the code
		 */
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		/**
		 * @return the error
		 */
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		
		/**
		 * @return the errorDescription
		 */
		public String getErrorDescription() {
			return errorDescription;
		}
		/**
		 * @param errorDescription the errorDescription to set
		 */
		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}
		@Override
		public RequestStatus getStatus() {
			return RequestStatus.ERROR;
		}
	}
	
	public static String internalError(Exception e) {
		LogUtils.debug(e.toString());
		return RequestResult.error(500, "服务器内部错误", e.getMessage()).toJson();
	}
}
