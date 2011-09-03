package com.yediat.makeatest.rmi.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
	private Object contextMock = null;
	
	public AssertObjectNotOnServerProcessor(Method annotated, String fixtureName, String serverName) {
		this.annotated = annotated;
		this.fixtureName = fixtureName;
		this.serverName = serverName;
	}
	
	@Override
	public void process(Object instance) throws MakeATestRMIAssertionError {				
		// Get Remote Interface to Mock in Context
		Field[] fields = this.annotated.getDeclaringClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(field.getName().equals(this.fixtureName)) {
				this.contextMock = field;
				break;
			}
		}
		
		// Validations		
		if(this.contextMock == null)
			throw new MakeATestRMIAssertionError("Não foi encontrado nenhuma instância com o nome " + this.fixtureName + " neste contexto.");
		
		
		RMIController rmi = new RMIController();
		rmi.objectOnServer(this.contextMock);
	}
	
}