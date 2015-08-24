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

public class relacaoSuspensosVencidos{
    
    conexao con_susVencidos = new conexao();
    
    
    
    public relacaoSuspensosVencidos() {
        try {
            
            
            
            con_susVencidos.conecta();
            con_susVencidos.executeSQL("select * from cliente where situacao = 'SUSPENSO' and dia_retorno != '0000-00-00' and dia_retorno <= '"+auxiliar.validaLogin.DATAFORMAT+"'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_susVencidos.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clientesSuspensosVencidos.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_susVencidos.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new relacaoSuspensosVencidos();
    }
}



/**
 *
 * @author ayrton monier
 */

