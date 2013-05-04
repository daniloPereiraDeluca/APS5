package br.com.APS.forms;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class FormServidorPrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = -5301724690763159401L;

	private JButton jbEntrar;
    private JLabel jlServMsg;
    private JMenu jmSobre;
    private JMenuBar jmbMenu;
    private JMenuItem jmiAjuda;
    
    public FormServidorPrincipal() {
        initComponents();
    }
               
    private void initComponents() {

        jbEntrar = new JButton("Entrar");
        jlServMsg = new JLabel("Servidor de mensagens");
        jmbMenu = new JMenuBar();
        jmSobre = new JMenu("Sobre");
        jmiAjuda = new JMenuItem("Ajuda");

        setTitle("Servidor de mensagens.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300, 150);
        setResizable(false);

        add(jbEntrar);
        jbEntrar.setBounds(110, 60, 73, 23);
        jlServMsg.setBounds(70, 10, 150, 17);

        jlServMsg.setFont(new Font("Tahoma", 0, 14));
        add(jbEntrar);
        add(jlServMsg);

        jmSobre.add(jmiAjuda);
        jmbMenu.add(jmSobre);
        setJMenuBar(jmbMenu);
    }                      

    public static void main(String args[]) {
    	FormServidorPrincipal formSP = new FormServidorPrincipal();
    	formSP.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource().equals(jbEntrar)) {
			
		}
	}         
}
