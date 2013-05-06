package br.com.APS.chat.clienteTeste;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import br.com.APS.chat.to.MensagemTO;
import br.com.APS.data.ServerDTO;
import br.com.APS.data.UsuarioDTO;

public class ClienteChatTeste extends ClienteChatAdapter{
	
	private Socket conexao;
	
	private UsuarioDTO usuarioDTO;
	
	private ServerDTO serverDTO;

	public ClienteChatTeste(UsuarioDTO usuario, ServerDTO serverDTO2) {
		super("messages", usuario.getNome(), usuario);
		this.usuarioDTO = usuario;
		this.serverDTO = serverDTO2;
		actions();
		
		try {
			this.conexao =  new Socket(InetAddress.getLocalHost(), this.serverDTO.getPortaConexao());
			getStreams();
			output.writeObject(usuario.getId());
			output.flush(); 
			this.start();
//			verificaConexao();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run()
    {
        try {
            //recebe mensagens de outro cliente através do servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(inputStream));
            //cria variavel de mensagem
            String msg;
            while (true)
            {
                // pega o que o servidor enviou
                msg = entrada.readLine();
                //se a mensagem contiver dados, passa pelo if, 
                //caso contrario cai no break e encerra a conexao
                if (msg == null) {
                    System.out.println("Conexão encerrada!");
                    System.exit(0);
                }
                //imprime a mensagem recebida
                frameDeTrocaDeMensagens.getMensagensEnviadas().append(msg);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu uma Falha... .. ." + " IOException: " + e);
        }
    }
	
	private void actions() {
		quandoPressionarBotaoEnviar();
		quandoPressionarEnter();
	}
	
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(conexao.getOutputStream());
		output.flush(); 

		inputStream = new ObjectInputStream(conexao.getInputStream());

		adicionaMensagemAoDisplay(bundle.getMessage("conexaoValidada"));
	}
	
	public void quandoPressionarBotaoEnviar() {
		frameDeTrocaDeMensagens.getBotaoEnviar().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String mensagem = frameDeTrocaDeMensagens.getCampoDeMensagem().getText();
				enviarMensagem(mensagem);
				frameDeTrocaDeMensagens.getCampoDeMensagem().setText("");
				usuarioDTO.getMensagens().add(new MensagemTO(mensagem, new Date()));
			}
		});
	}
	
	private void quandoPressionarEnter() {
		frameDeTrocaDeMensagens.getCampoDeMensagem().addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(KeyEvent evento) {
				if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
					String mensagem = frameDeTrocaDeMensagens.getCampoDeMensagem().getText();
					enviarMensagem(mensagem);
					frameDeTrocaDeMensagens.getCampoDeMensagem().setText("");
					usuarioDTO.getMensagens().add(new MensagemTO(mensagem, new Date()));
				}
			}
		});
	}

}
