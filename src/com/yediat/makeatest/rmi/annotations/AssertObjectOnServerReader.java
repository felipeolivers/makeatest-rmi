package com.yediat.makeatest.rmi.annotations;

import java.lang.reflect.Method;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class AssertObjectOnServerReader implements MakeATestReaderInterface<AssertObjectOnServer> {

	@Override
	public void readAnnotation(AssertObjectOnServer annotation, AnnotationProperties annotationProperties) {
		try {
			// Validations
			if(annotation.fixtureName() == null || annotation.fixtureName().equals(""))
				throw new MakeATestRMIAssertionError("@AssertObjectOnServer annotation: Parâmetro fixtureName vazio.");
			
			if(annotation.serverName() == null || annotation.serverName().equals(""))
				throw new MakeATestRMIAssertionError("@AssertObjectOnServer annotation: Parâmetro nameServer vazio.");
			
			AssertObjectOnServerProcessor objectProcessor = new AssertObjectOnServerProcessor((Method) annotationProperties.getAnnotated(), annotation.fixtureName(), annotation.serverName());
			annotationProperties.setProcessor(objectProcessor);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}