/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RelatorioJava;

import java.util.HashMap;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.view.JasperViewer;

import Cadastro.CadastroCliente;
import auxiliar.validaCadCli;
import auxiliar.conexao;

public class clientesCancelados{
    
    conexao con_cancelados = new conexao();

    public clientesCancelados() {
        try {

            con_cancelados.conecta();
            con_cancelados.executeSQL("select * from cliente where nome_cli like '"+CadastroCliente.caixaPesquisaNome.getText()+"%' and situacao = 'CANCELADO' order by "+validaCadCli.ordenacao);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_cancelados.resultset);
            JasperPrint jasperPrint = JasperFillManager.fillReport("RelatoriosArqJasper/clientesCancelados.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false);
            //desconecta
            con_cancelados.desconecta();

            
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null,"deu erro ="+erro);
        }
    }
    
    public static void main(String args[])
{
        new clientesCancelados();
    }
}



/**
 *
 * @author ayrton monier
 */


//codigo que exporta o pdf

//            con_cancelados.conecta();
//            String jasperFile = "RelatoriosArqJasper/clientesCancelados.jasper";
//            con_cancelados.executeSQL("select * from cliente where nome_cli like '"+CadastroCliente.caixaPesquisaNome.getText()+"%' and situacao = 'CANCELADO' order by "+validaCadCli.ordenacao);
//            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_cancelados.resultset);
//            //Exporta para PDF
//            String localExportacaoPdf = "src\\RelatoriosArqJasper\\clientesCancelados.pdf";
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, new HashMap(), jrRS);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, localExportacaoPdf);
//
//            JasperViewer.viewReport(jasperPrint, false);

