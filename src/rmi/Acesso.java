package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Acesso extends Remote{
	String maisUm() throws RemoteException;
	String menosUm() throws RemoteException;
}