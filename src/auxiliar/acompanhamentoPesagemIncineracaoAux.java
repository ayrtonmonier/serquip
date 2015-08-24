/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import RelatorioForm.acompanhamentoPesagemIncineracao;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
/**
 *
 * @author ayrton monier
 */
public class acompanhamentoPesagemIncineracaoAux {
    public static conexao con_acompanhamentoPesagemIncineracaoAux;
    public static String periodoInicial, periodoFinal, turno, maquina, residuo;
    public static boolean pesquise = true;//permissão para fazer a pesquisa
    public static String ordenacao = "num_seq_incineracao";
    public static String dataFormatadataJTable;

//construtor auxiliar
public static void iniciaComponentes(){

    //instanciando conexao
    con_acompanhamentoPesagemIncineracaoAux = new conexao();

    //conexao iniciada
    con_acompanhamentoPesagemIncineracaoAux.conecta();

    //inicia com um tipo padrao de pesquisa
    acompanhaDiaPeriodo();
}

public static void desconecta(){

con_acompanhamentoPesagemIncineracaoAux.desconecta();

}

    public static void preencherJtable(){

int x = 1;//criada para pegar o primeiro registro
//acompanhamentoPesagemIncineracao.tabelaInci.getColumnModel().getColumn(0).setPreferredWidth(20); ajusta largura das colunas

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) acompanhamentoPesagemIncineracao.tabelaInci.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

    try {

        do{
             //convert para dd-mm-aaaa a data do banco para a tabela do usuario
             formataDataBancoParaJTable(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("data_incineracao"));

             if(x <= 1)//só no primeiro loop pega o primeiro registro, depois do 1° vai pegando os próximos até quando tiver
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();

                //vai add linhas
                //equivale a uma linha na minha tabela Bombona
                modelo.addRow(new Object[]{con_acompanhamentoPesagemIncineracaoAux.resultset.getString("num_seq_incineracao"),//cod car
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("peso_incinerado"),//peso
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("tipo_residuo"),//tipo de resíduo
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("id_maquina"),//nmaqina
                                           dataFormatadataJTable,//data
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("turno_incineracao"),//turno
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("hora_incineracao"), //hora
                                           con_acompanhamentoPesagemIncineracaoAux.resultset.getString("matricula_usuario")});//matricula


             x++;//add mais um no x e nao chama mais o if para ir pro primeiro registro

        }while(con_acompanhamentoPesagemIncineracaoAux.resultset.next());//enquanto tiver dados preenche o jtable com os valores

        //depois que finaliza atualiza o resultset
        atualizaResultset();
    }

    catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Não consta dados nesta data/período ou com as especificações do filtro", "Aviso", JOptionPane.WARNING_MESSAGE);
            //para trhead
//            acompanhamentoPesagemIncineracao.tempoReal.stop();

            //bt Para fica inabilitado
//            acompanhamentoPesagemIncineracao.btPara.setEnabled(false);

            //bt atualiza fica inabilitado
            acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(false);

            //habilita botao imprimir
            acompanhamentoPesagemIncineracao.btImprimir.setEnabled(false);

            //bt confirmaBusca é habilitado
            acompanhamentoPesagemIncineracao.btConfirmaBusca.setEnabled(true);

            //bt Sair e habilitado
            acompanhamentoPesagemIncineracao.btSair.setEnabled(true);

            //btNovo relatorio e´habilitado
            acompanhamentoPesagemIncineracao.btNovoRelatorio.setEnabled(true);

    }

}//fim preencherJtable

//limpa a tabels
public static void limpaJtable(){

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) acompanhamentoPesagemIncineracao.tabelaInci.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

}//LIMPA JTABLE

    //atualiza o painel detalhado
    public static void atualizaPainelDetalhado(){

    String sql;

    try{


         //***********************DETALHAMENTO DAS MÁQUINAS *********************************

                //grava o total das maquinas
                sql = "select sum(peso_incinerado) as 'total incinerado'from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+")  and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalMaquinas.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total incinerado"));

                //grava o total biologico das maquinas
                sql = "select sum(peso_incinerado) as 'total biologico' from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalBiologico.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total biologico"));

                //grava o total industrial das maquinas
                sql = "select sum(peso_incinerado) as 'industrial' from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalIndustrial.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("industrial"));

                //grava o total de carrinhos industrial de todas as maquinas
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarIndMs.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //grava o total de carrinhos biologico de todas as maquinas
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarBioMs.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));


                //grava o total de carrinhos de todas as maquinas
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalCarrinhos.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));



            //***********************DETALHAMENTO MAQUINA 1 LITROS*********************************

                //GRAVA TOTAL DA MAQUINA 1 (BIOLÓGICO E INDUSTRIAL)
                sql = "select sum(peso_incinerado) as 'total incinerado' from incineracao where id_maquina = 1 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalMaquina1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total incinerado"));

                //PEGA O TOTAL BIOLOGICO DA MAQUINA 1
                sql = "select sum(peso_incinerado) as 'total biologico' from incineracao where id_maquina = 1 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalBiologico1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total biologico"));

                //PEGA O TOTAL INDUSTRIAL DA MAQUINA 1
                sql = "select sum(peso_incinerado) as 'industrial' from incineracao where id_maquina = 1 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalIndustrial1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("industrial"));

                //grava o total de carrinhos industrial DA MAQUINA 1
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 1) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarIndM1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //grava o total de carrinhos biologico DA MAQUINA 1
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 1) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarBioM1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));


                //PEGA O TOTAL DE CARRINHOS DA MAQUINA 1
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where id_maquina = 1 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalCarrinhos1.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

          //***********************DETALHAMENTO DA MÁQUINA 2*********************************


                //GRAVA TOTAL DA MAQUINA 2 (BIOLÓGICO E INDUSTRIAL)
                sql = "select sum(peso_incinerado) as 'total incinerado' from incineracao where id_maquina = 2 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalMaquina2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total incinerado"));

                //PEGA O TOTAL BIOLOGICO DA MAQUINA 2
                sql = "select sum(peso_incinerado) as 'total biologico' from incineracao where id_maquina = 2 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalBiologico2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total biologico"));

                //PEGA O TOTAL INDUSTRIAL DA MAQUINA 2
                sql = "select sum(peso_incinerado) as 'industrial' from incineracao where id_maquina = 2 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalIndustrial2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("industrial"));

                //grava o total de carrinhos industrial DA MAQUINA 2
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 2) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarIndM2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //grava o total de carrinhos biologico DA MAQUINA 2
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 2) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarBioM2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //PEGA O TOTAL DE CARRINHOS DA MAQUINA 2
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where id_maquina = 2 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalCarrinhos2.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

         //***********************DETALHAMENTO DA MÁQUINA 3 *********************************

                //GRAVA TOTAL DA MAQUINA 3 (BIOLÓGICO E INDUSTRIAL)
                sql = "select sum(peso_incinerado) as 'total incinerado' from incineracao where id_maquina = 3 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalMaquina3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total incinerado"));

                //PEGA O TOTAL BIOLOGICO DA MAQUINA 3
                sql = "select sum(peso_incinerado) as 'total biologico' from incineracao where id_maquina = 3 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalBiologico3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("total biologico"));

                //PEGA O TOTAL INDUSTRIAL DA MAQUINA 3
                sql = "select sum(peso_incinerado) as 'industrial' from incineracao where id_maquina = 3 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalIndustrial3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("industrial"));

                //grava o total de carrinhos industrial DA MAQUINA 3
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 3) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'industrial') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarIndM3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //grava o total de carrinhos biologico DA MAQUINA 3
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where (id_maquina = 3) and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = 'biologico') and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.qtdCarBioM3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                //PEGA O TOTAL DE CARRINHOS DA MAQUINA 3
                sql = "select count(num_seq_incineracao) as 'qtCarrinho' from incineracao where id_maquina = 3 and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao;
                con_acompanhamentoPesagemIncineracaoAux.executeSQL(sql);
                con_acompanhamentoPesagemIncineracaoAux.resultset.first();
                acompanhamentoPesagemIncineracao.totalCarrinhos3.setText(con_acompanhamentoPesagemIncineracaoAux.resultset.getString("qtCarrinho"));

                atualizaResultset();
                configuraPainel();

    }
    catch(Exception e){

    JOptionPane.showMessageDialog(null, e);
    }

    }//fim atualiza painel detalhado

   public static void limpaCamposAcompanhamento(){

 //***********************DETALHAMENTO DO TOTAL DAS MAQUINA LITROS*********************************

                //tOTAL MAQUINAS (INDUSTRIAL E BIOLOGICO)
                acompanhamentoPesagemIncineracao.totalMaquinas.setText("");

                //TOTAL INDUSTRIAL
                acompanhamentoPesagemIncineracao.totalBiologico.setText("");

                //TOTAL BIOLOGICO
                acompanhamentoPesagemIncineracao.totalIndustrial.setText("");
                //TOTAL CAR BIO
                acompanhamentoPesagemIncineracao.qtdCarIndMs.setText("");
                //TOTAL CAR IND
                acompanhamentoPesagemIncineracao.qtdCarBioMs.setText("");
                //TOTAL CARRINHOS
                acompanhamentoPesagemIncineracao.totalCarrinhos.setText("");


          //***********************DETALHAMENTO DA MAQUINA 1*********************************

                //tOTAL MAQUINA 1
                acompanhamentoPesagemIncineracao.totalMaquina1.setText("");

                //TOTAL INDUSTRIAL
                acompanhamentoPesagemIncineracao.totalBiologico1.setText("");

                //TOTAL BIOLOGICO
                acompanhamentoPesagemIncineracao.totalIndustrial1.setText("");

                //TOTAL CAR BIO 1
                acompanhamentoPesagemIncineracao.qtdCarIndM1.setText("");
                //TOTAL CAR IND 1
                acompanhamentoPesagemIncineracao.qtdCarBioM1.setText("");

                //TOTAL CARRINHOS
                acompanhamentoPesagemIncineracao.totalCarrinhos1.setText("");

         //***********************DETALHAMENTO DA MAQUINA 2*********************************

                //tOTAL MAQUINAS 2
                acompanhamentoPesagemIncineracao.totalMaquina2.setText("");

                //TOTAL INDUSTRIAL
                acompanhamentoPesagemIncineracao.totalBiologico2.setText("");

                //TOTAL BIOLOGICO
                acompanhamentoPesagemIncineracao.totalIndustrial2.setText("");

                //TOTAL CAR BIO 2
                acompanhamentoPesagemIncineracao.qtdCarIndM2.setText("");
                //TOTAL CAR IND 2
                acompanhamentoPesagemIncineracao.qtdCarBioM2.setText("");

                //TOTAL CARRINHOS
                acompanhamentoPesagemIncineracao.totalCarrinhos2.setText("");

         //******************DETALHAMENTO DA MAQUINA 3************************

                //tOTAL MAQUINA 3
                acompanhamentoPesagemIncineracao.totalMaquina3.setText("");

                //TOTAL INDUSTRIAL
                acompanhamentoPesagemIncineracao.totalBiologico3.setText("");

                //TOTAL BIOLOGICO
                acompanhamentoPesagemIncineracao.totalIndustrial3.setText("");

                //TOTAL CAR BIO 3
                acompanhamentoPesagemIncineracao.qtdCarIndM3.setText("");
                //TOTAL CAR IND 3
                acompanhamentoPesagemIncineracao.qtdCarBioM3.setText("");

                //TOTAL CARRINHOS
                acompanhamentoPesagemIncineracao.totalCarrinhos3.setText("");


                //limpa a tabela
                limpaJtable();


                //inabilita botao atualizar
                acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(false);

                //inabilita botao imprimir
                acompanhamentoPesagemIncineracao.btImprimir.setEnabled(false);

   }//fim limpa campos

public static void atualizaResultset(){
    try{
    //atualiza o resultset
    con_acompanhamentoPesagemIncineracaoAux.executeSQL("select * from incineracao where (id_maquina = "+maquina+") and (turno_incineracao = "+turno+") and (data_incineracao between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_residuo = "+residuo+") and (situacao_reg = 'G') order by "+ordenacao);
    con_acompanhamentoPesagemIncineracaoAux.resultset.first();
    //con_bombona.resultset.next();
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "ATUALIZAÇÃO NÃO EFETUADA Erro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}//fim atualiza resultset


    public static void acompanhaDiaPeriodo(){

 //HABILITA***

        //botao A (HABILITA)
        acompanhamentoPesagemIncineracao.a.setEnabled(true);

        //TIPO E CAPACIDADE (HABILITADO)
        acompanhamentoPesagemIncineracao.maquina.setEnabled(true);
        acompanhamentoPesagemIncineracao.tipoResiduo.setEnabled(true);


  //INABILITA***

        //data FINAL pesquisa (LIMPA E INABILITA)
        acompanhamentoPesagemIncineracao.periodoA.setText("");
        acompanhamentoPesagemIncineracao.periodoA.setEditable(false);

        //congobox TURNO (HABILITA E SETA)
        acompanhamentoPesagemIncineracao.turno.setEnabled(true);
        acompanhamentoPesagemIncineracao.turno.setSelectedItem("Dia completo");



 //SETA***

        //CAPACIDADE DA BOMBONA (SETA)
        acompanhamentoPesagemIncineracao.maquina.setSelectedItem("Todas");

        //TIPO DE BOMBONA (SETA)
        acompanhamentoPesagemIncineracao.tipoResiduo.setSelectedItem("Todos");


 //FOCO****
        //campo de DATA INICIAL (LIMPA, FOCA E HABILITA)
        acompanhamentoPesagemIncineracao.periodoDe.setText("");
        acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
        acompanhamentoPesagemIncineracao.periodoDe.setEnabled(true);


    }

    public static void pesadasHoje(){
        //data de agota
        Data.le_data();

   //HABILITA*****


        //DATA DE HOJE (SETA E INABILITA)
        acompanhamentoPesagemIncineracao.periodoDe.setText(Data.dataAtualJFormatted);
        acompanhamentoPesagemIncineracao.periodoDe.setEnabled(false);

        //botao A (INABILITA)
        acompanhamentoPesagemIncineracao.a.setEnabled(false);

        //TURNO (HABILITA E SETA)
        acompanhamentoPesagemIncineracao.turno.setEnabled(true);
        acompanhamentoPesagemIncineracao.turno.setSelectedItem("Dia completo");

        //CAPACIDADE DA BOMBONA (SETA)
        acompanhamentoPesagemIncineracao.maquina.setSelectedItem("Todas");

        //TIPO DE BOMBONA (SETA)
        acompanhamentoPesagemIncineracao.tipoResiduo.setSelectedItem("Todos");

        //DATA FINAL (LIMPA E INABILITA)
        acompanhamentoPesagemIncineracao.periodoA.setText("");
        acompanhamentoPesagemIncineracao.periodoA.setEditable(false);

        //TIPO E CAPACIDADE (INABILITADO)
        acompanhamentoPesagemIncineracao.maquina.setEnabled(true);
        acompanhamentoPesagemIncineracao.tipoResiduo.setEnabled(true);

    }


//        public static void acompanharTempoReal(){
//        //pega hora atual para configurar o turno
//        Data.le_hora();
//
//        //informa o minuto e a hora atual para retornar o turno
//        Data.configuraTurno(Data.hora, Data.minuto);
//
//        // pega a data de hoje
//        Data.le_data();
//
//   //HABILITA****
//
//
//
//   //INABILITA****
//
//        //TURNO, CAPACIDADE E TIPO (INABILITADO)
//        acompanhamentoPesagemIncineracao.turno.setEnabled(false);
//        acompanhamentoPesagemIncineracao.maquina.setEnabled(false);
//        acompanhamentoPesagemIncineracao.tipoResiduo.setEnabled(false);
//
//        //DATA INICIAL (SETA E INABILITA)
//        acompanhamentoPesagemIncineracao.periodoDe.setText(Data.dataAtualJFormatted);
//        acompanhamentoPesagemIncineracao.periodoDe.setEnabled(false);
//
//        //botao A (INABILITADO)
//        acompanhamentoPesagemIncineracao.a.setEnabled(false);
//
//        //DATA FINAL (SETA E INABILITA)
//        acompanhamentoPesagemIncineracao.periodoA.setText("");
//        acompanhamentoPesagemIncineracao.periodoA.setEditable(false);
//
//
//   //SETA
//
//
//        //CAPACIDADE DA BOMBONA SETA TODAS
//        acompanhamentoPesagemIncineracao.maquina.setSelectedItem("Todas");
//
//        //TIPO DE BOMBONA SETA TODAS
//        acompanhamentoPesagemIncineracao.tipoResiduo.setSelectedItem("Todos");
//
//        //CONFIGURA O TURNO ATUAL
//        if(Data.mostraTurno().toString().equals("Manha"))
//            acompanhamentoPesagemIncineracao.turno.setSelectedItem("Manha");
//
//        else if(Data.mostraTurno().toString().equals("Tarde"))
//            acompanhamentoPesagemIncineracao.turno.setSelectedItem("Tarde");
//
//        else
//            acompanhamentoPesagemIncineracao.turno.setSelectedItem("Noite");
//
//
//    }


//formata data para gravar no banco
public static void formataDataBanco(String data, String nomeCampo){
int n = 0;
String dia, mes, ano, dataFormatada;

        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);


        dataFormatada = ano+"-"+mes+"-"+dia;

                if(nomeCampo.equals("data inicial da pesquisa"))
                    periodoInicial = dataFormatada;
                else if(nomeCampo.equals("data final da pesquisa"))
                    periodoFinal = dataFormatada;
                else
                    JOptionPane.showMessageDialog(null, "nome do campo nao confere");



}//fim convertData

public static void testaData(String data, String nomeCampo){

String dia, mes, ano;

        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

Data.le_data();

//imperfeições na data
            if(Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31){
                JOptionPane.showMessageDialog(null, "O dia deve ser de 1 a 31 no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemIncineracao.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12){

                JOptionPane.showMessageDialog(null, "O mês deve ser de 1 a 12 no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemIncineracao.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(dia) > Data.DIA && Integer.parseInt(mes) == Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                JOptionPane.showMessageDialog(null, "A dia digitado é maior que a data atual no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemIncineracao.periodoA.requestFocus();

                pesquise  = false;
            }

            else if(Integer.parseInt(dia) == Data.DIA && Integer.parseInt(mes) > Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                JOptionPane.showMessageDialog(null, "O mês digitado é maior que a data atual no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemIncineracao.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(ano) <= 2000){

                JOptionPane.showMessageDialog(null, "O ano deve ser maior que 2000 no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemIncineracao.periodoA.requestFocus();

                pesquise  = false;
            }
            else
                pesquise = true;


}

//METODO QUE VAI VER SE OS CAMPOS ESTAO CERTINHOS PARA BUSCA
public static void validaCampos(){

String msg = "";

//se acompanhar dia ou periodo de pesagem for selecionado
if(acompanhamentoPesagemIncineracao.acompanhaDiaPeriodo.isSelected()){

        //se data inicial nao for preenchida
        if(acompanhamentoPesagemIncineracao.periodoDe.getText().equals("  -  -    ")){
            msg = "Informe a data inicial da pesquisa.";
            acompanhamentoPesagemIncineracao.periodoDe.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if(acompanhamentoPesagemIncineracao.periodoA.isEditable() && acompanhamentoPesagemIncineracao.periodoA.getText().equals("  -  -    ")){
            msg = "Informe a data final da pesquisa";
            acompanhamentoPesagemIncineracao.periodoA.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else{

            testaData(acompanhamentoPesagemIncineracao.periodoDe.getText(), "data inicial da pesquisa");

            if(pesquise == true && acompanhamentoPesagemIncineracao.periodoA.isEditable())
               testaData(acompanhamentoPesagemIncineracao.periodoA.getText(), "data final da pesquisa");
            else
                return;
        }
}//fim se acompanhar por dia periodo

//no caso, se acompanha pesagem de hoje for selecionado
else{
            //testa a data de hoje, vai que o pc ta desconfigurado, sei lá...
            testaData(acompanhamentoPesagemIncineracao.periodoDe.getText(), "data inicial da pesquisa");

}


//FIM VALIDAR CAMPOS : SE OS CAMPOS ESTIVEREM CORRETOS A VARIÁVEL PESQUISE VAI RECEBER TRUE



}//fim validaCampos

//Vai pegar o valor dos campos e atualizar para fazer a busca no bd
public static void configuraParaPesquisa(){

    if(pesquise == true){


        //se acompanhar POR UM DIA OU PERIODO DE PESAGEM contrato vai receber o valor "contrato" (pesquisa todos do dia/periodo)
        if(acompanhamentoPesagemIncineracao.acompanhaDiaPeriodo.isSelected()){


             //habilita botao imprimir
             acompanhamentoPesagemIncineracao.btImprimir.setEnabled(true);

             //habilita atualizar
             acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(true);


        }
        //se acompanhar EM TEMPO REAL
//        else if(acompanhamentoPesagemIncineracao.acompanhaTempoReal.isSelected()){
//
//                //botao confirma pesquisa fica inabilitado
//                acompanhamentoPesagemIncineracao.btConfirmaBusca.setEnabled(false);
//
//                //botao atualizar é inabilitado
//                acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(false);
//
//                //botao novo relatório é inabilitado
//                acompanhamentoPesagemIncineracao.btNovoRelatorio.setEnabled(false);
//
//                //imprir fica inabilitado
//                acompanhamentoPesagemIncineracao.btImprimir.setEnabled(false);
//
//                //bt sair fica inabilitado
//                acompanhamentoPesagemIncineracao.btSair.setEnabled(false);
//
//        }

        //se ACOMPANHAR PESAGEM DE HOJE for selecionado contrato vai receber o valor "contrato" (pesquisa todos do dia)
        else{

              //habilita botao atualizar
              acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(true);

              //habilita botao imprimir
              acompanhamentoPesagemIncineracao.btImprimir.setEnabled(true);
        }


                //DATA INICIAL é formatada para pesquisar no banco no banco aaaa/mm/dd
                formataDataBanco(acompanhamentoPesagemIncineracao.periodoDe.getText(), "data inicial da pesquisa");

                //se campo de DATA FINAL da pesquisa estiver inabilitado ele recebe a mesma data inicial
                if(acompanhamentoPesagemIncineracao.periodoA.isEditable() == false)
                   formataDataBanco(acompanhamentoPesagemIncineracao.periodoDe.getText(), "data final da pesquisa");
                //se for o contrário pega a data do campo final para ser formatada para o banco aaaa/mm/dd
                else
                   formataDataBanco(acompanhamentoPesagemIncineracao.periodoA.getText(), "data final da pesquisa");


                //se TURNO selecionado for MANHA
                if(acompanhamentoPesagemIncineracao.turno.getSelectedItem().toString().equals("Manha"))
                    turno = "'Manha'";

                //se TURNO selecionado for TARDE
                else if(acompanhamentoPesagemIncineracao.turno.getSelectedItem().toString().equals("Tarde"))
                    turno = "'Tarde'";

                //se TURNO selecionado for NOITE
                else if(acompanhamentoPesagemIncineracao.turno.getSelectedItem().toString().equals("Noite"))
                    turno = "'Noite'";

                //se TURNO selecionado for DIA COMPLETO
                else
                    turno = "'Manha' or turno_incineracao = 'Tarde' or turno_incineracao = 'Noite'";


                //se MAQUINA 1 FOR SELECIONADA
                if(acompanhamentoPesagemIncineracao.maquina.getSelectedItem().toString().equals("Maquina 1"))
                    maquina = "1";

                //se MAQUINA 2
                else if(acompanhamentoPesagemIncineracao.maquina.getSelectedItem().toString().equals("Maquina 2"))
                    maquina = "2";

                //se MAQUINA 3
                else if(acompanhamentoPesagemIncineracao.maquina.getSelectedItem().toString().equals("Maquina 3"))
                    maquina = "3";

                //se TODAS AS MÁQUINAS FOREM SELECIONADAS
                else
                    maquina = "1 or id_maquina = 2 or id_maquina = 3";


                //se TIPO DE BOMBONA selecionado for CONTRATO litros
                if(acompanhamentoPesagemIncineracao.tipoResiduo.getSelectedItem().toString().equals("Biologico"))
                    residuo = "'biologico'";

                //se TIPO DE BOMBONA selecionado for EXTRA litros
                else if(acompanhamentoPesagemIncineracao.tipoResiduo.getSelectedItem().toString().equals("Industrial"))
                    residuo = "'industrial'";

                //se TIPO DE BOMBONA selecionado for TODOS litros
                else
                    residuo = "'biologico' or tipo_residuo = 'industrial'";


//acompanhamento em tempo real
//                if(acompanhamentoPesagemIncineracao.acompanhaTempoReal.isSelected()){
//
//                        acompanhamentoPesagemIncineracao.tempoReal.setDelay(1000);
//                        acompanhamentoPesagemIncineracao.tempoReal.start();
//                        acompanhamentoPesagemIncineracao.btPara.setEnabled(true);
//                }

//                //teste
//               JOptionPane.showMessageDialog(null,  "DATA INICIAL: "+periodoInicial+"\n" +
//                                                    "DATA FINAL: "+periodoFinal+"\n" +
//                                                    "TURNO: "+turno+"\n" +
//                                                    "MAQUINA : "+maquina+"\n" +
//                                                    "TIPO DE RESÍDUO: "+residuo+"\n");

                atualizaPainelDetalhado();
                preencherJtable();


    }//fim se pesquise for true


}//fim configura pesquisa

public static void atualizaPesquisa(){

                configuraParaPesquisa();
//                atualizaPainelDetalhado();
//                preencherJtable();

}//fim atualiza pesquisa

public static void novoRelatorio(){

        residuo = "";
        turno = "";
        maquina = "";
        periodoInicial = "";
        periodoFinal = "";
        ordenacao = "num_seq_incineracao";

        limpaCamposAcompanhamento();
        limpaJtable();

        acompanhaDiaPeriodo();

        //seleciona acompanhar por contrato
        acompanhamentoPesagemIncineracao.acompanhaDiaPeriodo.setSelected(true);

        //PARA THREAD
//        acompanhamentoPesagemIncineracao.tempoReal.stop();

        //inabilita botoes
        acompanhamentoPesagemIncineracao.btAtualiza.setEnabled(false);
//        acompanhamentoPesagemIncineracao.btPara.setEnabled(false);
        acompanhamentoPesagemIncineracao.btImprimir.setEnabled(false);

        //seleciona ordenacao por codigo
        acompanhamentoPesagemIncineracao.ordenaCodigo.setSelected(true);




}//FIM NOVO RELATORIO

public static void configuraPainel(){
            String c1, c2, c3, cb1, cb2, cb3, cbt, ci1, ci2, ci3, cit, cTotal, tb, tb1, tb2, tb3, ti, ti1, ti2, ti3;

            cb1 = acompanhamentoPesagemIncineracao.qtdCarBioM1.getText();
            cb2 = acompanhamentoPesagemIncineracao.qtdCarBioM2.getText();
            cb3 = acompanhamentoPesagemIncineracao.qtdCarBioM3.getText();
            cbt = acompanhamentoPesagemIncineracao.qtdCarBioMs.getText();

            ci1 = acompanhamentoPesagemIncineracao.qtdCarIndM1.getText();
            ci2 = acompanhamentoPesagemIncineracao.qtdCarIndM2.getText();
            ci3 = acompanhamentoPesagemIncineracao.qtdCarIndM3.getText();
            cit = acompanhamentoPesagemIncineracao.qtdCarIndMs.getText();

            c1 = acompanhamentoPesagemIncineracao.totalCarrinhos1.getText();
            c2 = acompanhamentoPesagemIncineracao.totalCarrinhos2.getText();
            c3 = acompanhamentoPesagemIncineracao.totalCarrinhos3.getText();
            cTotal = acompanhamentoPesagemIncineracao.totalCarrinhos.getText();

            tb1 = acompanhamentoPesagemIncineracao.totalBiologico1.getText();
            tb2 = acompanhamentoPesagemIncineracao.totalBiologico2.getText();
            tb3 = acompanhamentoPesagemIncineracao.totalBiologico3.getText();
            tb = acompanhamentoPesagemIncineracao.totalBiologico.getText();
            ti1 = acompanhamentoPesagemIncineracao.totalIndustrial1.getText();
            ti2 = acompanhamentoPesagemIncineracao.totalIndustrial2.getText();
            ti3 = acompanhamentoPesagemIncineracao.totalIndustrial3.getText();
            ti = acompanhamentoPesagemIncineracao.totalIndustrial.getText();

        if(tb1.equals(""))
            acompanhamentoPesagemIncineracao.totalBiologico1.setText("0");
        if(tb2.equals(""))
            acompanhamentoPesagemIncineracao.totalBiologico2.setText("0");
        if(tb3.equals(""))
            acompanhamentoPesagemIncineracao.totalBiologico3.setText("0");
        if(tb.equals(""))
            acompanhamentoPesagemIncineracao.totalBiologico.setText("0");
        if(ti1.equals(""))
            acompanhamentoPesagemIncineracao.totalIndustrial1.setText("0");
        if(ti2.equals(""))
            acompanhamentoPesagemIncineracao.totalIndustrial2.setText("0");
        if(ti3.equals(""))
            acompanhamentoPesagemIncineracao.totalIndustrial3.setText("0");
        if(ti.equals(""))
            acompanhamentoPesagemIncineracao.totalIndustrial.setText("0");


        if(c1.equals("0") && c2.equals("0") && c3.equals("0")){
            acompanhamentoPesagemIncineracao.totalMaquinas.setText("0.0");
            acompanhamentoPesagemIncineracao.totalBiologico.setText("0.0");
            acompanhamentoPesagemIncineracao.totalIndustrial.setText("0.0");
            acompanhamentoPesagemIncineracao.totalCarrinhos.setText("0");
        }

        if(c1.equals("0")){
            acompanhamentoPesagemIncineracao.totalMaquina1.setText("0.0");
            acompanhamentoPesagemIncineracao.totalBiologico1.setText("0.0");
            acompanhamentoPesagemIncineracao.totalIndustrial1.setText("0.0");
            acompanhamentoPesagemIncineracao.totalCarrinhos1.setText("0");
        }

        if(c2.equals("0")){
            acompanhamentoPesagemIncineracao.totalMaquina2.setText("0.0");
            acompanhamentoPesagemIncineracao.totalBiologico2.setText("0.0");
            acompanhamentoPesagemIncineracao.totalIndustrial2.setText("0.0");
            acompanhamentoPesagemIncineracao.totalCarrinhos2.setText("0");
        }

        if(c3.equals("0")){
            acompanhamentoPesagemIncineracao.totalMaquina3.setText("0.0");
            acompanhamentoPesagemIncineracao.totalBiologico3.setText("0.0");
            acompanhamentoPesagemIncineracao.totalIndustrial3.setText("0.0");
            acompanhamentoPesagemIncineracao.totalCarrinhos3.setText("0");
        }

}//fim configura painel

public static void formataDataBancoParaJTable(String data){
String dia, mes, ano;

        ano = data.substring(0, 4);
        mes = data.substring(5, 7);
        dia = data.substring(8, 10);

        dataFormatadataJTable = dia+"-"+mes+"-"+ano;

}//fim convert area text
}
