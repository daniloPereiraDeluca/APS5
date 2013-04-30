package chat;
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


public class Server extends ChatAdapter implements Serializable{
	
	private static final long serialVersionUID = 9208182619020493694L;

	private ServerSocket server; // server socket
	
	private int counter = 1; // counter of number of connections

	public Server(Usuario serverUser) {
		super("Server", serverUser, "messages");

		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sendData(event.getActionCommand());
					enterField.setText("");
				} 
			} 
		); 

		add(enterField, BorderLayout.NORTH);

		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		setSize(300, 150);
		setVisible(true); 
	} 

	@Override
	public void conectarBatePapo() {
		try 
		{
			server = new ServerSocket(PORT_CONNECTION, 100); 

			while (true) {
				try {
					esperandoConexaoDoCliente(); 
					inicializarOutputStream(); 
					verificaConexao(); 
				} 
				catch (EOFException eofException) {
					enviarMensagem("\nConexao terminada");
				} 
				finally {
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
		enviarMensagem("\nWaiting for connection\n");
		connection = server.accept(); 
		enviarMensagem(bundle.getMessage("totalConexao") + counter + ", conectadas a: "
				+ connection.getInetAddress().getHostName());
	}

	private void inicializarOutputStream() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		input = new ObjectInputStream(connection.getInputStream());

		enviarMensagem(bundle.getMessage("conexaoValidada"));
	} 
}

