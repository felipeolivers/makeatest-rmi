package com.yediat.makeatest.rmi.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import junit.framework.Assert;

import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;
import com.yediat.makeatest.rmi.core.RMIController;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class AssertObjectNotOnServerProcessor extends AnnotationProcessor {
	
	private Method annotated;
	String fixtureName;
	String serverName;
	private Class<? extends Remote> contextMockType = null;
	
	public AssertObjectNotOnServerProcessor(Method annotated, String fixtureName, String serverName) {
		this.annotated = annotated;
		this.fixtureName = fixtureName;
		this.serverName = serverName;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void process(Object instance) throws MakeATestRMIAssertionError {				
		// Get Remote Interface to Mock
		Field[] fields = this.annotated.getDeclaringClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.getName().equals(this.fixtureName)) {
				this.contextMockType = (Class<? extends Remote>) field.getType();
				break;
			}
		}
		
		// Validations		
		if(this.contextMockType == null)
			throw new MakeATestRMIAssertionError("Não foi encontrado nenhuma instância com o nome " + this.fixtureName + " neste contexto.");
		
		
		RMIController rmi = new RMIController();
		try {
			Assert.assertTrue(!rmi.objectOnServer(this.contextMockType, this.serverName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
}