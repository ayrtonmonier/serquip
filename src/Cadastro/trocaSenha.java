/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * trocaSenha.java
 *
 * Created on 19/01/2010, 18:34:05
 */

package Cadastro;
import auxiliar.conexao;
import javax.swing.JOptionPane;
import auxiliar.validaLogin;
/**
 *
 * @author ayrton monier
 */
public class trocaSenha extends javax.swing.JDialog {

    private conexao con_trocaSenha;
    private boolean grava;
    /** Creates new form trocaSenha */
    public trocaSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        con_trocaSenha = new conexao();
        con_trocaSenha.conecta();
        con_trocaSenha.executeSQL("select * from usuario");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        matricula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        confSenha = new javax.swing.JPasswordField();
        btEditarMatricula = new javax.swing.JButton();
        operador = new javax.swing.JTextField();
        contaConfSenha = new javax.swing.JLabel();
        contaSenha = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trocar senha - Sistema Serquip 1.0");
        setIconImage(null);
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/senha.png"))); // NOI18N
        jLabel1.setText("Trocar senha de segurança");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok22.png"))); // NOI18N
        jButton1.setToolTipText("Confirmar troca de senha");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar22.png"))); // NOI18N
        jButton2.setToolTipText("Cancelar troca de senha");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setText("Nome:");

        jLabel5.setText("Matrícula :");

        matricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculaActionPerformed(evt);
            }
        });

        jLabel4.setText("Confimar Senha:");

        confSenha.setEditable(false);
        confSenha.setFont(new java.awt.Font("Tahoma", 1, 14));
        confSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confSenhaKeyReleased(evt);
            }
        });

        btEditarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editaCampo.png"))); // NOI18N
        btEditarMatricula.setToolTipText("Editar matrícula");
        btEditarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarMatriculaActionPerformed(evt);
            }
        });

        operador.setEditable(false);

        contaConfSenha.setText("(0)");

        contaSenha.setText("(0)");

        senha.setEditable(false);
        senha.setFont(new java.awt.Font("Tahoma", 1, 14));
        senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaActionPerformed(evt);
            }
        });
        senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                senhaKeyReleased(evt);
            }
        });

        jLabel2.setText("Nova Senha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operador, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditarMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(senha)
                            .addComponent(confSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contaConfSenha)
                            .addComponent(contaSenha))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btEditarMatricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(operador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contaSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(senha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contaConfSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(confSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Obs: A senha deve conter 8 números.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaActionPerformed

    private void matriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculaActionPerformed
        pesquisaUsuario();
    }//GEN-LAST:event_matriculaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gravaNovaSenha();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int opcao_escolhida = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);

       if(opcao_escolhida == JOptionPane.YES_OPTION){
            con_trocaSenha.desconecta();
            dispose();
       }
       else
           return;

    }//GEN-LAST:event_jButton2ActionPerformed

    private void senhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaKeyReleased
        if(senha.getText().toString().length() > 8){
            senha.setText(senha.getText().substring(0, 8));
            return;
        }
        else if(senha.getText().isEmpty())
            contaSenha.setText("(0)");
        else
            contaSenha.setText("("+senha.getText().toString().length()+")");

    }//GEN-LAST:event_senhaKeyReleased

    private void confSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confSenhaKeyReleased
        if(confSenha.getText().toString().length() > 8){
            confSenha.setText(confSenha.getText().substring(0, 8));
            return;
        }
        else if(confSenha.getText().isEmpty())
            contaConfSenha.setText("(0)");
        else
            contaConfSenha.setText("("+confSenha.getText().toString().length()+")");
    }//GEN-LAST:event_confSenhaKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       int opcao_escolhida = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);

       if(opcao_escolhida == JOptionPane.YES_OPTION){
            con_trocaSenha.desconecta();
            dispose();
       }
       else
           return;
    }//GEN-LAST:event_formWindowClosing

    private void btEditarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarMatriculaActionPerformed
                matricula.setEditable(true);
                matricula.selectAll();
                operador.setText("");
                senha.setText("");
                confSenha.setText("");
               
                senha.setEditable(false);
                confSenha.setEditable(false);

                matricula.requestFocus();

                contaSenha.setText("(0)");
                contaConfSenha.setText("(0)");

    }//GEN-LAST:event_btEditarMatriculaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                trocaSenha dialog = new trocaSenha(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btEditarMatricula;
    public javax.swing.JPasswordField confSenha;
    private javax.swing.JLabel contaConfSenha;
    private javax.swing.JLabel contaSenha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField matricula;
    public javax.swing.JTextField operador;
    public javax.swing.JPasswordField senha;
    // End of variables declaration//GEN-END:variables

public void pesquisaUsuario(){

            

            //se o campo de matrícula estiver vazio
            if(matricula.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Digite a matrícula", "Aviso", JOptionPane.WARNING_MESSAGE);
                matricula.setText("");
                matricula.setEditable(true);
                matricula.requestFocus();
                operador.setText("");
            }
            
            else{
                    try{

                    //testa para ver se so tem números para nao dar erro sql
                        
                    Integer.parseInt(matricula.getText());

                    con_trocaSenha.executeSQL("select * from usuario where matricula_usuario = "+matricula.getText());
                    con_trocaSenha.resultset.first();


                    //pega tipo de usuario e situacao
                    String tipoUser = con_trocaSenha.resultset.getString("tipo_de_usuario");
                    String situacao = con_trocaSenha.resultset.getString("situacao");

                    //se o tipo de usuario for administrador nao podera mudar a senha
                    if(tipoUser.equals("Administrador") && (validaLogin.USUARIO.equals("Gerente") || validaLogin.USUARIO.equals("Operador"))){
                        JOptionPane.showMessageDialog(null, "Esta matrícula é do administrador e nao permite modificação de senha se não pelo mesmo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        matricula.setText("");
                        matricula.setEditable(true);
                        matricula.requestFocus();
                        operador.setText("");
                    }

                    //se tipo de usuario for operador e situacao for bloqueado nao pode mudar senha
                    else if(tipoUser.equals("Operador") && situacao.equals("Bloqueado")){

                        JOptionPane.showMessageDialog(null, "Usuário bloqueado não pode alterar senha", "Aviso", JOptionPane.WARNING_MESSAGE);
                        matricula.setText("");
                        matricula.setEditable(true);
                        matricula.requestFocus();
                        operador.setText("");

                    }

                    else{
                        //pega o nome, codigo e o total de bombona de contrato do cliente
                        operador.setText(con_trocaSenha.resultset.getString("nome_usuario"));

                        matricula.setEditable(false);
                        senha.setEditable(true);
                        senha.requestFocus();
                        confSenha.setEditable(true);
                    }

                    }//fim try
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Matrícula não confere, tente outra vez", "Aviso", JOptionPane.WARNING_MESSAGE);
                        matricula.setText("");
                        matricula.setEditable(true);
                        matricula.requestFocus();
                        operador.setText("");
                    }
         }//fim else

}

public void verificaIncompatibilidade(){
    grava = false;
    String senha1, senha2;
    senha1 = senha.getText();
    senha2 = confSenha.getText();

    if(matricula.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Preencha o campo de matrícula", "Aviso", JOptionPane.WARNING_MESSAGE);
        matricula.requestFocus();
        grava = false;
        return;
    }

    if(senha1.isEmpty() || senha1.length() < 8){
        JOptionPane.showMessageDialog(null, "Preencha o campo de senha (8 números)", "Aviso", JOptionPane.WARNING_MESSAGE);
        grava = false;
        senha.requestFocus();
        return;
    }
    else if(senha2.isEmpty() || senha2.length() < 8){
        JOptionPane.showMessageDialog(null, "Preencha o campo de confirmação de senha (8 números)", "Aviso", JOptionPane.WARNING_MESSAGE);
        grava = false;
        confSenha.requestFocus();
        return;
    }

    if(senha1.equals(senha2))
        grava = true;
    
    else{
      JOptionPane.showMessageDialog(null, "Senhas incompatíveis", "Aviso", JOptionPane.WARNING_MESSAGE);
          senha.setText("");
          confSenha.setText("");
          senha.requestFocus();
          contaSenha.setText("(0)");     //ayrtonmonier@hotmail.com
          contaConfSenha.setText("(0)");
          grava = false;
          return;


    }
}

public void gravaNovaSenha(){

   verificaIncompatibilidade();

   if(grava == true){
   try{
        Integer.parseInt(senha.getText());

        String sql = "UPDATE usuario SET senha_usuario = PASSWORD('"+senha.getText()+"')"+" where matricula_usuario = "+matricula.getText();
   
        int atualizado = con_trocaSenha.statement.executeUpdate(sql);

        if(atualizado == 1){
            JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso");
            configuracoesDeUsuario.senhaAtualizada = true;
            dispose();
        }
   }

   catch(Exception e){

        JOptionPane.showMessageDialog(null, "A senha deverá ser composta somente por números!", "Aviso", JOptionPane.WARNING_MESSAGE);
        senha.setText("");
        confSenha.setText("");
        senha.requestFocus();
   }
   }
}

}
