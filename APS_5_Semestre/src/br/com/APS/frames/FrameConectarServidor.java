package br.com.APS.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.com.APS.utils.BundleUtil;

public class FrameConectarServidor extends JFrame {

	private static final long serialVersionUID = 4640751695039898057L;

	private JButton botaoConectar;

	private JButton botaoDesconectar;

	private BundleUtil bundle;

	private JPanel painel;

	public JLabel statusField = null;

	public FrameConectarServidor(String chatName, String bundleName) {
		super(chatName);
		bundle = new BundleUtil("messages");
		setSize(350, 120);
		setVisible(true);
		initComponents();
	}

	private void initComponents() {
		painel = new JPanel();
		painel.setLayout(new GridLayout(1, 2));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		botaoConectar = new JButton();
		botaoDesconectar = new JButton();

		botaoConectar.setText(bundle.getMessage("conectar"));
		botaoDesconectar.setText(bundle.getMessage("desconectar"));

		botaoConectar.setEnabled(true);
		botaoConectar.setBounds(110, 180, 70, 100);

		botaoDesconectar.setEnabled(false);
		botaoDesconectar.setBounds(200, 400, 70, 100);

		painel.add(botaoConectar);
		painel.add(botaoDesconectar);

		add(painel, BorderLayout.CENTER);
		pack();
	}

	public JButton getBotaoConectar() {
		return botaoConectar;
	}

	public JButton getBotaoDesconectar() {
		return botaoDesconectar;
	}

	public static void main(String[] args) {
		FrameConectarServidor f = new FrameConectarServidor("teste", "messages");
		f.setVisible(true);
		f.setSize(350, 120);
	}
}
