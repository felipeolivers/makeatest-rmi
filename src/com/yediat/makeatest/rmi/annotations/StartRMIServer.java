package com.yediat.makeatest.rmi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Annotation MakeATest with RMI JUnit Test
 * @author Luiz Filipe Miranda de Oliveira
 *
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=StartRMIServerReader.class, proxyBehavior={MakeATestProxyBehavior.BEFORE}, scope=MakeATestScope.LOAD)
public @interface StartRMIServer {
	String codebasePath();
	String securityPolicyPath();
} 
