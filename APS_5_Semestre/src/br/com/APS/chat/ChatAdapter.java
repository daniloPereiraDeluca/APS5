package br.com.APS.chat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import br.com.APS.data.BaseDTO;
import br.com.APS.frames.FrameDeTrocaDeMensagens;
import br.com.APS.utils.BundleUtil;


public abstract class ChatAdapter {

	protected String message = StringUtils.EMPTY; 
	
	protected ObjectInputStream inputStream; 
	
	protected ObjectOutputStream output; 

	protected BaseDTO base;
	
	protected Socket connection; 
	
	protected BundleUtil bundle;

	protected FrameDeTrocaDeMensagens frame;
	
	public ChatAdapter(String frameName, BaseDTO user, String bundleName) {
		frame = new FrameDeTrocaDeMensagens(frameName,bundleName);
		this.bundle = new BundleUtil(bundleName);
		this.base = user;
	}		 

	public abstract void conectarBatePapo();
	
	public abstract void quandoPressionarEnterOuEnviar();
	
	protected void enviarMensagem(String message){
		try 
		{
			output.writeObject(this.base.getNome() + ">>> " + message);
			output.flush(); 
			adicionaMensagemAoDisplay("\n" + this.base.getNome() + ">>> " + message);
		} catch (IOException ioException) {
			frame.getMensagensEnviadas().append(bundle.getMessage("erroAoEnviarMensagem"));
		} 
	}

	protected void adicionaMensagemAoDisplay(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
					public void run() 
					{
						frame.getMensagensEnviadas().append(messageToDisplay);
					} 
				}
			);
	}
	
	protected void closeConnection() {
		adicionaMensagemAoDisplay(bundle.getMessage("erroAoEnviarMensagem"));
		frame.setTextFieldEditable(false);
		try {
			output.close(); 
			inputStream.close(); 
			connection.close(); 
		} catch (IOException ioException) {
			adicionaMensagemAoDisplay(bundle.getMessage("falhaConexao"));
		} 
	} 
	
	protected void verificaConexao() throws IOException {
		frame.setTextFieldEditable(true);
		do 
		{
			try
			{
				message = (String) inputStream.readObject(); 
				adicionaMensagemAoDisplay("\n" + message); 
			} catch (ClassNotFoundException classNotFoundException) {
				adicionaMensagemAoDisplay(bundle.getMessage("unknowProjectType"));
			}
		} while (!message.equals(this.base.getNome() + ">>> TERMINATE"));
	}

}
