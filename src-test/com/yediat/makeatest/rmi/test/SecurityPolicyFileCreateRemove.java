package com.yediat.makeatest.rmi.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.yediat.makeatest.rmi.core.RMIConfigControl;

public class SecurityPolicyFileCreateRemove {

    @Test
	public void testCreateFile() {
    	String securityPolicyFilePath = "C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\makeatest-rmi\\";
    	RMIConfigControl.setPropertySecurityPolicyFile(securityPolicyFilePath);
    	File f = new File(securityPolicyFilePath + "security.policy");
        Assert.assertTrue(f.exists());
	}
    
    @Test
	public void testRemoveFile() {
    	String securityPolicyFilePath = "C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\makeatest-rmi\\";
    	RMIConfigControl.removeSecurityPolicyFile(securityPolicyFilePath);
    	File f = new File(securityPolicyFilePath + "security.policy");
        Assert.assertTrue(!f.exists());
	}
	
	
}