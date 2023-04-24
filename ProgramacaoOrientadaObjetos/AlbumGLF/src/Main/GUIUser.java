package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import myTools.Ferramentas;
import myTools.UsarGridBagLayout;

class GUIUser extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private BorderLayout borderLayout = new BorderLayout();
    private FlowLayout flowLayout = new FlowLayout();
   
    private Container cp;
    
    private JPanel pnCrud = new JPanel(borderLayout);
    
    private JPanel pnInferior = new JPanel(flowLayout);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date hoje = new Date();
    private JLabel lbData = new JLabel(sdf.format(hoje));
    private JLabel lbData1 = new JLabel("Hoje: ");
    
    private JTextField tfExistencia = new JTextField(5);
    private JTextField tfPosse = new JTextField(5);
    private JTextField tfPagina = new JTextField(5);
    private JTextField tfTipo = new JTextField(5);
    private JLabel lbExistencia = new JLabel("Existe: ");
    private JLabel lbPosse = new JLabel("Posse: ");
    private JLabel lbPagina = new JLabel("Pagina: ");
    private JLabel lbTipo = new JLabel("Tipo: ");
    
    private JLabel lbQtdCopasBrasao = new JLabel("Qtd de copas");
    private JTextField tfQtdCopas = new JTextField(5);
    private JLabel lbSelecao = new JLabel("Seleção: ");
    private JTextField tfSelecao = new JTextField(5);
    
    private JLabel lbCidade = new JLabel("Cidade: ");
    private JLabel lbEstado = new JLabel("Estado: ");
    private JLabel lbTimeDono = new JLabel("Time Dono: ");
    private JLabel lbLotacao = new JLabel("Lotação: ");
    private JTextField tfCidade = new JTextField(5);
    private JTextField tfEstado = new JTextField(5);
    private JTextField tfTimeDono = new JTextField(5);
    private JTextField tfLotacao = new JTextField(5);
    
    private JLabel lbNomeJ = new JLabel("Nome: ");
    private JLabel lbIdadeJ = new JLabel("Idade: ");
    private JLabel lbNumeroJ = new JLabel("Numero Camisa: ");
    private JLabel lbTimeJ = new JLabel("Time: ");
    private JLabel lbSelecaoJ = new JLabel("Seleção: ");
    private JLabel lbNacionalidadeJ = new JLabel("Nacionalidade: ");
    private JLabel lbPosicaoJ = new JLabel("Posição: ");
    private JLabel lbTitularJ = new JLabel("Titular: ");
    private JTextField tfNomeJ = new JTextField(5);
    private JTextField tfIdadeJ = new JTextField(5);
    private JTextField tfNumeroJ = new JTextField(5);
    private JTextField tfTimeJ = new JTextField(5);
    private JTextField tfSelecaoJ = new JTextField(5);
    private JTextField tfNacionalidadeJ = new JTextField(5);
    private JTextField tfPosicaoJ = new JTextField(5);
    private JCheckBox cbTitularJ = new JCheckBox();
    
    private JPanel pnNorte = new JPanel();
    private JPanel pnOeste = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnLeste = new JPanel(new BorderLayout());
    private JPanel pnLeste1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel();
    private JPanel pnSul = new JPanel(cardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));

    private JButton btBuscar = new JButton("Buscar");
    private JLabel lbBuscar = new JLabel("ID: ");
    private JTextField tfBuscar = new JTextField(5);
    private JButton btLimpar = new JButton("Limpar");
    private JButton btColar = new JButton("Colar");
    private JButton btDescolar = new JButton("Descolar");
    private JButton btListar = new JButton("Listar Minhas Figurinhas");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton();
    private JButton btLogout = new JButton("Logout");
    
    JScrollPane scrollList = new JScrollPane();
    private JScrollPane scrollMensagem = new JScrollPane(); //barra de rolagem
    JTextArea textAreaMsg = new JTextArea(5, 150); //campo para texto com várias linhas
    DefaultCaret caret = (DefaultCaret) textAreaMsg.getCaret(); //para que haja rolagem automática do textArea
    UsarGridBagLayout usarGridBagLayoutCentro = new UsarGridBagLayout(pnCentro);
    Ferramentas fer = new Ferramentas();
    
    String[] colunas = new String[]{"Figurinhas","Pagina", "Tipo"};
    String[][] dados = new String[0][1];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    
    public Connection connection = null;
    
    public int userAtual;
    public int albumAtual;
    
    private void editarAtributosTipoFig(Boolean a) {
       
        tfQtdCopas.setEditable(a);
        tfSelecao.setEditable(a);
        
        tfNomeJ.setEditable(a);
        tfIdadeJ.setEditable(a);
        tfNumeroJ.setEditable(a);
        tfTimeJ.setEditable(a);
        tfSelecaoJ.setEditable(a);
        tfNacionalidadeJ.setEditable(a);
        tfPosicaoJ.setEditable(a);
        cbTitularJ.setEnabled(a);
        
        tfCidade.setEditable(a);
        tfEstado.setEditable(a);
        tfTimeDono.setEditable(a);
        tfLotacao.setEditable(a);
    }
    
    private void mostrarAtributosTipoFig(String msg) {
                
        lbQtdCopasBrasao.setVisible(false);
        tfQtdCopas.setVisible(false);
        lbSelecao.setVisible(false);
        tfSelecao.setVisible(false);
        
        lbNomeJ.setVisible(false);
        lbIdadeJ.setVisible(false);
        lbNumeroJ.setVisible(false);
        lbTimeJ.setVisible(false);
        lbSelecaoJ.setVisible(false);
        lbNacionalidadeJ.setVisible(false);
        lbPosicaoJ.setVisible(false);
        lbTitularJ.setVisible(false);
        tfNomeJ.setVisible(false);
        tfIdadeJ.setVisible(false);
        tfNumeroJ.setVisible(false);
        tfTimeJ.setVisible(false);
        tfSelecaoJ.setVisible(false);
        tfNacionalidadeJ.setVisible(false);
        tfPosicaoJ.setVisible(false);
        cbTitularJ.setVisible(false);
        
        lbCidade.setVisible(false);
        lbEstado.setVisible(false);
        lbTimeDono.setVisible(false);
        lbLotacao.setVisible(false);
        tfCidade.setVisible(false);
        tfEstado.setVisible(false);
        tfTimeDono.setVisible(false);
        tfLotacao.setVisible(false);
        
        if("Brasão".equals(msg)){
            
            lbQtdCopasBrasao.setVisible(true);
            tfQtdCopas.setVisible(true);
            lbSelecao.setVisible(true);
            tfSelecao.setVisible(true);
    
        
        }else if("Jogador".equals(msg)){

            lbNomeJ.setVisible(true);
            lbIdadeJ.setVisible(true);
            lbNumeroJ.setVisible(true);
            lbTimeJ.setVisible(true);
            lbSelecaoJ.setVisible(true);
            lbNacionalidadeJ.setVisible(true);
            lbPosicaoJ.setVisible(true);
            lbTitularJ.setVisible(true);
            tfNomeJ.setVisible(true);
            tfIdadeJ.setVisible(true);
            tfNumeroJ.setVisible(true);
            tfTimeJ.setVisible(true);
            tfSelecaoJ.setVisible(true);
            tfNacionalidadeJ.setVisible(true);
            tfPosicaoJ.setVisible(true);
            cbTitularJ.setVisible(true);
        
        }else if("Estádio".equals(msg)){
        
            lbCidade.setVisible(true);
            lbEstado.setVisible(true);
            lbTimeDono.setVisible(true);
            lbLotacao.setVisible(true);
            tfCidade.setVisible(true);
            tfEstado.setVisible(true);
            tfTimeDono.setVisible(true);
            tfLotacao.setVisible(true);
    
        }
    }
    
    
    //construtor da classe GUI
    public GUIUser(int idUserAtual) {   
        
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
        
        userAtual = idUserAtual;
        String sql = "SELECT A.idAlbum FROM Album A INNER JOIN Usuario U ON U.IDUSUARIO = A.FK_USUARIO_ALBUM WHERE U.IDUSUARIO = "+ userAtual;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                albumAtual = rs.getInt("idAlbum");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnCrud);
        cp.add(pnInferior, BorderLayout.SOUTH);
        pnInferior.setSize(900, 50);
        pnInferior.setBackground(Color.LIGHT_GRAY);
        
        pnInferior.add(lbData1);
        pnInferior.add(lbData);
        
        pnCrud.add(pnNorte, BorderLayout.NORTH);
        pnCrud.add(pnCentro, BorderLayout.CENTER);
        pnCrud.add(pnSul, BorderLayout.SOUTH);
        
        pnNorte.setLayout(new GridLayout(1, 2));
        pnNorte.add(pnOeste);
        pnNorte.add(pnLeste);
        pnLeste.setLayout(new GridLayout(1, 2));
        pnLeste.add(pnLeste1);
        
        pnOeste.add(btLogout);
        pnOeste.add(lbBuscar);
        pnOeste.add(tfBuscar);
        pnOeste.add(btBuscar);
        pnOeste.add(btLimpar);
        pnOeste.add(btColar);
        pnOeste.add(btDescolar);
        pnOeste.add(btSalvar);
        pnOeste.add(btCancelar);
        pnOeste.add(btListar);
        
        btLimpar.setVisible(false);
        btColar.setVisible(false);
        btDescolar.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");
        pnSul.setBackground(Color.red);
        
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        usarGridBagLayoutCentro.add(lbExistencia, tfExistencia, lbPosse, tfPosse);
        usarGridBagLayoutCentro.add(lbPagina, tfPagina, lbTipo, tfTipo);
        
        usarGridBagLayoutCentro.add(lbQtdCopasBrasao, tfQtdCopas, lbSelecao, tfSelecao);
        
        usarGridBagLayoutCentro.add(lbNomeJ, tfNomeJ, lbIdadeJ, tfIdadeJ);
        usarGridBagLayoutCentro.add(lbNumeroJ, tfNumeroJ, lbTimeJ, tfTimeJ);
        usarGridBagLayoutCentro.add(lbSelecaoJ, tfSelecaoJ, lbNacionalidadeJ, tfNacionalidadeJ);
        usarGridBagLayoutCentro.add(lbPosicaoJ, tfPosicaoJ, lbTitularJ, cbTitularJ);
        
        usarGridBagLayoutCentro.add(lbCidade, tfCidade, lbEstado, tfEstado);
        usarGridBagLayoutCentro.add(lbTimeDono, tfTimeDono, lbLotacao, tfLotacao);
        
        UsarGridBagLayout usarGridBagLayoutSul = new UsarGridBagLayout(pnSulMsg);
        usarGridBagLayoutSul.add(new JLabel("Mensagem:"), scrollMensagem);
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");
        
        mostrarAtributosTipoFig("");
        editarAtributosTipoFig(false);
        
        tfPosse.setEditable(false);
        tfExistencia.setEditable(false);
        tfPagina.setEditable(false);
        tfTipo.setEditable(false);
        textAreaMsg.setEditable(false);
        
        btLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                GUILogin guiLogin = new GUILogin();
                dispose();
            }
        });
        
        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAtributosTipoFig("");
                Date hoje = new Date();
                tfBuscar.setEditable(true);
                tfBuscar.setText("");
                tfExistencia.setEditable(false);
                tfExistencia.setText("");
                tfPagina.setEditable(false);
                tfPagina.setText("");
                tfPosse.setText("");
                tfPosse.setEditable(false);
                tfTipo.setText("");
                tfTipo.setEditable(false);
                editarAtributosTipoFig(false);
                
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btLimpar.setVisible(false);
                btCancelar.setVisible(false);
                btSalvar.setVisible(false);
                btDescolar.setVisible(false);
                btColar.setVisible(false);
            }
        });
        
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                String sql = "SELECT * FROM Figurinha WHERE idFigurinha = "+tfBuscar.getText();
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        tfExistencia.setText("sim");
                        tfPagina.setText(rs.getString("paginaFigurinha"));
                        tfTipo.setText(rs.getString("tipoFigurinha"));
                        
                        if ("Brasão".equals(String.valueOf(rs.getString("tipoFigurinha")))) {
                            sql = "SELECT * FROM FigurinhaBrasao WHERE idFigurinhaBrasao = " + tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                tfQtdCopas.setText(rs.getString("qtdCopasBrasao"));
                                tfSelecao.setText(rs.getString("selecaoBrasao"));
                            }
                        } else if("Jogador".equals(String.valueOf(rs.getString("tipoFigurinha")))) {
                            sql = "SELECT * FROM FigurinhaJogador WHERE idFigurinhaJogador = " + tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                tfNomeJ.setText(rs.getString("nomeJogador"));
                                tfIdadeJ.setText(String.valueOf(rs.getInt("idadeJogador")));
                                tfNumeroJ.setText(String.valueOf(rs.getInt("numeroCamisaJogador")));
                                tfTimeJ.setText(rs.getString("timeJogador"));
                                tfSelecaoJ.setText(rs.getString("selecaoJogador"));
                                tfNacionalidadeJ.setText(rs.getString("nacionalidadeJogador"));
                                tfPosicaoJ.setText(rs.getString("posicaoJogador"));
                                cbTitularJ.setSelected(rs.getBoolean("titularJogador"));
                            }
                        } else if ("Estádio".equals(String.valueOf(rs.getString("tipoFigurinha")))) {
                            sql = "SELECT * FROM FigurinhaEstadio WHERE idFigurinhaEstadio = " + tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                tfCidade.setText(rs.getString("cidadeEstadio"));
                                tfEstado.setText(rs.getString("estadoEstadio"));
                                tfTimeDono.setText(rs.getString("timeDonoEstadio"));
                                tfLotacao.setText(String.valueOf(rs.getInt("lotacaoMaximaEstadio")));
                            }
                        }
                        
                        
                        sql = "SELECT F.idFigurinha, F.paginaFigurinha, F.tipoFigurinha FROM Figurinha F INNER JOIN AlbumFigurinha W ON W.FK_idFigurinha = F.idFigurinha INNER JOIN Album A ON W.FK_IDALBUM = A.IDALBUM WHERE A.FK_USUARIO_ALBUM ="+ userAtual +" AND F.IDFIGURINHA = "+ tfBuscar.getText();
                        ps = connection.prepareStatement(sql);
                        rs = ps.executeQuery();
                        if(rs.next()){
                            tfPosse.setText("sim");
                            btDescolar.setVisible(true);
                        }else{
                            tfPosse.setText("não");
                            btColar.setVisible(true);
                        }
                        
                    }else{
                        tfExistencia.setText("não");
                    }
                    mostrarAtributosTipoFig(String.valueOf(tfTipo.getText()));
                    tfBuscar.setEditable(false);
                    btBuscar.setVisible(false);
                    btListar.setVisible(false);
                    btLimpar.setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnSul, "pnLst");
                scrollList.setPreferredSize(tabela.getPreferredSize());
                pnSulListagem.add(scrollList);
                scrollList.setViewportView(tabela);
                dados = new String[0][1];
                
                colunas = new String[]{"Figurinhas", "Página", "Tipo"};
                
                String sql = "SELECT F.idFigurinha, F.paginaFigurinha, F.tipoFigurinha FROM Figurinha F INNER JOIN AlbumFigurinha W ON W.FK_idFigurinha = F.idFigurinha INNER JOIN Album A ON W.FK_IDALBUM = A.IDALBUM WHERE A.FK_USUARIO_ALBUM ="+ userAtual;
                model.setDataVector(dados, colunas);
                
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] linha = new String[]{String.valueOf(rs.getString("idFigurinha")),  String.valueOf(rs.getString("paginaFigurinha")), String.valueOf(rs.getString("tipoFigurinha"))};                        
                        model.addRow(linha);
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        btColar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                String sql = "INSERT INTO AlbumFigurinha(FK_IdAlbum, FK_IdFigurinha, dataColagem) VALUES (?, ?, ?)";
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ps.setInt(1, albumAtual);
                    ps.setInt(2, Integer.valueOf(tfBuscar.getText()));
                    ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
                    ps.execute();
                    tfPosse.setText("sim");
                    tfPagina.setEditable(false);
                    tfTipo.setEditable(false);
                    btBuscar.setVisible(false);
                    btListar.setVisible(false);
                    btSalvar.setVisible(false);
                    btLimpar.doClick();

                } catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        });
        
        btDescolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                String sql = "DELETE FROM AlbumFigurinha WHERE FK_IdFigurinha = " + tfBuscar.getText() + " AND FK_IdAlbum = " + albumAtual;
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ps.execute();
                    tfExistencia.setText("sim");
                    tfPosse.setText("não");
                    tfPagina.setEditable(false);
                    tfTipo.setEditable(false);
                    btBuscar.setVisible(false);
                    btListar.setVisible(false);
                    btSalvar.setVisible(false);
                    btLimpar.doClick();

                } catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // parâmetros para janela inicial
        setSize(900, 400);
        setTitle("Album GFL - User");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
