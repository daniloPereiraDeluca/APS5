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
import br.com.APS.utils.BundleUtil;


public abstract class ChatAdapter extends JFrame {

	private static final long serialVersionUID = -2177977274268148656L;
	
	protected String message = StringUtils.EMPTY; 
	
	protected ObjectInputStream inputStream; 
	
	protected ObjectOutputStream output; 

	protected JTextField enterField; 
	
	protected JTextArea displayArea;
	
	protected BaseDTO base;
	
	protected Socket connection; 
	
	protected BundleUtil bundle;

	public ChatAdapter(String frameName, BaseDTO user, String bundleName) {
		super(frameName);
		this.base = user;
		enterField = new JTextField(); 
		enterField.setSize(400, 200);
		this.displayArea = new JTextArea(); 
		this.bundle = new BundleUtil(bundleName);
	}

	public abstract void conectarBatePapo();
	
	protected void sendData(String message){
		try 
		{
			output.writeObject(this.base.getNome() + ">>> " + message);
			output.flush(); 
			enviarMensagem("\n" + this.base.getNome() + ">>> " + message);
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
			inputStream.close(); 
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
				message = (String) inputStream.readObject(); 
				enviarMensagem("\n" + message); 
			} catch (ClassNotFoundException classNotFoundException) {
				enviarMensagem(bundle.getMessage("unknowProjectType"));
			}
		} while (!message.equals(this.base.getNome() + ">>> TERMINATE"));
	}

}
