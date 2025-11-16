package DAO;

import Model.Professor;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorDAO {
    
    // Arraylist dinâmico para armazenar temporariamente os dados que serão retornados pela função getMinhaLista()
    public static ArrayList<Professor> MinhaLista2 = new ArrayList<Professor>();

    public ProfessorDAO() {
        criarTabelaSeNecessario();
    }
    
    // Retorna o maior ID do banco de dados
    public int maiorID() throws SQLException {
        int maiorID = 0;
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_professores");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }
    
    // Estabelece a conexão com o banco de dados SQLite
    public Connection getConexao() {

        Connection connection = null;  // Instância da conexão

        try {
            // Carregamento do JDBC Driver do SQLite
            String driver = "org.sqlite.JDBC";
            Class.forName(driver);

            // Configurar a conexão - banco local em arquivo
            String url = "jdbc:sqlite:database.db";
            
            connection = DriverManager.getConnection(url);

            // Testando a conexão
            if (connection != null) {
                System.out.println("Status: Conectado ao SQLite!");
            } else {
                System.out.println("Status: Não conectado!");
            }

            return connection;
            
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver não foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Não foi possível conectar: " + e.getMessage());
            return null;
        }
    }
    
    // Cria a tabela de professores se não existir
    private void criarTabelaSeNecessario() {
        String sqlProfessores = "CREATE TABLE IF NOT EXISTS tb_professores (" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "idade INTEGER, " +
                "campus TEXT, " +
                "cpf TEXT, " +
                "contato TEXT, " +
                "titulo TEXT, " +
                "salario INTEGER)";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlProfessores);
            System.out.println("Tabela tb_professores verificada/criada!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
    
    // Retorna a lista de professores do banco de dados
    public ArrayList getMinhaLista() {
        
        MinhaLista2.clear(); // Limpa o arrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores");
            while (res.next()) {

                String campus = res.getString("campus");
                String cpf = res.getString("cpf");
                String contato = res.getString("contato");
                String titulo = res.getString("titulo");
                int salario = res.getInt("salario");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                int idade = res.getInt("idade");

                Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);

                MinhaLista2.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista2;
    }
    
    // Cadastra novo professor
    public boolean InsertProfessorBD(Professor objeto) {
        String sql = "INSERT INTO tb_professores(id,nome,idade,campus,cpf,contato,titulo,salario) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCampus());
            stmt.setString(5, objeto.getCpf());
            stmt.setString(6, objeto.getContato());
            stmt.setString(7, objeto.getTitulo());
            stmt.setInt(8, objeto.getSalario());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }
    
    // Deleta um professor específico pelo seu campo ID
    public boolean DeleteProfessorBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_professores WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }
    
    // Edita um aluno específico pelo seu campo ID
    public boolean UpdateProfessorBD(Professor objeto) {

        String sql = "UPDATE tb_professores set nome = ? ,idade = ? ,campus = ? ,cpf = ? ,contato = ? ,titulo = ? ,salario = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            
            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCampus());
            stmt.setString(4, objeto.getCpf());
            stmt.setString(5, objeto.getContato());
            stmt.setString(6, objeto.getTitulo());
            stmt.setInt(7, objeto.getSalario());
            stmt.setInt(8, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    // Carrega as informações de um professor específico com base no ID
    public Professor carregaProfessor(int id) {
        
        Professor objeto = new Professor();
        objeto.setId(id);
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setIdade(res.getInt("idade"));
            objeto.setCampus(res.getString("campus"));
            objeto.setCpf(res.getString("cpf"));
            objeto.setContato(res.getString("contato"));
            objeto.setTitulo(res.getString("titulo"));
            objeto.setSalario(res.getInt("salario"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto;
    }
}
