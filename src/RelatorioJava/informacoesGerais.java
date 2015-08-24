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

public class informacoesGerais{
    
    conexao conInfo = new conexao();
    
    
    
    public informacoesGerais() {
        try {
            
            
            
            conInfo.conecta();
            conInfo.executeSQL("select * from cliente where situacao = 'ATIVO' or situacao = 'SUSPENSO' or situacao = 'CANCELADO'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conInfo.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/informacoesGerais.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            conInfo.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new informacoesGerais();
    }
}



/**
 *
 * @author ayrton monier
 */

