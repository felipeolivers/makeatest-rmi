package com.yediat.makeatest.rmi.core;

public class MakeATestRMIAssertionError extends AssertionError {
	
	private static final long serialVersionUID = 1L;

	public MakeATestRMIAssertionError(String message) {
		super("[MakeATest RMI] " + message);
	}
	
	public MakeATestRMIAssertionError(Throwable t) {
		super(t);
	}
	

}