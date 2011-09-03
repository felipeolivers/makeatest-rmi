package com.yediat.makeatest.rmi.annotations;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.rmi.Remote;

import org.jmock.Mockery;

import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;
import com.yediat.makeatest.rmi.core.RMIController;
import com.yediat.makeatest.rmi.tools.ConfigMemory;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class PutMockRMIProcessor extends AnnotationProcessor {
	
	private Method annotated;
	private String fixtureName;
	private String serverName;
	private int serverPort;
	private Object contextMockery = null;
	private Object contextMock = null;
	private Class<? extends Remote> contextMockType = null;
	private Class<? extends Mockery> contextMockeryType = null;
	
	public PutMockRMIProcessor(Method annotated, String fixtureName, String serverName, int serverPort) throws IOException, IllegalArgumentException, SecurityException {
		this.annotated = annotated;
		this.fixtureName = fixtureName;
		this.serverName = serverName;
		this.serverPort = serverPort;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void process(Object instance) throws MakeATestRMIAssertionError {
		System.out.println("PutMockRMI process");
		try {
			
			Mockery newContext = new Mockery();
			Object mock = null;
			
			// Get Mockery
			Field[] fields = this.annotated.getDeclaringClass().getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if(field.getType().equals(Mockery.class)) {
					this.contextMockeryType = (Class<? extends Mockery>) field.getType();
					try {
						field.set(instance, newContext);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					this.contextMockery = field;
					break;
				}
			}
			
			// Get Remote Interface to Mock
			fields = this.annotated.getDeclaringClass().getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if(field.getName().equals(this.fixtureName)) {
					this.contextMockType = (Class<? extends Remote>) field.getType();
					try {
						mock = newContext.mock(this.contextMockType); 
						field.set(instance, mock);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					this.contextMock = field;
					
					break;
				}
			}
			
			// Validations
			if(this.contextMockery == null || this.contextMockeryType == null)
				throw new MakeATestRMIAssertionError("Não foi encontrado nenhuma instância de Mockery no contexto.");
			
			if(this.contextMock == null || this.contextMockType == null)
				throw new MakeATestRMIAssertionError("Não foi encontrado nenhuma instância com o nome " + this.fixtureName + " neste contexto.");
			
			
			// Singleton
			ConfigMemory.getInstance().serverName = this.serverName;
			ConfigMemory.getInstance().serverPort = this.serverPort;
			
			RMIController rmi = new RMIController();
			rmi.bindServer(mock);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
}