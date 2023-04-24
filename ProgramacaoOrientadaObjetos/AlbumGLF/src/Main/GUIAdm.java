package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

class GUIAdm extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private BorderLayout borderLayout = new BorderLayout();
    private FlowLayout flowLayout = new FlowLayout();
   
    private Container cp;
    
    private JPanel pnCentral = new JPanel(cardLayout);
    private JPanel pnMenu = new JPanel(flowLayout);
    private JPanel pnCrud = new JPanel(borderLayout);
    
    private JPanel pnInferior = new JPanel(flowLayout);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date hoje = new Date();
    private JLabel lbData = new JLabel(sdf.format(hoje));
    private JLabel lbData1 = new JLabel("Hoje: ");
    
    private JLabel lbNome = new JLabel("O que deseja administrar?");
    private JButton btFig = new JButton("Figurinhas");
    private JButton btUser = new JButton("Usuarios");
    
    private JLabel lbID = new JLabel("ID");
    private JLabel lbExistencia = new JLabel("Existencia: ");
    private JLabel lbPagina = new JLabel("Pagina: ");
    private JLabel lbTipo = new JLabel("Tipo: ");
    private JTextField tfID = new JTextField(5);
    private JTextField tfExistencia = new JTextField(5);
    private JTextField tfPagina = new JTextField(5);
    
    private JLabel lbNomePessoa = new JLabel("Nome");
    private JLabel lbEmailPessoa = new JLabel("Email: ");
    private JLabel lbSenhaPessoa = new JLabel("Senha: ");
    private JLabel lbCpfPessoa = new JLabel("Cpf: ");
    private JLabel lbIdadePessoa = new JLabel("Idade: ");
    private JLabel lbTimeUser = new JLabel("Time favorito: ");
    private JTextField tfNomePessoa = new JTextField(5);
    private JTextField tfEmailPessoa = new JTextField(5);
    private JTextField tfSenhaPessoa = new JTextField(5);
    private JTextField tfCpfPessoa = new JTextField(5);
    private JTextField tfIdadePessoa = new JTextField(5);
    private JTextField tfTimeUser = new JTextField(5);
    
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
    
    private String tipos[] = {"Brasão", "Jogador", "Estádio"};
    private JComboBox cbTipoFig = new JComboBox(tipos);
    
    private JPanel pnNorte = new JPanel();
    private JPanel pnOeste = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnLeste = new JPanel(new BorderLayout());
    private JPanel pnLeste1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel();
    private JPanel pnSul = new JPanel(cardLayout);
    private JPanel pnSulMsg = new JPanel();
    private JPanel pnSulListagem = new JPanel(new GridLayout(1, 1));

    private JButton btLogout = new JButton("Logout");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btLimpar = new JButton("Limpar");
    private JButton btEditar = new JButton("Editar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btListar = new JButton("Listar");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCriar = new JButton("Criar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btGravar = new JButton();
    private JButton btVoltar = new JButton("Voltar");
    private JLabel lbBuscar = new JLabel("ID: ");
    private JTextField tfBuscar = new JTextField(5);
    
    
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
    
    private int pncrud = 0;
   
    private void setLog(String msg) {
        textAreaMsg.append(msg + "\n");
        textAreaMsg.setCaretPosition(textAreaMsg.getDocument().getLength());
    }
    
    private void editarAtributosTipoFig(Boolean a) {
        
        cbTipoFig.setEnabled(a);
        
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
        
        if(msg == "Brasão"){
            
            lbQtdCopasBrasao.setVisible(true);
            tfQtdCopas.setVisible(true);
            lbSelecao.setVisible(true);
            tfSelecao.setVisible(true);
    
        
        }else if(msg == "Jogador"){

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
        
        }else if(msg == "Estádio"){
        
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
    
    private void mostrarAtributosCrud(int pncrud) {
        if(pncrud == 0){
            lbNomePessoa.setVisible(false);
            lbEmailPessoa.setVisible(false);
            lbSenhaPessoa.setVisible(false);
            lbCpfPessoa.setVisible(false);
            lbIdadePessoa.setVisible(false);
            tfNomePessoa.setVisible(false);
            tfEmailPessoa.setVisible(false);
            tfSenhaPessoa.setVisible(false);
            tfCpfPessoa.setVisible(false);
            tfIdadePessoa.setVisible(false);
            lbPagina.setVisible(true);
            lbTipo.setVisible(true);
            tfPagina.setVisible(true);
            cbTipoFig.setVisible(true);
        }else{
            lbNomePessoa.setVisible(true);
            lbEmailPessoa.setVisible(true);
            lbSenhaPessoa.setVisible(true);
            lbCpfPessoa.setVisible(true);
            lbIdadePessoa.setVisible(true);
            tfNomePessoa.setVisible(true);
            tfEmailPessoa.setVisible(true);
            tfSenhaPessoa.setVisible(true);
            tfCpfPessoa.setVisible(true);
            tfIdadePessoa.setVisible(true);
            lbPagina.setVisible(false);
            lbTipo.setVisible(false);
            tfPagina.setVisible(false);
            cbTipoFig.setVisible(false);
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
        }
    }
    
    public Connection connection = null;
    
    //construtor da classe GUI
    public GUIAdm() throws SQLException {
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
        cp.setLayout(new BorderLayout());
        cp.add(pnCentral);
        cp.add(pnInferior, BorderLayout.SOUTH);
        pnInferior.setSize(900, 50);
        pnInferior.setBackground(Color.LIGHT_GRAY);        
        pnInferior.add(lbData1);
        pnInferior.add(lbData);
        
        pnCentral.add(pnMenu, "pnMenu");
        pnCentral.add(pnCrud, "pnCrud");

        pnMenu.add(btLogout);
        pnMenu.add(lbNome);
        pnMenu.add(btFig);
        pnMenu.add(btUser);
       
        pnCrud.add(pnNorte, BorderLayout.NORTH);
        pnCrud.add(pnCentro, BorderLayout.CENTER);
        pnCrud.add(pnSul, BorderLayout.SOUTH);
        
        pnNorte.setLayout(new GridLayout(1, 2));
        pnNorte.add(pnOeste);
        pnNorte.add(pnLeste);
        pnLeste.setLayout(new GridLayout(1, 2));
        pnLeste.add(pnLeste1);
        
        pnOeste.add(btVoltar);
        pnOeste.add(lbBuscar);
        pnOeste.add(tfBuscar);
        pnOeste.add(tfID);
        pnOeste.add(btBuscar);
        pnOeste.add(btLimpar);
        pnOeste.add(btEditar);
        pnOeste.add(btExcluir);
        pnOeste.add(btSalvar);
        pnOeste.add(btCancelar);
        pnOeste.add(btListar);
        pnOeste.add(btCriar);
        btLimpar.setVisible(false);
        btEditar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btCriar.setVisible(false);
        
        pnSul.add(pnSulMsg, "pnMsg");
        pnSul.add(pnSulListagem, "pnLst");
        pnSul.setBackground(Color.red);
 
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollMensagem.setViewportView(textAreaMsg);
        scrollMensagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//esconde a barra horizontal

        usarGridBagLayoutCentro.add(lbExistencia, tfExistencia, lbID, tfID);
        usarGridBagLayoutCentro.add(lbPagina, tfPagina, lbTipo, cbTipoFig);
        usarGridBagLayoutCentro.add(lbNomePessoa, tfNomePessoa, lbCpfPessoa, tfCpfPessoa);
        usarGridBagLayoutCentro.add(lbEmailPessoa, tfEmailPessoa, lbSenhaPessoa, tfSenhaPessoa);
        usarGridBagLayoutCentro.add(lbIdadePessoa, tfIdadePessoa, lbTimeUser, tfTimeUser);
        
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
        
        tfExistencia.setEditable(false);
        tfID.setEditable(false);
        tfPagina.setEditable(false);
        cbTipoFig.setEnabled(false);
        tfNomePessoa.setEditable(false);
        tfCpfPessoa.setEditable(false);
        tfEmailPessoa.setEditable(false);
        tfSenhaPessoa.setEditable(false);
        tfIdadePessoa.setEditable(false);
        tfTimeUser.setEditable(false);
        textAreaMsg.setEditable(false);
        
        mostrarAtributosTipoFig("");
        editarAtributosTipoFig(false);
        
        cbTipoFig.setSelectedIndex(-1);
        
        cbTipoFig.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                mostrarAtributosTipoFig(String.valueOf(cbTipoFig.getSelectedItem()));
            }
        });
        
        btLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                GUILogin guiLogin = new GUILogin();
                dispose();
            }
        });
        
        btFig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                pncrud = 0;
                lbTimeUser.setVisible(false);
                tfTimeUser.setVisible(false);
                mostrarAtributosCrud(pncrud);
                cardLayout.show(pnCentral, "pnCrud");
            }
        });
        
        btUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                pncrud = 1;
                lbTimeUser.setVisible(true);
                tfTimeUser.setVisible(true);
                mostrarAtributosCrud(pncrud);
                cardLayout.show(pnCentral, "pnCrud");
            }
        });
        
//=========================BOTOES CRUD ====================================
        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                cardLayout.show(pnSul, "pnMsg");
                cardLayout.show(pnCentral, "pnMenu");
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
                tfID.setEditable(false);
                tfID.setText("");
                if(pncrud == 0){
                    tfPagina.setEditable(false);
                    tfPagina.setText("");
                    cbTipoFig.setSelectedIndex(-1);
                    editarAtributosTipoFig(false);
                }else{
                    tfNomePessoa.setEditable(false);
                    tfNomePessoa.setText("");
                    tfEmailPessoa.setEditable(false);
                    tfEmailPessoa.setText("");
                    tfSenhaPessoa.setEditable(false);
                    tfSenhaPessoa.setText("");
                    tfCpfPessoa.setEditable(false);
                    tfCpfPessoa.setText("");
                    tfIdadePessoa.setEditable(false);
                    tfIdadePessoa.setText("");
                    tfTimeUser.setEditable(false);
                    tfTimeUser.setText("");
                }
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btLimpar.setVisible(false);
                btCancelar.setVisible(false);
                btCriar.setVisible(false);
                btExcluir.setVisible(false);
                btSalvar.setVisible(false);
                btEditar.setVisible(false);
            }
        });
        
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                btLimpar.doClick();
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
                String sql = "SELECT * FROM Figurinha";
                
                if(pncrud == 0){
                    colunas = new String[]{"Figurinhas", "Página", "Tipo"};
                    sql = "SELECT * FROM Figurinha";
                }else{
                    colunas = new String[]{"ID", "Nome", "CPF"};
                    sql = "SELECT * FROM Usuario";
                }
                model.setDataVector(dados, colunas);
                
                PreparedStatement ps = null;
                try {
                    ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        if(pncrud == 0){
                            String[] linha = new String[]{String.valueOf(rs.getString("idFigurinha")),  String.valueOf(rs.getString("paginaFigurinha")), String.valueOf(rs.getString("tipoFigurinha"))};                        
                            model.addRow(linha);
                        }else{
                            String[] linha = new String[]{String.valueOf(rs.getString("idUsuario")),  String.valueOf(rs.getString("nomeUsuario")), String.valueOf(rs.getString("cpfUsuario"))};                        
                            model.addRow(linha);
                        }
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                if(pncrud == 0){
                    String sql = "SELECT * FROM Figurinha WHERE idFigurinha = "+tfBuscar.getText();
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            tfExistencia.setText("sim");
                            tfID.setText(String.valueOf(rs.getInt("idFigurinha")));
                            tfPagina.setText(rs.getString("paginaFigurinha"));
                            cbTipoFig.setSelectedItem(rs.getString("tipoFigurinha"));
                            if(String.valueOf(cbTipoFig.getSelectedItem()) == "Brasão"){
                                sql = "SELECT * FROM FigurinhaBrasao WHERE idFigurinhaBrasao = "+tfBuscar.getText();
                                ps = connection.prepareStatement(sql);
                                rs = ps.executeQuery();
                                if(rs.next()){
                                    tfQtdCopas.setText(rs.getString("qtdCopasBrasao"));
                                    tfSelecao.setText(rs.getString("selecaoBrasao"));
                                }
                            }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Jogador"){
                                sql = "SELECT * FROM FigurinhaJogador WHERE idFigurinhaJogador = "+tfBuscar.getText();
                                ps = connection.prepareStatement(sql);
                                rs = ps.executeQuery();
                                if(rs.next()){
                                    tfNomeJ.setText(rs.getString("nomeJogador"));
                                    tfIdadeJ.setText(String.valueOf(rs.getInt("idadeJogador")));
                                    tfNumeroJ.setText(String.valueOf(rs.getInt("numeroCamisaJogador")));
                                    tfTimeJ.setText(rs.getString("timeJogador"));
                                    tfSelecaoJ.setText(rs.getString("selecaoJogador"));
                                    tfNacionalidadeJ.setText(rs.getString("nacionalidadeJogador"));
                                    tfPosicaoJ.setText(rs.getString("posicaoJogador"));
                                    cbTitularJ.setSelected(rs.getBoolean("titularJogador"));
                                }
                            }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Estádio"){
                                sql = "SELECT * FROM FigurinhaEstadio WHERE idFigurinhaEstadio = "+tfBuscar.getText();
                                ps = connection.prepareStatement(sql);
                                rs = ps.executeQuery();
                                if(rs.next()){
                                    tfCidade.setText(rs.getString("cidadeEstadio"));
                                    tfEstado.setText(rs.getString("estadoEstadio"));
                                    tfTimeDono.setText(rs.getString("timeDonoEstadio"));
                                    tfLotacao.setText(String.valueOf(rs.getInt("lotacaoMaximaEstadio")));
                                }
                            }
                            mostrarAtributosTipoFig(String.valueOf(cbTipoFig.getSelectedItem()));
                            btExcluir.setVisible(true);
                            btEditar.setVisible(true);
                        }else{
                            tfExistencia.setText("não");
                            tfPagina.setEditable(true);
                            cbTipoFig.setEnabled(true);
                            editarAtributosTipoFig(true);
                            btCriar.setVisible(true);
                            tfID.setText(tfBuscar.getText());
                        }
                        tfBuscar.setEditable(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btLimpar.setVisible(true);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    String sql = "SELECT * FROM Usuario WHERE idUsuario = "+tfBuscar.getText();
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                            tfExistencia.setText("sim");
                            tfID.setText(rs.getString("idUsuario"));
                            tfNomePessoa.setText(rs.getString("nomeUsuario"));
                            tfEmailPessoa.setText(rs.getString("emailUsuario"));
                            tfSenhaPessoa.setText(rs.getString("senhaUsuario"));
                            tfCpfPessoa.setText(rs.getString("cpfUsuario"));
                            tfIdadePessoa.setText(rs.getString("idadeUsuario"));
                            tfTimeUser.setText(rs.getString("timeTorcedorUsuario"));
                            btExcluir.setVisible(true);
                            btEditar.setVisible(true);
                        }else{
                            tfExistencia.setText("não");
                            tfNomePessoa.setEditable(true);
                            tfEmailPessoa.setEditable(true);
                            tfSenhaPessoa.setEditable(true);
                            tfCpfPessoa.setEditable(true);
                            tfIdadePessoa.setEditable(true);
                            tfTimeUser.setEditable(true);
                            btCriar.setVisible(true);
                            tfID.setText(tfBuscar.getText());
                        }
                        tfBuscar.setEditable(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btLimpar.setVisible(true);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        btCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                if(pncrud == 0){
                    String sql = "INSERT INTO Figurinha(idFigurinha, numeroFigurinha, paginaFigurinha, tipoFigurinha) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, tfID.getText());
                        ps.setString(2, tfID.getText());
                        ps.setString(3, tfPagina.getText());
                        ps.setString(4, String.valueOf(cbTipoFig.getSelectedItem()));
                        ps.execute();
                        if(String.valueOf(cbTipoFig.getSelectedItem()) == "Brasão"){
                            sql = "INSERT INTO FigurinhaBrasao(idFigurinhaBrasao, qtdCopasBrasao, selecaoBrasao) VALUES (?, ?, ?)";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfQtdCopas.getText());
                            ps.setString(3, tfSelecao.getText());
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Jogador"){
                            sql = "INSERT INTO FigurinhaJogador(idFigurinhaJogador, nomeJogador, idadeJogador, numeroCamisaJogador, timeJogador, selecaoJogador, nacionalidadeJogador, posicaoJogador, titularJogador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfNomeJ.getText());
                            ps.setString(3, tfIdadeJ.getText());
                            ps.setString(4, tfNumeroJ.getText());
                            ps.setString(5, tfTimeJ.getText());
                            ps.setString(6, tfSelecaoJ.getText());
                            ps.setString(7, tfNacionalidadeJ.getText());
                            ps.setString(8, tfPosicaoJ.getText());
                            ps.setBoolean(9, cbTitularJ.isSelected());
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Estádio"){
                            sql = "INSERT INTO FigurinhaEstadio(idFigurinhaEstadio, cidadeEstadio, estadoEstadio, timeDonoEstadio, lotacaoMaximaEstadio) VALUES (?, ?, ?, ?, ?)";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfCidade.getText());
                            ps.setString(3, tfEstado.getText());
                            ps.setString(4, tfTimeDono.getText());
                            ps.setString(5, tfLotacao.getText());
                            ps.execute();
                        }
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfPagina.setEditable(false);
                        editarAtributosTipoFig(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    String sql = "INSERT INTO Usuario(idUsuario, nomeUsuario, emailUsuario, senhaUsuario, cpfUsuario, idadeUsuario, timeTorcedorUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, tfID.getText());
                        ps.setString(2, tfNomePessoa.getText());
                        ps.setString(3, tfEmailPessoa.getText());
                        ps.setString(4, tfSenhaPessoa.getText());
                        ps.setString(5, tfCpfPessoa.getText());
                        ps.setInt(6, Integer.parseInt(tfIdadePessoa.getText()));
                        ps.setString(7, tfTimeUser.getText());
                        ps.execute();
                        sql = "INSERT INTO Album (idAlbum, anoAlbum, qtdFigurinhasAlbum, qtdPaginasAlbum, FK_usuario_Album) VALUES (?, ?, ?, ?, ?)";
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, tfID.getText());
                        ps.setString(2, "2022");
                        ps.setString(3, "600");
                        ps.setString(4, "30");
                        ps.setString(5, tfID.getText());
                        ps.execute();
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfNomePessoa.setEditable(false);
                        tfEmailPessoa.setEditable(false);
                        tfSenhaPessoa.setEditable(false);
                        tfCpfPessoa.setEditable(false);
                        tfIdadePessoa.setEditable(false);
                        tfTimeUser.setEditable(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                if(pncrud == 0){
                    String sql = "UPDATE Figurinha SET idFigurinha=?, numeroFigurinha=?, paginaFigurinha=?, tipoFigurinha=? WHERE idFigurinha=?";
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, tfID.getText());
                        ps.setString(2, tfID.getText());
                        ps.setString(3, tfPagina.getText());
                        ps.setString(4, String.valueOf(cbTipoFig.getSelectedItem()));
                        ps.setString(5, tfID.getText());
                        ps.execute();
                        if(String.valueOf(cbTipoFig.getSelectedItem()) == "Brasão"){
                            sql = "UPDATE FigurinhaBrasao SET idFigurinhaBrasao=?, qtdCopasBrasao=?, selecaoBrasao=? WHERE idFigurinhaBrasao=?";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfQtdCopas.getText());
                            ps.setString(3, tfSelecao.getText());
                            ps.setString(4, tfID.getText());
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Jogador"){
                            sql = "UPDATE FigurinhaJogador SET idFigurinhaJogador=?, nomeJogador=?, idadeJogador=?, numeroCamisaJogador=?, timeJogador=?, selecaoJogador=?, nacionalidadeJogador=?, posicaoJogador=?, titularJogador=? WHERE idFigurinhaJogador=?";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfNomeJ.getText());
                            ps.setString(3, tfIdadeJ.getText());
                            ps.setString(4, tfNumeroJ.getText());
                            ps.setString(5, tfTimeJ.getText());
                            ps.setString(6, tfSelecaoJ.getText());
                            ps.setString(7, tfNacionalidadeJ.getText());
                            ps.setString(8, tfPosicaoJ.getText());
                            ps.setBoolean(9, cbTitularJ.isSelected());
                            ps.setString(10, tfID.getText());
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Estádio"){
                            sql = "UPDATE FigurinhaEstadio SET idFigurinhaEstadio=?, cidadeEstadio=?, estadoEstadio=?, timeDonoEstadio=?, lotacaoMaximaEstadio=? WHERE idFigurinhaEstadio=?";
                            ps = connection.prepareStatement(sql);
                            ps.setString(1, tfID.getText());
                            ps.setString(2, tfCidade.getText());
                            ps.setString(3, tfEstado.getText());
                            ps.setString(4, tfTimeDono.getText());
                            ps.setString(5, tfLotacao.getText());
                            ps.setString(6, tfID.getText());
                            ps.execute();
                        }
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfPagina.setEditable(false);
                        editarAtributosTipoFig(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    String sql = "UPDATE Usuario SET idUsuario=?, nomeUsuario=?, emailUsuario=?, senhaUsuario=?, cpfUsuario=?, idadeUsuario=?, timeTorcedorUsuario=? WHERE idUsuario=?";
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, tfID.getText());
                        ps.setString(2, tfNomePessoa.getText());
                        ps.setString(3, tfEmailPessoa.getText());
                        ps.setString(4, tfSenhaPessoa.getText());
                        ps.setString(5, tfCpfPessoa.getText());
                        ps.setString(6, tfIdadePessoa.getText());
                        ps.setString(7, tfTimeUser.getText());
                        ps.setString(8, tfID.getText());
                        ps.execute();
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfNomePessoa.setEditable(false);
                        tfEmailPessoa.setEditable(false);
                        tfSenhaPessoa.setEditable(false);
                        tfCpfPessoa.setEditable(false);
                        tfIdadePessoa.setEditable(false);
                        tfTimeUser.setEditable(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                if(pncrud == 0){
                    tfPagina.setEditable(true);
                    cbTipoFig.setEnabled(true);
                    editarAtributosTipoFig(true);
                }else{
                    tfNomePessoa.setEditable(true);
                    tfEmailPessoa.setEditable(true);
                    tfSenhaPessoa.setEditable(true);
                    tfCpfPessoa.setEditable(true);
                    tfIdadePessoa.setEditable(true);
                    tfTimeUser.setEditable(true);
                }
                btEditar.setVisible(false);
                btExcluir.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
            }
        });
        
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date hoje = new Date();
                if(pncrud == 0){
                    
                    String sql = "DELETE FROM AlbumFigurinha WHERE FK_idFigurinha = "+tfBuscar.getText();
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.execute();

                        sql = "DELETE FROM Figurinha WHERE idFigurinha = "+tfBuscar.getText();
                        ps = connection.prepareStatement(sql);
                        ps.execute();
                        if(String.valueOf(cbTipoFig.getSelectedItem()) == "Brasão"){
                            sql = "DELETE FROM FigurinhaBrasao WHERE idFigurinhaBrasao = "+tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Jogador"){
                            sql = "DELETE FROM FigurinhaJogador WHERE idFigurinhaJogador = "+tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            ps.execute();
                        }else if(String.valueOf(cbTipoFig.getSelectedItem()) == "Estádio"){
                            sql = "DELETE FROM FigurinhaEstadio WHERE idFigurinhaEstadio = "+tfBuscar.getText();
                            ps = connection.prepareStatement(sql);
                            ps.execute();
                        }
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfPagina.setEditable(false);
                        editarAtributosTipoFig(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    String sql = "DELETE FROM Usuario WHERE idUsuario = "+tfBuscar.getText();
                    PreparedStatement ps = null;
                    try {
                        ps = connection.prepareStatement(sql);
                        ps.execute();
                        tfExistencia.setText("sim");
                        tfID.setText(tfBuscar.getText());
                        tfNomePessoa.setEditable(false);
                        tfEmailPessoa.setEditable(false);
                        tfSenhaPessoa.setEditable(false);
                        tfCpfPessoa.setEditable(false);
                        tfIdadePessoa.setEditable(false);
                        tfTimeUser.setEditable(false);
                        btBuscar.setVisible(false);
                        btListar.setVisible(false);
                        btSalvar.setVisible(false);
                        btLimpar.doClick();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(GUIAdm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        
        // parâmetros para janela inicial
        setSize(900, 400);
        setTitle("Album GFL - ADM");
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
