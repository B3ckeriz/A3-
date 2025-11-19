package dao;

import model.Professor;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorDAO {

    private final List<Professor> listaProfessores = new ArrayList<>();

    public ProfessorDAO() {
        criarTabelaSeNecessario();
    }

    public int maiorID() {
        String sql = "SELECT MAX(id) AS id FROM tb_professores";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("id");
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar maior ID", ex);
        }

        return 0;
    }

    public static Connection getConnection() {
        try {
            String url = System.getenv("DATABASE_URL");
            if (url == null || url.isEmpty()) {
                url = "jdbc:sqlite:database.db";
            }
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }

    public Connection getConexao() {
        return getConnection();
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
                    salario INTEGER
                )
                """;

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela tb_professores", e);
        }
    }

    public List<Professor> getMinhaLista() {
        listaProfessores.clear();
        String sql = "SELECT * FROM tb_professores";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {

                String campus = res.getString("campus");
                if (campus == null) campus = "Não informado";

                Professor p = new Professor(
                        campus,
                        res.getString("cpf"),
                        res.getString("contato"),
                        res.getString("titulo"),
                        res.getInt("salario"),
                        res.getInt("id"),
                        res.getString("nome"),
                        res.getInt("idade")
                );

                listaProfessores.add(p);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao carregar lista de professores", ex);
        }

        return listaProfessores;
    }

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
            stmt.setInt(8, objeto.getSalario());

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
            stmt.setInt(7, objeto.getSalario());
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
                    p.setSalario(res.getInt("salario"));

                    return p;
                }
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao carregar professor", erro);
        }

        return null;
    }
}