/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import javax.swing.*;
import principal.*;
//import auxiliar.*;

/**
 *
 * @author ayrton monier
 */
public class validaLogin {
          public static int NUM_LOG_ATUAL;
          public static String USUARIO = "", MATRICULA = "" , DATAFORMAT = "", DATANORM = "", TURNO = "", SENHA = "", HORA = "", HOST = "localhost", SITUACAO_LOGOUT = "", msg = "";
          public static boolean valida = true;

          
    public static void guardaDados(){

        Data.le_hora();

        //ajeita a data para guardar no banco
        //possibilidades:
        //d-m-aaaa    d-mm-aaaa    dd-m-aaaa     dd-mm-aaaa
        String dia = "", mes = "", ano = "", data;

        data = telaAcesso.loginData.getText();

        if(data.substring(1,2).equals("-"))//d-m-aaaa
            dia = data.substring(0,1);
        else if(data.substring(2,3).equals("-"))//dd-m-aaaa
            dia = data.substring(0, 2);

        if(data.substring(3,4).equals("-")){//d-m-aaaa
            mes = data.substring(2,3);
            ano = data.substring(4, 8);
        }
        else if(data.substring(2,3).equals("-") && data.substring(5,6).equals("-")){//dd-mm-aaaa
            mes = data.substring(3, 5);
            ano = data.substring(6, 10);
        }
        else if(data.substring(2,3).equals("-") && data.substring(4,5).equals("-")){//dd-m-aaaa
            mes = data.substring(3, 4);
            ano = data.substring(5, 9);
        }
        else if(data.substring(2,3).equals("-") && data.substring(5,6).equals("-")){//dd-mm-aaaa
            mes = data.substring(3, 5);
            ano = data.substring(6, 10);
        }



        //pegar data anterior
        //estes dados ficam guardados em memória até que o usuário faça outro login
        DATAFORMAT = ano+"-"+mes+"-"+dia;

        USUARIO     = telaAcesso.loginUsuario.getSelectedItem()+"";
        SENHA       = telaAcesso.loginSenha.getSelectedText();
        MATRICULA   = telaAcesso.loginMatricula.getText();
        TURNO       = telaAcesso.loginTurno.getSelectedItem()+"";
        DATANORM    = telaAcesso.loginData.getText();
        HORA        = Data.horaAtual;

//        JOptionPane.showMessageDialog(null, "host: " +HOST+"\n"+
//                                            "tipo de usuario: "+USUARIO+"\n" +
//                                            "matricula: "+MATRICULA+"\n" +
//                                            "turno: "+TURNO+"\n" +
//                                            "data login: "+DATANORM+"\n" +
//                                            "dataBanco: "+DATAFORMAT+"\n" +
//                                            "hora: "+HORA);
    }

    public static void validaCampos(){
        valida = true;
        
        //se tipo de usuario nao for preenchido...
        if(telaAcesso.loginUsuario.getSelectedItem().toString().isEmpty()){
            msg ="INFORME O TIPO DE USUÁRIO";
            telaAcesso.loginUsuario.requestFocus();
            valida = false;
        }

        //se matrícula nao for preenchido...
        else if(telaAcesso.loginMatricula.getText().isEmpty() && (telaAcesso.loginUsuario.getSelectedItem().toString().equals("Operador") || (telaAcesso.loginUsuario.getSelectedItem().toString().equals("Gerente")))){
            msg = "INFORME A MATRÍCULA";

            telaAcesso.loginMatricula.requestFocus();

            valida = false;

        }

        //se senha não for preenchida...
        else if(telaAcesso.loginSenha.getText().isEmpty() == true){
            msg = "INFORME A SENHA";
            telaAcesso.loginSenha.requestFocus();
            valida = false;
        }

        //se data não for preenchida...
        else if(telaAcesso.loginData.getText().isEmpty() && telaAcesso.loginUsuario.getSelectedItem().toString().equals("Operador")){
            msg = "INFORME A DATA";
            telaAcesso.loginData.requestFocus();
            valida = false;
        }

        //se turno não for preenchido...
        else if(telaAcesso.loginTurno.getSelectedItem().toString().isEmpty() && telaAcesso.loginUsuario.getSelectedItem().toString().equals("Operador")){
            msg = "INFORME O TURNO";
            telaAcesso.loginTurno.requestFocus();
            valida = false;

        }

        if(valida == false){
            //mensagem de aviso
            JOptionPane.showMessageDialog(null, msg, "AVISO", JOptionPane.WARNING_MESSAGE);
        }

        //se nenhuma ocorrencia anterior acontecer...
        else{

            //valida  "pode entrar" e fechar a tela inicial
            valida = true;
        }

    }//FIM VALIDA CAMPOS



        //de acordo com o tipo de usuário a telaPrincipal se ajustará
        public static void configuraInterface(String tipoUsuario){

        //intância da telaPrincipal tp
        telaPrincipal tp = new telaPrincipal();

        //teste de privilégio Operador
        if( tipoUsuario.equals("Operador")){

            //restrições de operador
            tp.menuSistema.setVisible(false);
            tp.menuOpcoes.setVisible(false);
            tp.menuRelatorio.setVisible(false);
            tp.munuSobre.setVisible(false);
            tp.btCliente.setVisible(false);
            tp.btUsuario.setVisible(false);
            tp.btConfiguracao.setVisible(false);
            tp.menuUtilitarios.setVisible(false);
            tp.btRelatBombona.setVisible(false);
            tp.btRelatInicineracao.setVisible(false);
            tp.informaSituacaoGeral.setVisible(false);
            tp.setVisible(true);

        }

        //login do Gerente
        else if(tipoUsuario.equals("Gerente")){

            //restrições do Gerente
            tp.itemMenuBombona.setVisible(false);
            tp.itemMenuIncinerar.setVisible(false);
            tp.btBombona.setVisible(false);
            tp.btIncinera.setVisible(false);
            tp.trocaUsuario.setVisible(false);
            tp.btConfiguracao.setVisible(false);
            tp.menuLogUser.setVisible(false);
            tp.setVisible(true);

            
        }
        //login do administrador
        else{
            tp.setVisible(true);
        }

    }//fim do metodo configuraInterface

}





