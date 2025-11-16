package DAO;

import Model.Aluno;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoDAO {
    
    // Arraylist dinâmico para armazenar temporariamente os dados que serão retornados pela função getMinhaLista()
    public static ArrayList<Aluno> MinhaLista = new ArrayList<Aluno>();
    
    // Construtor padrão
    public AlunoDAO() {
        criarTabelaSeNecessario();
    }
    
    // Retorna o maior ID do banco de dados
    public int maiorID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_alunos");
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

        } catch (ClassNotFoundException e) {  // Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;

        } catch (SQLException e) {
            System.out.println("Não foi possivel conectar: " + e.getMessage());
            return null;
        }
    }
    
    // Cria a tabela de alunos se não existir
    private void criarTabelaSeNecessario() {
        String sqlAlunos = "CREATE TABLE IF NOT EXISTS tb_alunos (" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "idade INTEGER, " +
                "curso TEXT, " +
                "fase INTEGER)";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlAlunos);
            System.out.println("Tabela tb_alunos verificada/criada!");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    // Retorna a lista de alunos do banco de dados
    public ArrayList getMinhaLista() {
        
        MinhaLista.clear(); // Limpa o ArrayList

        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_alunos");
            while (res.next()) {

                String curso = res.getString("curso");
                int fase = res.getInt("fase");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                int idade = res.getInt("idade");

                Aluno objeto = new Aluno(curso, fase, id, nome, idade);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    // Cadastra novo aluno
    public boolean InsertAlunoBD(Aluno objeto) {
        String sql = "INSERT INTO tb_alunos(id,nome,idade,curso,fase) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCurso());
            stmt.setInt(5, objeto.getFase());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    // Deleta um aluno específico pelo seu campo ID
    public boolean DeleteAlunoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_alunos WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true;
    }

    // Edita um aluno específico pelo seu campo ID
    public boolean UpdateAlunoBD(Aluno objeto) {

        String sql = "UPDATE tb_alunos set nome = ? ,idade = ? ,curso = ? ,fase = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCurso());
            stmt.setInt(4, objeto.getFase());
            stmt.setInt(5, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    // Carrega as informações de um aluno específico com base no ID
    public Aluno carregaAluno(int id) {
        
        Aluno objeto = new Aluno();
        objeto.setId(id);
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_alunos WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setIdade(res.getInt("idade"));
            objeto.setCurso(res.getString("curso"));
            objeto.setFase(res.getInt("fase"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return objeto;
    }
}
