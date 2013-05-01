package br.com.APS.chat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import br.com.APS.data.BaseDTO;


public abstract class ChatAdapter extends JFrame {

	private static final long serialVersionUID = -2177977274268148656L;
	
	protected String message = StringUtils.EMPTY; 
	
	protected JTextField enterField; 
	
	protected ObjectOutputStream output; 

	protected ObjectInputStream input; 
	
	protected JTextArea displayArea;
	
	protected BaseDTO user;
	
	protected Socket connection; 
	
	protected BundleUtil bundle;

	public ChatAdapter(String frameName, BaseDTO user, String bundleName) {
		super(frameName);
		this.user = user;
		enterField = new JTextField(); 
		this.displayArea = new JTextArea(); 
		this.bundle = new BundleUtil(bundleName);
	}

	public abstract void conectarBatePapo();
	
	protected void sendData(String message){
		try 
		{
			output.writeObject(this.user.getNome() + ">>> " + message);
			output.flush(); 
			enviarMensagem("\n" + this.user.getNome() + ">>> " + message);
		} catch (IOException ioException) {
			displayArea.append(bundle.getMessage("erroAoEnviarMensagem"));
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
		enviarMensagem(bundle.getMessage("erroAoEnviarMensagem"));
		setTextFieldEditable(false);
		try {
			output.close(); 
			input.close(); 
			connection.close(); 
		} catch (IOException ioException) {
			enviarMensagem(bundle.getMessage("falhaConexao"));
		} 
	} 
	
	protected void verificaConexao() throws IOException {
		setTextFieldEditable(true);
		do 
		{
			try
			{
				message = (String) input.readObject(); 
				enviarMensagem("\n" + message); 
			} catch (ClassNotFoundException classNotFoundException) {
				enviarMensagem(bundle.getMessage("unknowProjectType"));
			}
		} while (!message.equals(this.user.getNome() + ">>> TERMINATE"));
	}

}
