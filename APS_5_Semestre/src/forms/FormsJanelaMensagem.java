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
	
	private JButton jbEnviar;
    private JLabel jlNomePessoa;
    private JLabel jlIP;
    private JMenu jmExtras;
    private JMenuBar jmbMenuConversa;
    private JScrollPane jScroll1;
    private JScrollPane jScroll2;
    private JTextArea jtaConversa;
    private JTextArea jtaEscrever;
    
    public FormsJanelaMensagem() {
        initComponents();
    }
                      
    private void initComponents() {

        jlNomePessoa = 		new JLabel("Nome da pessoa:");
        jlIP = 		new JLabel("IP:");
        jScroll1 = 	new JScrollPane();
        jtaConversa = 	new JTextArea();
        jScroll2 = 	new JScrollPane();
        jtaEscrever = 	new JTextArea();
        jbEnviar = 		new JButton("Enviar");
        jmbMenuConversa = 	new JMenuBar();
        jmExtras = 		new JMenu("Extras");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 420);
        setResizable(false);

        add(jlNomePessoa);
        add(jlIP);
        add(jScroll1);
        add(jScroll2);
        add(jbEnviar);
        
        jlNomePessoa.setBounds(20, 10, 130, 14);
        jlIP.setBounds(20, 30, 120, 14);
        jScroll1.setBounds(10, 60, 470, 200);
        jScroll2.setBounds(10, 270, 470, 70);
        jbEnviar.setBounds(490, 290, 90, 50);

        //TextAreas
        jtaConversa.setColumns(20);
        jtaConversa.setRows(5);
        jScroll1.setViewportView(jtaConversa);
        jtaEscrever.setColumns(20);
        jtaEscrever.setRows(5);
        jScroll2.setViewportView(jtaEscrever);

        jmbMenuConversa.add(jmExtras);
        setJMenuBar(jmbMenuConversa);
    }                    

    public static void main(String args[]) {
    	FormsJanelaMensagem formJM = new FormsJanelaMensagem();
    	formJM.setVisible(true);
    }
}
