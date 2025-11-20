package dao;

import model.Professor;
import java.sql.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessorDAO {

    private Connection conn;
    private String url = "jdbc:sqlite:database.db";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDAO.class);

    public ProfessorDAO() {
        try {
            this.conn = DriverManager.getConnection(url);
            criarTabelaSeNecessario();
        } catch (SQLException e) {
            LOGGER.error("Erro ao conectar ao banco de dados", e);
        }

    }

    /** Usado SOMENTE em teste automatizado */
    public void setTestDatabase() {
        try {
            this.url = "jdbc:sqlite::memory:";
            this.conn = DriverManager.getConnection(url);
            criarTabelaSeNecessario();
        } catch (SQLException e) {
            LOGGER.error("Erro ao conectar ao banco de dados", e);
        }

    }

    public Connection getConexao() {
        return this.conn;
    }

    private void criarTabelaSeNecessario() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS tb_professores (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                idade INTEGER NOT NULL,
                campus TEXT NOT NULL,
                cpf TEXT UNIQUE NOT NULL,
                contato TEXT NOT NULL,
                titulo TEXT NOT NULL,
                salario REAL NOT NULL
            );
            """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
}

    public int maiorID() {
        String sql = "SELECT MAX(id) AS id FROM tb_professores";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
        LOGGER.error("Erro ao conectar ao banco de dados", e);
    }

        return 0;
    }


    public boolean insertProfessor(Professor p) {
        String sql = """
        INSERT INTO tb_professores
        (nome, idade, campus, cpf, contato, titulo, salario)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getCampus());
            stmt.setString(4, p.getCpf());
            stmt.setString(5, p.getContato());
            stmt.setString(6, p.getTitulo());
            stmt.setDouble(7, p.getSalario()); // Corrigido para aceitar tipo double
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Erro ao conectar ao banco de dados", e); // Adicionado log para facilitar depuração
            return false;
        }
    }


    public boolean updateProfessor(Professor p) {
        String sql = """
            UPDATE tb_professores SET
            nome=?, idade=?, campus=?, cpf=?, contato=?, titulo=?, salario=?
            WHERE id=?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getCampus());
            stmt.setString(4, p.getCpf());
            stmt.setString(5, p.getContato());
            stmt.setString(6, p.getTitulo());
            stmt.setDouble(7, p.getSalario());
            stmt.setInt(8, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteProfessor(int id) {
        String sql = "DELETE FROM tb_professores WHERE id=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Professor> getMinhaLista() {
        ArrayList<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_professores";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor p = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("campus"),
                    rs.getString("cpf"),
                    rs.getString("contato"),
                    rs.getString("titulo"),
                    rs.getDouble("salario")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            LOGGER.error("Erro ao conectar ao banco de dados", e);
        }

        return lista;
    }

    public Professor carregaProfessor(int id) {
        String sql = "SELECT * FROM tb_professores WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("campus"),
                    rs.getString("cpf"),
                    rs.getString("contato"),
                    rs.getString("titulo"),
                    rs.getDouble("salario")
                );
            }

        } catch (SQLException e) {
            LOGGER.error("Erro ao conectar ao banco de dados", e);
        }
        return null;
    }
}