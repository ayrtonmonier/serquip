/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.*;
import Cadastro.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ayrton monier
 */
public class validaCadInci {


private static float pesoCar;
private static float acumulaPeso[];
private static int n;
private static float ultimaSacola;
private static conexao con_incineracao;
public static String residuo = "Biologico";
public static String direcao;
public static boolean habilitaBotoes;
public static boolean exclusao;

public validaCadInci(){

}


//construtor auxiliar
public static void iniciaComponentes(){

    //array que acumula peso e outras varíaveis estão sendo inicializadas
    
    acumulaPeso = new float[30];

    //instanciando conexao
    con_incineracao = new conexao();
    
    //conexao iniciada
    con_incineracao.conecta();

    //chama metodo novo registro onde estao incluidos os metodos: atualizaResultset() e atualizaPainel()
    atualizaPainel();//linha 352 obs no final ele atualiza o resultset
    preencherJtable();
    btNovo();//linha 180
}


public static void addSacoCar(){
        try{
            //caso o peso for zero não inserir
            if(CadastroIncineracao.peso.getText().equals("00.00")){
                JOptionPane.showMessageDialog(null, "INSIRA O PESO DO RESÍDUO", "aviso", JOptionPane.WARNING_MESSAGE);
                CadastroIncineracao.peso.requestFocus();
            }

            else{
                //pesoCarrinho vai somando os valores inseridos
                pesoCar += Float.parseFloat(CadastroIncineracao.peso.getText());
                                       
               //teste para não ultrapassar mais de duas casas decimais
               if(String.valueOf(pesoCar).length() <= 4)
                    CadastroIncineracao.pesoCarrinho.setText(String.valueOf(pesoCar)+"0");
               else
                    CadastroIncineracao.pesoCarrinho.setText(String.valueOf((pesoCar)).substring(0, 5));

               //chama o método para resolver o resto do abacaxi
               acumulaVolumeCar();
                
               //depois que ele resolve tudo o metodo limpa campos entra em ação
               CadastroIncineracao.peso.setText("");
               CadastroIncineracao.peso.requestFocus();
            }//fim else
        }//fim try
        catch(Exception e){
               JOptionPane.showMessageDialog(null, "INSIRA O PESO DO RESÍDUO"+e, "aviso", JOptionPane.WARNING_MESSAGE);
               CadastroIncineracao.peso.requestFocus();
        }
}//fim validaCadInci


//método acumula saco no carrinho
public static void acumulaVolumeCar(){
            
             acumulaPeso[n] = pesoCar;
          
             //variável que guarda o valor da ultima sacola
             //se igual a zero é pq não pode subtrair a menos que o slot zero
             if(n == 0)
                ultimaSacola = acumulaPeso[n];
             else
                ultimaSacola = acumulaPeso[n] - acumulaPeso[n-1];

                 //teste para mostrar ao usuário
                 if(String.valueOf(ultimaSacola).length() <= 4)
                    CadastroIncineracao.ultimoSaco.setText(String.valueOf(ultimaSacola)+"0");
                 else
                    CadastroIncineracao.ultimoSaco.setText(String.valueOf(ultimaSacola).substring(0, 5));
             
             n++;
}//fim acumular saco

public static void retiraSacoCar(){
         //aray volta ao slot anterior (que contém valor) após avançar 1 no metodo acumular peso
         n = n -1;
         try{
                     //teste para saber se indice do array está igual a zero
                     if( n <= 0){

                                ultimaSacola = acumulaPeso[n];

                                //contador é zerado
                                n = 0;

                                //zera Peso do carinho
                                pesoCar = 00.00f;

                                CadastroIncineracao.ultimoSaco.setText(String.valueOf("00.00"));

                                CadastroIncineracao.pesoCarrinho.setText("00.00");

                                //depois que ele resolve tudo o metodo limpa campos entra em ação
                                CadastroIncineracao.peso.setText("");
                                CadastroIncineracao.peso.requestFocus();
                    }//fim if


                    //se for maior que zero cairá neste laço
                    else{
                                //se o indice for igual a 1 a ultima sacola recebe o slot 0 que foi o primeiro a ser preenchido
                                if(n == 1)
                                   ultimaSacola = acumulaPeso[n-1];                                      //valor do indice 1 - valor do indice 0

                                //se o indice for maior que 1. ex: n = 2; acumulaPeso[2-1] - acumulaPeso[2-2]; acumulaPeso[1] - acumulaPeso[0];
                                else
                                  ultimaSacola = acumulaPeso[n-1] - acumulaPeso[n-2];

                                //peso do carrinho = peso atual do carrinho - (peso do indice atual - peso do indice anterior)
                                pesoCar = pesoCar - (acumulaPeso[n] - acumulaPeso[n-1]);
                                                                    //sacola anterior
                                //teste de casas decimais só pra mostrar para o usuario
                                if(String.valueOf(pesoCar).length() <= 4)
                                    CadastroIncineracao.pesoCarrinho.setText(String.valueOf((pesoCar))+"0");
                                else
                                    CadastroIncineracao.pesoCarrinho.setText(String.valueOf((pesoCar)).substring(0, 5));

                                //teste de casas decimais so pra mostrar para o usuario o valor do ultimo saco
                                if(String.valueOf(ultimaSacola).length() <= 4)
                                    CadastroIncineracao.ultimoSaco.setText(String.valueOf(ultimaSacola)+"0");
                                else
                                    CadastroIncineracao.ultimoSaco.setText(String.valueOf(ultimaSacola).substring(0, 5));

                                //mostra qt sacos que logo no início é decrescido 1
                               // CadastroIncineracao.qtSacos.setText(n+"");

                                //mostra mensagem de confirmação de retirada da ultima sacola
                                JOptionPane.showMessageDialog(null, "VOLUME RETIRADO COM SUCESSO!");

                    }//fim else
         }//fim try
         catch(Exception e){
                    if(CadastroIncineracao.codigoCar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "CARRINHO ESTA VAZIO, ADICIONE RESÍDUO!"+e, "aviso", JOptionPane.WARNING_MESSAGE);
                        CadastroIncineracao.peso.requestFocus();
                        n = 0;//volta a ser zero
                    }
                    else{
                       JOptionPane.showMessageDialog(null, "ERRO AO TENTAR RETIRAR O VOLUME\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
                    }

         }//fim catch
}//fim retirar saco



public static void btNovo(){

    CadastroIncineracao.peso.setText("");
    CadastroIncineracao.pesoCarrinho.setText("00.00");
   // CadastroIncineracao.qtSacos.setText("0");
    CadastroIncineracao.ultimoSaco.setText("00.00");
    CadastroIncineracao.codigoCar.setText("");

    pesoCar = 0.0f;
    n = 0;
    ultimaSacola = 0.0f;
    CadastroIncineracao.peso.requestFocus();

}

public static void zeraValores(){

    //contador é zerado
    n = 0;
    //valor da ultima sacola é zerado
    ultimaSacola = 0.0f;
    //zera Peso do carinho
    pesoCar = 00.00f;
    CadastroIncineracao.ultimoSaco.setText(String.valueOf("00.00"));
   // CadastroIncineracao.qtSacos.setText("0");
    CadastroIncineracao.pesoCarrinho.setText("00.00");
    CadastroIncineracao.peso.requestFocus();
    CadastroIncineracao.codigoCar.setText("");
}
public static void gravaDadosInci(){
try{
    //carrega hora
    Data.le_hora();


    String sqlInsert = "insert into incineracao (matricula_usuario" +
            ", peso_incinerado" +
            ", id_maquina" +
            ", tipo_residuo" +
            ", data_incineracao" +
            ", turno_incineracao" +
            ", hora_incineracao" +
            ", situacao_reg) values ('" +
            validaLogin.MATRICULA+"'"+
            ", "+EscolhaMaquina.pesoCarrinhoDialog.getText()+
            ", "+EscolhaMaquina.selecaoMaquina.getSelectedItem()+
            ", '"+residuo+"'"+
            ", '"+validaLogin.DATAFORMAT+"'"+
            ", '"+validaLogin.TURNO+"'" +
            ", '"+Data.horaAtual+"'" +
            ", 'G')";
          //  ", "+EscolhaMaquina.qtSacos.getText()+")";
    
    int grava = con_incineracao.statement.executeUpdate(sqlInsert);

    //se conseguiu gravar, retorna 1 e entra no if
    if(grava == 1){
    JOptionPane.showMessageDialog(null, "GRAVAÇÃO EFETUADA COM SUCESSO!");
    atualizaPainel();//atualiza painel
    preencherJtable();//atualiza tabela
    zeraValores();//zera valores
    //CadastroIncineracao.codigoCar.setText("");
    }
}//fim try

catch(Exception e){
    JOptionPane.showMessageDialog(null, "Erro ao tentar gravar o registro\nErro: "+e, "erro", JOptionPane.ERROR_MESSAGE);
}


}//fim grava dados
public static void btNavega(){
        try{
            //atualizaResultset();
            
            if(direcao.equals("primeiro"))
                con_incineracao.resultset.first();

            else if(direcao.equals("anterior"))
                con_incineracao.resultset.previous();

            else if(direcao.equals("proximo"))
                con_incineracao.resultset.next();

            else
                con_incineracao.resultset.last();

            //mostra os dados
            mostrarDados();
        }
        catch(Exception e){

        }
}
public static void mostrarDados(){
            try{
            CadastroIncineracao.pesoCarrinho.setText(con_incineracao.resultset.getString("peso_incinerado"));
            //CadastroIncineracao.qtSacos.setText(con_incineracao.resultset.getString("qt_saco"));
            CadastroIncineracao.codigoCar.setText(con_incineracao.resultset.getString("num_seq_incineracao"));
            }
            
            catch(Exception e){
                if(CadastroIncineracao.navega == 1)
                    JOptionPane.showMessageDialog(null, "VOCÊ JÁ ESTÁ NO PRIMEIRO REGISTRO", "Aviso", JOptionPane.WARNING_MESSAGE);
                else if(CadastroIncineracao.navega == 2)
                    JOptionPane.showMessageDialog(null, "VOCÊ JÁ ESTÁ NO ULTIMO REGISTRO", "Aviso", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "DADOS NÃO LOCALIZADOS", "Aviso", JOptionPane.WARNING_MESSAGE);
                    CadastroIncineracao.navega = 0;
            }

}//fim mostrar dados


public static void fechaConexao(){

con_incineracao.desconecta();
}
public static void configuraExclusao(){
    try{
        int linha = con_incineracao.resultset.getRow();

        String sql = "select * from incineracao where num_seq_incineracao = "+CadastroIncineracao.codigoCar.getText();
        con_incineracao.executeSQL(sql);
        //atualiza o resultset com um unico registro
        con_incineracao.resultset.first();

        //botao que exclui um carrinho
        excluirCarrinho dialog = new excluirCarrinho(new javax.swing.JFrame(), true);
        dialog.pesoExc.setText(CadastroIncineracao.pesoCarrinho.getText());
        dialog.nCarrinho.setText(CadastroIncineracao.codigoCar.getText());
        dialog.tipoResiduo.setText(con_incineracao.resultset.getString("tipo_residuo"));
        //dialog.qtdSacolas.setText(con_incineracao.resultset.getString("qt_saco"));
        dialog.nMaquina.setText(con_incineracao.resultset.getString("id_maquina"));
        dialog.excluiCarrinho.requestFocus();
        dialog.setVisible(true);

        //se nao for excluir volta a linha anterior (antes de abrir tela de exclusao)
        if(exclusao == false){
            con_incineracao.executeSQL("select * from incineracao where data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and (situacao_reg = 'G') order by num_seq_incineracao");
            con_incineracao.resultset.absolute(linha);
            exclusao = true;
        }

    }//fim try

    catch(Exception e){
        JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ATRAVÉS DOS BOTÕES DE NAVEGAÇÃO!", "Aviso", JOptionPane.WARNING_MESSAGE);
        CadastroIncineracao.peso.requestFocus();
    }
}
//public static void excluiRegistro(){
//    try{
//    //strig sql guarda o codigo de seleção do carrinho
//    String sql = "select * from incineracao where num_seq_incineracao = "+excluirCarrinho.nCarrinho.getText();
//    con_incineracao.executeSQL(sql);
//    //atualiza o resultset com um unico registro
//    con_incineracao.resultset.first();
//    //String sql guarda o codigo de exclusao
//    sql = "DELETE FROM incineracao where num_seq_incineracao = "+excluirCarrinho.nCarrinho.getText();
//    //codigo de exclusao é executado
//    int conseguiu_excluir = con_incineracao.statement.executeUpdate(sql);
//
//    if(conseguiu_excluir == 1){
//        JOptionPane.showMessageDialog(null, "EXCLUSÃO EFETUADA COM SUCESSO!");
//
//        //atualiza o painel e consequentemente o resultset
//        atualizaPainel();
//        preencherJtable();
//        btNovo();
//
//        //adiciona true para habilitar os botoes na chamada do metodo que resolve isso
//        validaCadInci.habilitaBotoes = true;
//        validaCadInci.desabiliHabilitataBotoes();
//    }
//    else{
//     return;
//    }
//    }
//    catch(Exception e){
//        JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O REGISTRO", "Erro", JOptionPane.ERROR_MESSAGE);
//    }

//}//fim exclui registro
public static void atualizaPainel(){

        try{

            //System.out.println("entrou no atualiza Painel");

                //grava o total da maquina 1 neste dia e turno
                String sql = "select sum(peso_incinerado) as 'total m1' from incineracao where id_maquina = 1 and data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.maquina1.setText(con_incineracao.resultset.getString("total m1"));

                //grava o total da maquina 2 neste dia e turno
                sql = "select sum(peso_incinerado) as 'total m2' from incineracao where id_maquina = 2 and data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.maquina2.setText(con_incineracao.resultset.getString("total m2"));

                //grava o total da maquina 3 neste dia e turno
                sql = "select sum(peso_incinerado) as 'total m3' from incineracao where id_maquina = 3 and data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.maquina3.setText(con_incineracao.resultset.getString("total m3"));

                //grava o total biológico neste dia e turno
                sql = "select sum(peso_incinerado) as 'total biologico' from incineracao where tipo_residuo = 'Biologico' and data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.totalBiologico.setText(con_incineracao.resultset.getString("total biologico"));

                //grava o total biologico neste dia e turno
                sql = "select sum(peso_incinerado) as 'total Industrial' from incineracao where tipo_residuo = 'Industrial' and data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.totalIndustrial.setText(con_incineracao.resultset.getString("total Industrial"));


                //grava o total das maquinas 1, 2 e 3 neste dia e turno
                sql = "select sum(peso_incinerado) as 'total maquinas' from incineracao where data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.totalMaquinas.setText(con_incineracao.resultset.getString("total maquinas"));

                //grava o total de carrinhos neste dia e turno
                sql = "select count(peso_incinerado) as 'qtCarrinho' from incineracao where data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
               
                con_incineracao.executeSQL(sql);
                con_incineracao.resultset.first();
                CadastroIncineracao.qtCarrinhos.setText(con_incineracao.resultset.getString("qtCarrinho"));

                //System.out.println("saiu do atualiza Painel e vai para o result set...");

                atualizaResultset();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar o painel\n erro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
        }
}//fim exclui registro

public static void atualizaResultset(){
    try{

    //atualiza o resultset
    con_incineracao.executeSQL("select * from incineracao where data_incineracao = '"+validaLogin.DATAFORMAT+"' and turno_incineracao = '"+validaLogin.TURNO+"' and (situacao_reg = 'G') order by num_seq_incineracao");
    con_incineracao.resultset.first();

//System.out.println("atualizou o resultset");
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Nao ocorreu atualização da tabela de incineração\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}//fim atualiza resultset

public static void desabiliHabilitataBotoes(){

        if(habilitaBotoes == false){
        CadastroIncineracao.btColocaResiduo.setEnabled(false);
        CadastroIncineracao.btRetiraVolumme.setEnabled(false);
        CadastroIncineracao.btLimpar.setEnabled(false);
        CadastroIncineracao.btIncinerar.setEnabled(false);
        CadastroIncineracao.peso.setEnabled(false);
        CadastroIncineracao.btExcluir.setEnabled(true);
        CadastroIncineracao.btNovo.setEnabled(true);
        }
        else{
        CadastroIncineracao.btColocaResiduo.setEnabled(true);
        CadastroIncineracao.btRetiraVolumme.setEnabled(true);
        CadastroIncineracao.btLimpar.setEnabled(true);
        CadastroIncineracao.btIncinerar.setEnabled(true);
        CadastroIncineracao.peso.setEnabled(true);
        CadastroIncineracao.btExcluir.setEnabled(false);
        CadastroIncineracao.btNovo.setEnabled(false);
        }
}//fim habilitadesabilitabotoes

public static void preencherJtable(){

//CadastroIncineracao.tabelaInci.getColumnModel().getColumn(0).setPreferredWidth(20); ajusta largura das colunas
int c = 1;
//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) CadastroIncineracao.tabelaInci.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

    try {

        //System.out.println("entrou no preencher jtable");
        con_incineracao.resultset.first();

        do{

                //vai add linhas
                //equivale a uma linha na minha grid
                modelo.addRow(new Object[]{con_incineracao.resultset.getString("num_seq_incineracao"),
                                           con_incineracao.resultset.getString("peso_incinerado"),
                                           //con_incineracao.resultset.getString("qt_saco"),
                                           con_incineracao.resultset.getString("tipo_residuo"),
                                           con_incineracao.resultset.getString("id_maquina"),
                                           con_incineracao.resultset.getString("hora_incineracao")});

                //System.out.println("inseriu o "+(c++)+"° registro");


        }while(con_incineracao.resultset.next());//enquanto tiver dados preenche o jtable com os valores
        
        //depois que finaliza atualiza o resultset
        //System.out.println("saiu do laco do jtable");
        atualizaResultset();
    }

    catch (Exception e) {
         JOptionPane.showMessageDialog(null, "NENHUM CARRINHO INCINERADO NESTE TURNO");
    }

}//fim preencherJtable

public static void cliqueGrid(){
    //pega a linha que foi clicada na TABELA
    int linha = CadastroIncineracao.tabelaInci.getSelectedRow();
    //pega o modelo da tabela
    TableModel modelo = (TableModel) CadastroIncineracao.tabelaInci.getModel();
    //pega o codigo do cliente que está na linha que foi clicada e coluna 0 (primeira)
    String codigoInciClicado =String.valueOf(modelo.getValueAt(linha, 0));
    //var que vai gurdar o codigo pesquisado no banco
    String codigoInciPesquisado;
    //vai determinar o fim do laço
    String igual = "n";



    try{

        //vai para o primeiro registro
        con_incineracao.resultset.first();

        while(igual.equals("n")){

            codigoInciPesquisado = con_incineracao.resultset.getString("num_seq_incineracao");

            //se o codigo que foi clicado na gride for igual ao que está no banco
            if(codigoInciClicado.equals(codigoInciPesquisado)){
                mostrarDados();
                validaCadInci.habilitaBotoes = false;
                validaCadInci.desabiliHabilitataBotoes();
                igual = "s";//sai do laço e mostra dados
            }
            else
                con_incineracao.resultset.next();
        }//fim while
    }//fim try

    catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }

}//fim click grid

public static void cancelarRegistro(){

    try{
        // E = EXCLUIDO
        String sql = "UPDATE incineracao SET situacao_reg = 'E' where num_seq_incineracao = "+excluirCarrinho.nCarrinho.getText();

        int conseguiu_excluir = con_incineracao.statement.executeUpdate(sql);

        if(conseguiu_excluir == 1){
            JOptionPane.showMessageDialog(null, "EXCLUSÃO EFETUADA COM SUCESSO");

            //atualiza o painel e consequentemente o resultset
            atualizaPainel();
            preencherJtable();
            btNovo();

            habilitaBotoes = true;
            desabiliHabilitataBotoes();

        }
        else{
            JOptionPane.showMessageDialog(null, "EXCLUSÃO NÃO EFETUADA");

        }

    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O REGISTRO.\n\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
    }


}




}