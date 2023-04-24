package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

class GUILogin extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private BorderLayout borderLayout = new BorderLayout();
    private FlowLayout flowLayout = new FlowLayout();
   
    private Container cp;
    
    private JPanel pnCentral = new JPanel(flowLayout);
    private JPanel pnInferior = new JPanel(flowLayout);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date hoje = new Date();
    private JLabel lbData = new JLabel(sdf.format(hoje));
    private JLabel lbData1 = new JLabel("Hoje: ");
    
    private JLabel lbNome = new JLabel("Digite seu nome");
    private JTextField tfNome = new JTextField(20);
    private JButton btLoginAdm = new JButton("Login ADM");
    private JButton btLoginUser = new JButton("Login User");
    
    public Connection connection = null;
    
    //construtor da classe GUI
    public GUILogin() {
        String DATABASE_URL = "jdbc:derby://localhost:1527/dbFigs";
        String usuario = "adm";
        String senha = "adm";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        cp = getContentPane();
        cp.setLayout(borderLayout);
        cp.add(pnCentral);
        cp.add(pnInferior, BorderLayout.SOUTH);
        pnInferior.setSize(900, 50);
        pnInferior.setBackground(Color.LIGHT_GRAY);
        
        pnInferior.add(lbData1);
        pnInferior.add(lbData);
      
        pnCentral.add(lbNome);
        pnCentral.add(tfNome, FlowLayout.CENTER);
        pnCentral.add(btLoginAdm);
        pnCentral.add(btLoginUser);
        
//        tfNome.setText("molina");
        
        btLoginAdm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                String sql = "SELECT * FROM Administrador WHERE nomeAdministrador = '"+tfNome.getText()+"'";
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        GUIAdm guiAdm = new GUIAdm();
                        dispose();
                    }else{
                        tfNome.setText("");
                        JOptionPane.showMessageDialog(null, "CREDENCIAIS INCORRETAS!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btLoginUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                String sql = "SELECT * FROM Usuario WHERE nomeUsuario = '"+tfNome.getText()+"'";
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        GUIUser guiUser = new GUIUser(rs.getInt("idUsuario"));
                        dispose();
                    }else{
                        tfNome.setText("");
                        JOptionPane.showMessageDialog(null, "CREDENCIAIS INCORRETAS!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // parâmetros para janela inicial
        setSize(700, 200);
        setTitle("Album GFL");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
