package br.com.APS.data;

import java.util.List;

import br.com.APS.chat.MensagemTO;

public interface BaseDTO {
	
	public String getNome();
	
	public Integer getId();
	
	public List<MensagemTO> getMensagens();
	
	public List<String> getKeys(); 
	
	public Integer getPortaConexao();
	
	public String getSenha();
	
}
