package com.user.demo.exceptionHandler;

public class API_ExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public API_ExceptionHandler(String message, Throwable cause){
        super(message, cause);
    }

    public API_ExceptionHandler(String message){
        super(message);
    }
}
