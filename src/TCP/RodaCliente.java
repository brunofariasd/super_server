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
		
		int escolha = -1, number;
		while(escolha != 0) {
			
			System.out.print("Qual opção você deseja Realizar (0 P/ SAIR - 1 P/ SOMA - 2 P/ SUBTRAÇÃO): ");
			escolha = sc.nextInt();
		
			sc.nextLine();
			switch(escolha) {
				case 0: 
					c.encerrarConexao();
					System.exit(0);
					break;
				case 1:
					System.out.print("Digite um numero: ");
					number = sc.nextInt();
					c.enviarMensagem("SUM"+";"+number);
					break;
				case 2:
					System.out.print("Digite um numero: ");
					number = sc.nextInt();
					c.enviarMensagem("SUB"+";"+number);
					break;
				default: 
					System.out.print("Opção invalida: ");
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