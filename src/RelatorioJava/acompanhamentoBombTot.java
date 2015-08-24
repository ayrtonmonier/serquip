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
import auxiliar.acompanhamentoPesagemBombonaAux;

public class acompanhamentoBombTot{
    
    conexao conacompanhamentoPesagemBombonaAuxTot = new conexao();
    
    
    
    public acompanhamentoBombTot() {
        try {
            
            
            
            conacompanhamentoPesagemBombonaAuxTot.conecta();
            conacompanhamentoPesagemBombonaAuxTot.executeSQL("select * from cad_bombona where (contrato = "+acompanhamentoPesagemBombonaAux.contrato+") and (turno_pesagem = "+acompanhamentoPesagemBombonaAux.turno+") and (data_pesagem_bomb between '"+acompanhamentoPesagemBombonaAux.periodoInicial+"' and '"+acompanhamentoPesagemBombonaAux.periodoFinal+"') and (tipo_bomb = "+acompanhamentoPesagemBombonaAux.tipo+") and (tipo_pesagem = "+acompanhamentoPesagemBombonaAux.tipoPesagem+") and (capacidade_bomb = "+acompanhamentoPesagemBombonaAux.capacidade+") and (situacao_reg = 'G') order by "+acompanhamentoPesagemBombonaAux.ordenacao);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(conacompanhamentoPesagemBombonaAuxTot.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/acompanhaBombTotal.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            conacompanhamentoPesagemBombonaAuxTot.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoBombTot();
    }
}



/**
 *
 * @author ayrton monier
 */

