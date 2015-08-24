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

public class acompanhamentoUsuarioLogInd{
    
    conexao con_acBombUser = new conexao();
    
    
    
    public acompanhamentoUsuarioLogInd() {
        try {
            
            
            
            con_acBombUser.conecta();
            con_acBombUser.executeSQL("select l.*, u.* from log_usuario l, usuario u where l.matricula_usuario = u.matricula_usuario and (l.turno_login = "+logUsuario.turno+") and (l.data_login between '"+logUsuario.periodoInicial+"' and '"+logUsuario.periodoFinal+"') and (l.situacao_logout = "+logUsuario.situacaoLogout+") and (l.tipo_usuario = "+logUsuario.tipoDeUsuario+") and l.matricula_usuario = "+logUsuario.matricula+" and (l.situacao_logout != 'Em execucao...') order by num_log");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_acBombUser.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/logUsuarioInd.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_acBombUser.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new acompanhamentoUsuarioLogInd();
    }
}



/**
 *
 * @author ayrton monier
 */

