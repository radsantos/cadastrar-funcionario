package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import model.Funcionario;

/**
 *
 * @author rogerio
 */
public class FuncionarioDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public FuncionarioDao() {

    }

    public boolean conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_Cadastro", "cadastro", "123456789");

            return true;
        } catch (ClassNotFoundException | SQLException ex) {

            return false;

        }
    }

    public boolean salvar(Funcionario funcionario) {

        try {
            // String sql = "INSERT INTO funcionario(matricula,nome,cargo,salario) VALUES('" + funcionario.getMatricula() + "','" + funcionario.getNome() + "','" + funcionario.getCargo() + "'," + funcionario.getSalario() + ")";
            String sql = "INSERT INTO funcionario(matricula,nome,cargo,salario) VALUES(?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString(1, funcionario.getMatricula());
            st.setString(2, funcionario.getNome());
            st.setString(3, funcionario.getCargo());
            st.setDouble(4, funcionario.getSalario());

            st.executeUpdate();

            return true;

        } catch (SQLException ex) {
            return false;

        }

    }

    public Funcionario consultar(String matricula) {

        String sql = "SELECT * FROM funcionario WHERE matricula = ?";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, matricula);

            rs = st.executeQuery();

            if (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));

                return funcionario;

            } else {
                return null;
            }

        } catch (Exception e) {

            return null;
        }

    }

    public boolean excluir(String matricula) {

        String sql = "DELETE FROM funcionario  WHERE matricula = ?";

        try {

            st = conn.prepareStatement(sql);
            st.setString(1, matricula);

            st.executeUpdate();
            return true;

        } catch (SQLException e) {

            return false;
        }
    }
    
    
    public void editar(Funcionario funcionario){
        
        String sql = "UPDATE funcionario SET nome=?,cargo=?,salario=? WHERE matricula=?";
        
        try {
            
            st = conn.prepareStatement(sql);
            
            
            st.setString(1, funcionario.getNome());
            st.setString(2, funcionario.getCargo());
            st.setDouble(3,funcionario.getSalario());
            st.setString(4, funcionario.getMatricula());
            
            
            
            st.execute();
            JOptionPane.showMessageDialog(null, "Funcionário atualizado");
            
            desconectar();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário " + e.getMessage());
           
        }
        
    }

    public void desconectar() {

        try {

            conn.close();

        } catch (SQLException ex) {

        }
    }

}
