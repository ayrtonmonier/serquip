/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.*;
import Cadastro.*;
import java.awt.Color;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import principal.telaPrincipal;



/**
 *
 * @author Ayrton Monier
 */
public class validaCadCli {
String data;
private static conexao con_cliente, con_cidades, con_bairros;// obj conexao
public static boolean grava = false;//autorização para gravar se a validação de campos for ok
public static String direcao;//direção da navegação
public static boolean limpaDiaColeta = true;//usado como condição, serve para limpar o campo o dia da coleta (tela de clientes)se for a primeira vez que abre a tela de dia da coleta
public static String ordenacao = "nome_cli";
public static String selecaoSituacao = "'Ativo'";//seleciona de início os atívos
public static String situacaoInicial;//grava a situação que esta no textfield quando clicar no i ou suspender cliente
public static boolean modificado = false;//gravacao de dias de coleta "outro dia"
public static boolean campoVazio;//verifica se o campo da tela suspesao esta preenchido
public static boolean gravadoNoBanco = false;
public static boolean atualizaDadosManutencao = false;
public static String usuarioContrato;

public static boolean soNumeroNoContrato = true;//quando for true é pq o contrato é composto somente por números

//variáveis estáticas que guardarao datas de período de coleta (tela de clientes)
public static String dataInicialColeta;
public static String dataFimColeta;

//variáveis que guardam valores do form suspensao (tela de suspensao e cancelamento)
public static String motivo;
public static String dataCanSus;//tambem serve p/ data de cancelamento
public static String dataRetorno;

//VARIAVEL QUE GUARDA MSG DE ERRO
public static String msg = "";

public validaCadCli(){


}
//metodo chamado na chamada d form cliente
public static void iniciaComponentes(){
    //conecta a tabela de bairos e cidades (relacionan - se)
    con_bairros = new conexao();
    con_bairros.conecta();
    

    //conexao com banco na tabela de cidades
    con_cidades = new conexao();
    con_cidades.conecta();
    
    //conexao com o banco com tabela de clientes
    con_cliente = new conexao();
    con_cliente.conecta();

    preencherListaDeCidades();
    preencherListaDeBairros();

    selecaoSituacao = "'Ativo'";
    //variáveis que guardam valores do form suspensao
    zeraDatas_can_sus();
    atualizaResultset();
    preencherTabela();//ja atualiza o resultset
    mostraDadosCadCli();
    btMostraInfo();


}
public static void LimpaCamposCadCli(){
        //limpa todos os campos
        CadastroCliente.codigoCli.setText("");
        CadastroCliente.nomeCli.setText("");
        CadastroCliente.ruaCli.setText("");
        CadastroCliente.bairroCli.setSelectedItem("");
        CadastroCliente.cidadeCli.setSelectedItem("");
        CadastroCliente.ufCli.setSelectedItem("");
        CadastroCliente.foneCli.setText("");
        CadastroCliente.responsavelCli.setText("");
        CadastroCliente.ptReferenciaCli.setText("");
        CadastroCliente.obs.setText("");
        CadastroCliente.hrFun_de.setText("");
        CadastroCliente.hrFunc_a.setText("");
        CadastroCliente.contratoCli.setText("");
        CadastroCliente.diaColeta.setText("");
        CadastroCliente.qtBomb200.setSelectedItem(""+0);
        CadastroCliente.qtBomb50.setSelectedItem(""+0);
        CadastroCliente.qtBomb20.setSelectedItem(""+0);
        CadastroCliente.situacaoCli.setText("ATIVO");
        CadastroCliente.inicioColeta.setSelectedItem("");
        CadastroCliente.fimColeta.setSelectedItem("");
        CadastroCliente.qtTotalBomb.setText(""+0);
        
        btMostraInfo();

}

public static void botaoInformaSituacao(){
        situacaoInicial = CadastroCliente.situacaoCli.getText();//pega o texto da caixa de situação do cad cli se no caso for fechar vai precisar

        //se suspenso puxa os dados
        if(CadastroCliente.situacaoCli.getText().equals("SUSPENSO")){
            String diaRet;
            SuspensaoCli dialog = new SuspensaoCli(new javax.swing.JFrame(), true);

            try{//fazer im metodo so para preecher os campos da suspensao cli

                con_cliente.executeSQL("select * from cliente where cod_cli = "+CadastroCliente.codigoCli.getText());
                con_cliente.resultset.first();

                //configura as datas do banco para dd/mm/aaaa
                formataDataAreaTexto(con_cliente.resultset.getString("dia_cansus"), "dia de suspensao");
                    //testa o dia de retorno para ver se o é uma suspensão por tempo indeterminado
                    try{
                        diaRet = con_cliente.resultset.getString("dia_retorno");
                    }
                    catch(Exception e){
                        diaRet = "0000-00-00";
                    }

                //se a data de retorno for 0000-00-00 é pq a suspensão é indeterminada
                if(diaRet.equals("0000-00-00")){
                    dialog.rb_pIndeterminado.setSelected(true);
                    dialog.labelRetorno.setVisible(false);
                    dialog.diaRetorno.setVisible(false);
                    dialog.btAlterar.setVisible(false);
                }
                //senão a data será enviada para esse método e ele tratara para o formato dd/mm/aaaa
                else{
                    formataDataAreaTexto(diaRet, "data de retorno");
                    dialog.diaRetorno.setText(dataRetorno);
                }

                //nao deixa editar
                dialog.motivoSuspensao.setEditable(false);
                dialog.diaSuspensao.setEditable(false);
                dialog.diaRetorno.setEditable(false);

                //mostra os dados
                dialog.contrato.setText(con_cliente.resultset.getString("contrato_cli"));
                dialog.cliente.setText(con_cliente.resultset.getString("nome_cli"));
                dialog.motivoSuspensao.setText(con_cliente.resultset.getString("motivo"));
                dialog.diaSuspensao.setText(dataCanSus);
                

                //nao salva ao sair, só se clicar em alterar
                modificado = false;

                //mostra tela
                atualizaResultset();
                dialog.setVisible(true);
                return;
            }
            catch(Exception e){
                             System.out.println(e.getLocalizedMessage());
            }
                        
        }
        //se caixa de selecao setar Cancelado chama tela de suspensao de cliente puxando os dados do bd.
        if(CadastroCliente.situacaoCli.getText().equals("CANCELADO")){

            CancelamentoCli dialog = new CancelamentoCli(new javax.swing.JFrame(), true);

            try{

                con_cliente.executeSQL("select * from cliente where cod_cli = "+CadastroCliente.codigoCli.getText());
                con_cliente.resultset.first();
                
                //configura as datas do banco para dd/mm/aaaa
                formataDataAreaTexto(con_cliente.resultset.getString("dia_cansus"), "dia de cancelamento");

                //nao deixa editar
                dialog.motivoCancelamento.setEditable(false);
                dialog.diaCancelamento.setEditable(false);

                //mostra os dados
                dialog.contrato.setText(con_cliente.resultset.getString("contrato_cli"));
                dialog.cliente.setText(con_cliente.resultset.getString("nome_cli"));
                dialog.motivoCancelamento.setText(con_cliente.resultset.getString("motivo"));
                dialog.diaCancelamento.setText(dataCanSus);

                //nao salva ao sair, só se clicar em alterar
                modificado = false;

                //mostraTela
                atualizaResultset();
                dialog.setVisible(true);
            }
            catch(Exception e){

            }
                        
        }

                

}//fim botaoInformaSituacao

public static void calculaTotalBomb(){
        int b200 = Integer.parseInt(CadastroCliente.qtBomb200.getSelectedItem().toString());
        int b50  =  Integer.parseInt(CadastroCliente.qtBomb50.getSelectedItem().toString());
        int b20  =  Integer.parseInt(CadastroCliente.qtBomb20.getSelectedItem().toString());
        
        //cada seleção soma logo as três caixas
        CadastroCliente.somaBomb = b200 + b50 + b20;
        
        CadastroCliente.qtTotalBomb.setText(String.valueOf(CadastroCliente.somaBomb));
}

public static void fechaConexao(){

    con_cliente.desconecta();
    con_cidades.desconecta();
    con_bairros.desconecta();
    
    CadastroCliente.gravandoCliente = false;

}

public static void validaCampos(){
        grava = false;
                
        //limpa todos os campos
        if(CadastroCliente.nomeCli.getText().isEmpty()){
            msg = "Informe o nome do cliente";
            CadastroCliente.nomeCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.ruaCli.getText().isEmpty()){
            msg = "Informe a rua do cliente";
            CadastroCliente.ruaCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.bairroCli.getSelectedItem().toString().isEmpty()){
            msg = "Informe o bairro do cliente";
            CadastroCliente.bairroCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.cidadeCli.getSelectedItem().toString().isEmpty()){
            msg = "Informe a cidade do cliente";
            CadastroCliente.cidadeCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.ufCli.getSelectedItem().toString().isEmpty()){
            msg = "Informe a UF da cidade do cliente";
            CadastroCliente.ufCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.foneCli.getText().equals("(__)____-____")){
            msg = "Informe o telefone para contato";
            CadastroCliente.foneCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.responsavelCli.getText().isEmpty()){
            msg = "Informe o nome do responsável";
            CadastroCliente.responsavelCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.ptReferenciaCli.getText().isEmpty()){
            msg = "Informe o ponto de referência do cliente";
            CadastroCliente.ptReferenciaCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.hrFun_de.getText().equals("__:__")){
            msg = "Informe o horário que abre o estabelecimento";
            CadastroCliente.hrFun_de.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.hrFunc_a.getText().equals("__:__")){
            msg = "Informe o horário que fecha o estabelecimento";
            CadastroCliente.hrFunc_a.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.contratoCli.getText().isEmpty()){
            msg = "Informe o número do contrato";
            CadastroCliente.contratoCli.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.diaColeta.getText().isEmpty()){
            msg = "Informe o(s) dia(s) de coleta";
            CadastroCliente.diaColeta.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.inicioColeta.getSelectedItem().equals("")){
            msg = "Informe a data inicial do período de coleta";
            CadastroCliente.inicioColeta.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.fimColeta.getSelectedItem().equals("")){
            msg = "Informe a data final do período de coleta";
            CadastroCliente.fimColeta.requestFocus();
            grava = false;
        }
        else if(CadastroCliente.qtTotalBomb.getText().equals("0")){
            msg = "Informe a quantidade de bombonas de contrato deste cliente";
            CadastroCliente.qtBomb200.requestFocus();
            grava = false;
        }
        //verificaContrato se o campos de codigo estiver vazio ou preenchido
        else if(CadastroCliente.codigoCli.getText().isEmpty() || CadastroCliente.codigoCli.getText().length() > 0){
             
             //System.out.println("entrou para verificar contrato..");
             verificaContrato();//retorna grava = true ou false

             if(grava == false){

                //se o contrato so tiver números é pq ele ta igual a outro ja existente
                if(soNumeroNoContrato == true)
                    msg = "Este contrato pertence a:\n\n "+usuarioContrato;
                
                //se o contrato contiver caracteres não numeros o sistema nao deixa gravar
                else
                    msg = "O campo contrato só pode ser preenchido por números";

                CadastroCliente.contratoCli.requestFocus();
                CadastroCliente.contratoCli.setSelectedTextColor(Color.red);
                CadastroCliente.contratoCli.selectAll();


            }
        }

        //ve se teve algum erro acima
        if(grava == false){
            JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);

            if(msg.equals("Informe o(s) dia(s) de coleta"))
               diaColeta();//abre logo a tela para escolha dos dias de coleta
        }

        //se não ocorrer nenhum imprevisto a gravação pode ser efetuada
        else{

            if(grava == true)
                
                return;//tudo certo

            else{

                //if(grava == false){
                    JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);

                if(msg.equals("Informe o(s) dia(s) de coleta"))
                    diaColeta();//abre logo a tela para escolha dos dias de coleta
               // }
            }
        }
}

public static void gravaDadosCli(){

        
        try{
        //carrega hora
        //Data dat = new Data();
        //dat.le_hora();

        String SqlInsert = "insert into cliente(nome_cli"+
                           ", rua"+
                           ", bairro"+
                           ", cidade"+
                           ", uf"+
                           ", fone"+
                           ", responsavel"+
                           ", pReferencia"+
                           ", inicio_coleta"+
                           ", fim_coleta"+
                           ", obs"+
                           ", contrato_cli" +
                           ", situacao" +
                           ", h_abre" +
                           ", h_fecha" +
                           ", dia_de_coleta" +
                           ", qt_bomb200" +
                           ", qt_bomb50" +
                           ", qt_bomb20" +
                           ", qt_bomb_total" +
                           ", motivo" +
                           ", dia_cansus" +
                           ", dia_retorno" +
                           ", data_cadastro) " +
                           "values("+
                           "'"+CadastroCliente.nomeCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.ruaCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.bairroCli.getSelectedItem().toString().toUpperCase()+"'" +
                           ", '"+CadastroCliente.cidadeCli.getSelectedItem().toString().toUpperCase()+"'" +
                           ", '"+CadastroCliente.ufCli.getSelectedItem().toString().toUpperCase()+"'" +
                           ", '"+CadastroCliente.foneCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.responsavelCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.ptReferenciaCli.getText().toUpperCase()+"'" +
                           ", "+CadastroCliente.inicioColeta.getSelectedItem()+//inicioColeta
                           ", "+CadastroCliente.fimColeta.getSelectedItem()+//fimColeta
                           ", '"+CadastroCliente.obs.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.contratoCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.situacaoCli.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.hrFun_de.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.hrFunc_a.getText().toUpperCase()+"'" +
                           ", '"+CadastroCliente.diaColeta.getText().toUpperCase()+"'" +
                           ", "+CadastroCliente.qtBomb200.getSelectedItem()+
                           ", "+CadastroCliente.qtBomb50.getSelectedItem()+
                           ", "+CadastroCliente.qtBomb20.getSelectedItem()+
                           ", "+CadastroCliente.qtTotalBomb.getText()+
                           ", '"+motivo.toUpperCase()+"'"+
                           ", '"+dataCanSus+"'"+//data cancelamento\suspensão
                           ", '"+dataRetorno+"'" +//data de retorno
                           ", '"+validaLogin.DATAFORMAT+"')";//data cadastro

        int gravaNoBanco = con_cliente.statement.executeUpdate(SqlInsert);

        if(gravaNoBanco == 1){
            JOptionPane.showMessageDialog(null, "Gravação efetuada com sucesso!");

            atualizar();
            gravadoNoBanco = true;//indica que gravou
            zeraDatas_can_sus();
            //verifica se tem alguma data de periodo de coleta expirado pu suspensao expirada
            telaPrincipal.verificaExpirados();
        }

        }//fim try
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao tentar gravar o registro\nErro: "+e, "erro", JOptionPane.ERROR_MESSAGE);
        }

//    }//fim if

}//fim grava dadosCli


public static void navega(){
        try{
            
            if(direcao.equals("primeiro"))
                con_cliente.resultset.first();

            else if(direcao.equals("anterior"))
                con_cliente.resultset.previous();

            else if(direcao.equals("proximo"))
                con_cliente.resultset.next();

            else
                con_cliente.resultset.last();

            //mostra os dados
            mostraDadosCadCli();
            btMostraInfo();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao navegar: "+e);
        }
}//fim navega

public static void mostraDadosCadCli(){

try{
    //configura as datas de inicio e fim da coleta para serem mostradas nos campos no formato: dd/mm/aaaa

                           //mostra os dados
                           CadastroCliente.codigoCli.setText(con_cliente.resultset.getString("cod_cli"));
                           CadastroCliente.nomeCli.setText(con_cliente.resultset.getString("nome_cli"));
                           CadastroCliente.ruaCli.setText(con_cliente.resultset.getString("rua"));
                           CadastroCliente.ufCli.setSelectedItem(con_cliente.resultset.getString("uf"));//a lista de cidades por uf será preenchida
                           CadastroCliente.cidadeCli.setSelectedItem(con_cliente.resultset.getString("cidade"));//seta a cidade referente
                           CadastroCliente.bairroCli.setSelectedItem(con_cliente.resultset.getString("bairro"));//seta o bairro referente
                           CadastroCliente.foneCli.setText(con_cliente.resultset.getString("fone"));
                           CadastroCliente.responsavelCli.setText(con_cliente.resultset.getString("responsavel"));
                           CadastroCliente.ptReferenciaCli.setText(con_cliente.resultset.getString("pReferencia"));
                           CadastroCliente.inicioColeta.setSelectedItem(con_cliente.resultset.getString("inicio_coleta"));//mostra inicio da coleta
                           CadastroCliente.fimColeta.setSelectedItem(con_cliente.resultset.getString("fim_coleta"));//mostra fim da coleta
                           CadastroCliente.obs.setText(con_cliente.resultset.getString("obs"));
                           CadastroCliente.contratoCli.setText(con_cliente.resultset.getString("contrato_cli"));
                           CadastroCliente.situacaoCli.setText(con_cliente.resultset.getString("situacao"));
                           CadastroCliente.hrFun_de.setText(con_cliente.resultset.getString("h_abre"));
                           CadastroCliente.hrFunc_a.setText(con_cliente.resultset.getString("h_fecha"));
                           CadastroCliente.diaColeta.setText(con_cliente.resultset.getString("dia_de_coleta"));
                           CadastroCliente.qtBomb200.setSelectedItem(con_cliente.resultset.getString("qt_bomb200"));
                           CadastroCliente.qtBomb50.setSelectedItem(con_cliente.resultset.getString("qt_bomb50"));
                           CadastroCliente.qtBomb20.setSelectedItem(con_cliente.resultset.getString("qt_bomb20"));
                           CadastroCliente.qtTotalBomb.setText(con_cliente.resultset.getString("qt_bomb_total"));


                           //inabilita o campo contrato
                           CadastroCliente.contratoCli.setEditable(false);
                           habilitaBotoes(true);
                           
                           //se no campo de situação for ativo os campos poderao ser editados. caso contrario, não.
                           if(CadastroCliente.situacaoCli.getText().equals("ATIVO")){
                               EditarCampos(true);
                               CadastroCliente.btGravaCli.setEnabled(true);

                           }

                           //se o cliente for suspenso ou cancelado
                           else{

                               EditarCampos(false);//inabilita os campos
                               CadastroCliente.btSuspenderCli.setEnabled(false);//o botão suspender fica inabilitado
                               CadastroCliente.btExcluirCli.setEnabled(false);//o botao excluir fica inabilitado
                               CadastroCliente.btGravaCli.setEnabled(false);//o botão gravar fica inabilitado

                               //se a situação do cliente for suspenso
                               if(CadastroCliente.situacaoCli.getText().equals("SUSPENSO"))

                                   CadastroCliente.btCancelarCli.setEnabled(true);//o botão cancelar cliente fica habilitado
                               
                               //se a situacao for cancelado
                               else

                                   CadastroCliente.btCancelarCli.setEnabled(false);// o botão cancelar cliente fica inabilitado

                           }
}//fim try

catch(Exception e){



                if(CadastroCliente.navega == 1){
                    direcao = "primeiro";//posiciona no primeiro registro
                    navega();
                    JOptionPane.showMessageDialog(null, "Você já está no primeiro registro", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

                else if(CadastroCliente.navega == 2){
                    direcao = "ultimo";//posiciona no ultimo registro
                    navega();
                    JOptionPane.showMessageDialog(null, "Você já está no ultimo registro", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                else{

                    CadastroCliente.navega = 0;
                    
                    //limpa todos os campos
                    LimpaCamposCadCli();

                    //limpa o campo situação
                    CadastroCliente.situacaoCli.setText("");


                            //se a situação for ativo e não contiver dados um novo cliente terá que ser registrado
                            if(CadastroCliente.btAtivos.isSelected()){

                                novoCliente();

                                JOptionPane.showMessageDialog(null, "Não há cliente(s) ativos(s)");

                            }

                            //se o cliente for suspenso ou cancelado
                            else{

                                EditarCampos(false);//não deixa editar os campos
                                CadastroCliente.situacaoCli.setText("");//limpa o campo de situação do cliente
                                CadastroCliente.contratoCli.setEditable(false);//inabilita o campo contrato
                                habilitaBotoes(false);//desabilita os botões da tela de clientes
                                CadastroCliente.btGravaCli.setEnabled(false);//o botão de garvar fica inabilitado pois o metodo habilitaBotoes() não inclui o botão de gravarClientes
                               
                                if(CadastroCliente.btSuspensosDeterminados.isSelected() && CadastroCliente.rb_emDia.isSelected())
                                    JOptionPane.showMessageDialog(null, "Não há cliente(s) suspenso(s) com data de retorno determinada (em dia)", "Aviso", JOptionPane.WARNING_MESSAGE);
                                else if(CadastroCliente.btSuspensosDeterminados.isSelected() && CadastroCliente.rb_vencidos.isSelected())
                                    JOptionPane.showMessageDialog(null, "Não há cliente(s) suspenso(s) com data de retorno determinada (vencida)", "Aviso", JOptionPane.WARNING_MESSAGE);
                                else if(CadastroCliente.btSuspensosIndeterminados.isSelected())
                                    JOptionPane.showMessageDialog(null, "Não há cliente(s) suspenso(s) com data de retorno indeterminada", "Aviso", JOptionPane.WARNING_MESSAGE);
                                else if(CadastroCliente.btTodos.isSelected())
                                    JOptionPane.showMessageDialog(null, "Não há cliente(s) registrado(s)", "Aviso", JOptionPane.WARNING_MESSAGE);
                                else
                                    JOptionPane.showMessageDialog(null, "Não há cliente(s) cancelado(s)", "Aviso", JOptionPane.WARNING_MESSAGE);
                             }

                 }//fim else caso o usuário tenha escolhido alguma situação

    }//fim catch
}//fim mostraDados

public static void atualizaResultset(){
        
    try{
        //atualiza o resultset
        con_cliente.executeSQL("select * from cliente where situacao = "+selecaoSituacao+" and situacao != 'DESCONHEC.' order by "+ordenacao);
        con_cliente.resultset.first();
//        btMostraInfo();//linha 829
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Não Atualizado\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}//fim atuaiza resultset

public static void atualizaDados(){

    try{

    String sql = "UPDATE cliente SET nome_cli = '"+CadastroCliente.nomeCli.getText().toUpperCase()+"'" +
                                    ", rua = '"+CadastroCliente.ruaCli.getText().toUpperCase()+"'" +
                                    ", bairro = '"+CadastroCliente.bairroCli.getSelectedItem().toString().toUpperCase()+"'" +
                                    ", cidade = '"+CadastroCliente.cidadeCli.getSelectedItem().toString().toUpperCase()+"'" +
                                    ", uf = '"+CadastroCliente.ufCli.getSelectedItem().toString().toUpperCase()+"'" +
                                    ", fone = '"+CadastroCliente.foneCli.getText()+"'" +
                                    ", responsavel = '"+CadastroCliente.responsavelCli.getText().toUpperCase()+"'" +
                                    ", pReferencia = '"+CadastroCliente.ptReferenciaCli.getText().toUpperCase()+"'" +
                                    ", inicio_coleta = "+CadastroCliente.inicioColeta.getSelectedItem()+
                                    ", fim_coleta = "+CadastroCliente.fimColeta.getSelectedItem()+
                                    ", obs = '"+CadastroCliente.obs.getText().toUpperCase()+"'" +
                                    ", contrato_cli = '"+CadastroCliente.contratoCli.getText()+"'" +
                                    ", situacao = '"+CadastroCliente.situacaoCli.getText().toUpperCase()+"'" +
                                    ", h_abre = '"+CadastroCliente.hrFun_de.getText()+"'" +
                                    ", h_fecha = '"+CadastroCliente.hrFunc_a.getText()+"'" +
                                    ", dia_de_coleta = '"+CadastroCliente.diaColeta.getText().toUpperCase()+"'" +
                                    ", qt_bomb200 = "+CadastroCliente.qtBomb200.getSelectedItem()+
                                    ", qt_bomb50 = "+CadastroCliente.qtBomb50.getSelectedItem()+
                                    ", qt_bomb20 = "+CadastroCliente.qtBomb20.getSelectedItem()+
                                    ", qt_bomb_total = "+CadastroCliente.qtTotalBomb.getText()+
                                    ", motivo = '"+motivo.toUpperCase()+"'" +
                                    ", dia_cansus = '"+dataCanSus+"'" +
                                    ", dia_retorno = '"+dataRetorno+"'" +
                                    " where cod_cli = "+CadastroCliente.codigoCli.getText();
    
    con_cliente.statement.executeUpdate(sql);

    JOptionPane.showMessageDialog(null, "Atualização efetuada com sucesso!");

      gravadoNoBanco = true;//indica que gravou
      atualizar();
      //verifica se tem alguma data de periodo de coleta expirado pu suspensao expirada
      telaPrincipal.verificaExpirados();
    //aki o metodo de inabilitar botoes




}//fim ry
catch(Exception e){
    JOptionPane.showMessageDialog(null, "Não foi possível atualizar o registro, verifique o que foi digitado nos campos\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
}
}//fim atualiza dados

//
public static void mostraDiaColeta(String dia){

if(dia.equals("seg."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"SEG./");
else if(dia.equals("ter."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"TER./");
else if(dia.equals("qua."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"QUA./");
else if(dia.equals("qui."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"QUI./");
else if(dia.equals("sex."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"SEX./");
else if(dia.equals("sab."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"SAB./");
else if(dia.equals("dom."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText()+"DOM./");
else if(dia.equals("quinzenal"))
    CadastroCliente.diaColeta.setText("QUINZENAL");
else if(dia.equals("DezEmDez"))
    CadastroCliente.diaColeta.setText("DEZ EM DEZ DIAS");
else if(dia.equals("alternada"))
    CadastroCliente.diaColeta.setText("ALTERNADA (DIA SIM, DIA NÃO)");
else if(dia.equals("diario"))
    CadastroCliente.diaColeta.setText("DIÁRIO");
else if(dia.equals("outro"))
    CadastroCliente.diaColeta.setText("");

}

public static void retiraDiaColeta(String dia){

if(dia.equals("seg."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceFirst("SEG./", ""));//substitui por ""
else if(dia.equals("ter."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("TER./", ""));
else if(dia.equals("qua."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("QUA./", ""));
else if(dia.equals("qui."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("QUI./", ""));
else if(dia.equals("sex."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("SEX./", ""));
else if(dia.equals("sab."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("SAB./", ""));
else if(dia.equals("dom."))
    CadastroCliente.diaColeta.setText(CadastroCliente.diaColeta.getText().replaceAll("DOM./", ""));
else if(dia.equals("quinzenal"))
    CadastroCliente.diaColeta.setText("");
else if(dia.equals("quinzenal"))
    CadastroCliente.diaColeta.setText("");
else if(dia.equals("DezEmDez"))
    CadastroCliente.diaColeta.setText("");
else if(dia.equals("alternada"))
    CadastroCliente.diaColeta.setText("");
else if(dia.equals("diario"))
    CadastroCliente.diaColeta.setText("");
else if(dia.equals("outro"))
    CadastroCliente.diaColeta.setText("");

}
//limpa as seleções dos check box's
public static void limpa_cb(){
    
    DiaColeta.cb_segunda.setSelected(false);
    DiaColeta.cb_terca.setSelected(false);
    DiaColeta.cb_quarta.setSelected(false);
    DiaColeta.cb_quinta.setSelected(false);
    DiaColeta.cb_sexta.setSelected(false);
    DiaColeta.cb_sabado.setSelected(false);
    DiaColeta.cb_domingo.setSelected(false);

}//fim limpa_cb


//limpa as seleções dos radio butons
public static void limpa_rb(){
    
    DiaColeta.bgPeriodo.clearSelection();//limpa todas as seleções dos radioButons
    DiaColeta.modificado = 1;//FOI MODIFICADO e confirmado
    DiaColeta.ta_outro.setText("");//LIMPA O CAMPO "OUTRO DIA DE COLETA"
    DiaColeta.ta_outro.setEditable(false);//NAO DEIXA EDITAR O CAMPO "OURO DIA DE COLETA"

    //limpa o campo dia de coleta (tela de cadastro de cliente) quando clicar a primeira vez em qualquer checkBox
    if((limpaDiaColeta == true)){
       // DiaColeta.bgPeriodo.clearSelection();//limpa todas as seleções dos radioButons
        limpaDiaColeta = false;
        CadastroCliente.diaColeta.setText("");
    }


}//fim limpra_rb

//chama a tela dia de coleta e configura as seleções de acordo com o texto da caixa de seleção dia de coleta do cadastro de cliente
public static void diaColeta(){

    //variável dColeta recebe o texto da caixa de texto dia de colta do cadastro de clientes
    String dColeta = CadastroCliente.diaColeta.getText();
    
    //instancia da tela de seleção de dia de coleta
    DiaColeta dialog = new DiaColeta(new javax.swing.JFrame(), true);
    
    //variável dSemana guardara uma substring de 0 a 5 de dColeta
    String dSemana;
    //variáveis que gurdam o inicio e o fim da substring
    int contI, contF;

    //se o campo de texto dia de coleta do cadastro de cliente estiver vazio, abre somente a janela sem seleções
    if(dColeta.isEmpty())
            //mostra tela DiaColeta
            dialog.setVisible(true);

    //(1)caso contrario
    else{
                        /*verifica se o texto está escrito Quinzenal ou Dez em Dez dias ou Alternada ou Diário... se algum for correto a tela abre com a
                        respectiva seleção*/
                        if(dColeta.equals("QUINZENAL")){
                            dialog.rbQuinzenal.setSelected(true);
                        }
                        else if(dColeta.equals("DEZ EM DEZ DIAS")){
                            dialog.rbDezEmDez.setSelected(true);
                        }
                        else if(dColeta.equals("ALTERNADA (DIA SIM, DIA NÃO)")){
                            dialog.rbAlternada.setSelected(true);
                        }
                        else if(dColeta.equals("DIÁRIO")){
                            dialog.rbDiario.setSelected(true);
                        }

            //(2)caso contrário...
            else{
                        //pega quant. de caracteres de dColeta
                        int i = dColeta.length();
                        //inicio do indice recebe 0 e fim do indice recebe 5 pois os dias da semana estão padronizados com 5 caract.
                        contI = 0;
                        contF = 5;

                    /*se a qtd de caracteres for menor que cinco o dSemana recebe a tring [valida]. Caso contrario ocorrerá exceção na retirada
                    da substring*/
                    do{
                        if(i < 5)
                        dSemana = "valida";//ja sabemos que nao é nenhum dia da semana dos checkBox [será: "outro dia de coleta"]

                        //se maior ou igual a cinco retira a substring e testa nos ifs abaixo
                        else
                        dSemana = dColeta.substring(contI, contF);

                        if(dSemana.equals("SEG./"))
                                DiaColeta.cb_segunda.setSelected(true);
                        else if(dSemana.equals("TER./"))
                                DiaColeta.cb_terca.setSelected(true);
                        else if(dSemana.equals("QUA./"))
                                DiaColeta.cb_quarta.setSelected(true);
                        else if(dSemana.equals("QUI./"))
                                DiaColeta.cb_quinta.setSelected(true);
                        else if(dSemana.equals("SEX./"))
                                DiaColeta.cb_sexta.setSelected(true);
                        else if(dSemana.equals("SAB./"))
                                DiaColeta.cb_sabado.setSelected(true);
                        else if(dSemana.equals("DOM./"))
                                DiaColeta.cb_domingo.setSelected(true);

                        /*caso a substring não satisfaça os ifs acima o radioButon a ser marcado será o de nome "outro" e recebe logo o texto
                        correspondente*/
                        else{
                                dialog.rbOutro.setSelected(true);
                                dialog.ta_outro.setText(dColeta);
                                //i recebe 0 e não entra na condição abaixo e sai logo do laço
                                i = 0;
                        }

                        if(i >=5){
                                contI += 5;
                                contF += 5;
                                i -= 5;

                                //se entrou nessa condição é pq tem dia válido da semana seg./ter./qua./... entao limpaDiaColeta recebe
                                //false e nao limpa o campo diaColeta (TELA CLIENTE) a primeira vez que abrir a tela de escolha de dia de coleta
                                if(contI == 5)
                                    limpaDiaColeta = false;
                        }
                    //se i for igual a 0 o laço finaliza e sai do else
                    }while(i != 0);


            }//fim segundo else(2)

    //mostra tela DiaColeta com as opções devidamente marcadas ou em branco se caso o dColeta nao contiver texto
    dialog.setVisible(true);

    }//fim else(1)
}//fim preencheSelecaoDiaColeta
public static void excluirRegistro(){

    try{
    
            //faz uma busca no registro de peso de bombonas e vê se tem alguma referencia deste cliente
            String sql = "select count(num_seq_bomb) as 'qtBombonas' from cad_bombona where contrato = "+CadastroCliente.contratoCli.getText();
            con_cliente.executeSQL(sql);
            con_cliente.resultset.first();

            int qt_bombonas_pesadas = con_cliente.resultset.getInt("qtBombonas");

            if(qt_bombonas_pesadas > 0){
                JOptionPane.showMessageDialog(null, "Este cliente tem "+qt_bombonas_pesadas+" bombonas pesadas e não pode ser excluído. Ao invés de ser excluído, irá para a lista de cancelados!", "Aviso", JOptionPane.WARNING_MESSAGE);

                btCancelarCliente();
            }
            //se nao tiver nenhuma referência pode excluir
            else{

                    sql = "select * from cliente where cod_cli = "+CadastroCliente.codigoCli.getText();
                    con_cliente.executeSQL(sql);
                    //posiciona no primeiro registro
                    con_cliente.resultset.first();

                    String nome = "Deletar o cliente: "+con_cliente.resultset.getString("nome_cli")+"\n\nContrato: "+con_cliente.resultset.getString("contrato_cli");

                    int op = JOptionPane.showConfirmDialog(null, nome, "Deletar cliente", JOptionPane.YES_NO_OPTION);

                    if(op == JOptionPane.YES_OPTION){

                        sql = "DELETE FROM cliente WHERE cod_cli ="+CadastroCliente.codigoCli.getText();
                        int conseguiu_excluir = con_cliente.statement.executeUpdate(sql);

                        if(conseguiu_excluir == 1){

                            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

                              atualizar();

                        }
                    }
                    else{
                            atualizar();
                            return;
                    }
          }//fim do else de teste de referencia na tabela de pesagem de bombona
        }//fim try
catch(Exception e){
    JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o registro\nErro: "+e, "Erro", JOptionPane.ERROR_MESSAGE);
}



}//final excluir registro

public static void preencherTabela(){


int x = 1;//criada para pegar o primeiro registro
 //ajusta largura das colunas
CadastroCliente.tabelaCliente.getColumnModel().getColumn(0).setPreferredWidth(5);//codigo
CadastroCliente.tabelaCliente.getColumnModel().getColumn(1).setPreferredWidth(5);//contrato
CadastroCliente.tabelaCliente.getColumnModel().getColumn(2).setPreferredWidth(150);//nome do cliente
CadastroCliente.tabelaCliente.getColumnModel().getColumn(3).setPreferredWidth(80);//bairro
CadastroCliente.tabelaCliente.getColumnModel().getColumn(4).setPreferredWidth(30);//cidade
CadastroCliente.tabelaCliente.getColumnModel().getColumn(5).setPreferredWidth(4);//uf
CadastroCliente.tabelaCliente.getColumnModel().getColumn(6).setPreferredWidth(10);//fone
CadastroCliente.tabelaCliente.getColumnModel().getColumn(7).setPreferredWidth(80);//responsável
CadastroCliente.tabelaCliente.getColumnModel().getColumn(8).setPreferredWidth(10);//situação

//instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
DefaultTableModel modelo = (DefaultTableModel) CadastroCliente.tabelaCliente.getModel();
modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

    try {

        do{
             if(x <= 1)//só no primeiro loop pega o primeiro registro, depois do 1° vai pegando os próximos até quando tiver
                con_cliente.resultset.first();

                //vai add linhas
                //equivale a uma linha na minha grid
                modelo.addRow(new Object[]{con_cliente.resultset.getString("cod_cli"),
                                           con_cliente.resultset.getString("contrato_cli"),
                                           con_cliente.resultset.getString("nome_cli"),
                                           con_cliente.resultset.getString("bairro"),
                                           con_cliente.resultset.getString("cidade"),
                                           con_cliente.resultset.getString("uf"),
                                           con_cliente.resultset.getString("fone"),
                                           con_cliente.resultset.getString("responsavel"),
                                           con_cliente.resultset.getString("situacao")});

             x++;//add mais um no x e nao chama mais o if para ir pro primeiro registro

        }while(con_cliente.resultset.next());//enquanto tiver dados preenche o jtable com os valores

        //depois que finaliza atualiza o resultset
        atualizaResultset();
        preencherListaClientes();

        
    }

    catch (Exception e) {

        modelo = (DefaultTableModel) CadastroCliente.tabelaCliente.getModel();
        modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

        //limpa a lista de clientes
        CadastroCliente.listaClientes.removeAllItems();
        CadastroCliente.lbQtdCli.setText("0 Cliente(s)");

    }


}//fim preencherTabela
public static void ordenacao(String ordem){

    ordenacao = ordem;
    atualizar();

}

public static void vizualizacaoSituacao(String situacao){

    selecaoSituacao = situacao;
    atualizar();

}//fim vizualizaçãoSituação

public static void pesquisarClienteNome(){
        
    try{
               
        //TableModel modelo = (TableModel) CadastroClientee.tabelaCliente.getModel();
        con_cliente.resultset.first();
        String pesquisado, nomeEncontrado = "";
        String igual = "n";
        String nomePesquisado = CadastroCliente.caixaPesquisaNome.getText().toUpperCase();
        int tamanho_pesquisa = CadastroCliente.caixaPesquisaNome.getText().length();
        int tamanho_pesquisado;


        while(igual.equals("n")){
        //teste para saber se o que foi pesquisado no banco tem a qtd de caracteres menor do que o que está sendo pesquisado
        tamanho_pesquisado = con_cliente.resultset.getString("nome_cli").length();
        
            if(tamanho_pesquisado < tamanho_pesquisa)
                con_cliente.resultset.next();

            else{
                pesquisado = con_cliente.resultset.getString("nome_cli").substring(0 , (tamanho_pesquisa));

                if(nomePesquisado.equals(pesquisado)){
                    
                    //pega o nome que encontrou
                    nomeEncontrado = con_cliente.resultset.getString("nome_cli");
                    igual = "s";//sai do laço e mostra dados
                }
                else
                    con_cliente.resultset.next();
            }
        }//fim while

        mostraDadosCadCli();
        btMostraInfo();
        btFiltrarNaTabela();
        CadastroCliente.listaClientes.setSelectedItem(nomeEncontrado);
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Não há registro compatível, verifique o nome digitado.");
    }

}//fim pesquisar cliente por nome

public static void pesquisarClienteContrato(){

    try{

        con_cliente.resultset.first();
        //criada para controlar o laço
        String igual = "n";
        int tamanho_pesquisa = CadastroCliente.caixaPesquisaContrato.getText().length();//fim Index
        int tamanho_pesquisado;
        String pesquisado, nomeEncontrado = "";//o que pesquisou no jtext
        String contratoPesquisado = CadastroCliente.caixaPesquisaContrato.getText().toString();//o que tem na caixa de pesquisa

        
            while(igual.equals("n")){
                //teste para saber se o que foi pesquisado no banco tem a qtd de caracteres menor do que o que está sendo pesquisado
                tamanho_pesquisado = String.valueOf(con_cliente.resultset.getInt("contrato_cli")).length();

                if(tamanho_pesquisado < tamanho_pesquisa)
                    con_cliente.resultset.next();
                else{
                    pesquisado = String.valueOf(con_cliente.resultset.getInt("contrato_cli")).substring(0, (tamanho_pesquisa));
                    //System.out.println("tamanho da pesquisa: "+tamanho_pesquisa+"\ncaixa pesq.: "+contratoPesquisado+" pesq. no banco: "+pesquisado);

                    if(contratoPesquisado.equals(pesquisado)){

                        nomeEncontrado = con_cliente.resultset.getString("nome_cli");
                        igual = "s";//sai do laço e mostra dados
                    }
                    else
                        con_cliente.resultset.next();

                }

           
            }//fim while

        //mostra o primeiro registro da tabela nos campos da tela
        mostraDadosCadCli();
        btMostraInfo();
        btFiltrarNaTabelaCont();
        CadastroCliente.listaClientes.setSelectedItem(nomeEncontrado);
        
        
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Não há registro compatível, verifique o número digitado.");
    }


}//fim pesquisar cliente por contrato

public static void btSuspenderCliente(){

    if(CadastroCliente.situacaoCli.getText().equals("SUSPENSO"))
        JOptionPane.showMessageDialog(null, "O cliente ja se encontra suspenso. Clique no botão de informação de situaçao!", "Aviso", JOptionPane.WARNING_MESSAGE);
   
    else if(CadastroCliente.situacaoCli.getText().equals("CANCELADO"))
        JOptionPane.showMessageDialog(null, "Impossível suspender porque o cliente ja encontra-se cancelado! ", "Aviso", JOptionPane.WARNING_MESSAGE);
    
    else if(CadastroCliente.codigoCli.getText().isEmpty())
        JOptionPane.showMessageDialog(null, "Selecione um cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
    
    else{
        //pega a situação que esta no campo p/ se no caso cancelar voltar ao que era antes
        situacaoInicial = CadastroCliente.situacaoCli.getText();

        CadastroCliente.situacaoCli.setText("SUSPENSO");
        SuspensaoCli dialog = new SuspensaoCli(new javax.swing.JFrame(), true);

        //visiblidade dos botoes ativar e alterar
        dialog.btTornaAtivo.setVisible(false);
        dialog.btAlterar.setVisible(false);

        //mostra nome e contrato nos campos
        dialog.contrato.setText(CadastroCliente.contratoCli.getText());
        dialog.cliente.setText(CadastroCliente.nomeCli.getText());

        modificado = true;//vai salvar os dados
        dialog.setVisible(true);
    }
}//fim btSuspender cliente

public static void btCancelarCliente(){

        if(CadastroCliente.situacaoCli.getText().equals("CANCELADO")){
            JOptionPane.showMessageDialog(null, "O cliente já se encontra cancelado. Clique no botão de informação de situaçao.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        else if(CadastroCliente.codigoCli.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Selecione um cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);

        else{
            //pega a situação que esta no campo p/ se no caso cancelar voltar ao que era antes
            situacaoInicial = CadastroCliente.situacaoCli.getText();
            modificado = true;//pege para salvar se tentar sair
            //mostra suspenso na situação da teça de cadastro de clientes
            CadastroCliente.situacaoCli.setText("CANCELADO");
            
            //instarncia da tela
            CancelamentoCli dialog = new CancelamentoCli(new javax.swing.JFrame(), true);

             //visiblidade do botao alterar
            dialog.btTornarAtivo.setVisible(false);
      
            //mostra nome e contrato nos campos
            dialog.contrato.setText(CadastroCliente.contratoCli.getText());
            dialog.cliente.setText(CadastroCliente.nomeCli.getText());

            dialog.setVisible(true);
        }
}

public static void btMostraInfo(){//coloquei no atualizaResultSet, navega(), vizualizacaoSituação, limpaCamposCadCli(), ordenacao(), pesquisarClienteContrato(), ;
    
    if(CadastroCliente.situacaoCli.getText().equals("ATIVO") || CadastroCliente.situacaoCli.getText().equals(""))
        CadastroCliente.infoSituacao.setEnabled(false);
    else
        CadastroCliente.infoSituacao.setEnabled(true);

}public static void btFiltrarNaTabela(){
          con_cliente.executeSQL("select * from cliente where nome_cli like '"+CadastroCliente.caixaPesquisaNome.getText()+"%' and (situacao = "+selecaoSituacao+") order by "+ordenacao);
          preencherTabela();
}
public static void btFiltrarNaTabelaCont(){
          con_cliente.executeSQL("select * from cliente where contrato_cli like '"+CadastroCliente.caixaPesquisaContrato.getText()+"%' and (situacao = "+selecaoSituacao+") order by "+ordenacao);
          preencherTabela();

}

public static void atualizar_ou_gravar(){

        validaCampos();

        //se grava for verdadeiro(gravou no bd);
        if(validaCadCli.grava == true){


                //sem caixa de texto onde tem o cod do cliente estiver vazia grava os dados
                if(CadastroCliente.codigoCli.getText().isEmpty())
                    validaCadCli.gravaDadosCli();

                else{

                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);

                        if(opcao == JOptionPane.YES_OPTION){

                            
                            validaCadCli.atualizaDados();
                        }
                    
                        else
                            return;

                }
        }//fim 1° if
}//fim atualiza ou grava

public static void zeraDatas_can_sus(){
        //variáveis que guardam valores de outros forms
    motivo = "";
    dataCanSus = "0000-00-00";
    dataRetorno = "0000-00-00";
}//fim zera datas de cancelamento/suspensao


//formata data para gravar no banco
public static void formataDataBanco(String data, String nomeCampo){

    int n = 0;
String dia, mes, ano, dataFormatada;

        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);


        dataFormatada = ano+"-"+mes+"-"+dia;

                if(nomeCampo.equals("dia de suspensao") || nomeCampo.equals("dia de cancelamento"))
                    dataCanSus = dataFormatada;
                else if(nomeCampo.equals("data de retorno"))
                    dataRetorno = dataFormatada;

        

}//fim convertData

public static void testaData(String data, String nomeCampo){

String dia, mes, ano;

        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

Data.le_data();

//imperfeições na data
            if(Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31){
                msg = "O dia deve ser de 1 a 31 no campo : "+nomeCampo;

                if(nomeCampo.equals("data inicial da coleta"))
                    CadastroCliente.inicioColeta.requestFocus();
                else if(nomeCampo.equals("data final da coleta"))
                    CadastroCliente.fimColeta.requestFocus();
                else if(nomeCampo.equals("dia de suspensao"))
                    SuspensaoCli.diaSuspensao.requestFocus();
                else if(nomeCampo.equals("data de retorno"))
                    SuspensaoCli.diaRetorno.requestFocus();
                else if(nomeCampo.equals("dia de cancelamento"))
                    CancelamentoCli.diaCancelamento.requestFocus();

                grava  = false;
            }
            else if(Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12){

                msg = "O mês deve ser de 1 a 12 no campo : "+nomeCampo;

                if(nomeCampo.equals("data inicial da coleta"))
                    CadastroCliente.inicioColeta.requestFocus();
                else if(nomeCampo.equals("data final da coleta"))
                    CadastroCliente.fimColeta.requestFocus();
                else if(nomeCampo.equals("dia de suspensao"))
                    SuspensaoCli.diaSuspensao.requestFocus();
                else if(nomeCampo.equals("data de retorno"))
                    SuspensaoCli.diaRetorno.requestFocus();
                else if(nomeCampo.equals("dia de cancelamento"))
                    CancelamentoCli.diaCancelamento.requestFocus();

                grava  = false;
            }
            else if(Integer.parseInt(dia) > Data.DIA && Integer.parseInt(mes) == Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                msg = "O dia digitado é maior que a data atual no campo : "+nomeCampo;

                if(nomeCampo.equals("data inicial da coleta"))
                    CadastroCliente.inicioColeta.requestFocus();
                else if(nomeCampo.equals("data final da coleta"))
                    CadastroCliente.fimColeta.requestFocus();
                else if(nomeCampo.equals("dia de suspensao"))
                    SuspensaoCli.diaSuspensao.requestFocus();
                else if(nomeCampo.equals("data de retorno"))
                    SuspensaoCli.diaRetorno.requestFocus();
                else if(nomeCampo.equals("dia de cancelamento"))
                    CancelamentoCli.diaCancelamento.requestFocus();

                grava  = false;
            }

            else if(Integer.parseInt(dia) == Data.DIA && Integer.parseInt(mes) > Data.MES && Integer.parseInt(ano) == Data.ANO+1900){

                msg = "O mês digitado é maior que a data atual no campo : "+nomeCampo;

                if(nomeCampo.equals("data inicial da coleta"))
                    CadastroCliente.inicioColeta.requestFocus();
                else if(nomeCampo.equals("data final da coleta"))
                    CadastroCliente.fimColeta.requestFocus();
                else if(nomeCampo.equals("dia de suspensao"))
                    SuspensaoCli.diaSuspensao.requestFocus();
                else if(nomeCampo.equals("data de retorno"))
                    SuspensaoCli.diaRetorno.requestFocus();
                else if(nomeCampo.equals("dia de cancelamento"))
                    CancelamentoCli.diaCancelamento.requestFocus();

                grava  = false;
            }
            else if(Integer.parseInt(ano) <= 2000){

                msg = "O ano deve ser maior que 2000 no campo : "+nomeCampo;

                if(nomeCampo.equals("data inicial da coleta"))
                    CadastroCliente.inicioColeta.requestFocus();
                else if(nomeCampo.equals("data final da coleta"))
                    CadastroCliente.fimColeta.requestFocus();
                else if(nomeCampo.equals("dia de suspensao"))
                    SuspensaoCli.diaSuspensao.requestFocus();
                else if(nomeCampo.equals("data de retorno"))
                    SuspensaoCli.diaRetorno.requestFocus();
                else if(nomeCampo.equals("dia de cancelamento"))
                    CancelamentoCli.diaCancelamento.requestFocus();

                grava  = false;
            }
            else
                grava = true;


}


//formata data que vem do banco para o text
public static void formataDataAreaTexto(String data, String nomeCampo){
String dataFormatada, dia, mes, ano;

        ano = data.substring(0, 4);
        mes = data.substring(5, 7);
        dia = data.substring(8, 10);

        dataFormatada = dia+mes+ano;

        if(nomeCampo.equals("data inicial da coleta"))
            dataInicialColeta = dataFormatada;

        else if(nomeCampo.equals("data final da coleta"))
            dataFimColeta = dataFormatada;

        else if(nomeCampo.equals("dia de suspensao") || nomeCampo.equals("dia de cancelamento") )
            dataCanSus = dataFormatada;

        else if(nomeCampo.equals("data de retorno"))
            dataRetorno = dataFormatada;
        
        else
            JOptionPane.showMessageDialog(null, "Erro, nome do campo nao confere :"+nomeCampo, "Aviso ao desenvolvedor", JOptionPane.WARNING_MESSAGE);
       
        
}//fim convert area text

public static void verificaCamposSuspensao(){

        if(SuspensaoCli.motivoSuspensao.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe o motivo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            SuspensaoCli.motivoSuspensao.requestFocus();
            campoVazio = true;
        }

        else if(SuspensaoCli.diaSuspensao.getText().equals("  -  -    ")){
            JOptionPane.showMessageDialog(null, "Informe a data de suspensao!", "Aviso", JOptionPane.WARNING_MESSAGE);
            SuspensaoCli.diaSuspensao.requestFocus();
            campoVazio = true;
        }

        else if(SuspensaoCli.diaRetorno.getText().equals("  -  -    ")){
            JOptionPane.showMessageDialog(null, "Informe a data de retorno!", "Aviso", JOptionPane.WARNING_MESSAGE);
            SuspensaoCli.diaRetorno.requestFocus();
            campoVazio = true;
        }

        else
            //se tudo estiver preenchido ele pega a data de suspensao e de retorno e compara para saber se
            // a data de retorno e menor que a data de suspensão
            if(SuspensaoCli.rb_pDeterminado.isSelected())
                verificaDataInferior(SuspensaoCli.diaSuspensao.getText(), SuspensaoCli.diaRetorno.getText(), "suspensao");
            else
                campoVazio = false;

}//fim verifica campos suspensao
                                        //data de suspensao, data de retorno
public static void verificaDataInferior(String primeiraData, String segundaData, String tipoData){

    int dia_susp, dia_retorn, mes_susp, mes_retorn, ano_susp, ano_retorn;

        dia_susp = Integer.parseInt(primeiraData.substring(0, 2));
        mes_susp = Integer.parseInt(primeiraData.substring(3, 5));
        ano_susp = Integer.parseInt(primeiraData.substring(6, 10));

        dia_retorn = Integer.parseInt(segundaData.substring(0, 2));
        mes_retorn = Integer.parseInt(segundaData.substring(3, 5));
        ano_retorn = Integer.parseInt(segundaData.substring(6, 10));


        //se dia de retorno for menor que dia de suspensao
        if(dia_retorn < dia_susp){

            //se mes de retorno for menor ou igual ao mes de suspensão
            if(mes_retorn <= mes_susp){

                    //se o ano de retorno for menor ou igual ao ano de suspensao obs: o mês de retorno é menor
                    if(ano_retorn <= ano_susp){

                        //aviso de irregularidade de data de suspensao
                        if(tipoData.equals("suspensao")){
                            JOptionPane.showMessageDialog(null, "A data de retorno não pode ser menor que a data de suspensão", "Aviso", JOptionPane.WARNING_MESSAGE);
                            SuspensaoCli.diaRetorno.requestFocus();
                            campoVazio = true;
                        }
                        else if(tipoData.equals("coleta")){// testa a data de final de coleta pra ver se é menor que a data de início
                            msg = "A data de fim da coleta não pode ser menor que a data de início da coleta";
                            grava = false;//não pode gravar assim
                            CadastroCliente.fimColeta.requestFocus();
                        }
                                 
                    }
                    //se ano de retorno for maior que o ano de suspensao
                    else
                        //pode gravar
                        campoVazio = false;
            
            }//fim mes de retorno menor  // dia menor

            //se mes de retorno for maior que mes de suspensao    ex: suspen 03/02/2010 retorn 01/05/2010. obs: o dia continua menor...
            else{

                    //se o ano de retorno for menor que o ano de suspensao   obs: o mes é maior e o dia tambem
                    if(ano_retorn < ano_susp){

                        if(tipoData.equals("suspensao")){

                            JOptionPane.showMessageDialog(null, "A data de retorno não pode ser menor que a data de suspensão", "Aviso", JOptionPane.WARNING_MESSAGE);
                            SuspensaoCli.diaRetorno.requestFocus();
                            campoVazio = true;
                        }

                        else if(tipoData.equals("coleta")){// testa a data de final de coleta pra ver se é menor que a data de início
                                msg = "A data de fim da coleta não pode ser menor que a data de início da coleta";
                                grava = false;//não pode gravar assim
                                CadastroCliente.fimColeta.requestFocus();
                        }

                    }

                    //se o ano de retorno for maior ou igual
                    else
                         //pode gravar
                        campoVazio = false;
            
            }//fim mes de retorno maior

        }

        //se dia de retorno for maior que o dia de suspensao  Ex: suspens 10/02/2010  retorn 12/01/2010.
        else{

            //se mes de retorno for menor que o mes de suspensão
            if(mes_retorn < mes_susp){

                    //se o ano de retorno for menor ou igual ao ano de suspensao. obs: o dia é maior e o mes é menor
                    if(ano_retorn <= ano_susp){

                        if(tipoData.equals("suspensao")){

                            JOptionPane.showMessageDialog(null, "A data de retorno não pode ser menor que a data de suspensão", "Aviso", JOptionPane.WARNING_MESSAGE);
                            SuspensaoCli.diaRetorno.requestFocus();
                            campoVazio = true;
                        }

                        else if(tipoData.equals("coleta")){// testa a data de final de coleta pra ver se é menor que a data de início
                                msg = "A data de fim da coleta não pode ser menor que a data de início da coleta";
                                grava = false;//não pode gravar assim
                                CadastroCliente.fimColeta.requestFocus();
                        }
                    }
                    //se o ano de retorno é maior que ano de suspensao
                    else{
                        //pode gravar
                        campoVazio = false;

                    }

            }
            //se o mes de retorno for maior que mes de suspensao  Ex: suspen 10/02/2010   retorn 12/03/2010. obs: o dia é maior e o mes é maior
            else{
                    //se o ano de retorno for menor que o ano de suspensao  obs: o dia é maior e o mes é maior
                    if(ano_retorn < ano_susp){

                        if(tipoData.equals("suspensao")){

                            JOptionPane.showMessageDialog(null, "A data de retorno não pode ser menor que a data de suspensão", "Aviso", JOptionPane.WARNING_MESSAGE);
                            SuspensaoCli.diaRetorno.requestFocus();
                            campoVazio = true;
                        }

                        else if(tipoData.equals("coleta")){// testa a data de final de coleta pra ver se é menor que a data de início
                                msg = "A data de fim da coleta não pode ser menor que a data de início da coleta";
                                grava = false;//não pode gravar assim
                                CadastroCliente.fimColeta.requestFocus();
                        }
                    }
                    //se o ano de retorno for maior que o ano de suspensao
                    else
                        //pode gravar
                        campoVazio = false;

            }//fim mes maior  // dia maior
        
        
        }
            
            

}

public static void verificaCamposCancelamento(){

        if(CancelamentoCli.motivoCancelamento.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe o motivo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            CancelamentoCli.motivoCancelamento.requestFocus();
            campoVazio = true;
        }

        else if(CancelamentoCli.diaCancelamento.getText().equals("  -  -    ")){
            JOptionPane.showMessageDialog(null, "Informe a data de suspensao!", "Aviso", JOptionPane.WARNING_MESSAGE);
            CancelamentoCli.diaCancelamento.requestFocus();
            campoVazio = true;
        }

        else
            campoVazio = false;

}//fim verifica campos suspensao

public static void gravaSuspensaoCli(){

    //testa as datas para ver se nao tem falhas pois compromete na hora de ver a data

    testaData(SuspensaoCli.diaSuspensao.getText(), "dia de suspensao");

    if(grava == true){

        if(SuspensaoCli.rb_pDeterminado.isSelected())
            testaData(SuspensaoCli.diaRetorno.getText(), "data de retorno");
        else
            grava = true;

        
        if(grava == true){
              //MOTIVO
              motivo = SuspensaoCli.motivoSuspensao.getText();

              //DATA SUSPENSAO
              //formata data da area de texto para inserir no banco aaaa/mm/dd
              formataDataBanco(SuspensaoCli.diaSuspensao.getText(), "dia de suspensao");

              //DATA RETORNO
              //formata data da area de texto para inserir no banco aaaa/mm/dd
              formataDataBanco(SuspensaoCli.diaRetorno.getText(), "data de retorno");

              //SALVA OU ATUALIZA REGISTRO NO BANCO
              atualizar_ou_gravar();
        }
        else
        JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    else
        JOptionPane.showMessageDialog(null, msg, "Aviso", JOptionPane.WARNING_MESSAGE);


}//fim grava suspensaoCli

public static void gravaCancelamentoCli(){

        //testa a data de cancelamento
        testaData(CancelamentoCli.diaCancelamento.getText(), "dia de cancelamento");

        if(grava == true){
                //MOTIVO
                motivo = CancelamentoCli.motivoCancelamento.getText();

                //DATA SUSPENSAO
                //formata data da area de texto para inserir no banco aaaa/mm/dd
                formataDataBanco(CancelamentoCli.diaCancelamento.getText(), "dia de cancelamento");

                //SALVA OU ATUALIZA REGISTRO NO BANCO
                atualizar_ou_gravar();
        }

}//fim gravaCancelamentoCli

//serve para habilitar os campos em caso de cientes suspensos ou cancelados
public static void EditarCampos(boolean editar){

                           //mostra os dados
                           CadastroCliente.nomeCli.setEditable(editar);
                           CadastroCliente.ruaCli.setEditable(editar);
                           CadastroCliente.bairroCli.setEnabled(editar);
                           CadastroCliente.cidadeCli.setEnabled(editar);
                           CadastroCliente.ufCli.setEnabled(editar);
                           CadastroCliente.foneCli.setEditable(editar);
                           CadastroCliente.responsavelCli.setEditable(editar);
                           CadastroCliente.ptReferenciaCli.setEditable(editar);
                           CadastroCliente.inicioColeta.setEnabled(editar);
                           CadastroCliente.fimColeta.setEnabled(editar);
                           CadastroCliente.obs.setEditable(editar);
                           //CadastroClientee.contratoCli.setEditable(editar);
                           CadastroCliente.hrFun_de.setEditable(editar);
                           CadastroCliente.hrFunc_a.setEditable(editar);
                           CadastroCliente.qtBomb200.setEnabled(editar);
                           CadastroCliente.qtBomb50.setEnabled(editar);
                           CadastroCliente.qtBomb20.setEnabled(editar);
                           CadastroCliente.qtTotalBomb.setEnabled(editar);
                           CadastroCliente.btAddDiaColeta.setEnabled(editar);
                           CadastroCliente.addBairro.setEnabled(editar);
                           CadastroCliente.addCidade.setEnabled(editar);


}//fim nao deixa editar campos

public static void novoCliente(){
        //limpa os campos
        validaCadCli.LimpaCamposCadCli();//limpa os campos
        CadastroCliente.nomeCli.requestFocus();//foco no nome do cliente
        validaCadCli.EditarCampos(true);//deixa os campos editáveis
        CadastroCliente.contratoCli.setEditable(true);//o campo contrato fica editável
        CadastroCliente.btAtivos.setSelected(true);
        CadastroCliente.orderNome.setSelected(true);
        selecaoSituacao = "'Ativo'";
        atualizaResultset();
        preencherTabela();

        //inabilita botões que ameaçam a integridade dos dados que estão sendo inseridos
        habilitaBotoes(false);
        //botão gravar dados do cliente fica habilitado
        CadastroCliente.btGravaCli.setEnabled(true);

}//fim novo ciente

public static void atualizar(){

    zeraDatas_can_sus();
    preencherListaDeCidades();
    preencherListaDeBairros();
    atualizaResultset();
    preencherTabela();//ja atualiza o resultset
    mostraDadosCadCli();//mostra dados e define algumas configuraçoes
    btMostraInfo();//Mostra informações se o cliente for suspenso ou cancelado
    habilitaBotoesOrganizacaoSituacao();//


}//fim atualizar

public static void cliqueGrid(){
    //pega a linha que foi clicada na TABELA
    int linha = CadastroCliente.tabelaCliente.getSelectedRow();
    //pega o modelo da tabela
    TableModel modelo = (TableModel) CadastroCliente.tabelaCliente.getModel();
    //pega o codigo do cliente que está na linha que foi clicada e coluna 0 (primeira)
    String codigoClienteClicado =String.valueOf(modelo.getValueAt(linha, 0));
    //var que vai gurdar o codigo pesquisado no banco
    String codigoClientePesquisado;
    //vai determinar o fim do laço
    String igual = "n";



    try{
            //vai para o primeiro registro
            con_cliente.resultset.first();

            while(igual.equals("n")){

                codigoClientePesquisado = con_cliente.resultset.getString("cod_cli");

                //se o codigo que foi clicado na gride for igual ao que está no banco
                if(codigoClienteClicado.equals(codigoClientePesquisado)){
                    mostraDadosCadCli();
                    btMostraInfo();
                    igual = "s";//sai do laço e mostra dados
                }
                else
                    con_cliente.resultset.next();
             }//fim while
      
    }//fim try

    catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
    }

}//fim click grid

public static void verificaContrato(){
    String contratoPesquisado, nome;
    String contratoNovoCliente = CadastroCliente.contratoCli.getText().toString();
    String nomeNovoCliente = CadastroCliente.nomeCli.getText();
    boolean achouNada = false;

    grava = false;
    soNumeroNoContrato = true;


    try{

            Integer.parseInt(CadastroCliente.contratoCli.getText().toString());

            con_cliente.executeSQL("select * from cliente where situacao != 'DESCONHEC.' ORDER BY cod_cli");

            if(con_cliente.resultset.first()){

                do{

                   contratoPesquisado = con_cliente.resultset.getString("contrato_cli");
                   nome = con_cliente.resultset.getString("nome_cli");

                   //System.out.println("CONTRATO: "+contratoPesquisado+"");
                   //System.out.println("NOME: "+nome+"");


                   //se o n° de contrato for igual entra no if
                   if(contratoNovoCliente.equals(contratoPesquisado)){
                       //System.out.println("os contratos sao iguais");
                       if(nomeNovoCliente.equals(nome)){
                         //System.out.println("os nomes sao iguais");
                          achouNada = true;
                          grava = true;
                          break;
                       }
                       else{

                          //System.out.println("os nomes sao diferentes");
                          achouNada = true;
                          grava = false;
                          usuarioContrato = nome;
                          break;
                       }
                   }


                }while(con_cliente.resultset.next());

                //se fez o loop e não encontrou nada pode gravar;
                if(achouNada == false)
                    grava = true;

            }
            //se nem conseguiu ver o primeiro registro é pq pode grava na boa
            else
                grava = true;

            //VOLTA AO PRIMEIRO REGISTRO
            con_cliente.resultset.first();

    }
    catch(NumberFormatException nfe){

        soNumeroNoContrato = false;
        return;
    }
    catch(Exception e){
        
        System.out.print("Erro ao verificar contratos existentes para comparar.\nErro: "+e);
    }

}

public static void preencherListaDeCidades(){
try{

                con_cidades.executeSQL("select * from cad_cidade order by nome_cidade");
                //remove todos os itens do congo
                CadastroCliente.cidadeCli.removeAllItems();

                if(con_cidades.resultset.first()){

                    do{
                        CadastroCliente.cidadeCli.addItem(con_cidades.resultset.getString("nome_cidade"));

                    }while(con_cidades.resultset.next());

                //INCLUI UM ESPACO VAZIO PARA SETAR QUANDO FOR INCLUIR UM NOVO CLIENTE
                CadastroCliente.cidadeCli.addItem("");
                }
                else
                    return;

}
catch(Exception e){

                //remove todos os itens do congo
                CadastroCliente.cidadeCli.removeAllItems();
                System.out.println("Erro na lista de cidades: "+e);
}


}

public static void preencherListaDeBairros(){
try{

                con_bairros.executeSQL("select * from cad_bairro order by nome_bairro");
                //remove todos os itens do congo
                CadastroCliente.bairroCli.removeAllItems();

                if(con_bairros.resultset.first()){

                    do{
                        
                        CadastroCliente.bairroCli.addItem(con_bairros.resultset.getString("nome_bairro"));

                    }while(con_bairros.resultset.next());
                
               //INCLUI UM ESPACO VAZIO PARA SETAR QUANDO FOR INCLUIR UM NOVO CLIENTE
               CadastroCliente.bairroCli.addItem("");
                }
                else
                    return;
}
catch(Exception e){

                //remove todos os itens do congo
                CadastroCliente.bairroCli.removeAllItems();
                System.out.println("Erro na lista de bairros: "+e);
}


}

public static void naoDeixaAleterarContrato(){

    con_cliente.executeSQL("select * from cad_bombona where contrato = "+CadastroCliente.contratoCli.getText());

    try{
        if(con_cliente.resultset.first()){
            CadastroCliente.contratoCli.setEditable(false);
            JOptionPane.showMessageDialog(null, "Este contrato já tem referências na tabela de pesagem de bombonas e não pode ser alterado");
            atualizaResultset();
        }
    }
    catch(Exception e){

        System.out.println("falha ao verificar referências na tabela de pesagem de bombonas");
    }




}

public static void habilitaBotoes(boolean h){

    CadastroCliente.btPrimeiroReg.setEnabled(h);
    CadastroCliente.btAnteriorReg.setEnabled(h);
    CadastroCliente.btProximoReg.setEnabled(h);
    CadastroCliente.btUltimoReg.setEnabled(h);
    CadastroCliente.btAddCli.setEnabled(h);
    CadastroCliente.btSuspenderCli.setEnabled(h);
    CadastroCliente.btCancelarCli.setEnabled(h);
    CadastroCliente.caixaPesquisaNome.setEditable(h);
    CadastroCliente.caixaPesquisaContrato.setEditable(h);
    CadastroCliente.btImprimirFicha.setEnabled(h);
    CadastroCliente.btImprimirFicha.setEnabled(h);
    CadastroCliente.btExcluirCli.setEnabled(h);
    CadastroCliente.btImprimirFicha.setEnabled(h);
    CadastroCliente.orderNome.setEnabled(h);
    CadastroCliente.orderCodigo.setEnabled(h);
    CadastroCliente.orderBairro.setEnabled(h);
    CadastroCliente.btImprimirLista.setEnabled(h);


}

public static void habilitaBotoesOrganizacaoSituacao(){

       if(CadastroCliente.nomeCli.getText().length() > 0 && CadastroCliente.codigoCli.getText().isEmpty()){
            CadastroCliente.btTodos.setEnabled(false);
            CadastroCliente.btSuspensosDeterminados.setEnabled(false);
            CadastroCliente.btSuspensosIndeterminados.setEnabled(false);
            CadastroCliente.btCancelados.setEnabled(false);
       }
       else{
            CadastroCliente.btTodos.setEnabled(true);
            CadastroCliente.btSuspensosDeterminados.setEnabled(true);
            CadastroCliente.btSuspensosIndeterminados.setEnabled(true);
            CadastroCliente.btCancelados.setEnabled(true);
       }


}

public static void preencherListaClientes(){
int cont = 0;
        //remove todos os itens
        CadastroCliente.listaClientes.removeAllItems();

        try{
            con_cliente.resultset.first();

        do{
            cont++;
            CadastroCliente.listaClientes.addItem(con_cliente.resultset.getString("nome_cli"));

        }while(con_cliente.resultset.next());

        CadastroCliente.lbQtdCli.setText(cont+" Cliente(s)");
        //VOLTA AO PRIMEIRO REGISTRO
        con_cliente.resultset.first();

    }

    catch(Exception e){

           CadastroCliente.lbQtdCli.setText("0 Cliente(s)");
           //remove todos os itens
           CadastroCliente.listaClientes.removeAllItems();

    }
}//fim preenche lista

public static void mostraClienteDaListaDeSelecao(){

try{
    String igual = "n";
    con_cliente.resultset.first();

    do{
            //se o nome do bairro que esta no banco for igual ao nome selecionado no congobox lista de bairros...
            if(con_cliente.resultset.getString("nome_cli").equals(CadastroCliente.listaClientes.getSelectedItem().toString())){

                igual = "s";
            }
            else
                con_cliente.resultset.next();

    }while(igual.equals("n"));

    mostraDadosCadCli();

}catch(Exception e){
        System.out.println("Erro :"+e);
}

}






}

