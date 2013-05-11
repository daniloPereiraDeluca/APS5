package br.com.APS.chat.clienteTeste;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import br.com.APS.data.BaseDTO;
import br.com.APS.frames.FrameDeTrocaDeMensagens;
import br.com.APS.utils.BundleUtil;

public abstract class ClienteChatAdapter extends Thread {
	
	protected String message = StringUtils.EMPTY; 

	protected BundleUtil bundle;
	
	protected ObjectInputStream inputStream; 	// to read from the socket 

	protected ObjectOutputStream output; 	// to write on the socket 
	
	protected FrameDeTrocaDeMensagens frameDeTrocaDeMensagens;
	
	private BaseDTO baseUser;

	public ClienteChatAdapter(String bundle, String frameName, BaseDTO baseUser) {
		super();
		this.frameDeTrocaDeMensagens = new FrameDeTrocaDeMensagens(frameName, bundle);
		this.bundle = new BundleUtil(bundle);
		this.baseUser = baseUser;
	}
	
	protected void enviarMensagem(String message){
		try 
		{
			JOptionPane.showInputDialog("teste1");
			output.writeObject(this.baseUser.getNome() + ">>> " + message);
			output.flush(); 
			JOptionPane.showInputDialog("teste2");
			adicionaMensagemAoDisplay("\n" + this.baseUser.getNome() + ">>> " + message);
		} catch (IOException ioException) {
			adicionaMensagemAoDisplay(bundle.getMessage("erroAoEnviarMensagem"));
		} 
	}
	
	protected void adicionaMensagemAoDisplay(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {
					public void run() 
					{
						frameDeTrocaDeMensagens.getMensagensEnviadas().append(messageToDisplay);
					} 
				}
			);
	}
	
	protected void verificaConexao() throws IOException {
		frameDeTrocaDeMensagens.setTextFieldEditable(true);
		do 
		{
			try
			{
				JOptionPane.showMessageDialog(frameDeTrocaDeMensagens, "?");
				message = (String) inputStream.readObject(); 
				JOptionPane.showMessageDialog(frameDeTrocaDeMensagens, "verificaConexao");
				adicionaMensagemAoDisplay("\n" + message); 
			} catch (ClassNotFoundException classNotFoundException) {
				adicionaMensagemAoDisplay(bundle.getMessage("unknowProjectType"));
			}
		} while (!message.equals(this.baseUser.getNome() + ">>> TERMINATE"));
	}
	
	
}

