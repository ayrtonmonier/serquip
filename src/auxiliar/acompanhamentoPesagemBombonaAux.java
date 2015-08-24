/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import RelatorioForm.acompanhamentoPesagemBombona;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


/**
 *
 * @author ayrton monier
 */
public class acompanhamentoPesagemBombonaAux {

    public static conexao con_acompanhamentoPesagemBombonaAux;
    public static String periodoInicial, periodoFinal, turno, contrato = "", capacidade, tipo, tipoPesagem;
    public static boolean pesquise = false;//permissão para fazer a pesquisa
    public static String ordenacao = "num_seq_bomb";
    public static String dataFormatadataJTable;
    public static int qtd_clientes;
    public static String dataFormatadaAreaTexto;

//construtor auxiliar
public static void iniciaComponentes(){

    //instanciando conexao
    con_acompanhamentoPesagemBombonaAux = new conexao();

    //conexao iniciada
    con_acompanhamentoPesagemBombonaAux.conecta();

    //inicia com um tipo padrao de pesquisa
    novoRelatorio();
}

public static void desconecta(){

con_acompanhamentoPesagemBombonaAux.desconecta();

}

    public static void preencherJtable(){

int x = 1;//criada para pegar o primeiro registro
//acompanhamentoPesagemBombona.tabelaInci.getColumnModel().getColumn(0).setPreferredWidth(20); ajusta largura das colunas

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) acompanhamentoPesagemBombona.tabelaAcompanhamentoBomb.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

    try {

        do{

             //formatada a data do banco para dd/mm/aaaa para mostrar na tabela
             formataDataBancoParaJTable(con_acompanhamentoPesagemBombonaAux.resultset.getString("data_pesagem_bomb"));

             if(x <= 1)//só no primeiro loop pega o primeiro registro, depois do 1° vai pegando os próximos até quando tiver
                con_acompanhamentoPesagemBombonaAux.resultset.first();

                //vai add linhas
                //equivale a uma linha na minha tabela Bombona
                modelo.addRow(new Object[]{con_acompanhamentoPesagemBombonaAux.resultset.getString("num_seq_bomb"),//codigo
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("contrato"),//contrato
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("capacidade_bomb"),//capacidade
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("peso_bruto"),//peso bruto
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("peso_liquido"),//peso liquido
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("peso_excedido"),//peso excedido
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("tipo_bomb"),//tipo de bombona (contrato ou extra)
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("tipo_pesagem"),//tipo de pesagem (em dia ou acumulada)
                                           dataFormatadataJTable,//data
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("turno_pesagem"),//turno
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("hora_pesagem_bomb"),//hora
                                           con_acompanhamentoPesagemBombonaAux.resultset.getString("matricula_usuario")});//operador

             x++;//add mais um no x e nao chama mais o if para ir pro primeiro registro

        }while(con_acompanhamentoPesagemBombonaAux.resultset.next());//enquanto tiver dados preenche o jtable com os valores

        //depois que finaliza atualiza o resultset
        atualizaResultset();
    }

    catch (Exception e) {

         JOptionPane.showMessageDialog(null, "Não consta dados nesta data/período ou com as especificações do filtro", "Aviso", JOptionPane.WARNING_MESSAGE);
            //para trhead
            acompanhamentoPesagemBombona.tempoReal.stop();

            //bt Para fica inabilitado
//            acompanhamentoPesagemBombona.btPara.setEnabled(false);

            //bt atualiza fica inabilitado
            acompanhamentoPesagemBombona.btAtualiza.setEnabled(false);

            //habilita botao imprimir
            acompanhamentoPesagemBombona.btImprimir.setEnabled(false);

            //bt confirmaBusca é habilitado
            acompanhamentoPesagemBombona.btConfirmaBusca.setEnabled(true);

            //bt Sair e habilitado
            acompanhamentoPesagemBombona.btSair.setEnabled(true);

            //btNovo relatorio e´habilitado
            acompanhamentoPesagemBombona.btNovoRelatorio.setEnabled(true);

    }

}//fim preencherJtable

//limpa a tabels
public static void limpaJtable(){

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) acompanhamentoPesagemBombona.tabelaAcompanhamentoBomb.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

}//LIMPA JTABLE

    public static void atualizaPainelTotal(){

        try{

                //pega o total bruto
                String sql = "select sum(peso_bruto) as 'total bruto' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBruto.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bruto"));

                //pega o total liquido
                sql = "select sum(peso_liquido) as 'total liquido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalLiquido.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total liquido"));

                //pega o total excedido
                sql = "select sum(peso_excedido) as 'total excedido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalExcedido.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total excedido"));


                //pega a quantidade total de bombonas de contrato pesadas
                sql = "select count(tipo_bomb) as 'total contrato' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'contrato' and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBContrato.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total contrato"));

                //pega a quantidade total de bombonas extras
                sql = "select count(tipo_bomb) as 'total extra' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'extra' and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBExtra.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total extra"));

                //quantidade de bombonas em dia
                sql = "select count(tipo_pesagem) as 'total em dia' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = 'Em dia') and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBEmDia.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total em dia"));

                //quantidade de bombonas acumuladas
                sql = "select count(tipo_pesagem) as 'total acumuladas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = 'Acumulada') and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBAcumulada.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total acumuladas"));

                //quantidade de bombonas desconhecida
                sql = "select count(tipo_bomb) as 'total desconhecida' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'Descon.' and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.totalBDesconhecida.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total desconhecida"));

                //pega a quantidade total de bombonas pesadas
                sql = "select count(tipo_bomb) as 'total bombonas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtTotal.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bombonas"));

                atualizaResultset();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "PAINEL NÃO FOI ATUALIZADO.", "Erro"+e, JOptionPane.ERROR_MESSAGE);
        }
}//fim atualiza painel

    //atualiza o painel detalhado
    public static void atualizaPainelDetalhado(){

    String sql;

    try{
            //***********************DETALHAMENTO DE BOMBONAS DE 200 LITROS*********************************

                //peso bruto
                sql = "select sum(peso_bruto) as 'total bruto' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pBruto200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bruto"));

                //peso liquido
                sql = "select sum(peso_liquido) as 'total liquido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pLiquido200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total liquido"));

                //peso excedido
                sql = "select sum(peso_excedido) as 'total excedido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pExcedido200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total excedido"));

                //quantidade de bombonas de contrato
                sql = "select count(tipo_bomb) as 'total contrato' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'contrato' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombCont200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total contrato"));

                //quantidade de bombonas extras
                sql = "select count(tipo_bomb) as 'total extra' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'extra' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombExtra200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total extra"));

                //quantidade de bombonas em dia
                sql = "select count(tipo_pesagem) as 'total em dia' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Em dia' and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombEmDia200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total em dia"));

                //quantidade de bombonas acumuladas
                sql = "select count(tipo_pesagem) as 'total acumuladas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Acumulada' and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombAcumulada200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total acumuladas"));

                //quantidade de bombonas desconhecida
                sql = "select count(tipo_bomb) as 'total desconhecida' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'Descon.' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombDesconhecida200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total desconhecida"));

                //quantidade total de bombonas
                sql = "select count(tipo_bomb) as 'total bombonas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 200 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombTotal200.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bombonas"));

          //***********************DETALHAMENTO DE BOMBONAS DE 50 LITROS*********************************

                //peso bruto
                sql = "select sum(peso_bruto) as 'total bruto' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pBruto50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bruto"));

                //peso liquido
                sql = "select sum(peso_liquido) as 'total liquido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pLiquido50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total liquido"));

                //peso excedido
                sql = "select sum(peso_excedido) as 'total excedido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pExcedido50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total excedido"));

                //quantidade de bombonas de contrato
                sql = "select count(tipo_bomb) as 'total contrato' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'contrato' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombCont50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total contrato"));

                //quantidade de bombonas extras
                sql = "select count(tipo_bomb) as 'total extra' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'extra' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombExtra50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total extra"));

                //quantidade de bombonas em dia
                sql = "select count(tipo_pesagem) as 'total em dia' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Em dia' and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombEmDia50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total em dia"));

                //quantidade de bombonas acumuladas
                sql = "select count(tipo_pesagem) as 'total acumuladas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Acumulada' and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombAcumulada50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total acumuladas"));

                //quantidade de bombonas desconhecida
                sql = "select count(tipo_bomb) as 'total desconhecida' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'Descon.' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombDesconhecida50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total desconhecida"));

                //quantidade total de bombonas
                sql = "select count(tipo_bomb) as 'total bombonas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 50 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombTotal50.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bombonas"));

         //***********************DETALHAMENTO DE BOMBONAS DE 20 LITROS*********************************

                //peso bruto
                sql = "select sum(peso_bruto) as 'total bruto' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pBruto20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bruto"));

                //peso liquido
                sql = "select sum(peso_liquido) as 'total liquido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pLiquido20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total liquido"));

                //peso excedido
                sql = "select sum(peso_excedido) as 'total excedido' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.pExcedido20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total excedido"));

                //quantidade de bombonas de contrato
                sql = "select count(tipo_bomb) as 'total contrato' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'contrato' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombCont20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total contrato"));

                //quantidade de bombonas extras
                sql = "select count(tipo_bomb) as 'total extra' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'extra' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombExtra20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total extra"));

                //quantidade de bombonas em dia
                sql = "select count(tipo_pesagem) as 'total em dia' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Em dia' and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombEmDia20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total em dia"));

                //quantidade de bombonas acumuladas
                sql = "select count(tipo_pesagem) as 'total acumuladas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and tipo_pesagem = 'Acumulada' and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombAcumulada20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total acumuladas"));

                //quantidade de bombonas desconhecida
                sql = "select count(tipo_bomb) as 'total desconhecida' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and tipo_bomb = 'Descon.' and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombDesconhecida20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total desconhecida"));

                //quantidade total de bombonas
                sql = "select count(tipo_bomb) as 'total bombonas' from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and capacidade_bomb = 20 and (situacao_reg = 'G')";
                con_acompanhamentoPesagemBombonaAux.executeSQL(sql);
                con_acompanhamentoPesagemBombonaAux.resultset.first();
                acompanhamentoPesagemBombona.qtdBombTotal20.setText(con_acompanhamentoPesagemBombonaAux.resultset.getString("total bombonas"));

                atualizaResultset();
                configuraPainel();
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null, "cliente nao existe");

    }

    }//fim atualiza painel detalhado

   public static void limpaCamposAcompanhamento(){

 //***********************DETALHAMENTO DE BOMBONAS DE 200 LITROS*********************************

                //peso bruto
                acompanhamentoPesagemBombona.pBruto200.setText("");

                //peso liquido
                acompanhamentoPesagemBombona.pLiquido200.setText("");

                //peso excedido
                acompanhamentoPesagemBombona.pExcedido200.setText("");

                //quantidade de bombonas de contrato
                acompanhamentoPesagemBombona.qtdBombCont200.setText("");

                //quantidade de bombonas extras
                acompanhamentoPesagemBombona.qtdBombExtra200.setText("");

                //quantidade total de bomb em dia
                acompanhamentoPesagemBombona.qtdBombEmDia200.setText("");

                //quantidade de bombonas acumulada
                acompanhamentoPesagemBombona.qtdBombAcumulada200.setText("");

                //quantidade de bombonas desconhecidas
                acompanhamentoPesagemBombona.qtdBombDesconhecida200.setText("");

                //quantidade total de bombonas
                acompanhamentoPesagemBombona.qtdBombTotal200.setText("");

          //***********************DETALHAMENTO DE BOMBONAS DE 50 LITROS*********************************

                //peso bruto
                acompanhamentoPesagemBombona.pBruto50.setText("");

                //peso liquido
                acompanhamentoPesagemBombona.pLiquido50.setText("");

                //peso excedido
                acompanhamentoPesagemBombona.pExcedido50.setText("");

                //quantidade de bombonas de contrato
                acompanhamentoPesagemBombona.qtdBombCont50.setText("");

                //quantidade de bombonas extras
                acompanhamentoPesagemBombona.qtdBombExtra50.setText("");

                //quantidade total de bomb em dia
                acompanhamentoPesagemBombona.qtdBombEmDia50.setText("");

                //quantidade de bombonas acumulada
                acompanhamentoPesagemBombona.qtdBombAcumulada50.setText("");

                //quantidade de bombonas desconhecidas
                acompanhamentoPesagemBombona.qtdBombDesconhecida50.setText("");

                //quantidade total de bombonas
                acompanhamentoPesagemBombona.qtdBombTotal50.setText("");

         //***********************DETALHAMENTO DE BOMBONAS DE 20 LITROS*********************************

                //peso bruto
                acompanhamentoPesagemBombona.pBruto20.setText("");

                //peso liquido
                acompanhamentoPesagemBombona.pLiquido20.setText("");

                //peso excedido
                acompanhamentoPesagemBombona.pExcedido20.setText("");

                //quantidade de bombonas de contrato
                acompanhamentoPesagemBombona.qtdBombCont20.setText("");

                //quantidade de bombonas extras
                acompanhamentoPesagemBombona.qtdBombExtra20.setText("");

                //quantidade total de bomb em dia
                acompanhamentoPesagemBombona.qtdBombEmDia20.setText("");

                //quantidade de bombonas acumulada
                acompanhamentoPesagemBombona.qtdBombAcumulada20.setText("");

                //quantidade de bombonas desconhecidas
                acompanhamentoPesagemBombona.qtdBombDesconhecida20.setText("");

                //quantidade total de bombonas
                acompanhamentoPesagemBombona.qtdBombTotal20.setText("");

         //***********************PAINEL TOTAL*********************************

                //peso bruto
                acompanhamentoPesagemBombona.totalBruto.setText("");

                //peso liquido
                acompanhamentoPesagemBombona.totalLiquido.setText("");

                //peso excedido
                acompanhamentoPesagemBombona.totalExcedido.setText("");

                //quantidade de bombonas de contrato
                acompanhamentoPesagemBombona.totalBContrato.setText("");

                //quantidade de bombonas extras
                acompanhamentoPesagemBombona.totalBExtra.setText("");

                //quantidade total de bomb em dia
                acompanhamentoPesagemBombona.totalBEmDia.setText("");

                //quantidade de bombonas acumulada
                acompanhamentoPesagemBombona.totalBAcumulada.setText("");

                //quantidade de bombonas desconhecidas
                acompanhamentoPesagemBombona.totalBDesconhecida.setText("");

                //quantidade total de bombonas
                acompanhamentoPesagemBombona.qtTotal.setText("");


                //limpa a tabela
                limpaJtable();
                limpaMiniPainel();

                //campo de total de clientes zera
                acompanhamentoPesagemBombona.qtdCli.setText("");


                //inabilita botao atualizar
                acompanhamentoPesagemBombona.btAtualiza.setEnabled(false);

                //inabilita botao imprimir
                acompanhamentoPesagemBombona.btImprimir.setEnabled(false);

   }//fim limpa campos

public static void atualizaResultset(){
    try{
    //atualiza o resultset
    con_acompanhamentoPesagemBombonaAux.executeSQL("select * from cad_bombona where (contrato = "+contrato+") and (turno_pesagem = "+turno+") and (data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (tipo_bomb = "+tipo+") and (tipo_pesagem = "+tipoPesagem+") and (capacidade_bomb = "+capacidade+") and (situacao_reg = 'G') order by "+ordenacao);
    con_acompanhamentoPesagemBombonaAux.resultset.first();
    //con_bombona.resultset.next();
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "ATUALIZAÇÃO NÃO EFETUADA", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}//fim atualiza resultset


public static void acompanhaContrato(){

    //HABILITA****

        //campo CONTRATO (LIMPA E HABILITA)
        acompanhamentoPesagemBombona.contrato.setText("");
        acompanhamentoPesagemBombona.contrato.setEditable(true);

        //botão A (HABILITA)
        acompanhamentoPesagemBombona.a.setEnabled(true);

        //TIPO BOMBONA, CAPACIDADE, TIPO PESAGEM (HABILITA)
        acompanhamentoPesagemBombona.capacidadeBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoPesagem.setEnabled(true);

        //congobox TURNO habilitado
        acompanhamentoPesagemBombona.turno.setEnabled(true);


 //INABILITA****


        //campo de data FINAL (LIMPA E INABILITA)
        acompanhamentoPesagemBombona.periodoA.setText("");
        acompanhamentoPesagemBombona.periodoA.setEditable(false);

        //data INICIAL (LIMPA INABILITA)
        acompanhamentoPesagemBombona.periodoDe.setText("");
        acompanhamentoPesagemBombona.periodoDe.setEnabled(true);


  //SETA***

        //combobox CLIENTES (SETA)
        acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cliente(s)"}));

        //campo SITUAÇAO (SETA)
        acompanhamentoPesagemBombona.situacaoCli.setText("");

        //campo CONTRATO (SETA)
        acompanhamentoPesagemBombona.contratoCli.setText("");

        //campo qt pesada hoje (SETA)
        acompanhamentoPesagemBombona.qtdPhoje.setText("");

        //CAPACIDADE DA BOMBONA (SETA)
        acompanhamentoPesagemBombona.capacidadeBomb.setSelectedItem("Todas");

        //TIPO DE BOMBONA (SETA)
        acompanhamentoPesagemBombona.tipoBomb.setSelectedItem("Todas");

        //seleção no congo o dia completo
        acompanhamentoPesagemBombona.turno.setSelectedItem("Dia completo");


  //FOCO***

        //foco para o campo contrato
        acompanhamentoPesagemBombona.contrato.requestFocus();

    }

    public static void acompanhaDiaPeriodo(){


 //HABILITA***

        //botao A (HABILITA)
        acompanhamentoPesagemBombona.a.setEnabled(true);

        //TIPO BOMBONA, CAPACIDADE E TIPO PESAGEM (HABILITADO)
        acompanhamentoPesagemBombona.capacidadeBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoPesagem.setEnabled(true);


  //INABILITA***

        //data FINAL pesquisa (LIMPA E INABILITA)
        acompanhamentoPesagemBombona.periodoA.setText("");
        acompanhamentoPesagemBombona.periodoA.setEditable(false);

        //campo CONTRATO (LIMPA E INABILITA)
        acompanhamentoPesagemBombona.contrato.setText("");
        acompanhamentoPesagemBombona.contrato.setEditable(false);

        //congobox TURNO (HABILITA E SETA)
        acompanhamentoPesagemBombona.turno.setEnabled(true);
        acompanhamentoPesagemBombona.turno.setSelectedItem("Dia completo");



 //SETA***

        //campo TODOS CLIENTES (SETA)
        acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cliente(s)"}));

        //SITUACAO (SETA)
        acompanhamentoPesagemBombona.situacaoCli.setText("");

        //CONTRATO (SETA)
        acompanhamentoPesagemBombona.contratoCli.setText("");

        //CAPACIDADE DA BOMBONA (SETA)
        acompanhamentoPesagemBombona.capacidadeBomb.setSelectedItem("Todas");

        //TIPO DE BOMBONA (SETA)
        acompanhamentoPesagemBombona.tipoBomb.setSelectedItem("Todas");


 //FOCO****
        //campo de DATA INICIAL (LIMPA, FOCA E HABILITA)
        acompanhamentoPesagemBombona.periodoDe.setText("");
        acompanhamentoPesagemBombona.periodoDe.requestFocus();
        acompanhamentoPesagemBombona.periodoDe.setEnabled(true);


    }

    public static void pesadasHoje(){

        //data de agota
        Data.le_data();

   //HABILITA*****

        //CONTRATO (LIMPA E INABILITA)
        acompanhamentoPesagemBombona.contrato.setText("");
        acompanhamentoPesagemBombona.contrato.setEditable(false);

        //TODOS CLIENTES (SETA E HABILITA)
        acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cliente(s)"}));
        acompanhamentoPesagemBombona.todosClientes.setEnabled(true);

        //DATA DE HOJE (SETA E INABILITA)
        acompanhamentoPesagemBombona.periodoDe.setText(Data.dataAtualJFormatted);
        acompanhamentoPesagemBombona.periodoDe.setEnabled(false);

        //botao A (INABILITA)
        acompanhamentoPesagemBombona.a.setEnabled(false);

        //TURNO (HABILITA E SETA)
        acompanhamentoPesagemBombona.turno.setEnabled(true);
        acompanhamentoPesagemBombona.turno.setSelectedItem("Dia completo");

        //CAPACIDADE DA BOMBONA (SETA)
        acompanhamentoPesagemBombona.capacidadeBomb.setSelectedItem("Todas");

        //TIPO DE BOMBONA (SETA)
        acompanhamentoPesagemBombona.tipoBomb.setSelectedItem("Todas");

        //DATA FINAL (LIMPA E INABILITA)
        acompanhamentoPesagemBombona.periodoA.setText("");
        acompanhamentoPesagemBombona.periodoA.setEditable(false);

        //TIPO BOMBONA, CAPACIDADE E  TIPO PESAGEM (INABILITADO)
        acompanhamentoPesagemBombona.capacidadeBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoBomb.setEnabled(true);
        acompanhamentoPesagemBombona.tipoPesagem.setEnabled(true);

    }


//        public static void acompanharTempoReal(){
//
//        //pega hora atual
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
//        //TURNO, CAPACIDADE, TIPO DE BOMBONA E TIPO DE PESAGEM (INABILITADO)
//        acompanhamentoPesagemBombona.turno.setEnabled(false);
//        acompanhamentoPesagemBombona.capacidadeBomb.setEnabled(false);
//        acompanhamentoPesagemBombona.tipoBomb.setEnabled(false);
//        acompanhamentoPesagemBombona.tipoPesagem.setEnabled(false);
//
//
//        //DATA INICIAL (SETA E INABILITA)
//        acompanhamentoPesagemBombona.periodoDe.setText(Data.dataAtualJFormatted);
//
//        acompanhamentoPesagemBombona.periodoDe.setEnabled(false);
//
//        //botao A (INABILITADO)
//        acompanhamentoPesagemBombona.a.setEnabled(false);
//
//        //DATA FINAL (SETA E INABILITA)
//        acompanhamentoPesagemBombona.periodoA.setText("");
//        acompanhamentoPesagemBombona.periodoA.setEditable(false);
//
//
//        //CONTRATO (LIMPA E INABILITA)
//        acompanhamentoPesagemBombona.contrato.setText("");
//        acompanhamentoPesagemBombona.contrato.setEditable(false);
//
//
//   //SETA
//
//
//        //CAPACIDADE DA BOMBONA SETA TODAS
//        acompanhamentoPesagemBombona.capacidadeBomb.setSelectedItem("Todas");
//
//        //TIPO DE BOMBONA SETA TODAS
//        acompanhamentoPesagemBombona.tipoBomb.setSelectedItem("Todas");
//
//        //TODOS CIENTES (SETA)
//        acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cliente(s)"}));
//
//        //TIPO PESAGEM
//        acompanhamentoPesagemBombona.tipoPesagem.setSelectedItem("Todas");
//
//        //CONFIGURA O TURNO ATUAL
//        if(Data.mostraTurno().toString().equals("Manha"))
//            acompanhamentoPesagemBombona.turno.setSelectedItem("Manha");
//
//        else if(Data.mostraTurno().toString().equals("Tarde"))
//            acompanhamentoPesagemBombona.turno.setSelectedItem("Tarde");
//
//        else
//            acompanhamentoPesagemBombona.turno.setSelectedItem("Noite");
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
                    acompanhamentoPesagemBombona.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemBombona.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12){

                JOptionPane.showMessageDialog(null, "O mês deve ser de 1 a 12 no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemBombona.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemBombona.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(dia) > Data.DIA && Integer.parseInt(mes) == Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                JOptionPane.showMessageDialog(null, "A dia digitado é maior que a data atual no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemBombona.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemBombona.periodoA.requestFocus();

                pesquise  = false;
            }

            else if(Integer.parseInt(dia) == Data.DIA && Integer.parseInt(mes) > Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                JOptionPane.showMessageDialog(null, "O mês digitado é maior que a data atual no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemBombona.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemBombona.periodoA.requestFocus();

                pesquise  = false;
            }
            else if(Integer.parseInt(ano) <= 2000){

                JOptionPane.showMessageDialog(null, "O ano deve ser maior que 2000 no campo : "+nomeCampo, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(nomeCampo.equals("data inicial da pesquisa"))
                    acompanhamentoPesagemBombona.periodoDe.requestFocus();
                else
                    acompanhamentoPesagemBombona.periodoA.requestFocus();

                pesquise  = false;
            }
            else
                pesquise = true;


}

//METODO QUE VAI VER SE OS CAMPOS ESTAO CERTINHOS PARA BUSCA
public static void validaCampos(){

String msg = "";

//se acompanhar por contrato for selecionado
if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected()){

        if(acompanhamentoPesagemBombona.contrato.getText().isEmpty()){
            msg = "Informe o contrato a ser pesquisado.";
            acompanhamentoPesagemBombona.contrato.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if(acompanhamentoPesagemBombona.periodoDe.getText().equals("  -  -    ")){
            msg = "Informe a data inicial da pesquisa";
            acompanhamentoPesagemBombona.periodoDe.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if(acompanhamentoPesagemBombona.periodoA.isEditable() && acompanhamentoPesagemBombona.periodoA.getText().equals("  -  -    ")){
            msg = "Informe a data final da pesquisa";
            acompanhamentoPesagemBombona.periodoA.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else{

            testaData(acompanhamentoPesagemBombona.periodoDe.getText(), "data inicial da pesquisa");

            if(pesquise == true && acompanhamentoPesagemBombona.periodoA.isEditable())
               testaData(acompanhamentoPesagemBombona.periodoA.getText(), "data final da pesquisa");
            else
                return;
        }

}//fim se acompanhar por contrato for selecionado


//se acompanhar dia ou periodo de pesagem for selecionado
else if(acompanhamentoPesagemBombona.acompanhaDiaPeriodo.isSelected()){

        //se data inicial nao for preenchida
        if(acompanhamentoPesagemBombona.periodoDe.getText().equals("  -  -    ")){
            msg = "Informe a data inicial da pesquisa.";
            acompanhamentoPesagemBombona.periodoDe.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else if(acompanhamentoPesagemBombona.periodoA.isEditable() && acompanhamentoPesagemBombona.periodoA.getText().equals("  -  -    ")){
            msg = "Informe a data final da pesquisa";
            acompanhamentoPesagemBombona.periodoA.requestFocus();
            pesquise = false;
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else{

            testaData(acompanhamentoPesagemBombona.periodoDe.getText(), "data inicial da pesquisa");

            if(pesquise == true && acompanhamentoPesagemBombona.periodoA.isEditable())
               testaData(acompanhamentoPesagemBombona.periodoA.getText(), "data final da pesquisa");
            else
                return;
        }
}//fim se acompanhar por dia periodo

//no caso, se acompanha pesagem de hoje for selecionado
else{
            //testa a data de hoje, vai que o pc ta desconfigurado, sei lá...
            testaData(acompanhamentoPesagemBombona.periodoDe.getText(), "data inicial da pesquisa");

}


//FIM VALIDAR CAMPOS : SE OS CAMPOS ESTIVEREM CORRETOS A VARIÁVEL PESQUISE VAI RECEBER TRUE



}//fim validaCampos

//Vai pegar o valor dos campos e atualizar para fazer a busca no bd
public static void configuraParaPesquisa(){

    if(pesquise == true){

        //se acompanhar POR CONTRATO for selecionado vai receber o contrato digitado
        if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected()){
              contrato = acompanhamentoPesagemBombona.contrato.getText();

              acompanhamentoPesagemBombona.todosClientes.setEnabled(true);
              acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));


              //habilita botao imprimir
              acompanhamentoPesagemBombona.btImprimir.setEnabled(true);

              //habilita atualizar
              acompanhamentoPesagemBombona.btAtualiza.setEnabled(true);

        }

        //se acompanhar POR UM DIA OU PERIODO DE PESAGEM contrato vai receber o valor "contrato" (pesquisa todos do dia/periodo)
        else if(acompanhamentoPesagemBombona.acompanhaDiaPeriodo.isSelected()){
              contrato = "contrato";

             //habilita botao imprimir
             acompanhamentoPesagemBombona.btImprimir.setEnabled(true);

             //habilita atualizar
             acompanhamentoPesagemBombona.btAtualiza.setEnabled(true);


        }
        //se acompanhar EM TEMPO REAL contrato vai receber o valor "contrato" (pesquisa todos do dia/periodo)
//        else if(acompanhamentoPesagemBombona.acompanhaTempoReal.isSelected()){
//              acompanhamentoPesagemBombona.tempoReal.setDelay(10000);
//
//                contrato = "contrato";
//
//                //botao confirma pesquisa fica inabilitado
//                acompanhamentoPesagemBombona.btConfirmaBusca.setEnabled(false);
//
//                //botao atualizar é inabilitado
//                acompanhamentoPesagemBombona.btAtualiza.setEnabled(false);
//
//                //botao novo relatório é inabilitado
//                acompanhamentoPesagemBombona.btNovoRelatorio.setEnabled(false);
//
//                //imprir fica inabilitado
//                acompanhamentoPesagemBombona.btImprimir.setEnabled(false);
//
//                //bt sair fica inabilitado
//                acompanhamentoPesagemBombona.btSair.setEnabled(false);
//
//                //bt tODOS OS CLIENTES fica inabilitado
//                acompanhamentoPesagemBombona.todosClientes.setEnabled(false);
//
//                //campos contrato, situação e qt hoje limpam
//                acompanhamentoPesagemBombona.contratoCli.setText("");
//                acompanhamentoPesagemBombona.situacaoCli.setText("");
//                acompanhamentoPesagemBombona.qtdPhoje.setText("");
//        }

        //se ACOMPANHAR PESAGEM DE HOJE for selecionado contrato vai receber o valor "contrato" (pesquisa todos do dia)
        else{
              contrato = "contrato";

              //habilita botao atualizar
              acompanhamentoPesagemBombona.btAtualiza.setEnabled(true);

              //habilita botao imprimir
              acompanhamentoPesagemBombona.btImprimir.setEnabled(true);
        }

                //DATA INICIAL é formatada para ser gravada no banco aaaa/mm/dd
                formataDataBanco(acompanhamentoPesagemBombona.periodoDe.getText(), "data inicial da pesquisa");

                //se campo de DATA FINAL da pesquisa estiver inabilitado ele recebe a mesma data inicial
                if(acompanhamentoPesagemBombona.periodoA.isEditable() == false)
                   formataDataBanco(acompanhamentoPesagemBombona.periodoDe.getText(), "data final da pesquisa");
                //se for o contrário pega a data do campo final para ser formatada para o banco aaaa/mm/dd
                else
                   formataDataBanco(acompanhamentoPesagemBombona.periodoA.getText(), "data final da pesquisa");

                //se TURNO selecionado for MANHA
                if(acompanhamentoPesagemBombona.turno.getSelectedItem().toString().equals("Manha"))
                    turno = "'Manha'";

                //se TURNO selecionado for TARDE
                else if(acompanhamentoPesagemBombona.turno.getSelectedItem().toString().equals("Tarde"))
                    turno = "'Tarde'";

                //se TURNO selecionado for NOITE
                else if(acompanhamentoPesagemBombona.turno.getSelectedItem().toString().equals("Noite"))
                    turno = "'Noite'";

                //se TURNO selecionado for DIA COMPLETO
                else
                    turno = "'Manha' or turno_pesagem = 'Tarde' or turno_pesagem = 'Noite'";


                //se CAPACIDADE DA BOMBONA selecionado for 200 litros
                if(acompanhamentoPesagemBombona.capacidadeBomb.getSelectedItem().toString().equals("200 Litros"))
                    capacidade = "200";

                //se CAPACIDADE DA BOMBONA selecionado for 50 litros
                else if(acompanhamentoPesagemBombona.capacidadeBomb.getSelectedItem().toString().equals("50 Litros"))
                    capacidade = "50";

                //se CAPACIDADE DA BOMBONA selecionado for 20 litros
                else if(acompanhamentoPesagemBombona.capacidadeBomb.getSelectedItem().toString().equals("20 Litros"))
                    capacidade = "20";

                //se CAPACIDADE DA BOMBONA selecionado for 200
                else
                    capacidade = "200 or capacidade_bomb = 50 or capacidade_bomb = 20";


                //se TIPO DE BOMBONA selecionado for CONTRATO litros
                if(acompanhamentoPesagemBombona.tipoBomb.getSelectedItem().toString().equals("Contrato"))
                    tipo = "'contrato'";

                //se TIPO DE BOMBONA selecionado for EXTRA litros
                else if(acompanhamentoPesagemBombona.tipoBomb.getSelectedItem().toString().equals("Extra"))
                    tipo = "'extra'";

                //se TIPO DE BOMBONA selecionado for EXTRA litros
                else if(acompanhamentoPesagemBombona.tipoBomb.getSelectedItem().toString().equals("Desconhecida"))
                    tipo = "'Descon.'";

                //se TIPO DE BOMBONA selecionado for TODOS litros
                else
                    tipo = "'contrato' or tipo_bomb = 'extra' or tipo_bomb = 'Descon.'";


                //se TIPO DE PESAGEM selecionado for EM DIA
                if(acompanhamentoPesagemBombona.tipoPesagem.getSelectedItem().toString().equals("Em dia"))
                    tipoPesagem = "'Em dia'";

                //se TIPO DE PESAGEM selecionado for ACUMULADAS
                else if(acompanhamentoPesagemBombona.tipoPesagem.getSelectedItem().toString().equals("Acumuladas"))
                    tipoPesagem = "'Acumulada'";

                //se TIPO DE PESAGEM selecionado for TODAS
                else
                    tipoPesagem = "'Em dia' or tipo_pesagem = 'Acumulada'";


//                if(acompanhamentoPesagemBombona.acompanhaTempoReal.isSelected()){
//                        acompanhamentoPesagemBombona.tempoReal.start();
//                        acompanhamentoPesagemBombona.btPara.setEnabled(true);
//                }


                //teste
               /*JOptionPane.showMessageDialog(null, "CONTRATO: "+contrato+"\n" +
                                                    "DATA INICIAL: "+periodoInicial+"\n" +
                                                    "DATA FINAL: "+periodoFinal+"\n" +
                                                    "TURNO: "+turno+"\n" +
                                                    "CAPACIDADE : "+capacidade+"\n" +
                                                    "TIPO: "+tipo+"\n");
                */
                atualizaPainelTotal();
                atualizaPainelDetalhado();
                preencherJtable();
                preencheListaCli();


                if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected() && qtd_clientes > 0)
                    acompanhamentoPesagemBombona.todosClientes.setSelectedIndex(1);

    }//fim se pesquise == true


}//fim configura pesquisa

public static void atualizaPesquisa(){

                configuraParaPesquisa();



}//fim atualiza pesquisa

public static void novoRelatorio(){

        contrato = "";
        turno = "";
        capacidade = "";
        tipo = "";
        periodoInicial = "";
        periodoFinal = "";
        ordenacao = "num_seq_bomb";

        //nao altoriza para configurar a pesquisa
        pesquise = false;

        limpaCamposAcompanhamento();
        acompanhaContrato();

        //PARA THREAD
        acompanhamentoPesagemBombona.tempoReal.stop();

        //seleciona acompanhar por contrato
        acompanhamentoPesagemBombona.acompanhaContrato.setSelected(true);

        //inabilita botoes
        acompanhamentoPesagemBombona.btAtualiza.setEnabled(false);
//        acompanhamentoPesagemBombona.btPara.setEnabled(false);

        //seleciona ordenacao por codigo
        acompanhamentoPesagemBombona.ordenaCodigo.setSelected(true);

        //mostra a qtd de clientes
        limpaMiniPainel();

        //bt imprimir fica inabilitado
        acompanhamentoPesagemBombona.btImprimir.setEnabled(false);


}//FIM NOVO RELATORIO


public static void preencheListaCli(){

    try{
//        limpa o minipainel
        limpaMiniPainel();


        //sql que relaciona o numero do contrato da tabela de bombona com o nome de do respectivo cliente na tabela de cliente
        String sql = "SELECT distinct(a.contrato), b.nome_cli FROM cad_bombona a, cliente b WHERE a.contrato = b.contrato_cli and (a.contrato = "+contrato+") and (a.turno_pesagem = "+turno+") and (a.data_pesagem_bomb between '"+periodoInicial+"' and '"+periodoFinal+"') and (a.tipo_bomb = "+tipo+") and (a.tipo_pesagem = "+tipoPesagem+") and (a.capacidade_bomb = "+capacidade+") and (a.situacao_reg = 'G') order by b.nome_cli";

        //sql = "SELECT distinct(a.contrato), b.nome_cli FROM cad_bombona a, cliente b WHERE a.contrato = b.contrato_cli and a.data_pesagem_bomb = "2010-1-7" order by b.nome_cli"
        con_acompanhamentoPesagemBombonaAux.executeSQL(sql);

        con_acompanhamentoPesagemBombonaAux.resultset.first();


            do{
                    //mostra o conteúdo na lista
                    acompanhamentoPesagemBombona.todosClientes.addItem(con_acompanhamentoPesagemBombonaAux.resultset.getString("b.nome_cli"));

                    //cada vez que acha um faz a contagem
                    qtd_clientes++;

            }while(con_acompanhamentoPesagemBombonaAux.resultset.next());

            if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected() == true && qtd_clientes == 1){
                acompanhamentoPesagemBombona.todosClientes.setSelectedIndex(1);
                mostraReferenciasCli();
            }

            acompanhamentoPesagemBombona.qtdCli.setText(qtd_clientes+"");


    }//fim try


    catch(Exception e){

        limpaMiniPainel();

    }


}//fim PREENCHER LISTA COM CLIENTES

public static void limpaMiniPainel(){

        //ZERA O CONGOBOX...
        acompanhamentoPesagemBombona.todosClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Cliente(s)"}));

        acompanhamentoPesagemBombona.qtdCli.setText("");
        acompanhamentoPesagemBombona.situacaoCli.setText("");
        acompanhamentoPesagemBombona.contratoCli.setText("");
        acompanhamentoPesagemBombona.qtdPhoje.setText("");

        qtd_clientes = 0;

}

public static void mostraReferenciasCli(){

    String situacao;
    int contratoCliente, qtBhoje;

    try{

    //seleciona a tabela de cliente para pegar a situacao, dias de coleta e a qt de bombonas de 20, 50 e 200
    con_acompanhamentoPesagemBombonaAux.executeSQL("select * from cliente where nome_cli = '"+acompanhamentoPesagemBombona.todosClientes.getSelectedItem().toString()+"' order by nome_cli");
    con_acompanhamentoPesagemBombonaAux.resultset.first();

    situacao = con_acompanhamentoPesagemBombonaAux.resultset.getString("situacao");
    contratoCliente = con_acompanhamentoPesagemBombonaAux.resultset.getInt("contrato_cli");


    //seleciona a tabela de bombona para pegar a qtd de vezes que esse contrato pesou hoje
    con_acompanhamentoPesagemBombonaAux.executeSQL("select count(cod_cli) as 'qtPesada' from cad_bombona where contrato = "+contratoCliente+" and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and situacao_reg = 'G'");
    con_acompanhamentoPesagemBombonaAux.resultset.first();
    //pega a qt pesaa hoje
    qtBhoje = con_acompanhamentoPesagemBombonaAux.resultset.getInt("qtPesada");

    //envia dados para aletaBombona
    acompanhamentoPesagemBombona.situacaoCli.setText(situacao);
    acompanhamentoPesagemBombona.qtdPhoje.setText(""+qtBhoje);
    //mostra oo contrato do cliente que esta sendo mostrado
    acompanhamentoPesagemBombona.contratoCli.setText(contratoCliente+"");

    //a variável statica contrato recebe a variavel local contratoCliente

    contrato = String.valueOf(contratoCliente);

    //se usuário clicar no cliente do congobox ee vai pegar os dados só deste cliente
    atualizaPainelDetalhado();
    atualizaPainelTotal();
    preencherJtable();

    }
    catch(Exception e){

    if(acompanhamentoPesagemBombona.todosClientes.getSelectedItem().toString().equals("Cliente(s)") && pesquise == true){

        if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected() == true)
            contrato = acompanhamentoPesagemBombona.contrato.getText();
        else
            contrato = "contrato";

            acompanhamentoPesagemBombona.contratoCli.setText("");
            acompanhamentoPesagemBombona.situacaoCli.setText("");
            acompanhamentoPesagemBombona.qtdPhoje.setText("");

            atualizaPesquisa();

    }
    else
        novoRelatorio();



    }

}//fim mostra referencia cli

public static void formataDataBancoParaJTable(String data){
String dia, mes, ano;

        ano = data.substring(0, 4);
        mes = data.substring(5, 7);
        dia = data.substring(8, 10);

        dataFormatadataJTable = dia+"-"+mes+"-"+ano;

}//fim convert area text

//deixa o painle com valores padrão (0.0) e (0)
public static void configuraPainel(){
            String pBruto200, pLiq200, pExc200, bCont200, bExt200, bEmD200, bAcu200, bDesc200, bTot200;
            String pBruto50, pLiq50, pExc50, bCont50, bExt50, bEmD50, bAcu50, bDesc50, bTot50;
            String pBruto20, pLiq20, pExc20, bCont20, bExt20, bEmD20, bAcu20, bDesc20, bTot20;
            String pBruto, pLiq, pExc, bCont, bExt, bEmD, bAcu, bDesc, bTot;

            //bombon de 200
            pBruto200 = acompanhamentoPesagemBombona.pBruto200.getText();
            pLiq200 = acompanhamentoPesagemBombona.pLiquido200.getText();
            pExc200 = acompanhamentoPesagemBombona.pExcedido200.getText();
            bCont200 = acompanhamentoPesagemBombona.qtdBombCont200.getText();
            bExt200 = acompanhamentoPesagemBombona.qtdBombExtra200.getText();
            bEmD200 = acompanhamentoPesagemBombona.qtdBombEmDia200.getText();
            bAcu200 = acompanhamentoPesagemBombona.qtdBombAcumulada200.getText();
            bDesc200 = acompanhamentoPesagemBombona.qtdBombDesconhecida200.getText();
            bTot200 = acompanhamentoPesagemBombona.qtdBombTotal200.getText();

            //bombona de 50
            pBruto50 = acompanhamentoPesagemBombona.pBruto50.getText();
            pLiq50 = acompanhamentoPesagemBombona.pLiquido50.getText();
            pExc50 = acompanhamentoPesagemBombona.pExcedido50.getText();
            bCont50 = acompanhamentoPesagemBombona.qtdBombCont50.getText();
            bExt50 = acompanhamentoPesagemBombona.qtdBombExtra50.getText();
            bEmD50 = acompanhamentoPesagemBombona.qtdBombEmDia50.getText();
            bAcu50 = acompanhamentoPesagemBombona.qtdBombAcumulada50.getText();
            bDesc50 = acompanhamentoPesagemBombona.qtdBombDesconhecida50.getText();
            bTot50 = acompanhamentoPesagemBombona.qtdBombTotal50.getText();
            //bombona de 20
            pBruto20 = acompanhamentoPesagemBombona.pBruto20.getText();
            pLiq20 = acompanhamentoPesagemBombona.pLiquido20.getText();
            pExc20 = acompanhamentoPesagemBombona.pExcedido20.getText();
            bCont20 = acompanhamentoPesagemBombona.qtdBombCont20.getText();
            bExt20 = acompanhamentoPesagemBombona.qtdBombExtra20.getText();
            bEmD20 = acompanhamentoPesagemBombona.qtdBombEmDia20.getText();
            bAcu20 = acompanhamentoPesagemBombona.qtdBombAcumulada20.getText();
            bDesc20 = acompanhamentoPesagemBombona.qtdBombDesconhecida20.getText();
            bTot20 = acompanhamentoPesagemBombona.qtdBombTotal20.getText();
            //total
            pBruto = acompanhamentoPesagemBombona.totalBruto.getText();
            pLiq = acompanhamentoPesagemBombona.totalLiquido.getText();
            pExc = acompanhamentoPesagemBombona.totalExcedido.getText();
            bCont = acompanhamentoPesagemBombona.totalBContrato.getText();
            bExt = acompanhamentoPesagemBombona.totalBExtra.getText();
            bEmD = acompanhamentoPesagemBombona.totalBEmDia.getText();
            bAcu = acompanhamentoPesagemBombona.totalBAcumulada.getText();
            bDesc = acompanhamentoPesagemBombona.totalBDesconhecida.getText();
            bTot = acompanhamentoPesagemBombona.qtTotal.getText();

        //BOMBONAS DE 200
        if(pBruto200.equals(""))
            acompanhamentoPesagemBombona.pBruto200.setText("0.0");
        if(pLiq200.equals(""))
            acompanhamentoPesagemBombona.pLiquido200.setText("0.0");
        if(pExc200.equals(""))
            acompanhamentoPesagemBombona.pExcedido200.setText("0.0");
        if(bCont200.equals(""))
            acompanhamentoPesagemBombona.qtdBombCont200.setText("0.0");
        if(bExt200.equals(""))
            acompanhamentoPesagemBombona.qtdBombExtra200.setText("0.0");
        if(bEmD200.equals(""))
            acompanhamentoPesagemBombona.qtdBombEmDia200.setText("0.0");
        if(bAcu200.equals(""))
            acompanhamentoPesagemBombona.qtdBombAcumulada200.setText("0.0");
        if(bDesc200.equals(""))
            acompanhamentoPesagemBombona.qtdBombDesconhecida200.setText("0.0");
        if(bTot200.equals(""))
            acompanhamentoPesagemBombona.qtTotal.setText("0.0");

         //bombonas de 50
        if(pBruto50.equals(""))
            acompanhamentoPesagemBombona.pBruto50.setText("0.0");
        if(pLiq50.equals(""))
            acompanhamentoPesagemBombona.pLiquido50.setText("0.0");
        if(pExc50.equals(""))
            acompanhamentoPesagemBombona.pExcedido50.setText("0.0");
        if(bCont50.equals(""))
            acompanhamentoPesagemBombona.qtdBombCont50.setText("0.0");
        if(bExt50.equals(""))
            acompanhamentoPesagemBombona.qtdBombExtra50.setText("0.0");
        if(bEmD50.equals(""))
            acompanhamentoPesagemBombona.qtdBombEmDia50.setText("0.0");
        if(bAcu50.equals(""))
            acompanhamentoPesagemBombona.qtdBombAcumulada50.setText("0.0");
        if(bDesc50.equals(""))
            acompanhamentoPesagemBombona.qtdBombDesconhecida50.setText("0.0");
        if(bTot50.equals(""))
            acompanhamentoPesagemBombona.qtTotal.setText("0.0");

        //bombonas de 20

        if(pBruto20.equals(""))
            acompanhamentoPesagemBombona.pBruto20.setText("0.0");
        if(pLiq20.equals(""))
            acompanhamentoPesagemBombona.pLiquido20.setText("0.0");
        if(pExc20.equals(""))
            acompanhamentoPesagemBombona.pExcedido20.setText("0.0");
        if(bCont20.equals(""))
            acompanhamentoPesagemBombona.qtdBombCont20.setText("0.0");
        if(bExt20.equals(""))
            acompanhamentoPesagemBombona.qtdBombExtra20.setText("0.0");
        if(bEmD20.equals(""))
            acompanhamentoPesagemBombona.qtdBombEmDia20.setText("0.0");
        if(bAcu20.equals(""))
            acompanhamentoPesagemBombona.qtdBombAcumulada20.setText("0.0");
        if(bDesc20.equals(""))
            acompanhamentoPesagemBombona.qtdBombDesconhecida20.setText("0.0");
        if(bTot20.equals(""))
            acompanhamentoPesagemBombona.qtTotal.setText("0.0");

        //bombonas totais

        if(pBruto.equals(""))
            acompanhamentoPesagemBombona.totalBruto.setText("0.0");
        if(pLiq.equals(""))
            acompanhamentoPesagemBombona.totalLiquido.setText("0.0");
        if(pExc.equals(""))
            acompanhamentoPesagemBombona.totalExcedido.setText("0.0");
        if(bCont.equals(""))
            acompanhamentoPesagemBombona.totalBContrato.setText("0.0");
        if(bExt.equals(""))
            acompanhamentoPesagemBombona.totalBExtra.setText("0.0");
        if(bEmD.equals(""))
            acompanhamentoPesagemBombona.totalBEmDia.setText("0.0");
        if(bAcu.equals(""))
            acompanhamentoPesagemBombona.totalBAcumulada.setText("0.0");
        if(bDesc.equals(""))
            acompanhamentoPesagemBombona.totalBDesconhecida.setText("0.0");
        if(bTot.equals(""))
            acompanhamentoPesagemBombona.qtTotal.setText("0.0");


}//fim configura painel

public static void pesquisaCli(){

            if(acompanhamentoPesagemBombona.acompanhaContrato.isSelected()){
                    //data de hoje
                    Data.le_data();

                    try{

                    //testa se o contrato só contém números

                    Integer.parseInt(acompanhamentoPesagemBombona.contrato.getText());
                    
                    con_acompanhamentoPesagemBombonaAux.executeSQL("select * from cliente where contrato_cli = "+acompanhamentoPesagemBombona.contrato.getText());
                    con_acompanhamentoPesagemBombonaAux.resultset.first();

                    String dataCadastro = con_acompanhamentoPesagemBombonaAux.resultset.getString("data_cadastro");

                    //formata a data de cadastro do usuario que esta no banco e passa para area de texto no formato dd/mm/aaaa
                    formataDataBancoParaAreaTexto(dataCadastro);
                    acompanhamentoPesagemBombona.periodoDe.setText(dataFormatadaAreaTexto);

                    acompanhamentoPesagemBombona.periodoA.setText(Data.dataAtualJFormatted);
                    acompanhamentoPesagemBombona.periodoA.setEditable(true);

                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Contrato não confere, tente outra vez", "Aviso", JOptionPane.WARNING_MESSAGE);
                        acompanhamentoPesagemBombona.contrato.setText("");
                        acompanhamentoPesagemBombona.contrato.requestFocus();
                        acompanhamentoPesagemBombona.periodoDe.setText("");
                        acompanhamentoPesagemBombona.periodoA.setText("");
                        acompanhamentoPesagemBombona.periodoA.setEditable(false);

                    }
            }//fim if acompanha contrato for selecionado
}

public static void formataDataBancoParaAreaTexto(String data){
String dia, mes, ano;

        ano = data.substring(0, 4);
        mes = data.substring(5, 7);
        dia = data.substring(8, 10);

        dataFormatadaAreaTexto = dia+mes+ano;

}//fim convert area text

}
