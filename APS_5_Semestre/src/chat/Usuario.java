package chat;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = -1055798285864609452L;
	
	private String nomeUsuario;
	
	private Integer idUsuario;
	
	private String senha;

	public Usuario(String nomeUsuario, Integer idUsuario, String senha) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.idUsuario = idUsuario;
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	
}
