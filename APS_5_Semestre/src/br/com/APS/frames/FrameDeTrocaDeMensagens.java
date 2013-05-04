package br.com.APS.frames;

import java.awt.HeadlessException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import br.com.APS.utils.BundleUtil;

public class FrameDeTrocaDeMensagens extends JFrame {

	private static final long serialVersionUID = -4195317171065364310L;

	private JTextArea mensagensEnviadas;
	
	private JButton botaoEnviar;
	
	private JScrollPane scrollMensagensEnviadas;
	
	private JScrollPane scrollMensagem;
	
	private JTextArea campoDeMensagem;
	
	private BundleUtil bundle;


	public FrameDeTrocaDeMensagens(String chatName, String bundleName)
			throws HeadlessException {
		super(chatName);
		bundle = new BundleUtil("messages");
		setSize(600, 450);
		setVisible(true);
		initComponents();
	}

	private void initComponents() {

		inciarCampos();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		atribuiValoresComponentesDaTela();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
        getContentPane().setLayout(layout);
        
        organizaLayoutHorizontal(layout);
        
        organizaLayoutVertical(layout);


		pack();
	}

	private void organizaLayoutVertical(GroupLayout layout) {
		layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollMensagensEnviadas, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollMensagem)
                    .addComponent(botaoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
	}

	private void organizaLayoutHorizontal(GroupLayout layout) {
		layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMensagensEnviadas)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        );
	}

	private void atribuiValoresComponentesDaTela() {
		mensagensEnviadas.setEditable(false);
		mensagensEnviadas.setColumns(20);
		mensagensEnviadas.setRows(5);
		scrollMensagensEnviadas.setViewportView(mensagensEnviadas);

		campoDeMensagem.setColumns(20);
		campoDeMensagem.setRows(5);
		
		scrollMensagem.setViewportView(campoDeMensagem);

		botaoEnviar.setText(bundle.getMessage("enviar"));
	}


	private void inciarCampos() {
		scrollMensagensEnviadas = new JScrollPane();
		mensagensEnviadas = new JTextArea();
		scrollMensagem = new JScrollPane();
		campoDeMensagem = new JTextArea();
		botaoEnviar = new JButton();
	}


	public void setTextFieldEditable(final boolean editable) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				campoDeMensagem.setEditable(editable);
			}
		});
	}

	public JTextArea getCampoDeMensagem() {
		return campoDeMensagem;
	}

	public JTextArea getMensagensEnviadas() {
		return mensagensEnviadas;
	}

	public JButton getBotaoEnviar() {
		return botaoEnviar;
	}


}
