package chat;

import java.io.Serializable;
import java.util.Date;

public class MessageTO implements Serializable {

	private static final long serialVersionUID = 3926349087618259801L;
	
	private String mensagem;
	
	private Date hora;
	
	private Usuario usuarioDaMensagem;

	public MessageTO(String mensagem, Date hora, Usuario usuarioDaMensagem) {
		super();
		this.mensagem = mensagem;
		this.hora = hora;
		this.usuarioDaMensagem = usuarioDaMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Date getHora() {
		return hora;
	}

	public Usuario getUsuarioDaMensagem() {
		return usuarioDaMensagem;
	}
	
	

}
