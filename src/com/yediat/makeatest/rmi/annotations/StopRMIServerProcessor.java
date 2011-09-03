package com.yediat.makeatest.rmi.annotations;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.rmi.core.RMIController;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class StopRMIServerProcessor extends AnnotationProcessor {
	
	public StopRMIServerProcessor() {
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		System.out.println("StopRMIServer process");
		try {
			RMIController rmi = new RMIController();
			rmi.stopRMI();
		} catch (Exception e) {
			throw new MakeATestAssertionError("[MakeATest-RMI Module Error] (" + e.getMessage() + ")");
		}
		
	}
	

}
