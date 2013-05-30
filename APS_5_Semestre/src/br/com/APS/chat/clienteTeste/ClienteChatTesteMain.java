package br.com.APS.chat.clienteTeste;

import br.com.APS.impl.service.ServerServiceImpl;
import br.com.APS.impl.service.UsuarioServiceImpl;
import br.com.APS.service.ServerService;
import br.com.APS.service.UsuarioService;

public class ClienteChatTesteMain {
	
	public static void main(String args[]) throws InterruptedException {
		ClienteChatTeste cliente = new ClienteChatTeste(getUsuarioService().buscarQualquerUsuario(), getServerService().getServidor());
	}

	public static UsuarioService getUsuarioService() {
		return new UsuarioServiceImpl();
	}
	
	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}
}
