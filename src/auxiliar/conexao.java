/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Administrador
 */
public class conexao {

final private String banco = "mysql";
final private String host = validaLogin.HOST;
final private String porta = "3306";
final private String baseDados = "serquip";
final private String driver= "com.mysql.jdbc.Driver";
final private String url= "jdbc:"+banco+"://"+host+":"+porta+"/"+baseDados;
final private String usuario= "root";
final private String senha= "suporte";
private Connection conexao;
public Statement statement;
public ResultSet resultset;

//metodo que irá efetuar a conexão com o banco de dados
public boolean conecta(){

boolean result = true;

  try{
    //método foName vai carregar o driver de conexão
    Class.forName(driver);

    conexao = DriverManager.getConnection(url, usuario, senha);
    //JOptionPane.showMessageDialog(null, "CONECTADO A BASE DE DADOS!");
  }

  catch(ClassNotFoundException Driver){

    JOptionPane.showMessageDialog(null, "Driver não localizado configure a conexão\n\nErro: "+Driver, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;

  }

  catch(SQLException erroSQL){

    JOptionPane.showMessageDialog(null, "A base de dados não foi localizada no host informado\n\n"+erroSQL, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;

  }

  return result;

}//fim da classe conecta

public void desconecta(){

boolean result = true;

    try{

    conexao.close();
    //JOptionPane.showMessageDialog(null, "DESCONECTADO DA BASE DE DADOS!");

    }

    catch(SQLException fecha){

    JOptionPane.showMessageDialog(null, "Não foi possível fechar o banco de dados: "+fecha, "erro", JOptionPane.ERROR_MESSAGE);
    result = false;
    }
}

public void executeSQL(String sql){

    try{

    statement = conexao.createStatement(resultset.TYPE_SCROLL_INSENSITIVE, resultset.CONCUR_READ_ONLY);
    resultset = statement.executeQuery(sql);

    }

    catch(SQLException sqlex){

    JOptionPane.showMessageDialog(null, "Não foi possível executar o comando sql!\nO comando foi: "+sqlex, "erro", JOptionPane.ERROR_MESSAGE);
    }

}

}
