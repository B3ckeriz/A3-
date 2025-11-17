package DAO;

import Model.Aluno;
import java.util.*;
import java.sql.*;
import java.util.logging.Logger;

public class AlunoDAO {

    private static final Logger logger = Logger.getLogger(AlunoDAO.class.getName());

    public static ArrayList<Aluno> MinhaLista = new ArrayList<>();

    public AlunoDAO() {
        criarTabelaSeNecessario();
    }

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

    // Conexão com SQLite
    public static Connection getConnection() {
        try {
            String url = System.getenv("DATABASE_URL");
            if (url == null || url.isEmpty()) {
                url = "jdbc:sqlite:database.db";
            }

            logger.info("URL utilizada: " + url);
            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConexao() {
        return getConnection();
    }


    // Cria tabela se necessário
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
            logger.info("Tabela tb_alunos verificada/criada!");

        } catch (SQLException e) {
            logger.info("Erro ao criar tabelas: " + e.getMessage());
        }
    }


    public ArrayList<Aluno> getMinhaLista() {

        MinhaLista.clear();

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


    public boolean DeleteAlunoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_alunos WHERE id = " + id);
            stmt.close();

        } catch (SQLException erro) {
        }
        return true;
    }


    public boolean UpdateAlunoBD(Aluno objeto) {

        String sql = "UPDATE tb_alunos SET nome=?, idade=?, curso=?, fase=? WHERE id=?";

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
