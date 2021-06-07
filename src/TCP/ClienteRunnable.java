package TCP;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import controllers.Encript;

public class ClienteRunnable implements Runnable{

	private Socket cliente;
	public String scheduleClient;
	public ClienteRunnable(Socket c){
		this.cliente = c;
	}

	public void run() {
		
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner s = null;
				try {	
					
					s = new Scanner(cliente.getInputStream());
					String mensagem;
					String [] msg;
					
					while(s.hasNextLine()){
						mensagem = s.nextLine();
	
						mensagem = Encript.decriptarCifraCesar(3, mensagem);
						
						System.out.println("\n\n##########################  NOVA MENSAGEM RECEBIDA!  ##########################\n");
						System.out.println(mensagem);
						System.out.println("\n###############################################################################\n");		
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				s.close();
			}
		});
		
		t.start();	
	}

	public void enviarMensagem(String mensagem) throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		saida.println(Encript.encriptarCifraCesar(3,mensagem));
	}
	
	public void encerrarConexao() throws IOException {
		PrintStream saida = new PrintStream(cliente.getOutputStream());	
		saida.println("fim");
		saida.close();
		cliente.close();
		
		System.out.println("Cliente finaliza conexao.");
	}

	 public static String [] msgSeparada(String msg) {
			
		String [] arrayString = msg.split(";");
		
		return arrayString;
	}
}
