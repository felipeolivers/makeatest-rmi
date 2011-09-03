package com.yediat.makeatest.rmi.tools;

import java.rmi.Remote;
import java.rmi.registry.Registry;

public class ConfigMemory {
	
	// Singleton
	private static ConfigMemory _instance = new ConfigMemory();
	
	public Registry registry;
	public Class<? extends Remote> classInterface;
	public String serverName;
	public String codebasePath;
	public String securityPolicyPath;
	public int serverPort;
	
	/* Getters and Setters */
	public static ConfigMemory getInstance() {
		if(_instance == null)
			_instance = new ConfigMemory();
		return _instance;
	}

}