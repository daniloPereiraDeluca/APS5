package br.com.APS.impl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.APS.data.UsuarioDTO;
import br.com.APS.data.UsuarioXMLReader;
import br.com.APS.data.XMLReader;
import br.com.APS.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	private static final String RESOURCES_USUARIO_XML = "resources/usuario.xml";
	
	private static final long serialVersionUID = -9106101069213178332L;

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> buscarUsuarios() {
		File userFile = new File(RESOURCES_USUARIO_XML);
		
		XMLReader reader = new UsuarioXMLReader(userFile, new UsuarioDTO().getKeys());
		
		List<UsuarioDTO> dados = (List<UsuarioDTO>) reader.getDadosDoXML();
		
		List<UsuarioDTO> usuarios = new ArrayList<>();
		for (UsuarioDTO userDTO : dados) {
			String nome = userDTO.getNome();
			Integer id = userDTO.getId();
			String senha = userDTO.getSenha();
			UsuarioDTO user = new UsuarioDTO(nome, id, senha);
			usuarios.add(user);
		}
		return usuarios;
	}

	@Override
	public UsuarioDTO buscarQualquerUsuario() {
		List<UsuarioDTO> usuarios = buscarUsuarios();
		return usuarios.get(new Random().nextInt(usuarios.size()));
	}

}
