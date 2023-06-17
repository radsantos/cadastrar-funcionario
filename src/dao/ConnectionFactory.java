
package dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author rogerio
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        
        try {
            
            return DriverManager.getConnection("jdbc:mysql://localhost/db_Cadastro","cadastro","123456789");
            
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
        
    }
    
}
