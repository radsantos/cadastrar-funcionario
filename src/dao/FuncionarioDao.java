package dao;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public void desconectar() {

        try {

            conn.close();

        } catch (SQLException ex) {

        }
    }

}
