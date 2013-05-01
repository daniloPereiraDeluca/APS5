package br.com.APS.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.APS.chat.MensagemTO;

public class ServerDTO implements BaseDTO,Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;

	private Integer id;

	private List<MensagemTO> mensagens = new ArrayList<MensagemTO>();
	
	private Integer portaConexao;

	public ServerDTO(String nome, Integer id, Integer port) {
		super();
		this.nome = nome;
		this.id = id;
		this.portaConexao = port;
		this.mensagens = new ArrayList<MensagemTO>();
	}

	public ServerDTO() {
	}

	public ServerDTO(BaseDTO simpleDTO) {
		
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

	public Integer getPortaConexao() {
		return portaConexao;
	}

	public void setPortaConexao(Integer portaConexao) {
		this.portaConexao = portaConexao;
	}

	@Override
	public List<String> getKeys() {
		return Arrays.asList("id", "nome", "portaConexao");
	}

}
