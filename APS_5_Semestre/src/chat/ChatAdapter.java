package chat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public abstract class ChatAdapter extends JFrame {

	private static final long serialVersionUID = -2177977274268148656L;
	
	protected static final int PORT_CONNECTION = 12345;
	
	protected JTextField enterField; 
	
	protected ObjectOutputStream output; 

	protected ObjectInputStream input; 
	
	protected JTextArea displayArea;
	
	private String userName;

	public ChatAdapter(String string, String userName) {
		super(string);
		this.userName = userName;
		enterField = new JTextField(); 
		this.displayArea = new JTextArea(); 
	}

	public abstract void conectarBatePapo();
	
	public abstract void processConnection() throws IOException;
	
	protected void sendData(String message){
		try 
		{
			output.writeObject(this.userName + ">>> " + message);
			output.flush(); 
			displayMessage("\n" + this.userName + ">>> " + message);
		} 
		catch (IOException ioException) {
			displayArea.append("\nError writing object");
		} 
	}
	//TODO fazer com que a cada mensagem enviada ele role para baixo
	protected void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
					public void run() 
					{
						displayArea.append(messageToDisplay);
					} 
				}
			);
	}
	
	protected void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
					public void run() 
					{
						enterField.setEditable(editable);
					} 
				}
			); 
	} 
}
