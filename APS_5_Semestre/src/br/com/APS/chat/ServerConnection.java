package br.com.APS.chat;

import java.io.Serializable;

import javax.swing.JFrame;

import br.com.APS.data.ServerDTO;
import br.com.APS.impl.service.ServerServiceImpl;
import br.com.APS.service.ServerService;

public class ServerConnection implements Serializable{
	
	private static final long serialVersionUID = -8575133012712772879L;
	
	public static ServerDTO server;
	
	public static void main(String[] args) {
		
		server = getServerService().getServer();
		
		ServerChat application = new ServerChat(server);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.conectarBatePapo();
	}

	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}
	
}
