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
import auxiliar.acompanhamentoPesagemIncineracaoAux;

public class acompanhamentoInci{
    
    conexao conInci = new conexao();
    
    
    
    public acompanhamentoInci() {
        try {
            
            
            
            conInci.conecta();
            conInci.executeSQL("select * from incineracao where (id_maquina = "+acompanhamentoPesagemIncineracaoAux.maquina+") and (turno_incineracao = "+acompanhamentoPesagemIncineracaoAux.turno+") and (data_incineracao between '"+acompanhamentoPesagemIncineracaoAux.periodoInicial+"' and '"+acompanhamentoPesagemIncineracaoAux.periodoFinal+"') and (tipo_residuo = "+acompanhamentoPesagemIncineracaoAux.residuo+") and (situacao_reg = 'G') order by "+acompanhamentoPesagemIncineracaoAux.ordenacao);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conInci.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/acompanhaInci.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            conInci.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
            //desconecta
            conInci.desconecta();
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoInci();
    }
}



/**
 *
 * @author ayrton monier
 */

