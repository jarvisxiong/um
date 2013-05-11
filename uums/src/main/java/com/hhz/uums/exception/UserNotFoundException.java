package com.hhz.uums.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -2018826740161483274L;
	public UserNotFoundException() {
		super();
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
