package br.com.APS.chat;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

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
		frame.getCampoDeMensagem().setEditable(false);
		quandoPressionarEnterOuEnviar();
		
	}

	public void quandoPressionarEnterOuEnviar() {
		
		frame.getCampoDeMensagem().addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(KeyEvent evento) {
				if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
					String mensagem = frame.getCampoDeMensagem().getText();
					enviarMensagem(mensagem);
					frame.getCampoDeMensagem().setText("");
					user.getMensagens().add(new MensagemTO(mensagem, new Date()));
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
	public void conectarBatePapo() {
		try 
		{
			connectToServer(); 
			getStreams(); 
			verificaConexao(); 
		} 
		catch (EOFException eofException) {
			adicionaMensagemAoDisplay(bundle.getMessage("sessaoTerminada"));
		}
		catch (IOException ioException) {
			adicionaMensagemAoDisplay(bundle.getMessage("falhaConexao"));
		} finally {
			closeConnection(); 
		} 
	} 

	private void connectToServer() throws IOException {
		adicionaMensagemAoDisplay(bundle.getMessage("estabelecendoConexao"));
		connection = new Socket(this.inetAddress, this.serverConexao.getPortaConexao());
		adicionaMensagemAoDisplay(bundle.getMessage("conectadoA") + connection.getInetAddress().getHostName());
	} 

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		inputStream = new ObjectInputStream(connection.getInputStream());

		adicionaMensagemAoDisplay(bundle.getMessage("conexaoValidada"));
	}

	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}
} 
