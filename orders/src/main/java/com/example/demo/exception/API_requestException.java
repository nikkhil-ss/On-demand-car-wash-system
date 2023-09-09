package com.example.demo.exception;

public class API_requestException extends RuntimeException {
	
	 private static final long serialVersionUID = 1L;

	public API_requestException(String message, Throwable cause){
	        super(message, cause);
	    }

	    public API_requestException(String message){
	        super(message);
	    }
}
