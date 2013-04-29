package chat;
// Fig. 27.8: ClientTest.java
// Class that tests the Client.
import javax.swing.JFrame;

public class ClientTest {
	
	public static void main(String[] args) {
		Client application;

		// if no command line args
		//TODO fazer com que o usuario ponha um nome para login pela tela, 
		//pegar esse nome e passar como parametro, talvez pegar de um xml
		if (args.length == 0)
			application = new Client("127.0.0.1", "Joao");
		else
			application = new Client(args[0], "Joao");

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.conectarBatePapo();
	}
}
