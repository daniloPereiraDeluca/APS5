package br.com.APS.chat;

import java.io.File;
import java.io.Serializable;

import javax.swing.JFrame;

import br.com.APS.data.ServerDTO;
import br.com.APS.data.ServerXMLReader;
import br.com.APS.data.SimpleDTO;
import br.com.APS.data.XMLReader;

public class ServerConnection implements Serializable{
	
	private static final long serialVersionUID = -8575133012712772879L;
	
	public static ServerDTO server;
	
	static {
		criarServidor();
	}
	public static void main(String[] args) {
		
		ServerChat application = new ServerChat(server);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.conectarBatePapo();
	}
	
	private static void criarServidor() {
		File serverFile = new File("resources/servidor.xml");
		server = new ServerDTO();
		XMLReader reader = new ServerXMLReader(serverFile, server.getKeys());
		if (!reader.getDados().isEmpty()){
			SimpleDTO serverDados = reader.getDados().get(0);
			server = new ServerDTO(serverDados.getNome(), serverDados.getId(), serverDados.getPortaConexao());
		}
	}
}
