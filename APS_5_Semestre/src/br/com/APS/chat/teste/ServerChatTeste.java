package br.com.APS.chat.teste;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import br.com.APS.data.ServerDTO;
import br.com.APS.impl.service.ServerServiceImpl;
import br.com.APS.service.ServerService;


public class ServerChatTeste extends ServerAdapter implements Serializable{
	
	private static final long serialVersionUID = 9208182619020493694L;

	private ServerSocket server; 
	
	private ServerDTO serverUser;
	
	private Socket conexao;
	
	private static Set<ObjectOutputStream> lista = new HashSet<ObjectOutputStream>();
	
	public ServerChatTeste(ServerDTO serverUser) throws InterruptedException {
		super("Server", serverUser, "messages");
		frame.setVisible(true);
		frame.setSize(350, 120);
		this.serverUser = serverUser;
		actions();
	}
	public ServerChatTeste(Socket conexao, ServerDTO serverUser) throws InterruptedException {
		super();
		this.conexao = conexao;
		try {
			output =  new ObjectOutputStream(conexao.getOutputStream());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lista.add(output);
		JOptionPane.showInputDialog(lista);
	}

	private void actions() {
		quandoPressionarBotaoConectar();
		quandoPressionarBotaoDesconectar();
	}

	public void run() {//quando dou theard.start() ele executa isso
        try {
        	BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
        	Object readObject = new ObjectInputStream(conexao.getInputStream()).readObject();
//			output.writeObject(readObject);
//			output.flush();
        	
			for (ObjectOutputStream out : lista) {
        		out.writeObject(readObject);
        		out.flush();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
			
    }
	
	@Override
	public void conectarServidor() {
		frame.getBotaoConectar().setEnabled(false);
		frame.getBotaoDesconectar().setEnabled(false);
		JOptionPane.showMessageDialog(frame, bundle.getMessage("aguardandoConexaoAoServidor"));
		try {
			server = new ServerSocket(serverUser.getPortaConexao());
			while(true){
				conexao = server.accept();
				frame.getBotaoConectar().setEnabled(false);
				frame.getBotaoDesconectar().setEnabled(true);
				Thread t = new ServerChatTeste(conexao, serverUser);
				t.start();
				JOptionPane.showMessageDialog(frame, bundle.getMessage("conexaoEstabelecida"));
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void quandoPressionarBotaoConectar() {
		frame.getBotaoConectar().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				conectarServidor();
			}
		});
	}
	
	@Override
	public void quandoPressionarBotaoDesconectar() {
		frame.getBotaoDesconectar().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				desconectarServidor();
			}
		});
	}

	@Override
	public void desconectarServidor() {
		frame.getBotaoConectar().setEnabled(true);
		frame.getBotaoDesconectar().setEnabled(false);
		this.stop();
		try {
			server.close();
			conexao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ServerService getServerService() {
		return new ServerServiceImpl();
	}



}

