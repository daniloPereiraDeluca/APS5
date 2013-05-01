package br.com.APS.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.APS.chat.MensagemTO;

public class UsuarioDTO implements Serializable, ClienteConnector{

	private static final long serialVersionUID = -1055798285864609452L;

	private String nome;

	private Integer id;

	private String senha;

	private List<MensagemTO> mensagens = new ArrayList<MensagemTO>();

	public UsuarioDTO(String nomeUsuario, Integer idUsuario, String senha) {
		super();
		this.nome = nomeUsuario;
		this.id = idUsuario;
		this.senha = senha;
		this.mensagens = new ArrayList<MensagemTO>();
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public List<MensagemTO> getMensagens() {
		return mensagens;
	}
	
	@Override
	public List<String> getKeys() {
		return Arrays.asList("id", "nome", "senha");
	}

}