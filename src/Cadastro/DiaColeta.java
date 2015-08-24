/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DiaColeta.java
 *
 * Created on 19/10/2009, 16:36:55
 */

package Cadastro;
import javax.swing.*;
import auxiliar.validaCadCli;
/**
 *
 * @author ayrton monier
 */
public class DiaColeta extends javax.swing.JDialog {

public static String diasColeta[];
public static int n;
public static int modificado = 1;//usado para saber se o dia de coleta "outro dia de coleta" foi confirmado ou nao 0 = nao confirmado, 1= confirmado

    /** Creates new form DiaColeta */
    public DiaColeta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        diasColeta = new String[25];


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPeriodo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cb_segunda = new javax.swing.JCheckBox();
        cb_terca = new javax.swing.JCheckBox();
        cb_quarta = new javax.swing.JCheckBox();
        cb_quinta = new javax.swing.JCheckBox();
        cb_sexta = new javax.swing.JCheckBox();
        cb_sabado = new javax.swing.JCheckBox();
        cb_domingo = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        confirma = new javax.swing.JButton();
        cancela = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rbDezEmDez = new javax.swing.JRadioButton();
        rbAlternada = new javax.swing.JRadioButton();
        rbDiario = new javax.swing.JRadioButton();
        rbQuinzenal = new javax.swing.JRadioButton();
        confirmarOutro = new javax.swing.JButton();
        ta_outro = new javax.swing.JTextField();
        editarOutro = new javax.swing.JButton();
        rbOutro = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dia de coleta - Sistema Serquip 1.0");
        setIconImage(null);
        setIconImages(null);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dias de coleta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        cb_segunda.setText("Segunda-feira"); // NOI18N
        cb_segunda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_segundaActionPerformed(evt);
            }
        });

        cb_terca.setText("Terça-feira"); // NOI18N
        cb_terca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tercaActionPerformed(evt);
            }
        });

        cb_quarta.setText("Quarta-feira"); // NOI18N
        cb_quarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_quartaActionPerformed(evt);
            }
        });

        cb_quinta.setText("Quinta-feira"); // NOI18N
        cb_quinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_quintaActionPerformed(evt);
            }
        });

        cb_sexta.setText("Sexta-feira"); // NOI18N
        cb_sexta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_sextaActionPerformed(evt);
            }
        });

        cb_sabado.setText("Sábado"); // NOI18N
        cb_sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_sabadoActionPerformed(evt);
            }
        });

        cb_domingo.setText("Domingo"); // NOI18N
        cb_domingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_domingoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_segunda)
                    .addComponent(cb_terca)
                    .addComponent(cb_quarta))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_quinta)
                        .addGap(26, 26, 26)
                        .addComponent(cb_domingo))
                    .addComponent(cb_sexta)
                    .addComponent(cb_sabado))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_segunda)
                    .addComponent(cb_quinta)
                    .addComponent(cb_domingo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_terca)
                    .addComponent(cb_sexta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_quarta)
                    .addComponent(cb_sabado))
                .addGap(87, 87, 87))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Escolha os dias da semana específicos de coleta "); // NOI18N

        jLabel2.setText("ou escolha um período de datas fixas. "); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/calendario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        confirma.setToolTipText("Confirmar dias de coleta");
        confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaActionPerformed(evt);
            }
        });

        cancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cancelar.png"))); // NOI18N
        cancela.setToolTipText("Cancelar");
        cancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datas fixas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 14));

        bgPeriodo.add(rbDezEmDez);
        rbDezEmDez.setText("Dias 5, 15 e 25 de cada mês"); // NOI18N
        rbDezEmDez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDezEmDezActionPerformed(evt);
            }
        });

        bgPeriodo.add(rbAlternada);
        rbAlternada.setText("Alternada (dia sim dia não)"); // NOI18N
        rbAlternada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlternadaActionPerformed(evt);
            }
        });

        bgPeriodo.add(rbDiario);
        rbDiario.setText("Diário (domingo a domingo)"); // NOI18N
        rbDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDiarioActionPerformed(evt);
            }
        });

        bgPeriodo.add(rbQuinzenal);
        rbQuinzenal.setText("Dias 15 e 30 de cada mês "); // NOI18N
        rbQuinzenal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbQuinzenalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbQuinzenal)
                    .addComponent(rbDezEmDez))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbDiario)
                    .addComponent(rbAlternada))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbQuinzenal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbDezEmDez))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbAlternada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbDiario)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirmarOutro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok22.png"))); // NOI18N
        confirmarOutro.setToolTipText("Confirmar este dia de coleta");
        confirmarOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarOutroActionPerformed(evt);
            }
        });

        ta_outro.setEditable(false);
        ta_outro.setFont(new java.awt.Font("Tahoma", 1, 14));
        ta_outro.setForeground(new java.awt.Color(255, 0, 51));
        ta_outro.setBorder(javax.swing.BorderFactory.createTitledBorder("Outro dia de coleta"));
        ta_outro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ta_outroActionPerformed(evt);
            }
        });

        editarOutro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editaCampo.png"))); // NOI18N
        editarOutro.setToolTipText("Editar campo outro dia de coleta");
        editarOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarOutroActionPerformed(evt);
            }
        });

        bgPeriodo.add(rbOutro);
        rbOutro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOutroActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/minus white.png"))); // NOI18N
        jButton1.setToolTipText("Reiniciar escolha");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancela))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(rbOutro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ta_outro, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmarOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarOutro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editarOutro)
                    .addComponent(confirmarOutro)
                    .addComponent(ta_outro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbOutro))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancela)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirma, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaActionPerformed
        //modificado = 1 (modificado e confirmado)
        //modificado = 0 (modificado e nao confirmado)
        
        if(modificado == 1)//se for igual a 1 é pq o campo de "outro dia de coleta "nao foi modificado ou foi modificado e confirmado em seguida e então so fecha
            this.setVisible(false);

        else{//foi modificado e nao confirmado (modificado = 0). obs: se o campo "outro dia de coleta" foi modificado e nao confirmado a variável modificado recebera 0.

        //pergunta se deseja confirmar a modificação
        int op =  JOptionPane.showConfirmDialog(null, "Outro dia de coleta não foi confirmado!\n\nDeseja confirmar?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            //se clicar em sim...
            if(op == JOptionPane.YES_OPTION){
                //...se o campo "outro dia de coleta" estiver vazio ele pede para preecher e põe o foco so no ponto
                if(ta_outro.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha o campo antes de confirmar!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    ta_outro.requestFocus();
                }
                else{
                    //se preechido corretamente ele leva o valor para tela de clientes...
                    CadastroCliente.diaColeta.setText(ta_outro.getText());
                    //...não deixa editar mais o campo...
                    ta_outro.setEditable(false);
                    //... a variável modificado fica com valor 1 (modificado e confirmado)...
                    modificado = 1;
                    //...mensagem de confirmado...
                    JOptionPane.showMessageDialog(null,"Outro dia de coleta confirmado");
                    //...fecha.
                    dispose();
                }
            }
            //se clicar em nao...
            else
                //voltao foco para o campo "outro dia de coleta"
                ta_outro.requestFocus();
                return;

        }
    }//GEN-LAST:event_confirmaActionPerformed

    private void rbOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOutroActionPerformed
             modificado = 0;
             ta_outro.setEditable(true);
             ta_outro.requestFocus();
             validaCadCli.limpa_cb();//limpa os check box
             validaCadCli.limpaDiaColeta = true;

            if(rbQuinzenal.isSelected())
                validaCadCli.mostraDiaColeta("outro");

            else
                validaCadCli.retiraDiaColeta("outro");

    }//GEN-LAST:event_rbOutroActionPerformed

    private void rbQuinzenalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbQuinzenalActionPerformed
        ta_outro.setEditable(false);
        ta_outro.setText("");
        validaCadCli.limpa_cb();
        validaCadCli.limpaDiaColeta = true;//se clicar em qualquer checbox ele vai limpar o campo dia coleta(cliente) e colocar o calor do check box
        DiaColeta.modificado = 1;//FOI MODIFICADO e confirmado

        if(rbQuinzenal.isSelected())
            validaCadCli.mostraDiaColeta("quinzenal");
        else
            validaCadCli.retiraDiaColeta("quinzenal");


    }//GEN-LAST:event_rbQuinzenalActionPerformed

    private void rbDezEmDezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDezEmDezActionPerformed
        ta_outro.setEditable(false);
        ta_outro.setText("");
        validaCadCli.limpa_cb();
        validaCadCli.limpaDiaColeta = true;
        DiaColeta.modificado = 1;//FOI MODIFICADO e confirmado

        if(rbDezEmDez.isSelected())
            validaCadCli.mostraDiaColeta("DezEmDez");
        else
            validaCadCli.retiraDiaColeta("DezEmDez");

    }//GEN-LAST:event_rbDezEmDezActionPerformed

    private void rbAlternadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlternadaActionPerformed
        ta_outro.setEditable(false);
        ta_outro.setText("");
        validaCadCli.limpa_cb();
        validaCadCli.limpaDiaColeta = true;
        DiaColeta.modificado = 1;//FOI MODIFICADO e confirmado

        if(rbAlternada.isSelected())
            validaCadCli.mostraDiaColeta("alternada");
        else
            validaCadCli.retiraDiaColeta("alternada");
    }//GEN-LAST:event_rbAlternadaActionPerformed

    private void rbDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDiarioActionPerformed
        ta_outro.setEditable(false);
        ta_outro.setText("");
        validaCadCli.limpa_cb();
        validaCadCli.limpaDiaColeta = true;
        DiaColeta.modificado = 1;//FOI MODIFICADO e confirmado

        if(rbDiario.isSelected())
            validaCadCli.mostraDiaColeta("diario");
        else
            validaCadCli.retiraDiaColeta("diario");
    }//GEN-LAST:event_rbDiarioActionPerformed

    private void cb_segundaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_segundaActionPerformed
        validaCadCli.limpa_rb();

        if(cb_segunda.isSelected())
            validaCadCli.mostraDiaColeta("seg.");
        else
            validaCadCli.retiraDiaColeta("seg.");


    }//GEN-LAST:event_cb_segundaActionPerformed

    private void cb_tercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tercaActionPerformed
        validaCadCli.limpa_rb();

        if(cb_terca.isSelected())
            validaCadCli.mostraDiaColeta("ter.");
        else
            validaCadCli.retiraDiaColeta("ter.");

    }//GEN-LAST:event_cb_tercaActionPerformed

    private void cb_quartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_quartaActionPerformed
        validaCadCli.limpa_rb();

        if(cb_quarta.isSelected())
            validaCadCli.mostraDiaColeta("qua.");
        else
            validaCadCli.retiraDiaColeta("qua.");

    }//GEN-LAST:event_cb_quartaActionPerformed

    private void cb_quintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_quintaActionPerformed
        validaCadCli.limpa_rb();

        if(cb_quinta.isSelected())
            validaCadCli.mostraDiaColeta("qui.");
        else
            validaCadCli.retiraDiaColeta("qui.");

    }//GEN-LAST:event_cb_quintaActionPerformed

    private void cb_sextaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_sextaActionPerformed
        validaCadCli.limpa_rb();

        if(cb_sexta.isSelected())
            validaCadCli.mostraDiaColeta("sex.");
        else
            validaCadCli.retiraDiaColeta("sex.");

    }//GEN-LAST:event_cb_sextaActionPerformed

    private void cb_sabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_sabadoActionPerformed
        validaCadCli.limpa_rb();

        if(cb_sabado.isSelected())
            validaCadCli.mostraDiaColeta("sab.");
        else
            validaCadCli.retiraDiaColeta("sab.");

    }//GEN-LAST:event_cb_sabadoActionPerformed

    private void cb_domingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_domingoActionPerformed
        validaCadCli.limpa_rb();

        if(cb_domingo.isSelected())
            validaCadCli.mostraDiaColeta("dom.");
        else
            validaCadCli.retiraDiaColeta("dom.");

    }//GEN-LAST:event_cb_domingoActionPerformed

    private void confirmarOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarOutroActionPerformed
        if(ta_outro.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha o campo antes de confirmar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            ta_outro.requestFocus();
        }
        else{
            CadastroCliente.diaColeta.setText(ta_outro.getText());
            ta_outro.setEditable(false);
            modificado = 1;//modificado e confirmado
            JOptionPane.showMessageDialog(null,"Dia de coleta atualizado");
        }
    }//GEN-LAST:event_confirmarOutroActionPerformed

    private void ta_outroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ta_outroActionPerformed
        CadastroCliente.diaColeta.setText(ta_outro.getText());
        ta_outro.setEditable(false);
        modificado = 1;//mofificado e confimado
        JOptionPane.showMessageDialog(null, "Dia de coleta atualizado");
    }//GEN-LAST:event_ta_outroActionPerformed

    private void editarOutroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarOutroActionPerformed
        modificado = 0;//modificado e nao confirmado
        ta_outro.setEditable(true);
        ta_outro.selectAll();
        ta_outro.requestFocus();
    }//GEN-LAST:event_editarOutroActionPerformed

    private void cancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaActionPerformed
        dispose();
}//GEN-LAST:event_cancelaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(null,"Deseja limpar todas as seleções?", "Limpar seleções", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            validaCadCli.limpa_rb();
            validaCadCli.limpa_cb();
            CadastroCliente.diaColeta.setText("");
            modificado = 1;
        }
        else
            return;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiaColeta dialog = new DiaColeta(new javax.swing.JFrame(), true);
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
    public static javax.swing.ButtonGroup bgPeriodo;
    private javax.swing.JButton cancela;
    public static javax.swing.JCheckBox cb_domingo;
    public static javax.swing.JCheckBox cb_quarta;
    public static javax.swing.JCheckBox cb_quinta;
    public static javax.swing.JCheckBox cb_sabado;
    public static javax.swing.JCheckBox cb_segunda;
    public static javax.swing.JCheckBox cb_sexta;
    public static javax.swing.JCheckBox cb_terca;
    private javax.swing.JButton confirma;
    private javax.swing.JButton confirmarOutro;
    private javax.swing.JButton editarOutro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JRadioButton rbAlternada;
    public javax.swing.JRadioButton rbDezEmDez;
    public javax.swing.JRadioButton rbDiario;
    public javax.swing.JRadioButton rbOutro;
    public javax.swing.JRadioButton rbQuinzenal;
    public static javax.swing.JTextField ta_outro;
    // End of variables declaration//GEN-END:variables

}
