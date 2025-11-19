package dao;

import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class AlunoDAO {

    private static final Logger logger = Logger.getLogger(AlunoDAO.class.getName());

    private String databaseUrl = "jdbc:sqlite:database.db";
    private boolean isTestMode = false;

    public AlunoDAO() {
        if (!isTestMode) {
            criarTabelaSeNecessario();
        }
    }

    // Troca o banco ao rodar testes
    public void setTestDatabase(String url) {
        this.databaseUrl = url;
        this.isTestMode = true;
    }

    public Connection getConexao() {
        try {
            return DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void criarTabelaSeNecessario() {
        String sql = """
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

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela tb_alunos", e);
        }
    }

    public int maiorID() throws SQLException {
        String sql = "SELECT MAX(id) AS id FROM tb_alunos";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            return res.next() ? res.getInt("id") : 0;
        }
    }

    public List<Aluno> getMinhaLista() {
        List<Aluno> lista = new ArrayList<>();

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
                lista.add(a);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao carregar lista", ex);
        }

        return lista;
    }

    public boolean insertAluno(Aluno objeto) {
        String sql = """
            INSERT INTO tb_alunos (id, nome, idade, curso, fase)
            VALUES (?, ?, ?, ?, ?)
        """;

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
            throw new RuntimeException("Erro ao inserir aluno", e);
        }
    }

    public boolean deleteAluno(int id) {
        String sql = "DELETE FROM tb_alunos WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }

    public boolean updateAluno(Aluno objeto) {
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

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public Aluno carregaAluno(int id) {
        String sql = "SELECT * FROM tb_alunos WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            if (!res.next()) return null;

            return new Aluno(
                    res.getString("curso"),
                    res.getInt("fase"),
                    res.getInt("id"),
                    res.getString("nome"),
                    res.getInt("idade")
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar aluno", e);
        }
    }
}