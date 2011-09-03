package com.yediat.makeatest.rmi.annotations;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

public class StopRMIServerReader implements MakeATestReaderInterface<StopRMIServer> {

	@Override
	public void readAnnotation(StopRMIServer annotation, AnnotationProperties annotationProperties) {
		annotationProperties.setProcessor(new StopRMIServerProcessor());
	}

}
