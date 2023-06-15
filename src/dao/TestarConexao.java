package dao;


/**
 *
 * @author rogerio
 * 
 * Classe para testar conexão com o banco de dados
 */
public class TestarConexao {
    public static void main(String[] args) {
        
        
        try {
            
            new FuncionarioDao().conectar();
            
            System.out.println("Conectador com sucesso");
            
        } catch (Exception e) {
            
            System.out.println("Erro de conexão");
        }
    }
    
}






