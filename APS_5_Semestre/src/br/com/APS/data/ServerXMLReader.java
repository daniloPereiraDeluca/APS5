package br.com.APS.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ServerXMLReader extends XMLReader {

	private static final long serialVersionUID = -8914502238985801478L;

	public ServerXMLReader(File xmlFile, List<String> objectFromFile) {
		super(xmlFile, objectFromFile);
	}

	@Override
	public List<? extends BaseDTO> pegarDadosDoXML(Document doc){
		List<ServerDTO> mapValues = new ArrayList<ServerDTO>();
		NodeList nodes = doc.getElementsByTagName("server");
		if (nodes != null) {
			Node item = nodes.item(0);//sempre pega somente um server
			if (item.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) item;
				ServerDTO server = new ServerDTO((String)getValue("nome", element),
						new Integer((String) getValue("id", element)),
						new Integer((String) getValue("port", element)));
				mapValues.add(server);
			}
		}
		return mapValues;
	}
}
