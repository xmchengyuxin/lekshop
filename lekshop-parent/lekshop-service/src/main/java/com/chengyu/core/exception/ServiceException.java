package com.chengyu.core.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 5190291468401300592L;
	
	private String message;
	
	private String[] params;
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String[] getParams() {
		return params;
	}

	public ServiceException() {}

	/**
	 * @param type 提示类型
	 * @param message 提示信息
	 */
	public ServiceException(String type, String message) {
		super(message, null);
		this.message = type+message;
	}

	/**
	 * @param message 提示信息
	 */
	public ServiceException(String message) {
		super(message, null);
		this.message = message;
	}
	
	/**
	 * @param message 提示信息
	 * @param params 国际化信息参数
	 */
	public ServiceException(String message, String[] params) {
		super(message, null);
		
		this.params = params;
		this.message = message;
	}
	
	/**
	 * @param exception 异常信息
	 */
	public ServiceException(Exception exception) {
		super(exception);
	}
	
	/**
	 * @param message 提示信息
	 * @param exception 异常信息
	 */
	public ServiceException(String message, Exception exception) {
		super(message, exception);
		this.message = message;
	}
	
	/**
	 * @param message 提示信息
	 * @param exception 异常信息
	 */
	public ServiceException(String message, String[] params, Exception exception) {
		super(message, exception);
		
		this.params = params;
		this.message = message;
	}
}