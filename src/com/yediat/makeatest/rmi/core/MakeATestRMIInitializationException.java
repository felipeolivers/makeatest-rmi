package com.yediat.makeatest.rmi.core;

public class MakeATestRMIInitializationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MakeATestRMIInitializationException(String message, Exception e) {
		super("[MakeATest RMI] " + message, e);
	}

}