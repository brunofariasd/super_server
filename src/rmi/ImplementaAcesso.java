package rmi;

import java.rmi.RemoteException;

import controllers.Archiver;

public class ImplementaAcesso implements Acesso {

	@Override
	public String maisUm(int number) throws RemoteException {
		int newValue = ++number;
		Archiver.gravarArchiverTexto("src/data/logs", new String ("Numero informado: ["+number+"], now: ["+newValue+"]"));
		return "O valor do recurso agora e " + newValue;
	}

	@Override
	public String menosUm(int number) throws RemoteException {
		int newValue = --number;
		Archiver.gravarArchiverTexto("src/data/logs", new String ("Numero informado: ["+number+"], now: ["+newValue+"]"));
		return "O valor do recurso agora e: " + newValue;
	}
		
}
