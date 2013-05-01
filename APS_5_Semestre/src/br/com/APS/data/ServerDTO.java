package br.com.APS.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.com.APS.chat.MensagemTO;

public class ServerDTO implements ClienteConnector,Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;

	private Integer id;

	private List<MensagemTO> mensagens = new ArrayList<MensagemTO>();
	
	private int portaConexao;

	public ServerDTO(String nome, Integer id, List<MensagemTO> mensagens) {
		super();
		this.nome = nome;
		this.id = id;
		this.mensagens = mensagens;
	}

	public ServerDTO() {
	}

	public ServerDTO(Map<String, Object> mapValues){
		for (String key : mapValues.keySet()) {
			setValue(key, mapValues.get(key));
		}
	}
	
	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public List<MensagemTO> getMensagens() {
		return mensagens;
	}

	public int getPortaConexao() {
		return portaConexao;
	}

	public void setPortaConexao(int portaConexao) {
		this.portaConexao = portaConexao;
	}

	@Override
	public List<String> getKeys() {
		return Arrays.asList("id", "nome", "portaConexao");
	}

	public void setValue(String key, Object value){
		if(key.equals("id")){
			this.id = new Integer((String) value);
		} else if(key.equals("nome")) {
			this.nome = (String) value;
		} else if(key.equals("portaConexao")){
			this.portaConexao = (int) value;
		}
		
	}
	
}
