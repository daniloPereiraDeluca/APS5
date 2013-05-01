package br.com.APS.chat;
import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import br.com.APS.data.UsuarioDTO;
import br.com.APS.data.UsuarioXMLReader;
import br.com.APS.data.XMLReader;

public class ClientConnection implements Serializable{

	private static final long serialVersionUID = -6796325031692825755L;

	public static List<UsuarioDTO> usuariosMock = new ArrayList<>();
	
	public static Integer 	QUANTIDADE_USUARIOS = 5;
	
	static {
		gerarMockUsuarios(QUANTIDADE_USUARIOS);
	}
	
	public static void main(String[] args) throws UnknownHostException {
			ClientChat application;
			UsuarioDTO user = usuariosMock.get(new Random().nextInt(5));
			
			application = new ClientChat(InetAddress.getLocalHost(), user);

			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.conectarBatePapo();
	}

	//TODO gerar Mock apartir de um arquivo xml, onde temos os atributos:nome,id,senha   
	private static void gerarMockUsuarios(Integer quantidadeCriacao) {
		
		File userFile = new File("resources/usuario.xml");
		
		XMLReader reader = new UsuarioXMLReader(userFile, new UsuarioDTO().getKeys());
		reader.getDados();
		for (int i = 0; i < quantidadeCriacao; i++) {
			UsuarioDTO user = new UsuarioDTO("User " + i, 10+i, String.valueOf(new Random().nextInt(10000) + i));
			usuariosMock.add(user);
		}
	}
}
