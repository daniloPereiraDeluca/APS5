package chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class CarregaXML {

    public static void main(String[] args) {   
    	lerXML();
    }
    
    private static void lerXML() {
        try {
            XStream xStream = new XStream(new Dom4JDriver());
            xStream.alias("Usuario", ArrayList.class);
            xStream.processAnnotations(Usuario.class);
 
            BufferedReader input = new BufferedReader(new FileReader("user.xml"));
            ArrayList<Usuario> usuarios = (ArrayList) xStream.fromXML(input);
            input.close();
 
            for (Usuario usuario : usuarios) {
                System.out.println("usuario: " + usuario.getNomeUsuario() + " - " + usuario.getIdUsuario());
            }
 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

}  
