package forms;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FormsJanelaPrincipal extends JFrame {

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

        jlLogin		.setBounds(20, 100, 32, 14);
        jlSenha		.setBounds(20, 130, 34, 14);
        jtfLogin	.setBounds(80, 100, 130, 20);
        jtfSenha	.setBounds(80, 130, 130, 20);
        jbEntrar	.setBounds(110, 180, 63, 23);
        ljEvtSenhaInconrreta		.setBounds(80, 220, 120, 14);
        ljEvtConexaoErro		.setBounds(70, 260, 140, 14);
        
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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormsJanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormsJanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormsJanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormsJanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormsJanelaPrincipal().setVisible(true);
            }
        });
    }                 
}
