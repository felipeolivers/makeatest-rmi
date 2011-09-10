package com.yediat.makeatest.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMICaseTest extends Remote {
	public boolean hello(String value) throws RemoteException;
}
