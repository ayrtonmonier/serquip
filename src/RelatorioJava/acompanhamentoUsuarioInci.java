/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import RelatorioForm.acompanhamentoPesagemUsuarioIncineracao;
import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import auxiliar.conexao;

public class acompanhamentoUsuarioInci{
    
    conexao con_acInciUser = new conexao();
    
    
    
    public acompanhamentoUsuarioInci() {
        try {
            
            
            
            con_acInciUser.conecta();
            con_acInciUser.executeSQL("select * from usuario a, incineracao b where a.matricula_usuario = b.matricula_usuario and (b.id_maquina = "+acompanhamentoPesagemUsuarioIncineracao.maquina+") and (b.turno_incineracao = "+acompanhamentoPesagemUsuarioIncineracao.turno+") and (b.data_incineracao between '"+acompanhamentoPesagemUsuarioIncineracao.periodoInicial+"' and '"+acompanhamentoPesagemUsuarioIncineracao.periodoFinal+"') and (b.tipo_residuo = "+acompanhamentoPesagemUsuarioIncineracao.residuo+") and (b.situacao_reg = '"+acompanhamentoPesagemUsuarioIncineracao.gravadoExcluido+"') and b.matricula_usuario = '"+acompanhamentoPesagemUsuarioIncineracao.matriculaUsuarioInci.getText()+"' order by num_seq_incineracao");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_acInciUser.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/acUsuarioIncineracao.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_acInciUser.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoUsuarioInci();
    }
}



/**
 *
 * @author ayrton monier
 */

