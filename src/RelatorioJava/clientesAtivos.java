/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import auxiliar.conexao;
import auxiliar.validaCadCli;
import Cadastro.CadastroCliente;

public class clientesAtivos{

    conexao con_ativos = new conexao();



public clientesAtivos() {
try {



      con_ativos.conecta();
      con_ativos.executeSQL("select * from cliente where nome_cli like '"+CadastroCliente.caixaPesquisaNome.getText()+"%' and situacao = 'Ativo' order by "+validaCadCli.ordenacao);
      JRResultSetDataSource jrRS = new JRResultSetDataSource(con_ativos.resultset);
      JasperPrint jasperPrint = JasperFillManager.fillReport(
      "RelatoriosArqJasper/clientesAtivos.jasper", new HashMap(), jrRS);
      
      JasperViewer.viewReport(jasperPrint, false);


      
      //desconecta
      con_ativos.desconecta();


      
  } catch (Exception erro){
             JOptionPane.showMessageDialog(null,"deu erro ="+erro);
   }
}

  public static void main (String args[])
  {
    new clientesAtivos();
  }
}



/**
 *
 * @author ayrton monier
 */

