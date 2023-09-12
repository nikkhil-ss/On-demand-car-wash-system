package com.example.demo.exception;

public class API_ExceptionHandlers extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public API_ExceptionHandlers(String message, Throwable cause){
	        super(message, cause);
	    }

	    public API_ExceptionHandlers(String message){
	        super(message);
	    }
}
