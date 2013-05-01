package br.com.APS.impl.service;

import java.io.File;
import java.util.List;

import br.com.APS.data.ServerDTO;
import br.com.APS.data.ServerXMLReader;
import br.com.APS.data.XMLReader;
import br.com.APS.service.ServerService;

public class ServerServiceImpl implements ServerService{

	private static final String RESOURCES_SERVIDOR_XML = "resources/servidor.xml";
	
	private static final long serialVersionUID = -5245128233665495143L;

	@SuppressWarnings("unchecked")
	@Override
	public ServerDTO getServer() {
		File serverFile = new File(RESOURCES_SERVIDOR_XML);
		ServerDTO server = new ServerDTO();
		XMLReader reader = new ServerXMLReader(serverFile, new ServerDTO().getKeys());
		List<ServerDTO> dadosDoXML = (List<ServerDTO>) reader.getDadosDoXML();
		
		if (!dadosDoXML.isEmpty()){
			ServerDTO serverDados = dadosDoXML.get(0);
			server = new ServerDTO(serverDados.getNome(), serverDados.getId(), serverDados.getPortaConexao());
		}
		return server;
	}

	
}
