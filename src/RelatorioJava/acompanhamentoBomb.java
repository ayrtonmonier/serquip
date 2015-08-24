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
import RelatorioForm.acompanhamentoPesagemBombona;

public class acompanhamentoBomb{
    
    conexao con = new conexao();
    
    
    
    public acompanhamentoBomb() {
        try {
            
            
            
            con.conecta();
            con.executeSQL("select a.*, b.nome_cli, b.data_cadastro, b.contrato_cli, b.responsavel, b.fone, b.situacao, b.dia_de_coleta, b.cidade, b.uf from cad_bombona a, cliente b where a.contrato = b.contrato_cli and (a.turno_pesagem = "+acompanhamentoPesagemBombonaAux.turno+") and (a.data_pesagem_bomb between '"+acompanhamentoPesagemBombonaAux.periodoInicial+"' and '"+acompanhamentoPesagemBombonaAux.periodoFinal+"') and (a.tipo_bomb = "+acompanhamentoPesagemBombonaAux.tipo+") and (a.tipo_pesagem = "+acompanhamentoPesagemBombonaAux.tipoPesagem+") and (a.capacidade_bomb = "+acompanhamentoPesagemBombonaAux.capacidade+") and b.contrato_cli = "+acompanhamentoPesagemBombona.contratoCli.getText()+" and (a.situacao_reg = 'G') order by "+acompanhamentoPesagemBombonaAux.ordenacao);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/acompanhaBomb.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoBomb();
    }
}



/**
 *
 * @author ayrton monier
 */

