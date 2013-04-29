package chat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public abstract class ChatAdapter extends JFrame {

	private static final long serialVersionUID = -2177977274268148656L;
	
	protected static final int PORT_CONNECTION = 12345;
	
	protected String message = ""; 
	
	protected JTextField enterField; 
	
	protected ObjectOutputStream output; 

	protected ObjectInputStream input; 
	
	protected JTextArea displayArea;
	
	protected Usuario user;
	
	protected Socket connection; 

	public ChatAdapter(String string, Usuario user) {
		super(string);
		this.user = user;
		enterField = new JTextField(); 
		this.displayArea = new JTextArea(); 
	}

	public abstract void conectarBatePapo();
	
	protected void sendData(String message){
		try 
		{
			output.writeObject(this.user.getNomeUsuario() + ">>> " + message);
			output.flush(); 
			enviarMensagem("\n" + this.user.getNomeUsuario() + ">>> " + message);
		} 
		catch (IOException ioException) {
			displayArea.append(getMessageFromBundle("erroAoEnviarMensagem"));
		} 
	}

	protected void enviarMensagem(final String messageToDisplay) {
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

	protected void closeConnection() {
		enviarMensagem(getMessageFromBundle("erroAoEnviarMensagem"));
		setTextFieldEditable(false);
		try {
			output.close(); 
			input.close(); 
			connection.close(); 
		} 
		catch (IOException ioException) {
			enviarMensagem(getMessageFromBundle("falhaConexao"));
		} 
	} 
	
	protected void verificaConexao() throws IOException {
		setTextFieldEditable(true);

		do // process messages sent from server
		{
			try
			{
				message = (String) input.readObject(); 
				enviarMensagem("\n" + message); 
			}
			catch (ClassNotFoundException classNotFoundException) {
				enviarMensagem(getBundle().getString("unknowProjectType"));
			}

		} while (!message.equals(this.user.getNomeUsuario() + ">>> TERMINATE"));
	}

	public String getMessageFromBundle(String key){
		return getBundle().getString(key);
	}
	
	public ResourceBundle getBundle(){
		return ResourceBundle.getBundle("messages");
	}
}
