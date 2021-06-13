package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Acesso extends Remote{
	String maisUm(int server) throws RemoteException;
	String menosUm(int server) throws RemoteException;
}