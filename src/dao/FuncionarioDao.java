package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import model.Funcionario;

/**
 *
 * @author rogerio
 */
public class FuncionarioDao {

    Connection conn;
    Statement st;
    
    public FuncionarioDao(){
       
    }

    public boolean conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_Cadastro", "cadastro", "12345678");

            st = conn.createStatement();

            return true;
        } catch (ClassNotFoundException | SQLException ex) {

            return false;

        }
    }

    public boolean salvar(Funcionario funcionario) {

        try {
            String sql = "INSERT INTO funcionario(matricula,nome,cargo,salario) VALUES('" + funcionario.getMatricula() + "','" + funcionario.getNome() + "','" + funcionario.getCargo() + "'," + funcionario.getSalario() + ")";

            st.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            return false;

        }

    }
    
    
    public void desconectar(){
        
        try {
            
            conn.close();
            
        } catch (SQLException ex) {
           
        }
    }

}
