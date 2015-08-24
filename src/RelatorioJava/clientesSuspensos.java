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

import Cadastro.CadastroCliente;
import auxiliar.validaCadCli;
import auxiliar.conexao;

public class clientesSuspensos{
    
    conexao con_suspensos = new conexao();
    
    
    
    public clientesSuspensos() {
        try {
            
            
            
            con_suspensos.conecta();
            con_suspensos.executeSQL("select * from cliente where nome_cli like '"+CadastroCliente.caixaPesquisaNome.getText()+"%' and situacao = 'SUSPENSO' and dia_retorno != '0000-00-00' and dia_retorno > '"+auxiliar.validaLogin.DATAFORMAT+"' order by "+validaCadCli.ordenacao);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_suspensos.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clientesSuspensos.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_suspensos.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clientesSuspensos();
    }
}



/**
 *
 * @author ayrton monier
 */

