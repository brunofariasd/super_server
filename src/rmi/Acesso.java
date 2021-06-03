package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Acesso extends Remote{
	String maisUm(int id) throws RemoteException;
	String menosUm(int id) throws RemoteException;
}