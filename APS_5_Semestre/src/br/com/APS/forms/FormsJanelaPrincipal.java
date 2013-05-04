package br.com.APS.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JTextField;


public class FormsJanelaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7936945795665808094L;

	private JButton jbEntrar;
    private JLabel jlLogin;
    private JLabel jlSenha;
    private JLabel ljEvtSenhaInconrreta;
    private JLabel ljEvtConexaoErro;
    private JMenu jmArquivo;
    private JMenu jmSobre;
    private JMenuBar jmbMenu;
    private JMenuItem jmiConfig;
    private JMenuItem jmiDesconectar;
    private JMenuItem jmiSair;
    private Separator jsSeparador;
    private JTextField jtfLogin;
    private JTextField jtfSenha;
    
    public FormsJanelaPrincipal() {
        initComponents();
    }
                     
    private void initComponents() {

        jlLogin =		new JLabel("Login: ");
        jlSenha = 		new JLabel("Senha:");
        jtfLogin = 	new JTextField();
        jtfSenha = 	new JTextField();
        jbEntrar = 		new JButton("Entrar");
        ljEvtSenhaInconrreta = 		new JLabel("Evento Senha Incorreta");
        ljEvtConexaoErro = 		new JLabel("Evento de erro de conexao");
        jmbMenu = 	new JMenuBar();
        jmArquivo = 		new JMenu("Arquivo");
        jmiConfig = 	new JMenuItem("Configurações");
        jmiDesconectar = 	new JMenuItem("Desconectar");
        jsSeparador = 	new Separator();
        jmiSair = 	new JMenuItem("Sair");
        jmSobre = 		new JMenu("Sobre");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(270, 600);
        setResizable(false);

        jlLogin	.setBounds(20, 100, 50, 14);
        jlSenha	.setBounds(20, 130, 50, 14);
        jtfLogin.setBounds(80, 100, 130, 20);
        jtfSenha.setBounds(80, 130, 130, 20);
        jbEntrar.setBounds(110, 180, 70, 23);
        ljEvtSenhaInconrreta.setBounds(80, 220, 150, 14);
        ljEvtConexaoErro.setBounds(70, 260, 160, 14);
        
        add(jlLogin);
        add(jlSenha);
        add(jtfLogin);
        add(jtfSenha);
        add(jbEntrar);
        add(ljEvtSenhaInconrreta);
        add(ljEvtConexaoErro);
        
        jmArquivo.add(jmiConfig);
        jmArquivo.add(jmiDesconectar);
        jmArquivo.add(jsSeparador);
        jmArquivo.add(jmiSair);
        jmbMenu.add(jmArquivo);
        jmbMenu.add(jmSobre);
        setJMenuBar(jmbMenu);

    }                       

    public static void main(String args[]) {
    	FormsJanelaPrincipal formJP = new FormsJanelaPrincipal();
    	formJP.setVisible(true);
    }

	public void actionPerformed(ActionEvent evt) {
		
	}                 
}
