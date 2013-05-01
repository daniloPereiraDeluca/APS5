package chat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import java.io.*;
 
public class CarregaXML {
	
	static String file = "C:\\projectAPS\\APS5\\APS_5_Semestre\\resources\\Usuario.xml";
	
	
	public static void main(String[] args) {
		lerXML();
	}
	
	private static void lerXML() {
        try {
        	
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("usuarios", Usuario.class);
            //xStream.alias("usuario", Usuario.class);
            xStream.processAnnotations(Usuario.class);
 
            BufferedReader input = new BufferedReader(new FileReader(file));
            Usuario usuarios;
            //transformar isso num list
            usuarios = (Usuario) xStream.fromXML(input);
            input.close();
 
            System.out.println("NOME: " + usuarios.getNomeUsuario() + " - ID: " + usuarios.getIdUsuario() + " - Senha: " + usuarios.getSenha());
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
