package br.com.APS.chat;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import br.com.APS.data.UsuarioDTO;
import br.com.APS.impl.service.UsuarioServiceImpl;
import br.com.APS.service.UsuarioService;

public class ClientConnection implements Serializable{

	private static final long serialVersionUID = -6796325031692825755L;
	
	public static UsuarioDTO usuario;
	
	public static void main(String[] args) throws UnknownHostException {
			
			usuario = getUsuarioService().buscarQualquerUsuario();
			
			ClientChat application;
			application = new ClientChat(InetAddress.getLocalHost(), usuario);

			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.conectarBatePapo();
	}

	public static UsuarioService getUsuarioService() {
		return new UsuarioServiceImpl();
	}

}
