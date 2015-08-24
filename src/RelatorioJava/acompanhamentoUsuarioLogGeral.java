/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import RelatorioForm.logUsuario;
import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import auxiliar.conexao;

public class acompanhamentoUsuarioLogGeral{
    
    conexao con_logGeral = new conexao();
    
    
    
    public acompanhamentoUsuarioLogGeral() {
        try {
            
            
            
            con_logGeral.conecta();
            con_logGeral.executeSQL("select * from log_usuario where (turno_login = "+logUsuario.turno+") and (data_login between '"+logUsuario.periodoInicial+"' and '"+logUsuario.periodoFinal+"') and (situacao_logout = "+logUsuario.situacaoLogout+") and (tipo_usuario = "+logUsuario.tipoDeUsuario+") and (situacao_logout != 'Em execucao...') order by num_log");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_logGeral.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/logUsuarioGeral.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_logGeral.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoUsuarioLogGeral();
    }
}



/**
 *
 * @author ayrton monier
 */

