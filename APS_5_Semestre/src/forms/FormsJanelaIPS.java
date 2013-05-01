package forms;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class FormsJanelaIPS extends javax.swing.JFrame {

	private static final long serialVersionUID = -4623808877026326988L;

	private JLabel jlNome;
    private JLabel jlIP;
    private JList jlistIPs;
    private JMenu jmArquivo;
    private JMenu jmSobre;
    private JMenuBar jmbMenu;
    private JMenuItem jmiDesconectar;
    private JScrollPane jScroll;
    private Separator jmiSeparador;

    public FormsJanelaIPS() {
        initComponents();
    }
                     
    private void initComponents() {

        jlNome = 		new JLabel("Nome:");
        jlIP = 		new JLabel("IP:");
        jScroll = 	new JScrollPane();
        jlistIPs = 		new JList();
        jmbMenu = 	new JMenuBar();
        jmArquivo = 		new JMenu("Arquivo");
        jmiSeparador = 	new Separator();
        jmiDesconectar = 	new JMenuItem("Desconectar");
        jmSobre = 		new JMenu("Sobre");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(270, 600);
        setResizable(false);

        add(jlNome);
        add(jlIP);
        add(jScroll);
        
        jlNome		.setBounds(10, 30, 34, 14);
        jlIP		.setBounds(10, 60, 14, 14);
        jScroll.setBounds(10, 80, 240, 390);

        jlistIPs.setModel(new AbstractListModel() {
            String[] strings = { "IP 1", "IP 2", "IP 3", "IP 4", "IP 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
        jScroll.setViewportView(jlistIPs);

        jmArquivo.add(jmiSeparador);
        jmArquivo.add(jmiDesconectar);
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
            Logger.getLogger(FormsJanelaIPS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormsJanelaIPS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormsJanelaIPS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormsJanelaIPS.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormsJanelaIPS().setVisible(true);
            }
        });
    }
                
}
