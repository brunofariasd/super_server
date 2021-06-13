package rmi;

import java.rmi.RemoteException;

import controllers.Archiver;

public class ImplementaAcesso implements Acesso {
	
	static String value, serverLogs;
	static int newValue;
	
	@Override
	public String maisUm(int server) throws RemoteException {
		serverLogs = "src/data/logs_s"+server+".txt";
		value = Archiver.lerArchiver(serverLogs);
		newValue = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		++newValue;
		Archiver.gravarArchiverTexto(serverLogs, String.valueOf(newValue));
		return "O valor do recurso agora e: "+ newValue;
	}

	@Override
	public String menosUm(int server) throws RemoteException {
		serverLogs = "src/data/logs_s"+server+".txt";
		value = Archiver.lerArchiver(serverLogs);
		newValue = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		--newValue;
		Archiver.gravarArchiverTexto(serverLogs, String.valueOf(newValue));
		return "O valor do recurso agora e: "+ newValue;
	}
		
}
