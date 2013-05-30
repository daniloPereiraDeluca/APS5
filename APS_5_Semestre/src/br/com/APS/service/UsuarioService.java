package br.com.APS.service;

import java.io.Serializable;
import java.util.List;

import br.com.APS.data.UsuarioDTO;

public interface UsuarioService extends Serializable{

	public List<UsuarioDTO> buscarUsuarios();
	
	public UsuarioDTO buscarQualquerUsuario();
}
