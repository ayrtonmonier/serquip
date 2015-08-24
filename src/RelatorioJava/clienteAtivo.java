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

public class clienteAtivo{
    
    conexao con_ativo = new conexao();
    
    
    
    public clienteAtivo() {
        try {
                     
            
            con_ativo.conecta();
            con_ativo.executeSQL("select * from cliente where situacao = 'Ativo' and contrato_cli = "+CadastroCliente.contratoCli.getText());
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_ativo.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
            "RelatoriosArqJasper/clienteAtivo.jasper", new HashMap(), jrRS);
            
            JasperViewer.viewReport(jasperPrint, false);
            
            //descon_ativoecta
            con_ativo.desconecta();
            
            
            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clienteAtivo();
    }
}



/**
 *
 * @author ayrton monier
 */

