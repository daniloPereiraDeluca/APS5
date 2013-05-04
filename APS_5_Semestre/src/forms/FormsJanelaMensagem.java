package forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class FormsJanelaMensagem extends JFrame {

	private static final long serialVersionUID = -524050008764904521L;

	private JButton botaoEnviar;
	private JLabel labelNomePessoa;
	private JLabel labelIP;
	private JMenu menuExtras;
	private JMenuBar barraDeMenu;
	private JScrollPane scrollMensagensEnviadas;
	private JScrollPane scrollEscrevendoMensagens;
	private JTextArea textoConversa;
	private JTextArea inputDeMensagem;

	public FormsJanelaMensagem() {
		initComponents();
	}

	private void initComponents() {

		labelNomePessoa = new JLabel("Nome da pessoa");
		labelIP = new JLabel("IP:");
		scrollMensagensEnviadas = new JScrollPane();
		textoConversa = new JTextArea();
		scrollEscrevendoMensagens = new JScrollPane();
		inputDeMensagem = new JTextArea();
		botaoEnviar = new JButton("Enviar");
		barraDeMenu = new JMenuBar();
		menuExtras = new JMenu("Extras");

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(600, 420);
		setResizable(false);

		add(labelNomePessoa);
		add(labelIP);
		add(scrollMensagensEnviadas);
		add(scrollEscrevendoMensagens);
		add(botaoEnviar);

		// TextAreas
		textoConversa.setColumns(20);
		textoConversa.setRows(5);
		scrollMensagensEnviadas.setViewportView(textoConversa);
		inputDeMensagem.setColumns(20);
		inputDeMensagem.setRows(5);
		scrollEscrevendoMensagens.setViewportView(inputDeMensagem);

		barraDeMenu.add(menuExtras);
		setJMenuBar(barraDeMenu);
	}

    public static void main(String args[]) {
    	FormsJanelaMensagem formJM = new FormsJanelaMensagem();
    	formJM.setVisible(true);
    }
}
