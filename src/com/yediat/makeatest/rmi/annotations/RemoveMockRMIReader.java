package com.yediat.makeatest.rmi.annotations;

import java.io.IOException;
import java.lang.reflect.Method;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class RemoveMockRMIReader implements MakeATestReaderInterface<RemoveMockRMI> {

	@Override
	public void readAnnotation(RemoveMockRMI annotation, AnnotationProperties annotationProperties) {
		try {
			// Validations
			if(annotation.serverName() == null || annotation.serverName().equals(""))
				throw new MakeATestRMIAssertionError("@RemoveMockRMI annotation: Parâmetro nameServer vazio.");
			
			RemoveMockRMIProcessor objectProcessor = new RemoveMockRMIProcessor((Method) annotationProperties.getAnnotated(), annotation.serverName());
			annotationProperties.setProcessor(objectProcessor);	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}