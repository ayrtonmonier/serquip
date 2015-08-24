/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CancelamentoCli.java
 *
 * Created on 20/10/2009, 16:54:32
 */

package Cadastro;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import auxiliar.validaCadCli;
import auxiliar.Data;
/**
 *
 * @author ayrton monier
 */
public class CancelamentoCli extends javax.swing.JDialog {
MaskFormatter formData;

    public CancelamentoCli(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Data.le_data();
        diaCancelamento.setText(Data.dataAtualJFormatted);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelSuspenso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        motivoCancelamento = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        labelSuspenso1 = new javax.swing.JLabel();
        labelSuspenso2 = new javax.swing.JLabel();
        contrato = new javax.swing.JTextField();
        cliente = new javax.swing.JTextField();
        try{
            formData = new MaskFormatter("##-##-####");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível inserir a máscara no campo, erro :"+e);
        }
        diaCancelamento = new JFormattedTextField(formData);
        jButton2 = new javax.swing.JButton();
        labelSuspensao = new javax.swing.JLabel();
        btTornarAtivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cancelamento de cliente - Sistema Serquip 1.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        btCancelar.setToolTipText("Confirmar cancelamento");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelSuspenso.setText("Cancelado dia:");

        motivoCancelamento.setColumns(20);
        motivoCancelamento.setFont(new java.awt.Font("Monospaced", 1, 14));
        motivoCancelamento.setRows(5);
        jScrollPane1.setViewportView(motivoCancelamento);

        jLabel2.setText("Motivo: ");

        labelSuspenso1.setText("Cliente:");

        labelSuspenso2.setText("Contrato:");

        contrato.setEditable(false);
        contrato.setFont(new java.awt.Font("Tahoma", 1, 14));

        cliente.setEditable(false);
        cliente.setFont(new java.awt.Font("Tahoma", 1, 12));

        diaCancelamento.setFont(new java.awt.Font("Tahoma", 1, 14));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSuspenso)
                    .addComponent(labelSuspenso2)
                    .addComponent(labelSuspenso1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(diaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contrato, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSuspenso2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSuspenso1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(diaCancelamento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSuspenso, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        jButton2.setToolTipText("Cancelar cancelamento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        labelSuspensao.setFont(new java.awt.Font("Tahoma", 1, 12));
        labelSuspensao.setForeground(new java.awt.Color(255, 0, 0));
        labelSuspensao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bloquear.png"))); // NOI18N
        labelSuspensao.setText("CANCELAMENTO DE CLIENTE");

        btTornarAtivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cli.png"))); // NOI18N
        btTornarAtivo.setText("Ativar cliente");
        btTornarAtivo.setToolTipText("Ativar cliente");
        btTornarAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTornarAtivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuspensao)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btTornarAtivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                        .addComponent(btCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSuspensao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btTornarAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        sairDaTela();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        if(validaCadCli.modificado == true){
            validaCadCli.verificaCamposCancelamento();

            //ve se os campos estao preenchidos
            if(validaCadCli.campoVazio == false){
                    //ver se os campos estao preenchidos
                    validaCadCli.gravaCancelamentoCli();

                    //se gravou no banco só fecha e regula variável
                    if(validaCadCli.gravadoNoBanco == true){
                        validaCadCli.gravadoNoBanco = false;//volta ao normal
                        dispose();
                    }
                    else
                        return;
            }
            else
              return;
        }//fim if(modificado)
        else
            dispose();

    }//GEN-LAST:event_btCancelarActionPerformed

    private void btTornarAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTornarAtivoActionPerformed
       int opcao = JOptionPane.showConfirmDialog(null, "Deseja tornar ativo este cliente?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

       if(opcao == JOptionPane.YES_OPTION){
           CadastroCliente.situacaoCli.setText("ATIVO");

           validaCadCli.zeraDatas_can_sus();
           validaCadCli.atualizar_ou_gravar();

                //se nao gravou no banco volta a situacao inicial
                if(validaCadCli.gravadoNoBanco == false){
                    CadastroCliente.situacaoCli.setText(validaCadCli.situacaoInicial);
                    return;
                }
                else{//se gravou fecha e regula variáveis
                   validaCadCli.modificado = false;
                   validaCadCli.gravadoNoBanco = false;
                   dispose();

                }



       }
       else
           return;
    }//GEN-LAST:event_btTornarAtivoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        sairDaTela();
    }//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CancelamentoCli dialog = new CancelamentoCli(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btCancelar;
    public javax.swing.JButton btTornarAtivo;
    public static javax.swing.JTextField cliente;
    public static javax.swing.JTextField contrato;
    public static javax.swing.JFormattedTextField diaCancelamento;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    protected static javax.swing.JLabel labelSuspensao;
    protected static javax.swing.JLabel labelSuspenso;
    protected static javax.swing.JLabel labelSuspenso1;
    protected static javax.swing.JLabel labelSuspenso2;
    public static javax.swing.JTextArea motivoCancelamento;
    // End of variables declaration//GEN-END:variables

    public void sairDaTela(){
        if(validaCadCli.modificado == true){
               int opcao = JOptionPane.showConfirmDialog(null, "Deseja salvar a modificação?", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
               //se clicou yes
               if(opcao == JOptionPane.YES_OPTION){
                        validaCadCli.verificaCamposCancelamento();

                    //ve se os campos estao preenchidos
                    if(validaCadCli.campoVazio == false){
                            validaCadCli.gravaCancelamentoCli();

                            //se gravou no banco: fecha
                            if(validaCadCli.gravadoNoBanco == true){
                                validaCadCli.gravadoNoBanco = false;//volta ao normal
                                dispose();
                            }
                            else
                                return;

                    }
                    else//campos nao preenchidos
                      return;
               }

               else if(opcao == JOptionPane.NO_OPTION){
                        validaCadCli.zeraDatas_can_sus();
                        CadastroCliente.situacaoCli.setText(validaCadCli.situacaoInicial);
                        dispose();
                        validaCadCli.atualizaResultset();
               }

               else
                 return;


        }
        else
            dispose();

}//FIM SAIRDATELA
}