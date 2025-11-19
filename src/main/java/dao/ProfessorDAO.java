package dao;

import model.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private List<Professor> listaProfessores = new ArrayList<>();
    private String databaseUrl = "jdbc:sqlite:database.db";
    private boolean isTestMode = false;

    public ProfessorDAO() {
        if (!isTestMode) {
            criarTabelaSeNecessario();
        }
    }

    public void setTestDatabase(String url) {
        this.databaseUrl = url;
        this.isTestMode = true;
    }

    public Connection getConexao() {
        try {
            return DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }

    private void criarTabelaSeNecessario() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tb_professores (
                id INTEGER PRIMARY KEY,
                nome TEXT NOT NULL,
                idade INTEGER,
                campus TEXT,
                cpf TEXT,
                contato TEXT,
                titulo TEXT,
                salario REAL
            )
        """;

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela", e);
        }
    }

    public List<Professor> getMinhaLista() {
        listaProfessores.clear();
        String sql = "SELECT * FROM tb_professores";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                Professor p = new Professor();
                p.setId(res.getInt("id"));
                p.setNome(res.getString("nome"));
                p.setIdade(res.getInt("idade"));
                p.setCampus(res.getString("campus"));
                p.setCpf(res.getString("cpf"));
                p.setContato(res.getString("contato"));
                p.setTitulo(res.getString("titulo"));
                p.setSalario(res.getDouble("salario"));

                listaProfessores.add(p);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao carregar lista", ex);
        }

        return listaProfessores;
    }

    /* Métodos padronizados para os testes */

    public boolean insertProfessor(Professor p) {
        return InsertProfessorBD(p);
    }

    public boolean updateProfessor(Professor p) {
        return UpdateProfessorBD(p);
    }

    public boolean deleteProfessor(int id) {
        return DeleteProfessorBD(id);
    }

    /* Implementação de CRUD */

    public boolean InsertProfessorBD(Professor objeto) {
        String sql = """
            INSERT INTO tb_professores
            (id, nome, idade, campus, cpf, contato, titulo, salario)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCampus());
            stmt.setString(5, objeto.getCpf());
            stmt.setString(6, objeto.getContato());
            stmt.setString(7, objeto.getTitulo());
            stmt.setDouble(8, objeto.getSalario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir professor", erro);
        }
    }

    public boolean DeleteProfessorBD(int id) {
        String sql = "DELETE FROM tb_professores WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao deletar professor", erro);
        }
    }

    public boolean UpdateProfessorBD(Professor objeto) {
        String sql = """
            UPDATE tb_professores
            SET nome = ?, idade = ?, campus = ?, cpf = ?, contato = ?, titulo = ?, salario = ?
            WHERE id = ?
        """;

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCampus());
            stmt.setString(4, objeto.getCpf());
            stmt.setString(5, objeto.getContato());
            stmt.setString(6, objeto.getTitulo());
            stmt.setDouble(7, objeto.getSalario());
            stmt.setInt(8, objeto.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar professor", erro);
        }
    }

    public Professor carregaProfessor(int id) {
        String sql = "SELECT * FROM tb_professores WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    Professor p = new Professor();
                    p.setId(id);
                    p.setNome(res.getString("nome"));
                    p.setIdade(res.getInt("idade"));
                    p.setCampus(res.getString("campus"));
                    p.setCpf(res.getString("cpf"));
                    p.setContato(res.getString("contato"));
                    p.setTitulo(res.getString("titulo"));
                    p.setSalario(res.getDouble("salario"));

                    return p;
                }
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao carregar professor", erro);
        }

        return null;
    }

    /* Suporte para Professor.maiorID() */

    public int maiorID() throws SQLException {
        String sql = "SELECT MAX(id) AS id FROM tb_professores";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("id");
            }
            return 0;
        }
    }
}