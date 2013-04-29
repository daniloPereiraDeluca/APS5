package chat;

import javax.swing.JFrame;

public class ServerTest {
	
	public static void main(String[] args) {
		
		Server application = new Server("Server");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.conectarBatePapo();
	}
}
