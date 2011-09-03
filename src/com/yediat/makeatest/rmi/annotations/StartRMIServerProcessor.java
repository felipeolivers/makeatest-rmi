package com.yediat.makeatest.rmi.annotations;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.rmi.core.RMIController;
import com.yediat.makeatest.rmi.tools.ConfigMemory;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class StartRMIServerProcessor extends AnnotationProcessor {
	private String codebasePath;
	private String securityPolicyPath;
	
	public StartRMIServerProcessor(String codebasePath, String securityPolicyPath) {
		this.codebasePath = codebasePath;
		this.securityPolicyPath = securityPolicyPath;
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		System.out.println("StartRMIServer process");
		try {
			RMIController rmi = new RMIController();
			
			// Singleton
			ConfigMemory.getInstance().codebasePath = this.codebasePath;
			ConfigMemory.getInstance().securityPolicyPath = this.securityPolicyPath;
			
			rmi.startRMI();
		} catch (Exception e) {
			throw new MakeATestAssertionError("[MakeATest-RMI Module Error] Values: " + 
																			this.codebasePath + ", " + 
																			this.securityPolicyPath + ", " + 
																			" - (" + e.getMessage() + ")");
		}
		
	}
	

}
