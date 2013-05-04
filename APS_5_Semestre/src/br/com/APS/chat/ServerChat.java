package br.com.APS.chat;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;

import javax.swing.JScrollPane;

import br.com.APS.data.ServerDTO;


public class ServerChat extends ChatAdapter implements Serializable{
	
	private static final long serialVersionUID = 9208182619020493694L;

	private ServerSocket server; // server socket
	
	private int counter = 1; // counter of number of connections

	private ServerDTO severUser;
	
	public ServerChat(ServerDTO serverUser) {
		super("Server", serverUser, "messages");

		this.severUser = serverUser;
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sendData(event.getActionCommand());
					enterField.setText("");
				} 
			} 
		); 


		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		add(enterField, BorderLayout.SOUTH);
		
		setSize(600, 450);
		setVisible(true); 
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
					enviarMensagem(bundle.getMessage("conexaoTerminada"));
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
		enviarMensagem(bundle.getMessage("esperandoConexao"));
		connection = server.accept(); 
		enviarMensagem(bundle.getMessage("totalConexao") + counter + ", conectadas a: "
				+ connection.getInetAddress().getHostName());
	}

	private void inicializarOutputStream() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		inputStream = new ObjectInputStream(connection.getInputStream());

		enviarMensagem(bundle.getMessage("conexaoValidada"));
	} 
}

