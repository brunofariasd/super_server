package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Encript;
import rmi.Acesso;

public class Servidor implements Runnable{
	
	static ArrayList<Integer> arrlist = new ArrayList<Integer>();
	static ArrayList<String> arrlistHost = new ArrayList<String>();
	private static int errorCode;
	private static Acesso stub;
	static String Response;
	static String ResponseError;
	
	public Socket client;
	public static ArrayList<Socket> clientsConecteds = new ArrayList<Socket>();		
	public Servidor(Socket cliente) throws IOException{
		this.client = cliente;	
		Servidor.clientsConecteds.add(cliente);
	}
	
	public void run(){
		try {
			
			Scanner s = null;
			s = new Scanner(this.client.getInputStream());
			String rcv;
			
			while(s.hasNextLine()){
				rcv = s.nextLine();
				System.out.println("Texto encriptado"+ rcv);
				rcv = Encript.decriptarCifraCesar(3, rcv);
				System.out.println("Texto decriptado"+ rcv);
				
				
				rcv = s.nextLine();
				
				for (Integer index : arrlist) {		      
					try {
						Registry registro = LocateRegistry.getRegistry("localhost");
						
						stub = (Acesso) registro.lookup("Server" + index);
						
						errorCode = 204;
						
					} catch (Exception e) {
						errorCode = 400;
					}
					
					
					if (errorCode == 400) {
						ResponseError.concat("Server "+index+" Conection LOST;");
					} else {
						Response = stub.maisUm(19);
						break;
					}
			    }
				
				System.out.println(Response);
				PrintStream saida = new PrintStream(client.getOutputStream());
				saida.println(Response);
			}
			//Closed scanner e socket
			s.close();
			this.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	 public void enviarMensagem(String mensagem) throws IOException{
			PrintStream saida = new PrintStream(client.getOutputStream());
			saida.println(Encript.encriptarCifraCesar(3,mensagem));
	 }
 
	 public static void enviarMensagemBroadcast(String mensagem) throws IOException {
		for (int counter = 0; counter < clientsConecteds.size(); counter++) {
			try{
				PrintStream saida = new PrintStream(clientsConecteds.get(counter).getOutputStream());
				saida.println(Encript.encriptarCifraCesar(3,mensagem));
			} catch(Exception ex){
			    /*client.remove*/
				clientsConecteds.remove(counter);
				System.out.println(ex.getMessage());
			}
		}
	 }
 
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(";");
		return arrayString;
	}
 
}