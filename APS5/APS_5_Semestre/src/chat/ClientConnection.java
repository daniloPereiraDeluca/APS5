package chat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class ClientConnection implements Serializable{

	private static final long serialVersionUID = -6796325031692825755L;

	public static List<Usuario> usuariosMock = new ArrayList<>();
	
	public static Integer 	QUANTIDADE_USUARIOS = 5;
	
	static {
		gerarMockUsuarios(QUANTIDADE_USUARIOS);
	}
	
	public static void main(String[] args) {
//		for (Usuario user : usuariosMock) {
			Client application;
			Usuario user = usuariosMock.get(new Random().nextInt(5));
			if (args.length == 0) {
				application = new Client("127.0.0.1", user);
			} else
				application = new Client(args[0], user);

			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.conectarBatePapo();
//		}
	}

	//TODO gerar Mock apartir de um arquivo xml, onde temos os atributos:nome,id,senha   
	private static void gerarMockUsuarios(Integer quantidadeCriacao) {
		for (int i = 0; i < quantidadeCriacao; i++) {
			Usuario user = new Usuario("User " + i, 10+i, String.valueOf(new Random().nextInt(10000) + i));
			usuariosMock.add(user);
		}
	}
}
