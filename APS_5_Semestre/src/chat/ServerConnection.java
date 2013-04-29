package chat;

import java.util.Random;

import javax.swing.JFrame;

public class ServerConnection {
	
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
