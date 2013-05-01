package br.com.APS.data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class XMLReader implements Serializable{

	private static final long serialVersionUID = 4345402805647264453L;

	public File xmlFile;
	
	public List<String> objectFromFile;

	public XMLReader(File xmlFile, List<String> objectFromFile) {
		super();
		this.xmlFile = xmlFile;
		this.objectFromFile = objectFromFile;
	}
	
	public abstract List<? extends BaseDTO> pegarDadosDoXML(Document doc);
	
	//key, dado
	public List<? extends BaseDTO> getDados(){
		List<? extends BaseDTO> dados = new ArrayList();
		try {
			Document doc = criarDocumento();
			dados = pegarDadosDoXML(doc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dados;
	}

	private Document criarDocumento() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	public static Object getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
