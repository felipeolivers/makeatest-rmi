package com.yediat.makeatest.rmi.annotations;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.rmi.core.MakeATestRMIAssertionError;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class StartRMIServerReader implements MakeATestReaderInterface<StartRMIServer> {

	@Override
	public void readAnnotation(StartRMIServer annotation, AnnotationProperties annotationProperties) {
		// Validations
		if(annotation.codebasePath() == null || annotation.codebasePath().equals(""))
			throw new MakeATestRMIAssertionError("@StartRMIServer annotation: Parâmetro codebasePath vazio.");
		
		if(annotation.securityPolicyPath() == null || annotation.securityPolicyPath().equals(""))
			throw new MakeATestRMIAssertionError("@StartRMIServer annotation: Parâmetro securityPolicyPath vazio.");
		
		annotationProperties.setProcessor(new StartRMIServerProcessor(annotation.codebasePath(), annotation.securityPolicyPath()));
	}

}
