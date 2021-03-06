/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * configuracoes.java
 *
 * Created on 10/03/2010, 21:27:19
 */

package principal;

import javax.swing.text.MaskFormatter;
import javax.swing.*;
import auxiliar.conexao;
import auxiliar.validaLogin;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author ayrton monier
 */
public class configuracoes extends javax.swing.JFrame {

MaskFormatter formFone;
boolean grava;
static conexao con_suporte;
public static boolean existe;


public static String diretorioBackup, diretorio;
public static int contaDiretorio = 0;//usado para numeracao de diretórios

    /** Creates new form configuracoes */
    public configuracoes() {
        initComponents();

        con_suporte = new conexao();
        con_suporte.conecta();

        pegaInfoSuporte();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        emailSuporte = new javax.swing.JTextField();
        try{

            formFone = new MaskFormatter("(##)####-####");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível inserir a máscara no campo, erro: "+e);
        }
        telSuporte = new JFormattedTextField(formFone);
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cb_regUsuarios = new javax.swing.JCheckBox();
        cb_logUsuario = new javax.swing.JCheckBox();
        cb_regBombonas = new javax.swing.JCheckBox();
        cb_regCliente = new javax.swing.JCheckBox();
        cb_infoSuporte = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelDetalhes = new javax.swing.JTextPane();
        cb_regIcineracao = new javax.swing.JCheckBox();
        bt_backup = new javax.swing.JButton();
        cb_todosRegistros = new javax.swing.JCheckBox();
        cb_regCidades = new javax.swing.JCheckBox();
        cb_regBairros = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Configurações - Sistema Serquip 1.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel5.setText("E-mail:");

        jLabel4.setText("Telefone:");

        telSuporte.setFont(new java.awt.Font("Tahoma", 1, 14));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok22.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/email.png"))); // NOI18N
        jLabel3.setText("Atualizar informações de suporte");

        jButton3.setText("Limpar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(telSuporte, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton6))
                        .addComponent(emailSuporte, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                .addGap(200, 200, 200))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailSuporte, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(telSuporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(89, 89, 89))
        );

        jTabbedPane1.addTab("Suporte", jPanel4);

        cb_regUsuarios.setText("Registros de usuário");
        cb_regUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regUsuariosActionPerformed(evt);
            }
        });

        cb_logUsuario.setText("Registro de logs de usuário");
        cb_logUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_logUsuarioActionPerformed(evt);
            }
        });

        cb_regBombonas.setText("Registro de pesagem de bombonas");
        cb_regBombonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regBombonasActionPerformed(evt);
            }
        });

        cb_regCliente.setText("Registro de clientes");
        cb_regCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regClienteActionPerformed(evt);
            }
        });

        cb_infoSuporte.setText("Registro de Informações de susporte");
        cb_infoSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_infoSuporteActionPerformed(evt);
            }
        });

        painelDetalhes.setEditable(false);
        painelDetalhes.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(painelDetalhes);

        cb_regIcineracao.setText("Registro de incineração de resíduos");
        cb_regIcineracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regIcineracaoActionPerformed(evt);
            }
        });

        bt_backup.setText("Realizar backup");
        bt_backup.setEnabled(false);
        bt_backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_backupActionPerformed(evt);
            }
        });

        cb_todosRegistros.setText("Marcar todos");
        cb_todosRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_todosRegistrosActionPerformed(evt);
            }
        });

        cb_regCidades.setText("Registro de cidades");
        cb_regCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regCidadesActionPerformed(evt);
            }
        });

        cb_regBairros.setText("Registro de bairros");
        cb_regBairros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regBairrosActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/backup.png"))); // NOI18N
        jLabel1.setText("Selecione o(s) registro(s) que você deseja efetuar backup:");

        jLabel2.setText("Detalhes:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_regCliente)
                            .addComponent(cb_regIcineracao)
                            .addComponent(cb_regBombonas)
                            .addComponent(cb_regUsuarios)
                            .addComponent(cb_todosRegistros))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_logUsuario)
                            .addComponent(cb_regCidades)
                            .addComponent(cb_regBairros)
                            .addComponent(cb_infoSuporte)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(47, 47, 47)
                        .addComponent(bt_backup)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cb_regCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_regBombonas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_regIcineracao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_regUsuarios))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cb_infoSuporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_logUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_regCidades)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_regBairros)))
                .addGap(13, 13, 13)
                .addComponent(cb_todosRegistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bt_backup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Backup", jPanel5);

        jButton1.setText("Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //pergunta
        int opcao = JOptionPane.showConfirmDialog(null , "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);

        if(opcao == JOptionPane.YES_OPTION){
            //chama o metdo para fechar a conexao
            con_suporte.desconecta();
            dispose();
        } else
            return;


    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //pergunta
        int opcao = JOptionPane.showConfirmDialog(null , "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);

        if(opcao == JOptionPane.YES_OPTION){
            //chama o metdo para fechar a conexao
            con_suporte.desconecta();
            dispose();
        } else
            return;

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cb_regBairrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regBairrosActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regBairrosActionPerformed

    private void cb_regCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regCidadesActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regCidadesActionPerformed

    private void cb_todosRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_todosRegistrosActionPerformed

        if(cb_todosRegistros.isSelected() == false){
            selecionaCheckBox(false);
            cb_todosRegistros.setText("Marcar todos");
        } else{
            selecionaCheckBox(true);
            cb_todosRegistros.setText("Desmarcar todos");
        }

        segurançaBtBackup();
}//GEN-LAST:event_cb_todosRegistrosActionPerformed

    private void bt_backupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_backupActionPerformed
        realizaBackup();
}//GEN-LAST:event_bt_backupActionPerformed

    private void cb_regIcineracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regIcineracaoActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regIcineracaoActionPerformed

    private void cb_infoSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_infoSuporteActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_infoSuporteActionPerformed

    private void cb_regClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regClienteActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regClienteActionPerformed

    private void cb_regBombonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regBombonasActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regBombonasActionPerformed

    private void cb_logUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_logUsuarioActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_logUsuarioActionPerformed

    private void cb_regUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regUsuariosActionPerformed
        segurançaBtBackup();
}//GEN-LAST:event_cb_regUsuariosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        emailSuporte.setText("");
        telSuporte.setText("");
        emailSuporte.requestFocus();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        verificaCampos();
        atulizaInfoSup();
}//GEN-LAST:event_jButton6ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new configuracoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton bt_backup;
    private static javax.swing.JCheckBox cb_infoSuporte;
    private static javax.swing.JCheckBox cb_logUsuario;
    private static javax.swing.JCheckBox cb_regBairros;
    private static javax.swing.JCheckBox cb_regBombonas;
    private static javax.swing.JCheckBox cb_regCidades;
    private static javax.swing.JCheckBox cb_regCliente;
    private static javax.swing.JCheckBox cb_regIcineracao;
    private static javax.swing.JCheckBox cb_regUsuarios;
    private static javax.swing.JCheckBox cb_todosRegistros;
    private javax.swing.JTextField emailSuporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTextPane painelDetalhes;
    public static javax.swing.JFormattedTextField telSuporte;
    // End of variables declaration//GEN-END:variables

 public void atulizaInfoSup(){
        if(grava == true){
               auxiliar.Data.le_hora();
               try{



                    con_suporte.executeSQL("select * from info_suporte");

                    String sql = "UPDATE info_suporte SET email_suporte = '"+emailSuporte.getText()+"'" +
                                                          ", tel_suporte = '"+telSuporte.getText()+"'" +
                                                          ", data_atualizacao = '"+validaLogin.DATAFORMAT+"'" +
                                                          ", hora_atualizacao = '"+auxiliar.Data.horaAtual+"'"+
                                                          "where cod_suporte = 1";
                    int atualizado = con_suporte.statement.executeUpdate(sql);

                    if(atualizado == 1){
                        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                        pegaInfoSuporte();
                    }

                    

                }
                catch(Exception e){
                   System.out.print("Não foi possível atualizar informações de suporte\nErro: "+e);
                }
        }

    }
    public void verificaCampos(){

    if(emailSuporte.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Informe o email de suporte", "Aviso", JOptionPane.WARNING_MESSAGE);
        emailSuporte.requestFocus();
        grava = false;
    }

    else if(telSuporte.getText().equals("(__)________")){
        JOptionPane.showMessageDialog(null, "Informe o telefone para suporte", "Aviso", JOptionPane.WARNING_MESSAGE);
        telSuporte.requestFocus();
        grava = false;
    }
    else
        grava = true;

    }

    public void pegaInfoSuporte(){

    con_suporte.executeSQL("select * from info_suporte");


    try{
        con_suporte.resultset.first();

        String email = con_suporte.resultset.getString("email_suporte");
        String fone = con_suporte.resultset.getString("tel_suporte");

        emailSuporte.setText(email);
        telSuporte.setText(fone);

    }catch(Exception e ){


    }


    }//fim pega informações de suporte

    public static void selecionaCheckBox(boolean seleciona){

        cb_regCliente.setSelected(seleciona);
        cb_regBombonas.setSelected(seleciona);
        cb_regIcineracao.setSelected(seleciona);
        cb_regUsuarios.setSelected(seleciona);
        cb_regCidades.setSelected(seleciona);
        cb_infoSuporte.setSelected(seleciona);
        cb_logUsuario.setSelected(seleciona);
        cb_regBairros.setSelected(seleciona);
        cb_todosRegistros.setSelected(seleciona);

    }

    public static void realizaBackup(){

        auxiliar.Data.le_data();

        int contador = 0;
        String sql;

        String path = "C:/backup_serquip/";

        String dataBackup = auxiliar.Data.ANO+"."+auxiliar.Data.MES+"."+auxiliar.Data.DIA;

        diretorioBackup = path+dataBackup;

        painelDetalhes.setText("");


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
                        if(cb_regCliente.isSelected()){

                            sql = "select * into outfile '"+diretorio+"/cliente.xml' from cliente";

                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado clientes.xml em "+diretorioBackup+"\n");
                                contador++;
                        }

                        //BACKUP DO REGISTRO DE PESAGEM DE BOMBONAS
                        if(cb_regBombonas.isSelected()){

                                sql = "select * into outfile '"+diretorio+"/pesagem.xml' from cad_bombona";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado pesagem.xml em "+diretorioBackup+"\n");
                                contador++;

                        }

                        //BACKUP DO REGISTRO DE INCINERAÇÃO
                        if(cb_regIcineracao.isSelected()){


                                sql = "select * into outfile '"+diretorio+"/incineracao.xml' from incineracao";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado incineracao.xml em "+diretorioBackup+"\n");
                                contador++;

                        }

                        //BACKUP DO REGISTRO DE USUÁRIOS
                        if(cb_regUsuarios.isSelected()){



                                sql = "select * into outfile '"+diretorio+"/usuario.xml' from usuario";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado usuario.xml em "+diretorioBackup+"\n");
                                contador++;

                        }

                        //BACKUP DO REGISTRO DE INFORMAÇÕES DE SUPORTE
                        if(cb_infoSuporte.isSelected()){


                                sql = "select * into outfile '"+diretorio+"/suporte.xml' from info_suporte";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado suporte.xml em "+diretorioBackup+"\n");
                                contador++;

                         }

                        //BACKUP DO REGISTRO DE USUÁRIOS
                        if(cb_logUsuario.isSelected()){


                                sql = "select * into outfile '"+diretorio+"/logs.xml' from log_usuario";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado logs.xml em "+diretorioBackup+"\n");
                                contador++;

                        }

                        //BACKUP DO REGISTRO DE CIDADES
                        if(cb_regCidades.isSelected()){


                                sql = "select * into outfile '"+diretorio+"/cidades.xml' from cad_cidade";
                                con_suporte.executeSQL(sql);
                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado cidades.xml em "+diretorioBackup+"\n");
                                contador++;

                        }

                        //BACKUP DO REGISTRO DE BAIRROS
                        if(cb_regBairros.isSelected()){

                                sql = "select * into outfile '"+diretorio+"/bairros.xml' from cad_bairro";
                                con_suporte.executeSQL(sql);

                                painelDetalhes.setText(painelDetalhes.getText()+"*Criado bairros.xml em "+diretorioBackup+"\n");
                                contador++;
                        }


                            painelDetalhes.setText(painelDetalhes.getText()+"\nConcluído com sucesso: "+contador+ " registros foram guardados.");
                            JOptionPane.showMessageDialog(null, "Backup efetuado com sucesso!");

                            selecionaCheckBox(false);
                            segurançaBtBackup();
                

   

    }

    public static void criarDiretorio(){
        
        File dir = new File(diretorioBackup+"_"+contaDiretorio);

        existe = dir.exists();
        

        if (dir.mkdir())
            diretorio = diretorioBackup+"_"+contaDiretorio;
            painelDetalhes.setText(painelDetalhes.getText()+"Diretório criado com sucesso em "+diretorio+"\n\n");
            
    }
    
        
    

    public static void segurançaBtBackup(){

        if(cb_regCliente.isSelected() ||
           cb_regBairros.isSelected()||
           cb_infoSuporte.isSelected()||
           cb_logUsuario.isSelected() ||
           cb_regBombonas.isSelected() ||
           cb_regCidades.isSelected() ||
           cb_regIcineracao.isSelected() ||
           cb_regUsuarios.isSelected() ||
           cb_todosRegistros.isSelected())
                bt_backup.setEnabled(true);
        else
            bt_backup.setEnabled(false);
    }

}
