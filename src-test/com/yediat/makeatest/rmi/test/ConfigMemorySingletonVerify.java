package com.yediat.makeatest.rmi.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yediat.makeatest.rmi.tools.ConfigMemory;

public class ConfigMemorySingletonVerify {
	
	@BeforeClass
	public static void beforeClass() {
		// Singleton			
		ConfigMemory memory = ConfigMemory.getInstance();
		memory.serverName = "Singleton Test";
	}
	
    @Test
	public void testGetInstance() {
    	// Singleton			
		ConfigMemory memory = ConfigMemory.getInstance();
        Assert.assertEquals("Singleton Test", memory.serverName);
	}

}