package rmi;

import java.rmi.RemoteException;

import controllers.Archiver;

public class ImplementaAcesso implements Acesso {
	
	static String value;
	static int newValue;

	@Override
	public String maisUm() throws RemoteException {
		value = Archiver.lerArchiver("src/data/logs.txt");
		newValue = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		++newValue;
		Archiver.gravarArchiverTexto("src/data/logs.txt", String.valueOf(newValue));
		return "O valor do recurso agora e: "+ newValue;
	}

	@Override
	public String menosUm() throws RemoteException {
		value = Archiver.lerArchiver("src/data/logs.txt");
		newValue = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		--newValue;
		Archiver.gravarArchiverTexto("src/data/logs.txt", String.valueOf(newValue));
		return "O valor do recurso agora e: "+ value;
	}
		
}
