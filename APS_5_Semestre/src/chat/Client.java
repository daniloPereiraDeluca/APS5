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

	
	private String message = ""; // message from server

	private String chatServer; // host server for this application

	private Socket client; // socket to communicate with server

	public Client(String host, String userName) {
		super("Client", userName);
		
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
			processConnection(); 
		} 
		catch (EOFException eofException) {
			displayMessage("\nClient terminated connection");
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			closeConnection(); 
		} 
	} 

	private void connectToServer() throws IOException {
		displayMessage("Attempting connection\n");

		client = new Socket(InetAddress.getByName(chatServer), PORT_CONNECTION);

		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	} 

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush(); 

		input = new ObjectInputStream(client.getInputStream());

		displayMessage("\nGot I/O streams\n");
	}

	public void processConnection() throws IOException {
		setTextFieldEditable(true);

		do // process messages sent from server
		{
			try
			{
				message = (String) input.readObject(); 
				displayMessage("\n" + message); 
			}
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}

		} while (!message.equals("SERVER>>> TERMINATE"));
	}

	private void closeConnection() {
		displayMessage("\nClosing connection");
		setTextFieldEditable(false);

		try {
			output.close(); 
			input.close(); 
			client.close(); 
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	} 

} 
