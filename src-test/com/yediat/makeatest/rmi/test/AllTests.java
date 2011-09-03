package com.yediat.makeatest.rmi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 	ConfigMemorySingletonVerify.class,
						SecurityPolicyFileCreateRemove.class
})
public class AllTests { }