/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import RelatorioForm.acompanhamentoPesagemUsuarioBombona;
import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import auxiliar.conexao;

public class acompanhamentoUsuarioBomb{
    
    conexao con_acBombUser = new conexao();
    
    
    
    public acompanhamentoUsuarioBomb() {
        try {
            
            
            
            con_acBombUser.conecta();
            con_acBombUser.executeSQL("select * from usuario a, cad_bombona b where a.matricula_usuario = b.matricula_usuario and (b.turno_pesagem = "+acompanhamentoPesagemUsuarioBombona.turno+") and (b.data_pesagem_bomb between '"+acompanhamentoPesagemUsuarioBombona.periodoInicial+"' and '"+acompanhamentoPesagemUsuarioBombona.periodoFinal+"') and (b.tipo_bomb = "+acompanhamentoPesagemUsuarioBombona.tipo+") and (b.tipo_pesagem = "+acompanhamentoPesagemUsuarioBombona.pesagem+") and (b.capacidade_bomb = "+acompanhamentoPesagemUsuarioBombona.capacidade+") and (b.situacao_reg = '"+acompanhamentoPesagemUsuarioBombona.gravadoExcluido+"') and b.matricula_usuario = '"+acompanhamentoPesagemUsuarioBombona.matriculaUsuarioBomb.getText()+"'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_acBombUser.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/acUsuarioBombona.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_acBombUser.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoUsuarioBomb();
    }
}



/**
 *
 * @author ayrton monier
 */

