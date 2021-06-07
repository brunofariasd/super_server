package TCP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RodaCliente {
	
	Scanner sc = new Scanner(System.in);
	 
	ClienteRunnable c;
	public RodaCliente() throws UnknownHostException, IOException {
		Thread escutarServidor =new Thread(new Runnable() {
			@Override
			public void run() {
				Socket socket;
				try {
					socket = new Socket("127.0.0.1", 12345);
					c = new ClienteRunnable(socket);
					Thread t = new Thread(c);
					t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		escutarServidor.start();
		
		int escolha = -1;
		while(escolha != 0) {
			
			System.out.print("Qual opcao voce deseja Realizar (0 P/ SAIR - 1 P/ SOMA - 2 P/ SUBTRACAO): ");
			escolha = sc.nextInt();
		
			sc.nextLine();
			switch(escolha) {
				case 0: 
					c.encerrarConexao();
					System.exit(0);
					break;
				case 1:
					c.enviarMensagem("SUM");
					break;
				case 2:
					c.enviarMensagem("SUB");
					break;
				default: 
					System.out.print("Opcao invalida: ");
					break;
			}
		}
	}
	
	public static String [] msgSeparada(String msg) {
		
		String [] arrayString = msg.split(";");
		return arrayString;
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException {
		new RodaCliente(); 
	}
		
}