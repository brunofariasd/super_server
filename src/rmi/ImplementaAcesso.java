package rmi;

import java.rmi.RemoteException;

import controllers.Archiver;

public class ImplementaAcesso implements Acesso {

	@Override
	public String maisUm(int id) throws RemoteException {
		Archiver.gravarArchiverTexto("src/data/logs", new String ("["+id+"]"));
		return "O valor do recurso agora e " + id;
	}

	@Override
	public String menosUm(int id) throws RemoteException {
		Archiver.gravarArchiverTexto("src/data/logs", new String ("["+id+"]"));
		return "O valor do recurso agora e: " + id;
	}
		
}
