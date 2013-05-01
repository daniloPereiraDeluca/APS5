package br.com.APS.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UsuarioXMLReader extends XMLReader{

	private static final long serialVersionUID = 6606428033704431251L;

	public UsuarioXMLReader(File xmlFile, List<String> objectFromFile) {
		super(xmlFile, objectFromFile);
	}

	
	@Override
	public List<? extends BaseDTO> gerarDadosDoXML(Document doc){
		List<UsuarioDTO> mapValues = new ArrayList<UsuarioDTO>();
		NodeList nodes = doc.getElementsByTagName("user");
		
		for (int i = 0; i < nodes.getLength(); i++) {//todos usuarios 
			Node item = nodes.item(i);
			if (item.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) item;
				String nomeUsuario = (String) getValue("nome", element);
				Integer idUsuario = new Integer((String) getValue("id", element));
				String senha = (String) getValue("senha", element);
				UsuarioDTO user = new UsuarioDTO(nomeUsuario, idUsuario, senha);
				mapValues.add(user);
			}
		}
		return mapValues;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> getDadosDoXML() {
		List<UsuarioDTO> dados = new ArrayList<UsuarioDTO>();
		try {
			Document doc = criarDocumento();
			dados = (List<UsuarioDTO>) gerarDadosDoXML(doc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dados;
	}

}
