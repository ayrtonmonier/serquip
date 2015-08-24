/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.*;
import Cadastro.*;
import auxiliar.conexao;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

 /*
 * @author Administrador
 */
public class validaCadBomb {
    
private static float bruto;
private static float liquido;
private static float excedido;
private static int capacidade;
public static conexao con_bombona;
private static String codigoCliente;//pega o codigo do cliente do banco
private static String nomeCliente;//pega o nome do cliente do banco
private static int totalBombonaContrato, totalBombonaContrato200, totalBombonaContrato50, totalBombonaContrato20;
public static String tipoBombonaPesada, situacaoCliente;
public static String direcao;
private static int tentativas = 0; //adiciona 1  a cada tentativ errada
public static String tipoPesagem = "Em dia"; // em dia ou acumulado
public static String dataPesagem = validaLogin.DATAFORMAT; //inicialmente recebe a data atual (caso o operador pese bombonas acumuladas a data de ontem será add)
public static boolean exclusao;



public validaCadBomb(){
bruto = 0.0f;
liquido = 0.0f;
excedido = 0.0f;
}

//construtor auxiliar
public static void iniciaComponentes(){

    //instanciando conexao
    con_bombona = new conexao();

    //conexao iniciada
    con_bombona.conecta();

    atualizaPainel();
    preencherJtable();
    
}

public static void fechaConexao(){

con_bombona.desconecta();

}

public static void defineValores(){
    
   if(CadastroBombona.contrato.getText().length() >=1 && CadastroBombona.escolhaCapacidade.getSelectedItem().toString().length() >= 1 && CadastroBombona.pesoBruto.getText().equals("00.00")){
        JOptionPane.showMessageDialog(null, "O PESO BRUTO DA BOMBONA DEVE SER MAIOR DO QUE 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
        CadastroBombona.pesoBruto.requestFocus();
   }

   else{
        bruto = Float.parseFloat(CadastroBombona.pesoBruto.getText());
        liquido = 0.0f;
        excedido = 0.0f;
        capacidade = Integer.parseInt(CadastroBombona.escolhaCapacidade.getSelectedItem()+"");;

            //teste da capacidade para subtracao da tara para compor o peso liquido e validar
            switch(capacidade){
                //caso bombona de 200 l selecionada
                case 200:
                    //peso liquido validado
                    liquido = bruto - 8.04f;

                    //teste de peso excedido (se maior que 35kg na bombona de capacidade de 200 l)
                    if(bruto > 35.00){
                        JOptionPane.showMessageDialog(null, "PESO EXCEDIDO!", "aviso", JOptionPane.WARNING_MESSAGE);
                        excedido = bruto - 35.00f;
                    } else{
                        excedido = 00.00f;
                    }

                    break;

                    //caso bombona de 50 l
                case 50:
                    //peso liquido validado
                    liquido = bruto - 3.24f;

                    //teste de peso excedido (se maior que 10 kg)
                    if(bruto > 10.00){
                        JOptionPane.showMessageDialog(null, "PESO EXCEDIDO!", "aviso", JOptionPane.WARNING_MESSAGE);
                        excedido = bruto - 10.00f;
                    } else{
                        excedido = 00.00f;
                    }

                    break;

                    //caso bombona de 20 l
                case 20:
                    //peso liquido validado
                    liquido = bruto - 0.80f;

                    //teste de peso excedido (se maior que 3.50 kg)
                    if(bruto > 3.50){
                        JOptionPane.showMessageDialog(null, "PESO EXCEDIDO!", "aviso", JOptionPane.WARNING_MESSAGE);
                        excedido = bruto - 3.50f;
                    } else{
                        excedido = 00.00f;
                    }

                    break;

                default:
                    //dá mensagem de aviso e volta o foco para a ecolha de capacidade
                    JOptionPane.showMessageDialog(null, "ESCOLHA A CAPACIDADE DA BOMBONA", "aviso", JOptionPane.WARNING_MESSAGE);
                    CadastroBombona.escolhaCapacidade.requestFocus();
                    break;
            }


            //pega uma  substring do valor líquido pra não ter valor com mais de 2 casas decimais
            if(String.valueOf(liquido).length() >= 5){
                liquido = Float.parseFloat(String.valueOf(liquido).substring(0, 5));
            }

            //pega uma  substring do valor líquido pra não ter valor com mais de 2 casas decimais
            if(String.valueOf(excedido).length() >= 5){
                excedido = Float.parseFloat(String.valueOf(excedido).substring(0, 5));
            }

            //mostra o valor líquido para o operador
            CadastroBombona.pesoLiquido.setText(String.valueOf(liquido));

            //mostra o peso excedido para o operador
            CadastroBombona.pesoExcedido.setText(String.valueOf(excedido));

            //mostra o tipo de bombona para o operador
            CadastroBombona.tipoBombona.setText(tipoBombonaPesada);

            //chama a janela para confirmação
            confirmaBombona dialog = new confirmaBombona(new javax.swing.JFrame(), true);
            dialog.nomeCli.setText(CadastroBombona.cliente.getText());
            dialog.contratoCli.setText(CadastroBombona.contrato.getText());
            dialog.pBruto.setText(CadastroBombona.pesoBruto.getText());
            dialog.pLiquido.setText(CadastroBombona.pesoLiquido.getText());
            dialog.pExcedido.setText(CadastroBombona.pesoExcedido.getText());
            dialog.capacidadeBomb.setText(CadastroBombona.escolhaCapacidade.getSelectedItem()+"");
            dialog.tipoBomb.setText(CadastroBombona.tipoBombona.getText());
            dialog.pesagem.setText(validaCadBomb.tipoPesagem);

            //fecha o form e manda o foco para o botao de bconfirmação do cadastro da bombona
            dialog.btConfirmaCad.requestFocus();
            dialog.setVisible(true);
   }//fim else
}//fecha construtor

public static void limpaCamposCadBomb(){

         //limpa todos os campos do cadastro de bombonas
         CadastroBombona.contrato.setText("");
         CadastroBombona.codigoBomb.setText("");
         CadastroBombona.cliente.setText("");
         CadastroBombona.escolhaCapacidade.setSelectedItem("");
         CadastroBombona.pesoBruto.setText("");
         CadastroBombona.pesoLiquido.setText("");
         CadastroBombona.pesoExcedido.setText("");
         CadastroBombona.tipoBombona.setText("");
         CadastroBombona.contrato.requestFocus();

}

//GRAVA OS DADOS DE PESO BOMBONA
public static void gravaDadosBomb(){
try{
    //carrega hora
    Data.le_hora();
 

String sqlInsert = "insert into cad_bombona (cod_cli" +
            ", contrato" +
            ", matricula_usuario" +
            ", capacidade_bomb" +
            ", peso_bruto" +
            ", peso_liquido" +
            ", peso_excedido" +
            ", tipo_bomb" +
            ", data_pesagem_bomb" +
            ", turno_pesagem" +
            ", tipo_pesagem" +
            ", hora_pesagem_bomb"+
            ", situacao_reg)"+
            //valores dos campos
            "values (" +codigoCliente+                          //codigo do cliente
            ", "+confirmaBombona.contratoCli.getText()+         //contrato
            ", '"+validaLogin.MATRICULA+"'"+                    //matricula usuario
            ", "+confirmaBombona.capacidadeBomb.getText()+      //capacidade da bombona
            ", "+confirmaBombona.pBruto.getText()+              //peso bruto
            ", "+confirmaBombona.pLiquido.getText()+            //peso liquido
            ", "+confirmaBombona.pExcedido.getText()+           //peso excedido
            ", '"+tipoBombonaPesada+"'"+                        //tipo bombona(tipo: contrato, extra ou desconhecido)
            ", '"+validaLogin.DATAFORMAT+"'"+                   //data
            ", '"+validaLogin.TURNO+"'" +                       //turno
            ", '"+tipoPesagem+"'" +                             //tipo de pesagem (em dia ou acumulada)
            ", '"+Data.horaAtual+"'" +                          //hora
            ", 'G')";                                           //G = GRAVADO

    int grava = con_bombona.statement.executeUpdate(sqlInsert);

    //se conseguiu gravar, retorna 1 e entra no if
    if(grava == 1){
    JOptionPane.showMessageDialog(null, "GRAVAÇÃO EFETUADA COM SUCESSO.");

        atualizaPainel();//atualiza painel (e o resultset no final deste metodo)
        preencherJtable();//atualiza tabela
        limpaCamposCadBomb();//zera valores
        CadastroBombona.pesarAcumuladas.setEnabled(true);

    }
}//fim try

catch(Exception e){
    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR GRAVAR O REGISTRO."+ e, "erro", JOptionPane.ERROR_MESSAGE);
}


}//fim grava dados

public static void atualizaPainel(){
        
        try{

                //pega o total bruto do turno
                String sql = "select sum(peso_bruto) as 'total bruto' from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalBruto.setText(con_bombona.resultset.getString("total bruto"));

                //pega o total liquido do turno
                sql = "select sum(peso_liquido) as 'total liquido' from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalLiquido.setText(con_bombona.resultset.getString("total liquido"));

                //pega o total excedido do turno
                sql = "select sum(peso_excedido) as 'total excedido' from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalExcedido.setText(con_bombona.resultset.getString("total excedido"));


                //pega o total de bombonas de contrato do turno
                sql = "select count(tipo_bomb) as 'total contrato' from cad_bombona where tipo_bomb = 'contrato' and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalBContrato.setText(con_bombona.resultset.getString("total contrato"));

                //pega o total de bombonas extras do turno
                sql = "select count(tipo_bomb) as 'total extra' from cad_bombona where tipo_bomb = 'extra' and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalBExtra.setText(con_bombona.resultset.getString("total extra"));

                //pega o total de bombonas desconhecidas do turno
                sql = "select count(tipo_bomb) as 'total Descon.' from cad_bombona where tipo_bomb = 'Descon.' and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalDesconhedida.setText(con_bombona.resultset.getString("total Descon."));

                //pega o total de bombonas acumuladas do turno
                sql = "select count(tipo_pesagem) as 'total acumulada' from cad_bombona where tipo_pesagem = 'Acumulada' and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalAcumulada.setText(con_bombona.resultset.getString("total acumulada"));

                //pega o total de bombonas em dia pesadas no turno
                sql = "select count(tipo_pesagem) as 'total em dia' from cad_bombona where tipo_pesagem = 'Em dia' and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.totalEmDia.setText(con_bombona.resultset.getString("total em dia"));

                //pega o total de bombonas pesadas no turno
                sql = "select count(contrato) as 'total bombonas' from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G'";
                con_bombona.executeSQL(sql);
                con_bombona.resultset.first();
                CadastroBombona.qtTotal.setText(con_bombona.resultset.getString("total bombonas"));

                atualizaResultset();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "PAINEL NÃO FOI ATUALIZADO.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
}//fim atualiza painel

public static void preencherJtable(){

int x = 1;//criada para pegar o primeiro registro
//Cadastrobombona.tabelaInci.getColumnModel().getColumn(0).setPreferredWidth(20); ajusta largura das colunas

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) CadastroBombona.tabelaBombona.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

    try {
       
        do{
             if(x <= 1)//só no primeiro loop pega o primeiro registro, depois do 1° vai pegando os próximos até quando tiver
                con_bombona.resultset.first();

                //vai add linhas
                //equivale a uma linha na minha tabela Bombona
                modelo.addRow(new Object[]{con_bombona.resultset.getString("num_seq_bomb"),//codigo
                                           con_bombona.resultset.getString("contrato"),//contrato
                                           con_bombona.resultset.getString("capacidade_bomb"),//capacidade
                                           con_bombona.resultset.getString("peso_bruto"),//peso bruto
                                           con_bombona.resultset.getString("peso_liquido"),//peso liquido
                                           con_bombona.resultset.getString("peso_excedido"),//peso excedido
                                           con_bombona.resultset.getString("tipo_bomb"),//tipo de bombona
                                           con_bombona.resultset.getString("tipo_pesagem"),//tipo de pesagem
                                           con_bombona.resultset.getString("hora_pesagem_bomb")});//hora

             x++;//add mais um no x e nao chama mais o if para ir pro primeiro registro

        }while(con_bombona.resultset.next());//enquanto tiver dados preenche o jtable com os valores
        
        //depois que finaliza atualiza o resultset
        atualizaResultset();
    }

    catch (Exception e) {
         JOptionPane.showMessageDialog(null, "NENHUMA BOMBONA PESADA NESTE TURNO");
    }

}//fim preencherJtable

public static void pesquisaCliente(){

        //validando o campo do contrato (se vazio).
        if(CadastroBombona.contrato.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "DIGITE O NÚMERO DO CONTRATO!", "aviso", JOptionPane.WARNING_MESSAGE);
                CadastroBombona.contrato.requestFocus();
                tentativas++;
                return;
        }

        //validando o campo de contrato (se preenchido)
        else{

            try{

            Integer.parseInt(CadastroBombona.contrato.getText());
            
            con_bombona.executeSQL("select * from cliente where contrato_cli = "+CadastroBombona.contrato.getText());
            con_bombona.resultset.first();

            //pega o nome, codigo e o total de bombona de contrato do cliente (200, 50 e 20 litros)
            codigoCliente = con_bombona.resultset.getString("cod_cli");
            nomeCliente = con_bombona.resultset.getString("nome_cli");
            //TOTAL DE BOMBONAS
            totalBombonaContrato    = con_bombona.resultset.getInt("qt_bomb_total");
            //TOTAL DE BOMBONAS DE 200
            totalBombonaContrato200 = con_bombona.resultset.getInt("qt_bomb200");
            //TOTAL DE BOMBONAS DE 50
            totalBombonaContrato50  = con_bombona.resultset.getInt("qt_bomb50");
            //TOTAL DE BOMBONAS DE 20
            totalBombonaContrato20  = con_bombona.resultset.getInt("qt_bomb20");
            
            //pega a situação pq o cliente suspenso ou cancelado não pode pesar bombona
            situacaoCliente = con_bombona.resultset.getString("situacao");

            if(situacaoCliente.equals("SUSPENSO") || situacaoCliente.equals("CANCELADO")){
                JOptionPane.showMessageDialog(null, "O cliente "+nomeCliente+", contrato "+CadastroBombona.contrato.getText()+" encontra - se "+situacaoCliente+"\n" +
                                                    "e não pode registrar o peso da bombona.\n\nSepare esta bombona e informe " +
                                                    "ao gerente o acontecido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                CadastroBombona.contrato.setText("");
                CadastroBombona.contrato.requestFocus();
                return;
            }

            else{

                //habilitaCampos botao de pesar bomnobas acumuladas
                CadastroBombona.pesarAcumuladas.setEnabled(false);
                //Mostra o nome do cliente no campo nome/razão social
                CadastroBombona.cliente.setText(nomeCliente);
                //manda o fico p/ combo escolha a capacidade da bombona
                CadastroBombona.escolhaCapacidade.requestFocus();

                //zera o contador de tentativas se ocorreu tudo bem
                tentativas = 0;

            }
            
            }
            catch(Exception e){

                if(tentativas >= 2){
                
                    JOptionPane.showMessageDialog(null, "TENTATIVAS EXCEDIDAS, TENTE ACHAR O CLIENTE NA TELA DE LOCALIZAÇÃO!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    
                    //limpa os campo
                    limpaCamposCadBomb();

                    //mostra a janela
                    alertaBombona dialog = new alertaBombona(new javax.swing.JFrame(), true);
                    dialog.setVisible(true);
                    tentativas = 0;
            
                }
                else{
                    JOptionPane.showMessageDialog(null, "CONTRATO INVÁLIDO, VARIFIQUE O NÚMERO DIGITADO", "Não encontrado", JOptionPane.ERROR_MESSAGE);
                    //CadastroBombona.contrato.setText("");
                    CadastroBombona.contrato.requestFocus();
                    //adiciona 1  a cada tentativ errada
                    tentativas++;

                }
            }

        }//fim else

}//fim pesquisa cliente

public static void atualizaResultset(){
    try{
    //atualiza o resultset
    con_bombona.executeSQL("select * from cad_bombona where (data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"') and (turno_pesagem = '"+validaLogin.TURNO+"') and (situacao_reg = 'G') order by num_seq_bomb");
    con_bombona.resultset.first();
    //con_bombona.resultset.next();
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "ATUALIZAÇÃO NÃO EFETUADA", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}//fim atualiza resultset

public static void btNavega(){
        try{
            //atualizaResultset();

            if(direcao.equals("primeiro"))
                con_bombona.resultset.first();

            else if(direcao.equals("anterior"))
                con_bombona.resultset.previous();

            else if(direcao.equals("proximo"))
                con_bombona.resultset.next();

            else
                con_bombona.resultset.last();

            //mostra os dados
            mostrarDados();
        }
        catch(Exception e){

        }
}

public static void mostrarDados(){
            try{

            //guarda o valor atual do resultset antes de buscar o cliente e perder a contagem
            int guardaValorResultset = con_bombona.resultset.getRow();

            CadastroBombona.codigoBomb.setText(con_bombona.resultset.getString("num_seq_bomb"));
            CadastroBombona.contrato.setText(con_bombona.resultset.getString("contrato"));
            CadastroBombona.escolhaCapacidade.setSelectedItem(con_bombona.resultset.getString("capacidade_bomb"));
            CadastroBombona.pesoBruto.setText(con_bombona.resultset.getString("peso_bruto"));
            CadastroBombona.pesoLiquido.setText(con_bombona.resultset.getString("peso_liquido"));
            CadastroBombona.pesoExcedido.setText(con_bombona.resultset.getString("peso_excedido"));
            CadastroBombona.tipoBombona.setText(con_bombona.resultset.getString("tipo_bomb"));


                con_bombona.executeSQL("select * from cliente where contrato_cli = "+CadastroBombona.contrato.getText());
                con_bombona.resultset.first();
                CadastroBombona.cliente.setText(con_bombona.resultset.getString("nome_cli"));


            //volta a selecionar o cadastro de bombona
            con_bombona.executeSQL("select * from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' order by num_seq_bomb");

            //retorna a linha que estrava (continuando a busca...)
            con_bombona.resultset.absolute(guardaValorResultset);

            CadastroBombona.btNovo.setEnabled(true);
            CadastroBombona.btExcluir.setEnabled(true);

            }

            catch(Exception e){
                if(CadastroBombona.navega == 1)
                    JOptionPane.showMessageDialog(null, "VOCÊ JÁ ESTÁ NO PRIMEITO REGISTRO", "aviso", JOptionPane.WARNING_MESSAGE);

                else if(CadastroBombona.navega == 2)
                    JOptionPane.showMessageDialog(null, "VOCÊ JÁ ESTA NO ÚLTIMO REGISTRO", "aviso", JOptionPane.WARNING_MESSAGE);
                else{
                    CadastroBombona.btNovo.setEnabled(true);
                    CadastroBombona.btExcluir.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "DADOS NÃO LOCALIZADOS", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                CadastroBombona.navega = 0;
            }

}//fim mostrar dados

public static void configuraExclusao(){
    try{
        //PEGA A LINHA ATUAL DO RESULTSET
        int linha = con_bombona.resultset.getRow();

        String sql = "select * from cad_bombona where num_seq_bomb= "+CadastroBombona.codigoBomb.getText();
        con_bombona.executeSQL(sql);
        //atualiza o resultset com um unico registro
        con_bombona.resultset.first();

        //botao que exclui um carrinho
        excluirBombona dialog = new excluirBombona(new javax.swing.JFrame(), true);
        dialog.contrato.setText(CadastroBombona.contrato.getText());
        dialog.codBombona.setText(CadastroBombona.codigoBomb.getText());
        dialog.nomeCliente.setText(CadastroBombona.cliente.getText());
        dialog.pBruto.setText(CadastroBombona.pesoBruto.getText());
        dialog.pLiquido.setText(CadastroBombona.pesoLiquido.getText());
        dialog.pExcedido.setText(CadastroBombona.pesoExcedido.getText());
        dialog.tipoBomb.setText(CadastroBombona.tipoBombona.getText());

        dialog.btConfirmaExclusao.requestFocus();
        dialog.setVisible(true);

        //se nao for excluir volta a linha anterior (antes de abrir tela de exclusao)
        if(exclusao == false){
            con_bombona.executeSQL("select * from cad_bombona where data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"' and turno_pesagem = '"+validaLogin.TURNO+"' and situacao_reg = 'G' order by num_seq_bomb");
            //VOLTA A LINHA QUE PEGOU LÁ EM CIMA
            con_bombona.resultset.absolute(linha);
            exclusao = true;
        }

    }//fim try

    catch(Exception e){
        JOptionPane.showMessageDialog(null, "SELECIONE UM REGISTRO ATRAVÉS DOS BOTÕES DE NAVEGAÇÃO.", "Aviso", JOptionPane.WARNING_MESSAGE);
        CadastroBombona.contrato.requestFocus();
    }
}
//public static void excluiRegistro(){
//    try{
//    //strig sql guarda o codigo de seleção do carrinho
//    String sql = "select * from cad_bombona where num_seq_bomb = "+excluirBombona.codBombona.getText();
//    con_bombona.executeSQL(sql);
//    //atualiza o resultset com um unico registro
//    con_bombona.resultset.first();
//    //String sql guarda o codigo de exclusao
//    sql = "DELETE FROM cad_bombona where num_seq_bomb = "+excluirBombona.codBombona.getText();
//
//    //codigo de exclusao é executado
//    int conseguiu_excluir = con_bombona.statement.executeUpdate(sql);
//
//    if(conseguiu_excluir == 1){
//        JOptionPane.showMessageDialog(null, "EXCLUSÃO EFETUADA COM SUCESSO");
//
//        //atualiza o painel e consequentemente o resultset
//        atualizaPainel();
//        preencherJtable();
//        limpaCamposCadBomb();
//
//    }
//    else{
//        JOptionPane.showMessageDialog(null, "EXCLUSÃO NÃO EFETUADA");
//
//    }
//    }
//    catch(Exception e){
//        JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O REGISTRO", "Erro", JOptionPane.ERROR_MESSAGE);
//    }
//
//}//fim exclui registro

public static void pesquisarClienteContrato(){

    try{
        //ZERA O CONGOBOX...
        alertaBombona.clientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"**********Selecione o cliente**********"}));

        int qtd_clientes = 0;

        con_bombona.executeSQL("select * from cliente where contrato_cli like '%"+alertaBombona.contrato.getText()+"%' order by nome_cli");

        con_bombona.resultset.first();


             do{
                    //mostra o conteúdo na lista
                    alertaBombona.clientes.addItem(con_bombona.resultset.getString("nome_cli"));


                    //cada vez que acha um faz a contagem
                    qtd_clientes++;

            }while(con_bombona.resultset.next());
        


            if(qtd_clientes == 0)
                alertaBombona.contrato.requestFocus();

            else{
                JOptionPane.showMessageDialog(null, "Fim da pesquisa. "+qtd_clientes+" encontrados.");
                alertaBombona.clientes.requestFocus();
            }
    }//fim try


    catch(Exception e){
        JOptionPane.showMessageDialog(null, "NÃO HÁ REGISTRO COMPATÍVEL, VERIFIQUE O NÚMERO DIGITADO E TENTE NOVAMENTE", "aviso", JOptionPane.WARNING_MESSAGE);

    }


}//fim pesquisar cliente por contrato

public static void mostraReferenciasCli(){

    String situacao, diaColeta;
    int qtB20, qtB50, qtB200, qtBhoje, contrato;
    
    try{



    //seleciona a tabela de cliente para pegar a situacao, dias de coleta e a qt de bombonas de 20, 50 e 200
    con_bombona.executeSQL("select * from cliente where nome_cli = '"+alertaBombona.clientes.getSelectedItem().toString()+"' order by nome_cli");
    con_bombona.resultset.first();
    
    situacao = con_bombona.resultset.getString("situacao");
    diaColeta = con_bombona.resultset.getString("dia_de_coleta");
    qtB20 = con_bombona.resultset.getInt("qt_bomb20");
    qtB50 = con_bombona.resultset.getInt("qt_bomb50");
    qtB200 = con_bombona.resultset.getInt("qt_bomb200");
    contrato = con_bombona.resultset.getInt("contrato_cli");
    
   
    //seleciona a tabela de bombona para pegar a qtd de vezes que esse contrato pesou hoje
    con_bombona.executeSQL("select count(cod_cli) as 'qtPesada' from cad_bombona where contrato = "+contrato+" and data_pesagem_bomb = '"+validaLogin.DATAFORMAT+"'");
    con_bombona.resultset.first();
    //pega a qt pesaa hoje
    qtBhoje = con_bombona.resultset.getInt("qtPesada");
    
    //envia dados para aletaBombona
    alertaBombona.tf_situacao.setText(situacao);
    alertaBombona.tf_diasColeta.setText(diaColeta);
    alertaBombona.tf_qtBombPesada.setText(""+qtBhoje);
    alertaBombona.tf_qtBomb20.setText(""+qtB20);
    alertaBombona.tf_qtBomb50.setText(""+qtB50);
    alertaBombona.tf_qtBomb200.setText(""+qtB200);
    //mostra oo contrato do cliente que esta sendo mostrado
     alertaBombona.contratoCliente.setText(contrato+"");
    
    
    }
    catch(Exception e){

    if(alertaBombona.clientes.getSelectedItem().toString().equals("***********Selecione o cliente***********")){
        alertaBombona.contratoCliente.setText("");
        alertaBombona.tf_situacao.setText("");
        alertaBombona.tf_diasColeta.setText("");
        alertaBombona.tf_qtBombPesada.setText("");
        alertaBombona.tf_qtBomb20.setText("");
        alertaBombona.tf_qtBomb50.setText("");
        alertaBombona.tf_qtBomb200.setText("");
    }
    }

}//fim mostra referencia cli

public static void habilitaCampos(boolean i){

        CadastroBombona.contrato.setEditable(i);
        CadastroBombona.escolhaCapacidade.setEnabled(i);
        CadastroBombona.pesoBruto.setEditable(i);
        CadastroBombona.confirmaPeso.setEnabled(i);
        CadastroBombona.pesarAcumuladas.setEnabled(i);

}//fim habilitaCampos

public static void cliqueGrid(){
    //pega a linha que foi clicada na TABELA
    int linha = CadastroBombona.tabelaBombona.getSelectedRow();
    //pega o modelo da tabela
    TableModel modelo = (TableModel) CadastroBombona.tabelaBombona.getModel();
    //pega o codigo do cliente que está na linha que foi clicada e coluna 0 (primeira)
    String codigoBombonaClicado =String.valueOf(modelo.getValueAt(linha, 0));
    //var que vai gurdar o codigo pesquisado no banco
    String codigoBombonaPesquisado;
    //vai determinar o fim do laço
    String igual = "n";



    try{

        //vai para o primeiro registro
        con_bombona.resultset.first();

        while(igual.equals("n")){

            codigoBombonaPesquisado = con_bombona.resultset.getString("num_seq_bomb");

            //se o codigo que foi clicado na gride for igual ao que está no banco
            if(codigoBombonaClicado.equals(codigoBombonaPesquisado)){
                mostrarDados();
                validaCadBomb.habilitaCampos(false);
                igual = "s";//sai do laço e mostra dados
            }
            else
                con_bombona.resultset.next();
        }//fim while
    }//fim try

    catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }

}//fim click grid

public static void cancelarRegistro(){

    try{
        // E = EXCLUIDO
        String sql = "UPDATE cad_bombona SET situacao_reg = 'E' where num_seq_bomb = "+excluirBombona.codBombona.getText();

        int conseguiu_excluir = con_bombona.statement.executeUpdate(sql);

        if(conseguiu_excluir == 1){
            JOptionPane.showMessageDialog(null, "EXCLUSÃO EFETUADA COM SUCESSO");

            //atualiza o painel e consequentemente o resultset
            atualizaPainel();
            preencherJtable();
            limpaCamposCadBomb();

        }
        else{
            JOptionPane.showMessageDialog(null, "EXCLUSÃO NÃO EFETUADA");

        }

    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "ERRO AO TENTAR EXCLUIR O REGISTRO.\n\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
    }


}

public static void defineTipoBombona(){

    // variáveis inteiras que pegam o total de vezes que este cliente ja pesou neste dia especificamente de acordo com a capacidade
    int totalBombonasPesadas = 0; 
    int totalBombonasPesadas200 = 0;
    int totalBombonasPesadas50  = 0;
    int totalBombonasPesadas20  = 0;

    try {

        //se o total de bombonas de contrato for 0 e ocontrato pesquisado for 0
        if(totalBombonaContrato == 0 && CadastroBombona.contrato.getText().equals("0"))
                tipoBombonaPesada = "Descon.";

        else{

                //SE 200 LITROS SELECIONADO
                if(CadastroBombona.escolhaCapacidade.getSelectedItem().toString().equals("200")){

                    //SE FAZENDO PESAGEM ACUMULADA
                    if(CadastroBombona.pesarAcumuladas.isSelected())
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada200' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 200) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (situacao_reg = 'G')");
                    //SE FAZENDO PESAGEM EM DIA
                    else
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada200' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 200) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (tipo_pesagem = 'Em dia') and (situacao_reg = 'G')");

                        con_bombona.resultset.first();

                        //pega quantas vezes este cliente ja pesou neste dia (o que ja registrou no banco de dados) + essa vez que esta sendo pesada
                        totalBombonasPesadas200 = con_bombona.resultset.getInt("qtd pesada200") + 1;

                        //se total de bombonas´pesadas for maior que a quantidade de bombonas de contrato o cliente estará usando bombona extra
                        if(totalBombonasPesadas200 > totalBombonaContrato200)
                            tipoBombonaPesada = "extra";
                        else
                            tipoBombonaPesada = "contrato";

                }

                //SE 50 LITROS SELECIONADO
                else if(CadastroBombona.escolhaCapacidade.getSelectedItem().toString().equals("50")){


                    //SE FAZENDO PESAGEM ACUMULADA
                    if(CadastroBombona.pesarAcumuladas.isSelected())
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada50' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 50) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (situacao_reg = 'G')");
                    //SE FAZENDO PESAGEM EM DIA
                    else
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada50' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 50) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (tipo_pesagem = 'Em dia') and (situacao_reg = 'G')");

                        con_bombona.resultset.first();

                        //pega quantas vezes este cliente ja pesou neste dia (o que ja registrou no banco de dados) + essa vez que esta sendo pesada
                        totalBombonasPesadas50 = con_bombona.resultset.getInt("qtd pesada50") + 1;

                        //se total de bombonas´pesadas for maior que a quantidade de bombonas de contrato o cliente estará usando bombona extra
                        if(totalBombonasPesadas50 > totalBombonaContrato50)
                            tipoBombonaPesada = "extra";
                        else
                            tipoBombonaPesada = "contrato";

                }

                //SE 20 LITROS SELECIONADO
                else if(CadastroBombona.escolhaCapacidade.getSelectedItem().toString().equals("20")){

                    //SE FAZENDO PESAGEM ACUMULADA
                    if(CadastroBombona.pesarAcumuladas.isSelected())
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada20' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 20) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (situacao_reg = 'G')");
                    //SE FAZENDO PESAGEM EM DIA
                    else
                        con_bombona.executeSQL("select count(num_seq_bomb) as 'qtd pesada20' from cad_bombona where (contrato = "+CadastroBombona.contrato.getText()+") and (capacidade_bomb = 20) and (data_pesagem_bomb between '"+dataPesagem+"' and '"+validaLogin.DATAFORMAT+"') and (tipo_pesagem = 'Em dia') and (situacao_reg = 'G')");

                        con_bombona.resultset.first();

                    //pega quantas vezes este cliente ja pesou neste dia (o que ja registrou no banco de dados) + essa vez que esta sendo pesada
                    totalBombonasPesadas20 = con_bombona.resultset.getInt("qtd pesada20") + 1;

                    //se total de bombonas´pesadas for maior que a quantidade de bombonas de contrato o cliente estará usando bombona extra
                    if(totalBombonasPesadas20 > totalBombonaContrato20)
                        tipoBombonaPesada = "extra";
                    else
                        tipoBombonaPesada = "contrato";

                }
        }//fim else

    }

    catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERRO NA CONTAGEM PARA DEFINIÇÃO DE BOMBONA.\nESTA BOMBONA SERÁ DEFINIDA AUTOMATICAMENTE COMO DE CONTRATO", "Aviso", JOptionPane.WARNING_MESSAGE);
        tipoBombonaPesada = "contrato";
    }


}//fim define tipo de bombona

}

