package br.com.APS.chat.teste;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.APS.data.ServerDTO;
import br.com.APS.frames.FrameConectarServidor;
import br.com.APS.utils.BundleUtil;

public abstract class ServerAdapter  extends Thread {

	protected ObjectInputStream inputStream; 
	
	protected ObjectOutputStream output; 
	
	protected Socket connection; 
	
	protected BundleUtil bundle;
	
	protected FrameConectarServidor frame;
	
	protected ServerDTO server;
	
	public ServerAdapter() {
		
	}
	public ServerAdapter(String frameName, ServerDTO server, String bundleName) {
		frame = new FrameConectarServidor(frameName,bundleName);
		this.bundle = new BundleUtil(bundleName);
		this.server = server;
	}
	
	public abstract void conectarServidor();
	
	public abstract void desconectarServidor();
		
	public abstract void quandoPressionarBotaoConectar();

	public abstract void quandoPressionarBotaoDesconectar();
}
