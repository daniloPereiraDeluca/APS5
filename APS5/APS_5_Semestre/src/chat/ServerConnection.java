package chat;

import java.io.Serializable;
import java.util.Random;

import javax.swing.JFrame;

public class ServerConnection implements Serializable{
	
	private static final long serialVersionUID = -8575133012712772879L;
	
	public static Usuario server;
	
	static {
		criarServidor();
	}
	public static void main(String[] args) {
		
		Server application = new Server(server);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.conectarBatePapo();
	}
	
	//TODO pegar valores de um server.xml
	private static void criarServidor() {
		server = new Usuario("Server", 5555, String.valueOf(new Random().nextInt(50000)));
	}
}
