package br.com.APS.chat;
import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import br.com.APS.data.SimpleDTO;
import br.com.APS.data.UsuarioDTO;
import br.com.APS.data.UsuarioXMLReader;
import br.com.APS.data.XMLReader;

public class ClientConnection implements Serializable{

	private static final long serialVersionUID = -6796325031692825755L;
	
	//TODO passar para um service buscarUsuarios() que faz a consulta ao xml
	// nesse service poderemos ja fazer a parte de login
	public static List<UsuarioDTO> usuariosMock = new ArrayList<>();
	
	public static Integer QUANTIDADE_USUARIOS = 0;
	
	static {
		gerarMockUsuarios();
	}
	
	public static void main(String[] args) throws UnknownHostException {
			ClientChat application;
			UsuarioDTO user = usuariosMock.get(new Random().nextInt(QUANTIDADE_USUARIOS));
			
			application = new ClientChat(InetAddress.getLocalHost(), user);

			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.conectarBatePapo();
	}

	private static void gerarMockUsuarios() {
		
		File userFile = new File("resources/usuario.xml");
		
		XMLReader reader = new UsuarioXMLReader(userFile, new UsuarioDTO().getKeys());
		for (SimpleDTO userDTO : reader.getDados()) {
			
			String nome = userDTO.getNome();
			Integer id = userDTO.getId();
			String senha = userDTO.getSenha();
			UsuarioDTO user = new UsuarioDTO(nome, id, senha);
			usuariosMock.add(user);
		}
		QUANTIDADE_USUARIOS = usuariosMock.size();
	}
}
