/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import Cadastro.CadastroCliente;
import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import auxiliar.conexao;

public class clienteSuspenso{
    
    conexao con_suspenso = new conexao();
    
    
    
    public clienteSuspenso() {
        try {
            
            
            
            con_suspenso.conecta();
            con_suspenso.executeSQL("select * from cliente where situacao = 'Suspenso' and contrato_cli = "+CadastroCliente.contratoCli.getText());
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_suspenso.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clienteSuspenso.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_suspenso.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clienteSuspensoIndeterminado();
    }
}



/**
 *
 * @author ayrton monier
 */

