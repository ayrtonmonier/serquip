/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * telaAcesso.java
 *
 * Created on 15/10/2009, 13:59:48
 */

package principal;

import auxiliar.validaLogin;
import auxiliar.Data;
import auxiliar.conexao;
import javax.swing.*;
import java.net.*;
import java.io.File;

/**
 *
 * @author ayrton monier
 */
public class telaAcesso extends javax.swing.JDialog {
    String usuario, matricula, data, turno, hora, senha, caracterDigitado;
    //pega nome, senha e situação do banco e a senha digitada na tela de login para validações
    String nome;
    String senhaUs;
    String situacao;
    String tipoUsuario;
    String nomeHost, ipHost;
    String senhaCriptografada;
    static String diretorioBackup, diretorio;

    Data dataHora;
    public static conexao con_inicial;
    public static boolean valida;
    public int cont = 3;
    static boolean conectado, existe;
    static int contaDiretorio;

    static conexao con_logout;


    /** Creates new form telaAcesso */
    public telaAcesso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        loginMatricula = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        labelTurno = new javax.swing.JLabel();
        loginUsuario = new javax.swing.JComboBox();
        labelData = new javax.swing.JLabel();
        loginData = new javax.swing.JFormattedTextField();
        labelUsuario = new javax.swing.JLabel();
        loginTurno = new javax.swing.JComboBox();
        loginSenha = new javax.swing.JPasswordField();
        labelMatricula = new javax.swing.JLabel();
        labelUsuario1 = new javax.swing.JLabel();
        loginHost = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btSair = new javax.swing.JButton();
        btLogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login - Sistema Serquip 1.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Cooper Black", 0, 22)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(" Sistema Serquip 1.0");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Controle de pesagem de resíduos");

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("By Ayrton Monier");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo_acesso.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
            .addComponent(jLabel8)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Se errar a senha ou  se tentar entrar com sua senha em outro  tipo de usuário");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("não sendo o seu tipo será bloqueado automaticamente após a 3ª tentativa.");

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        labelSenha.setText("Senha:");

        labelTurno.setText("Turno:");

        loginUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Gerente", "Operador", "Administrador" }));
        loginUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUsuarioActionPerformed(evt);
            }
        });
        loginUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                loginUsuarioKeyTyped(evt);
            }
        });

        labelData.setText("Data:");

        loginData.setEnabled(false);

        labelUsuario.setText("Usuário:");

        loginTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Manha", "Tarde", "Noite" }));
        loginTurno.setEnabled(false);

        loginSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginSenhaActionPerformed(evt);
            }
        });

        labelMatricula.setText("Matricula:");

        labelUsuario1.setText("Host / IP:");

        loginHost.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "localhost", "Escolher outro..." }));
        loginHost.setToolTipText("Informe o nome / IP do computador onde se encontra a base de dados");
        loginHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginHostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelData, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelTurno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelSenha, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(labelUsuario1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginHost, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginSenha)
                    .addComponent(loginMatricula)
                    .addComponent(loginUsuario, 0, 132, Short.MAX_VALUE)
                    .addComponent(loginData))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTurno))
                .addContainerGap())
        );

        btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btLogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        btLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogarActionPerformed(evt);
            }
        });
        btLogar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btLogarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLogar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btLogar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void btLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogarActionPerformed
          loginSistema();
    }//GEN-LAST:event_btLogarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        System.exit(0);
}//GEN-LAST:event_btSairActionPerformed

    private void loginUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUsuarioActionPerformed
            //instancia da classe java Data
           // dataHora = new Data();
            //chama o metodo le_data e le_hora
            Data.le_data();
            Data.le_hora();

        //validação de tipo de usuário
        if(loginUsuario.getSelectedItem().equals("Gerente")){

            //campo e label Data invisíveis sendo que data vem logo preechido
            labelData.setVisible(false);
            loginData.setVisible(false);
            loginData.setText(Data.dataAtual);

            //campo e label Turno invisíveis
            labelTurno.setVisible(false);
            loginTurno.setVisible(false);

            //envia hora e minuto para classe data
            Data.configuraTurno(Data.hora, Data.minuto);
            //facilita para o operador
            loginTurno.setSelectedItem(Data.mostraTurno());
            //campo e label Matricula visíveis
            loginMatricula.setText("");
            labelMatricula.setVisible(true);
            loginMatricula.setVisible(true);
           
        }

        //validação de tipo de usuário
        else if(loginUsuario.getSelectedItem().equals("Operador")){


            //campo e label Turno visíveis
            labelTurno.setVisible(true);
            loginTurno.setVisible(true);
            //envia hora e minuto para classe data
            Data.configuraTurno(Data.hora, Data.minuto);
            if(Data.mostraTurno().toString().equals("Noite") && (Data.hora >= 0 && Data.hora <= 6))
                loginData.setText(Data.dataAtual);

            //facilita para o operador
            loginTurno.setSelectedItem(Data.mostraTurno());

            //campo e label Data visíveis sendo que data vem logo preechido
            labelData.setVisible(true);
            loginData.setVisible(true);
            loginData.setText(Data.dataAtual);

            //campo e label Matricula visíveis
            labelMatricula.setVisible(true);
            loginMatricula.setText("");
            loginSenha.setText("");


            }//fim valida operador
                
         //validação de tipo de usuário
        else if(loginUsuario.getSelectedItem().equals("Administrador")){

            //campo e label Data visíveis sendo que data vem logo preechido
            labelData.setVisible(false);
            loginData.setVisible(false);
            loginData.setText(Data.dataAtual);
            //campo e label Turno visíveis
            labelTurno.setVisible(false);
            loginTurno.setVisible(false);
            //envia hora e minuto para classe data
            Data.configuraTurno(Data.hora, Data.minuto);
            //facilita para o operador
            loginTurno.setSelectedItem(Data.mostraTurno());
            loginMatricula.setText("");
            loginSenha.setText("");
        }

        else{

            //campo e label Data visíveis sendo que data vem logo preechido
            labelData.setVisible(true);
            loginData.setVisible(true);
            loginData.setText("");
            //campo e label Turno visíveis
            labelTurno.setVisible(true);
            loginTurno.setVisible(true);
            loginTurno.setSelectedItem("");
            //limpa combo box
            loginMatricula.setText("");
            loginSenha.setText("");
            
            
        }
            
}//GEN-LAST:event_loginUsuarioActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing

    private void btLogarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLogarKeyTyped
        loginSistema();
    }//GEN-LAST:event_btLogarKeyTyped

    private void loginUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginUsuarioKeyTyped
        //foco vai para o campo de matricula
           loginMatricula.requestFocus();
    }//GEN-LAST:event_loginUsuarioKeyTyped


    private void loginSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginSenhaActionPerformed
        loginSistema();
    }//GEN-LAST:event_loginSenhaActionPerformed

    private void btLogar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btLogar1ActionPerformed

    private void btLogar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLogar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btLogar1KeyTyped

    private void loginHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginHostActionPerformed
        if(loginHost.getSelectedItem().equals("Escolher outro...")){
            loginHost.setEditable(true);
            loginHost.setSelectedItem("");
        }
        else
            loginHost.setEditable(false);
    }//GEN-LAST:event_loginHostActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        
        
            try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e){
                System.err.println("Erro ao tentar aplicar o look and feel");
            }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
                telaAcesso dialog = new telaAcesso(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(EXIT_ON_CLOSE);
                    }
                });

                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogar;
    private javax.swing.JButton btSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTurno;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelUsuario1;
    public static javax.swing.JFormattedTextField loginData;
    public static javax.swing.JComboBox loginHost;
    public static javax.swing.JTextField loginMatricula;
    public static javax.swing.JPasswordField loginSenha;
    public static javax.swing.JComboBox loginTurno;
    public static javax.swing.JComboBox loginUsuario;
    // End of variables declaration//GEN-END:variables

public void loginSistema(){

        validaLogin.HOST = telaAcesso.loginHost.getSelectedItem().toString();

        con_inicial = new conexao();
        
        conectado = con_inicial.conecta();

        if(conectado == false){
            loginHost.setSelectedItem("");
            return;
        }

        
        //valida os campos preenchidos ou nao
        validaLogin.validaCampos();

        //se der tudo ok entra neste if
        if(validaLogin.valida == true){

            try{

            //faz a conexao com o banco e osiciona no primeiro registro
            con_inicial.executeSQL("select * from usuario where matricula_usuario = "+loginMatricula.getText());
            con_inicial.resultset.first();


            //pega nome, senha e situação do banco e a senha digitada na tela de login para validações
            nome = con_inicial.resultset.getString("nome_usuario");
            senhaUs = con_inicial.resultset.getString("senha_usuario");
            situacao = con_inicial.resultset.getString("situacao");
            tipoUsuario = con_inicial.resultset.getString("tipo_de_usuario");
            
            String usuarioSelecionado = loginUsuario.getSelectedItem().toString();
            criptografaSenha(loginSenha.getText());


                // se o gerente estiver bloqueado
                if(situacao.equals("Bloqueado") && tipoUsuario.equals("Gerente")){
                    JOptionPane.showMessageDialog(null, "Esta matrícula está bloqueada. Contate o Administrador!");
                    return;
                }
                else if(situacao.equals("Bloqueado") && tipoUsuario.equals("Administrador")){
                    JOptionPane.showMessageDialog(null, "Por segurança não está permitido a entrada");
                    return;
                }
                // se o operador estiver bloqueado
                else if(situacao.equals("Bloqueado") && tipoUsuario.equals("Operador")){
                    JOptionPane.showMessageDialog(null, "Esta matrícula está bloqueada. Fale com o Gerente!");
                    return;
                }
            
                //se a senha digitada for igual a senha do banco (entra) (ambas criptografadas)
                else if(senhaCriptografada.equals(senhaUs) && usuarioSelecionado.equals(tipoUsuario)){

                        //bom serviço
                        JOptionPane.showMessageDialog(null, "Bom serviço");
                                                
                        //se tudo der certo guarda os dados da entrada na memória e logo abaixo na banco de dados
                        validaLogin.guardaDados();
                        guardaLogon();//no bd

                        //ajeita interface de acordo com o usuário
                        validaLogin.configuraInterface(validaLogin.USUARIO);
                        
                        //mostra o usuário do sistema
                        telaPrincipal.lbUsuario.setText(tipoUsuario+": "+nome);
                        telaPrincipal.nomeHost.setText(ipHost);

                        //desconecta a conexao feita
                        con_inicial.desconecta();

                        //fecha a tela
                        dispose();
                }
                //senao
                else{
                    cont--;
                    
                    if(cont != 0){
                            //se senha é igual o tipo de usuário é incompatível!
                            if(senhaCriptografada.equals(senhaUs)){
                                JOptionPane.showMessageDialog(null, "Tipo de usuário não está correto. Você só tem "+cont+" chance(s) para sua matrícula não ser bloqueada!");
                                loginUsuario.requestFocus();
                                loginSenha.setText("");
                            }
                            //se senha não estiver certa
                            else{
                                JOptionPane.showMessageDialog(null, "Senha incorreta. Você só tem "+cont+" chance(s) para sua matrícula não ser bloqueada");
                                loginSenha.setText("");
                                loginSenha.requestFocus();
                            }

                    }
                    //se o numero de vezes for igual a zero bloqueia
                    else{
                            if(tipoUsuario.equals("Administrador"))
                                JOptionPane.showMessageDialog(null, "Por segurança sua matricula foi bloqueada. Contate o Administrador!");

                            // se o gerente estiver bloqueado
                            else if(tipoUsuario.equals("Gerente"))
                                JOptionPane.showMessageDialog(null, "Por segurança sua matricula foi bloqueada. Contate o Administrador!");

                            // se o operador estiver bloqueado
                            else if(tipoUsuario.equals("Operador"))
                                JOptionPane.showMessageDialog(null, "Por segurança sua matricula foi bloqueada. Fale com o Gerente!");

                            String sql = "UPDATE usuario SET situacao = 'Bloqueado' where matricula_usuario = "+loginMatricula.getText();

                            con_inicial.statement.executeUpdate(sql);
                            con_inicial.desconecta();
                            organizaTela();

                    }

                }

            }
            catch(Exception e){

                JOptionPane.showMessageDialog(null, "Matrícula inexistente");
                loginMatricula.setText("");
                loginSenha.setText("");
                loginMatricula.requestFocus();
            }


        }
        else
            return;

    }//fim login sistema

public void organizaTela(){

    loginHost.setSelectedIndex(0);
    loginUsuario.setSelectedItem("");
    loginMatricula.setText("");
    loginSenha.setText("");
    loginData.setText("");
    loginTurno.setSelectedItem("");
    loginHost.requestFocus();


}

public void guardaLogon(){
//pega informações do pc: nome e ip do host que acessa
hostLogin();

String sqlInsert = "insert into log_usuario(matricula_usuario" +
            ", tipo_usuario" +
            ", situacao_usuario" +
            ", host_login" +
            ", turno_login" +
            ", data_login" +
            ", hora_login" +
            ", data_logout" +
            ", hora_logout" +
            ", situacao_logout)" +
            " values('"+validaLogin.MATRICULA+"'"+//matricula
            ", '"+validaLogin.USUARIO+"'"+ //tipo de usuario
            ", '"+situacao+"'"+//situacao
            ", '"+ipHost+"'"+ //ip do host que acessa
            ", '"+validaLogin.TURNO+"'"+ //turno login
            ", '"+validaLogin.DATAFORMAT+"'"+//data login
            ", '"+validaLogin.HORA+"'" +//hora login
            ", '0000-00-00'" + //data logout
            ", '00:00:00'" + //hora logout
            ", 'Em execução...')";//situação logout

    try{
            con_inicial.statement.executeUpdate(sqlInsert);

            con_inicial.executeSQL("select num_log from log_usuario");
            con_inicial.resultset.last();

            validaLogin.NUM_LOG_ATUAL = Integer.parseInt(con_inicial.resultset.getString("num_log"));

    }
    catch(Exception e){
        System.out.print("Informações de login não poderam ser gravadas\nErro: "+e);
    }

}

public static void guardaLogout(){
    Data.le_data();
    Data.le_hora();

    //pega o numero de login atual
    try{

    con_logout = new conexao();
    con_logout.conecta();
    con_logout.executeSQL("select * from log_usuario");

    //salva a saida do usuario
    String sql = "UPDATE log_usuario SET data_logout = '"+Data.dataAtualBD+"'" +
                                    ", hora_logout = '"+Data.horaAtual+"'" +
                                    ", situacao_logout = '"+validaLogin.SITUACAO_LOGOUT+"'"+
                                    " where num_log = "+validaLogin.NUM_LOG_ATUAL;

    con_logout.statement.executeUpdate(sql);
    
    if(validaLogin.USUARIO.equals("Gerente")){

        //faz o backup quando o gerente sair
        realizaBackup();
    }

    //desconecta
    con_logout.desconecta();

    }
    catch(Exception e){
        System.out.print("Informações de logout não poderam ser gravadas\nErro: "+e);
    }
    }

public void hostLogin(){

try {
 InetAddress myself = InetAddress.getLocalHost();

 nomeHost = myself.getHostName();
 ipHost = myself.getHostAddress();

}
catch (UnknownHostException e) {
    e.printStackTrace();
}

}

public void criptografaSenha(String senha){

    con_inicial.executeSQL("select password('"+senha+"') as 'senha_criptografada'");
    try{
        con_inicial.resultset.first();

        senhaCriptografada = con_inicial.resultset.getString("senha_criptografada");
       
    }
    catch(Exception e){
        System.out.println("A senha não pode ser criptografada.\nErro: "+e);

    }

}

 public static void realizaBackup(){

        auxiliar.Data.le_data();

        int contador = 0;
        String sql;

        String path = "C:/backup_serquip/";

        String dataBackup = auxiliar.Data.ANO+"."+auxiliar.Data.MES+"."+auxiliar.Data.DIA;

        diretorioBackup = path+dataBackup;

                //cria um diretório para salvar o backup
                criarDiretorio();

                //se o diretório existir
                if(existe == true){
                    do{

                    contaDiretorio++;

                    criarDiretorio();

                    }while(existe == true);

                 }

                        //BACKUP DO REGISTRO DE CLIENTES
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/cliente.xml' from cliente");
                        //BACKUP DO REGISTRO DE PESAGEM DE BOMBONAS
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/pesagem.xml' from cad_bombona");
                        //BACKUP DO REGISTRO DE INCINERAÇÃO
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/incineracao.xml' from incineracao");
                        //BACKUP DO REGISTRO DE USUÁRIOS
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/usuario.xml' from usuario");
                        //BACKUP DO REGISTRO DE INFORMAÇÕES DE SUPORTE
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/suporte.xml' from info_suporte");
                        //BACKUP DO REGISTRO DE USUÁRIOS
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/logs.xml' from log_usuario");
                        //BACKUP DO REGISTRO DE CIDADES
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/cidades.xml' from log_usuario");
                        //BACKUP DO REGISTRO DE BAIRROS
                        con_logout.executeSQL("select * into outfile '"+diretorio+"/bairros.xml' from log_usuario");

    }

    public static void criarDiretorio(){

        File dir = new File(diretorioBackup+"_"+contaDiretorio);

        existe = dir.exists();


        if (dir.mkdir())
            diretorio = diretorioBackup+"_"+contaDiretorio;

    }

}

