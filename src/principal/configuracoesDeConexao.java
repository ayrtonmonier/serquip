/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * configuracoesDeConexao.java
 *
 * Created on 11/02/2010, 18:08:52
 */

/*
Início do projeto: 05/09/2009
No dia 24 de fevereiro de 2010:

 -5 meses e 7 dias ou
 -172 dias ou
 -668 horas sendo 4 horas por dia


*/

package principal;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ayrton monier
 */
public class configuracoesDeConexao extends javax.swing.JDialog {

public static String driver;
public static String url;
public static String usuario;
public static String senha;
public static Connection conexao;
public static Statement statement;
public static ResultSet resultset;

    /** Creates new form configuracoesDeConexao */
    public configuracoesDeConexao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        btDesconecta.setVisible(false);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        btDesconecta = new javax.swing.JButton();
        btTestar = new javax.swing.JButton();
        senhaUsuario = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        baseDados = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        hostIp = new javax.swing.JTextField();
        nomeBanco = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nomeUsuario = new javax.swing.JTextField();
        driverBanco = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        porta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Teste de conxão Ayrton Monier");
        setResizable(false);

        jLabel8.setText("Status:");

        lbStatus.setText("Desconectado");

        btDesconecta.setText("Desconectar");
        btDesconecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesconectaActionPerformed(evt);
            }
        });

        btTestar.setText("Testar");
        btTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTestarActionPerformed(evt);
            }
        });

        jLabel7.setText("Base de dados:");

        jLabel6.setText("Host:");

        nomeBanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "MySQL" }));
        nomeBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeBancoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel5.setText("Driver:");

        jLabel3.setText("Usuário:");

        driverBanco.setEditable(false);

        jLabel2.setText("Porta:");

        jLabel4.setText("Senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(senhaUsuario)
                                .addComponent(nomeUsuario)
                                .addComponent(baseDados)
                                .addComponent(porta, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(driverBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hostIp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btTestar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(btDesconecta, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbStatus)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(driverBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hostIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(baseDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(senhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btTestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDesconecta)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void nomeBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeBancoActionPerformed
        if(nomeBanco.getSelectedItem().equals("MySQL"))
            driverBanco.setText("com.mysql.jdbc.Driver");
        
    }//GEN-LAST:event_nomeBancoActionPerformed

    private void btTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTestarActionPerformed
        

        String host,port, base, banco;

        banco = nomeBanco.getSelectedItem().toString().toLowerCase();
        host = hostIp.getText();
        port = porta.getText();
        base = baseDados.getText();

        driver = driverBanco.getText();
        usuario = nomeUsuario.getText();
        senha = senhaUsuario.getText();
        url = "jdbc:"+banco+"://"+host+":"+port+"/"+base;

        conecta();

    }//GEN-LAST:event_btTestarActionPerformed

    private void btDesconectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesconectaActionPerformed
        desconecta();
        btDesconecta.setVisible(false);
    }//GEN-LAST:event_btDesconectaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                configuracoesDeConexao dialog = new configuracoesDeConexao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField baseDados;
    private javax.swing.JButton btDesconecta;
    private javax.swing.JButton btTestar;
    private javax.swing.JTextField driverBanco;
    private javax.swing.JTextField hostIp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JComboBox nomeBanco;
    private javax.swing.JTextField nomeUsuario;
    private javax.swing.JTextField porta;
    private javax.swing.JPasswordField senhaUsuario;
    // End of variables declaration//GEN-END:variables

public boolean conecta(){

boolean result = true;

  try{
    //método foName vai carregar o driver de conexão
    Class.forName(driver);
    lbStatus.setText("Conectando a base de dados "+baseDados.getText()+"...");
    conexao = DriverManager.getConnection(url, usuario, senha);
    //JOptionPane.showMessageDialog(null, "CONECTADO COM SUCESSO!");
    lbStatus.setText("Conectado");
    btDesconecta.setVisible(true);
  }

  catch(ClassNotFoundException Driver){

    JOptionPane.showMessageDialog(null, "Driver não localizado: "+Driver, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;
    lbStatus.setText("Desconectado");
  }

  catch(SQLException erroSQL){

    JOptionPane.showMessageDialog(null, "Deu erro na conexão: "+erroSQL, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;
    lbStatus.setText("Desconectado");
  }

  return result;

}//fim da classe conecta

public void desconecta(){

boolean result = true;

    try{

    conexao.close();
    //JOptionPane.showMessageDialog(null, "DESCONECTADO DA BASE DE DADOS!");
    lbStatus.setText("Desconectado");

    }

    catch(SQLException fecha){

    JOptionPane.showMessageDialog(null, "Não foi possível fechar o banco de dados: "+fecha, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;
    }
}

}
