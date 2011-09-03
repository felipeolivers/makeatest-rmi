package com.yediat.makeatest.rmi.annotations;

import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;

import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.rmi.core.RMIController;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class RemoveMockRMIProcessor extends AnnotationProcessor {
	
	@SuppressWarnings("unused")
	private Method annotated;
	private String serverName;
	
	public RemoveMockRMIProcessor(Method annotated, String serverName) throws IOException, IllegalArgumentException, SecurityException {
		this.annotated = annotated;
		this.serverName = serverName;
	}
	
	@Override
	public void process(Object instance) {
		System.out.println("RemoveMockRMI process");
		try {			
			RMIController rmi = new RMIController();
			rmi.unbindServer(this.serverName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	

}
