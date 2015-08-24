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

public class clienteCancelado{
    
    conexao con_cancelado = new conexao();
    
    
    
    public clienteCancelado() {
        try {
            
            
            
            con_cancelado.conecta();
            con_cancelado.executeSQL("select * from cliente where situacao = 'Cancelado' and contrato_cli = "+CadastroCliente.contratoCli.getText());
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_cancelado.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "RelatoriosArqJasper/clienteCancelado.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //desconecta
            con_cancelado.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clienteCancelado();
    }
}



/**
 *
 * @author ayrton monier
 */

