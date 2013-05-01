package chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{

private static final long serialVersionUID = -1055798285864609452L;

private String nome;

private Integer id;

private String senha;

private List<MensagemTO> mensagens = new ArrayList<MensagemTO>();

public Usuario(String nomeUsuario, Integer idUsuario, String senha) {
super();
this.nome = nomeUsuario;
this.id = idUsuario;
this.senha = senha;
this.mensagens = new ArrayList<MensagemTO>();
}

public String getNomeUsuario() {
return nome;
}

public Integer getIdUsuario() {
return id;
}

public String getSenha() {
	return senha;
}

public List<MensagemTO> getMensagens() {
return mensagens;
}

public void setMensagens(List<MensagemTO> mensagens) {
this.mensagens = mensagens;
}


}