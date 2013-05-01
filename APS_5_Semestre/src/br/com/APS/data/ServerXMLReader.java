package br.com.APS.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> pegarDadosDoXML(Document doc) {
		Map<String, Object> mapValues = new HashMap<>();
		NodeList nodes = doc.getElementsByTagName("server");
		if (nodes != null) {
			Node item = nodes.item(0);
			if (item.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) item;
				mapValues.put("id", getValue("id", element));
				mapValues.put("nome", getValue("nome", element));
				mapValues.put("port", getValue("port", element));
			}
		}
		return mapValues;
	}

}
