package com.yediat.makeatest.rmi.core;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.yediat.makeatest.rmi.tools.ConfigMemory;


public class RMIController {
	
	/* Constructors */
	public RMIController() { }
	
	public void startRMI() throws IOException {
		try {	
			// Singleton			
			ConfigMemory memory = ConfigMemory.getInstance();
			
			// Set Properties
			RMIConfigControl.setPropertyCodebase(memory.codebasePath);
			RMIConfigControl.setPropertySecurityPolicyFile(memory.securityPolicyPath);
			
			// Start RMIRegistry
			Runtime.getRuntime().exec("rmiregistry");
			
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void bindServer(Object mock) throws IOException {
		try {				
			// Singleton			
			ConfigMemory memory = ConfigMemory.getInstance();
			
			// Generate Stub and Bind Remote Object
			Remote stubServer = (Remote)UnicastRemoteObject.exportObject((Remote) mock, 0);
			
			// Registry Remote Object
			memory.registry = LocateRegistry.getRegistry();
			memory.registry.rebind(memory.serverName, stubServer);	
			
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void unbindServer(String nameServer) throws IOException, NotBoundException {
		try {				
			ConfigMemory.getInstance().registry.unbind(nameServer);
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public boolean objectOnServer(Object objectContext) {
		return ConfigMemory.getInstance().registry.equals(objectContext);
	}
	
	public void stopRMI() { 
	    try { 
			// Singleton
	    	ConfigMemory memory = ConfigMemory.getInstance();
	        
	        // Remove Files
	        RMIConfigControl.removeSecurityPolicyFile(memory.securityPolicyPath);
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	} 
	

}
