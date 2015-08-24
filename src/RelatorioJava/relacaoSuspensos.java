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

public class relacaoSuspensos{
    
    conexao con_relacaoSusp = new conexao();
    
    
    
    public relacaoSuspensos() {
        try {
            
            
            
            con_relacaoSusp.conecta();
            con_relacaoSusp.executeSQL("select * from cliente where situacao = 'Suspenso'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_relacaoSusp.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clientesSuspensos.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_relacaoSusp.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new relacaoSuspensos();
    }
}



/**
 *
 * @author ayrton monier
 */

