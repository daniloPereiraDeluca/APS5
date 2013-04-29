package chat;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JScrollPane;


public class Server extends ChatAdapter{
	
	private static final long serialVersionUID = 9208182619020493694L;

	private ServerSocket server; // server socket
	
	private Socket connection; // connection to client
	
	private int counter = 1; // counter of number of connections

	public Server(String serverName) {
		super("Server", serverName);

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
					waitForConnection(); 
					getStreams(); 
					processConnection(); 
				} 
				catch (EOFException eofException) {
					displayMessage("\nServer terminated connection");
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

	private void waitForConnection() throws IOException {
		displayMessage("Waiting for connection\n");
		connection = server.accept(); 
		displayMessage("Connection " + counter + " received from: "
				+ connection.getInetAddress().getHostName());
	}

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 

		input = new ObjectInputStream(connection.getInputStream());

		displayMessage("\nGot I/O streams\n");
	} 

	public void processConnection() throws IOException {
		String message = "Connection successful";
		sendData(message); // send connection successful message

		setTextFieldEditable(true);

		do 
		{
			try 
			{
				message = (String) input.readObject(); 
				displayMessage("\n" + message); 
			} 
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}

		} while (!message.equals("CLIENT>>> TERMINATE"));
	} 

	private void closeConnection() {
		displayMessage("\nTerminating connection\n");
		setTextFieldEditable(false); // disable enterField

		try {
			output.close(); 
			input.close(); 
			connection.close(); 
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	} 

}

