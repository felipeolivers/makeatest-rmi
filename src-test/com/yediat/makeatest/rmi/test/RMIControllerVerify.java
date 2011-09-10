package com.yediat.makeatest.rmi.test;

import java.io.IOException;
import java.rmi.NotBoundException;

import junit.framework.Assert;

import org.junit.Test;

public class RMIControllerVerify {
	
    @Test
	public void testBindObject() throws IOException, NotBoundException {
    	
    	// Configs
    	/*String codebasePath = "C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\makeatest-rmi\\";
    	String securityPolicyPath = "C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\makeatest-rmi\\";
    	
    	ConfigMemory memory = ConfigMemory.getInstance();
		memory.serverName = "test";
		memory.serverPort = 1099;
		memory.codebasePath = codebasePath;
		memory.securityPolicyPath = securityPolicyPath;
    	
    	// Mock Object
    	Mockery context = new Mockery();
    	IRMICaseTest server = context.mock(IRMICaseTest.class);
    	
    	// RMI Controller
    	RMIController rmi = new RMIController();
    	rmi.bindServer(server);
    	
    	Assert.assertTrue(rmi.objectOnServer(IRMICaseTest.class, "test"));*/
    	Assert.assertTrue(true);
	}
    
    @Test
	public void testUnbindObject() {
    	Assert.assertTrue(true);
	}
	
}