package com.yediat.makeatest.rmi.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RMIConfigControl {

	public static void setPropertyCodebase(String codebasePath) {
		try {
			if(!codebasePath.equals("")) {
				// Set Property
				System.setProperty("java.rmi.server.codebase", "file:" + codebasePath + "bin\\");
			} else {
				new Exception("[MakeATest-RMI] Nothing path to codebase property.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setPropertySecurityPolicyFile(String securityPolicyFilePath) {
		try {
			if(!securityPolicyFilePath.equals("")) {
				// Create file 
				File f = new File(securityPolicyFilePath + "security.policy");
				if (!f.exists()) {
					FileWriter fstream = new FileWriter("security.policy");
					BufferedWriter out = new BufferedWriter(fstream);
					
					// All Permission
					out.write("/* MakeATest RMI Generated security.policy to RMI Permission */\n");
					out.write("/* --------------------------------------------------------- */\n");
					out.write("grant {	permission java.security.AllPermission; };\n");
					out.write("/* --------------------------------------------------------= */");
					//Close the output stream
					out.close();
				}
				// Set Property
				System.setProperty("java.security.policy", "file:" + securityPolicyFilePath + "security.policy");
			} else {
				new Exception("[MakeATest-RMI] Nothing path to security.policy file.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void removeSecurityPolicyFile(String securityPolicyFilePath) {
		try {
			if(!securityPolicyFilePath.equals("")) {
				// Create file 
				File f = new File(securityPolicyFilePath + "security.policy");
				if (f.exists())
					f.delete();
			} else {
				new Exception("[MakeATest-RMI] Nothing path to security.policy file.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}