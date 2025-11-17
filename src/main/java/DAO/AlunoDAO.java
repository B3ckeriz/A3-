package DAO;

import Model.Aluno;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class AlunoDAO {

    private static final Logger logger = Logger.getLogger(AlunoDAO.class.getName());
    public static ArrayList<Aluno> MinhaLista = new ArrayList<>();

    public AlunoDAO() {
        criarTabelaSeNecessario();
    }

    public int maiorID() {
        int maiorID = 0;

        String sql = "SELECT MAX(id) AS id FROM tb_alunos";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                maiorID = res.getInt("id");
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar maior ID", ex);
            throw new RuntimeException("Erro ao buscar maior ID", ex);
        }

        return maiorID;
    }

    public static Connection getConexao() {
        try {
            String url = System.getenv("DATABASE_URL");
            if (url == null || url.isEmpty()) {
                // Usa banco em memória como fallback
                url = "jdbc:sqlite::memory:";
            }
            System.out.println("URL utilizada: " + url);
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void criarTabelaSeNecessario() {
        String sqlAlunos = """
            CREATE TABLE IF NOT EXISTS tb_alunos (
                id INTEGER PRIMARY KEY,
                nome TEXT NOT NULL,
                idade INTEGER,
                curso TEXT,
                fase INTEGER
            )
        """;

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlAlunos);
            logger.info("Tabela tb_alunos verificada/criada!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar tabela tb_alunos", e);
            throw new RuntimeException("Erro ao criar tabela tb_alunos", e);
        }
    }

    public ArrayList<Aluno> getMinhaLista() {
        MinhaLista.clear();

        String sql = "SELECT * FROM tb_alunos";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                Aluno a = new Aluno(
                        res.getString("curso"),
                        res.getInt("fase"),
                        res.getInt("id"),
                        res.getString("nome"),
                        res.getInt("idade")
                );
                MinhaLista.add(a);
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao carregar lista de alunos", ex);
            throw new RuntimeException("Erro ao carregar lista de alunos", ex);
        }

        return MinhaLista;
    }

    public boolean InsertAlunoBD(Aluno objeto) {
        String sql = "INSERT INTO tb_alunos (id, nome, idade, curso, fase) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCurso());
            stmt.setInt(5, objeto.getFase());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir aluno", e);
            throw new RuntimeException("Erro ao inserir aluno", e);
        }
    }

    public boolean DeleteAlunoBD(int id) {
        String sql = "DELETE FROM tb_alunos WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar aluno", e);
            throw new RuntimeException("Erro ao deletar aluno", e);
        }

        return true;
    }

    public boolean UpdateAlunoBD(Aluno objeto) {
        String sql = """
            UPDATE tb_alunos
            SET nome = ?, idade = ?, curso = ?, fase = ?
            WHERE id = ?
        """;

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCurso());
            stmt.setInt(4, objeto.getFase());
            stmt.setInt(5, objeto.getId());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar aluno", e);
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public Aluno carregaAluno(int id) {
        String sql = "SELECT * FROM tb_alunos WHERE id = ?";

        Aluno objeto = new Aluno();
        objeto.setId(id);

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                objeto.setNome(res.getString("nome"));
                objeto.setIdade(res.getInt("idade"));
                objeto.setCurso(res.getString("curso"));
                objeto.setFase(res.getInt("fase"));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao carregar aluno", e);
            throw new RuntimeException("Erro ao carregar aluno", e);
        }

        return objeto;
    }
}
