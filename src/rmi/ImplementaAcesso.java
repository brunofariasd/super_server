package rmi;

import java.rmi.RemoteException;

public class ImplementaAcesso implements Acesso {

	@Override
	public String maisUm(int id) throws RemoteException {
		int numero = Integer.parseInt(Arquivo.lerArquivo("src/dados/recurso"+id)) + 1;
		Arquivo.gravarArquivoTexto("src/dados/recurso"+id, numero);
		return "O valor do recurso agora é: " + numero;
	}

	@Override
	public String menosUm(int id) throws RemoteException {
		int numero = Integer.parseInt(Arquivo.lerArquivo("src/dados/recurso"+id)) -1;
		Arquivo.gravarArquivoTexto("src/dados/recurso"+id, numero);
		return "O valor do recurso agora é: " + numero;
	}
	
	public String updateRecurso(int id, int opcao) throws RemoteException {
		return (opcao == 1) ? maisUm(id) : menosUm(id);
	}
	
}
