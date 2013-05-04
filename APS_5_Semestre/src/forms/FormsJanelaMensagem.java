package forms;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
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

		labelNomePessoa.setBounds(20, 10, 80, 14);
		labelIP.setBounds(20, 30, 100, 14);
		scrollMensagensEnviadas.setBounds(10, 60, 470, 200);
		scrollEscrevendoMensagens.setBounds(10, 270, 470, 70);
		botaoEnviar.setBounds(490, 290, 90, 50);

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
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(FormsJanelaMensagem.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(FormsJanelaMensagem.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(FormsJanelaMensagem.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			Logger.getLogger(FormsJanelaMensagem.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FormsJanelaMensagem().setVisible(true);
			}
		});
	}
}
