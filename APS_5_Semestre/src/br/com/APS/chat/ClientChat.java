package br.com.APS.chat;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JScrollPane;

import br.com.APS.chat.to.MensagemTO;
import br.com.APS.data.ServerDTO;
import br.com.APS.data.UsuarioDTO;
import br.com.APS.impl.service.ServerServiceImpl;
import br.com.APS.service.ServerService;


public class ClientChat extends ChatAdapter implements Serializable{
	
	private static final long serialVersionUID = 9159486593659897373L;
	
	private InetAddress inetAddress;
	
	private ServerDTO serverConexao;
	
	private UsuarioDTO user;

	public ClientChat(InetAddress inetAddress, UsuarioDTO user) throws UnknownHostException {
		super("ClienteSide", user,"messages");
		this.serverConexao = getServerService().getServer();
		this.user = user;
		this.inetAddress = inetAddress;
		enterField.setEditable(false);
		quandoPressionarEnter();
		
		add(enterField, BorderLayout.NORTH);

		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		setSize(300, 150);
		setVisible(true); 
	}

	private void quandoPressionarEnter() {
		enterField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String mensagem = event.getActionCommand();
					sendData(mensagem);
					enterField.setText("");
					user.getMensagens().add(new MensagemTO(mensagem, new Date()));
				}
			} 
		);
	} 

	public void conectarBatePapo() {
		try 
		{
			connectToServer(); 
			getStreams(); 
			verificaConexao(); 
		} 
		catch (EOFException eofException) {
			enviarMensagem(bundle.getMessage("sessaoTerminada"));
		}
		catch (IOException ioException) {
			enviarMensagem(bundle.getMessage("falhaConexao"));
		} finally {
			closeConnection(); 
		} 
	} 

	private void connectToServer() throws IOException {
		enviarMensagem(bundle.getMessage("estabelecendoConexao"));
		connection = new Socket(this.inetAddress, this.serverConexao.getPortaConexao());
		enviarMensagem(bundle.getMessage("conectadoA") + connection.getInetAddress().getHostName());
	} 

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		input = new ObjectInputStream(connection.getInputStream());

		enviarMensagem(bundle.getMessage("conexaoValidada"));
	}

	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}
} 
