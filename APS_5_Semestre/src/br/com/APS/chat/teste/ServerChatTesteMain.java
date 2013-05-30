package br.com.APS.chat.teste;

import br.com.APS.impl.service.ServerServiceImpl;
import br.com.APS.service.ServerService;

public class ServerChatTesteMain {
	
	public static void main(String args[]) throws InterruptedException {
		ServerChatTeste tete = new ServerChatTeste(getServerService().getServidor());
	}
	
	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}

}
