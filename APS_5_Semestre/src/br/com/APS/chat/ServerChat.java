package br.com.APS.chat;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;

import br.com.APS.data.ServerDTO;


public class ServerChat extends ChatAdapter implements Serializable{
	
	private static final long serialVersionUID = 9208182619020493694L;

	private ServerSocket server; 
	
	private int counter = 1; 

	private ServerDTO severUser;
	
	public ServerChat(ServerDTO serverUser) {
		super("Server", serverUser, "messages");

		this.severUser = serverUser;
		frame.getCampoDeMensagem().setEditable(false);
		quandoPressionarEnterOuEnviar();

	}

	public void quandoPressionarEnterOuEnviar() {
		frame.getCampoDeMensagem().addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(KeyEvent evento) {
					if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
					enviarMensagem(frame.getCampoDeMensagem().getText());
					frame.getCampoDeMensagem().setText("");
				}
			}
		});
		
		frame.getBotaoEnviar().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				enviarMensagem(frame.getCampoDeMensagem().getText());
				frame.getCampoDeMensagem().setText("");
			}
		});
	} 

	@Override
	public void conectarBatePapo() {
		try 
		{
			server = new ServerSocket(this.severUser.getPortaConexao(), 100); 

			while (true) {
				try {
					esperandoConexaoDoCliente(); 
					inicializarOutputStream(); 
					verificaConexao(); 
				} catch (EOFException eofException) {
					adicionaMensagemAoDisplay(bundle.getMessage("conexaoTerminada"));
				} finally {
					closeConnection(); 
					++counter;
				} 
			} 
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	} 

	private void esperandoConexaoDoCliente() throws IOException {
		adicionaMensagemAoDisplay(bundle.getMessage("esperandoConexao"));
		connection = server.accept(); 
		adicionaMensagemAoDisplay(bundle.getMessage("totalConexao") + counter + ", conectadas a: "
				+ connection.getInetAddress().getHostName());
	}

	private void inicializarOutputStream() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		inputStream = new ObjectInputStream(connection.getInputStream());

		adicionaMensagemAoDisplay(bundle.getMessage("conexaoValidada"));
	} 
}

