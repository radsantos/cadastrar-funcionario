package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author rogerio
 */
public class FuncionarioDao {
    
    Connection conn;
    Statement st;

    public boolean conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:/db_Cadastro", "root", "Allin1_2019");
            
            st = conn.createStatement();

            return true;
        } catch (ClassNotFoundException ex) {

            return false;
            
        } catch (SQLException ex) {
            return false;
        }
    }

}
