package rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Servidor {
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in);
		
		int port = in.nextInt();
		int id = in.nextInt();
		
		try {
			//criar objeto servidor
			ImplementaAcesso implAcesso = new ImplementaAcesso();
			
			//criar objeto stub do servidor
			Acesso stub = (Acesso) UnicastRemoteObject.exportObject(implAcesso, port);
			
			// adiciona rmi registry na porta padrão (Registry.REGISTRY_PORT)
			LocateRegistry.createRegistry(1099);
			
			//Registry registro = LocateRegistry.getRegistry(IP);
			Registry registro = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostAddress());

			registro.bind("Server"+id, stub);

			System.out.println(" >> Servidor pronto:");

		} catch (IOException e) {
			System.err.println("Erro no servidor ; " + e.toString());
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
		in.close();
	}
}
