package chat;
// Fig. 27.7: Client.java
// Client portion of a stream-socket connection between client and server.
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JScrollPane;


public class Client extends ChatAdapter {
	
	private static final long serialVersionUID = 9159486593659897373L;
	
	private String chatServer; // host server for this application

	public Client(String host, Usuario user) {
		super("ClienteSide", user);
		
		chatServer = host; 

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

	public void conectarBatePapo() {
		try 
		{
			connectToServer(); 
			getStreams(); 
			verificaConexao(); 
		} 
		catch (EOFException eofException) {
			enviarMensagem("\nClient terminated connection");
		}
		catch (IOException ioException) {
			enviarMensagem(FALHA_CONEXAO);
		} finally {
			closeConnection(); 
		} 
	} 

	private void connectToServer() throws IOException {
		enviarMensagem("Attempting connection\n");
		connection = new Socket(InetAddress.getByName(chatServer), PORT_CONNECTION);
		enviarMensagem("Connected to: " + connection.getInetAddress().getHostName());
	} 

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		input = new ObjectInputStream(connection.getInputStream());

		enviarMensagem("\nGot I/O streams\n");
	}

} 
