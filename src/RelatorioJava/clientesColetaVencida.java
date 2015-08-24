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
import auxiliar.validaLogin;


public class clientesColetaVencida{
    
    conexao conVencida = new conexao();
    
    
    
    public clientesColetaVencida() {
        try {
            
            
            
            conVencida.conecta();
            conVencida.executeSQL("select * from cliente where situacao = 'ATIVO' and (fim_coleta <= '"+validaLogin.DATAFORMAT+"') order by nome_cli");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conVencida.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clientesColetaVencida.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            conVencida.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clientesColetaVencida();
    }
}



/**
 *
 * @author ayrton monier
 */

