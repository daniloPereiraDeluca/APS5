package chat;

import java.io.Serializable;
import java.util.Date;

public class MensagemTO implements Serializable {

	private static final long serialVersionUID = 3926349087618259801L;
	
	private String mensagem;
	
	private Date hora;
	
	public MensagemTO(String mensagem, Date hora) {
		super();
		this.mensagem = mensagem;
		this.hora = hora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Date getHora() {
		return hora;
	}

}
