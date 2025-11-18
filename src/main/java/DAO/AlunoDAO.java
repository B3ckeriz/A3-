package DAO;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Aluno;

import java.sql.*;

public class AlunoDAO {

    private static final Logger logger = Logger.getLogger(AlunoDAO.class.getName());
    private static final String DATABASE_URL = initDatabaseUrl();
    
    // Cache de conexão para evitar recriações desnecessárias
    private static String initDatabaseUrl() {
        String url = System.getenv("DATABASE_URL");
        if (url == null || url.isEmpty()) {
            url = "jdbc:sqlite:database.db";
        }
        logger.info("Database URL configurada: " + url);
        return url;
    }

    public AlunoDAO() {
        criarTabelaSeNecessario();
    }

    private static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    /**
     * Testa se a conexão com o banco de dados está funcionando
     * @return true se a conexão for bem-sucedida, false caso contrário
     */
    public boolean testarConexao() {
        try (Connection conn = getConexao()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Falha ao testar conexão com banco de dados", e);
            return false;
        }
    }

    private void criarTabelaSeNecessario() {
        String sqlAlunos = """
            CREATE TABLE IF NOT EXISTS tb_alunos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                idade INTEGER,
                curso TEXT,
                fase INTEGER
            )
        """;

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlAlunos);
            
            // Criar índice para melhorar performance de buscas
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_nome ON tb_alunos(nome)");
            
            logger.info("Tabela tb_alunos verificada/criada com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar tabela tb_alunos", e);
            throw new RuntimeException("Erro ao criar tabela tb_alunos", e);
        }
    }

    public int maiorID() {
        String sql = "SELECT COALESCE(MAX(id), 0) AS id FROM tb_alunos";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("id");
            }
            return 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar maior ID", ex);
            throw new RuntimeException("Erro ao buscar maior ID", ex);
        }
    }

    public List<Aluno> getMinhaLista() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT id, nome, idade, curso, fase FROM tb_alunos ORDER BY id";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                lista.add(mapResultSetToAluno(res));
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao carregar lista de alunos", ex);
            throw new RuntimeException("Erro ao carregar lista de alunos", ex);
        }

        return lista;
    }

    public boolean insertAlunoBD(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        }

        String sql = "INSERT INTO tb_alunos (id, nome, idade, curso, fase) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setAlunoParameters(stmt, aluno, true);
            stmt.executeUpdate();
            
            logger.info("Aluno inserido com sucesso: ID=" + aluno.getId());
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir aluno: " + aluno.getNome(), e);
            throw new RuntimeException("Erro ao inserir aluno", e);
        }
    }

    public boolean deleteAlunoBD(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }

        String sql = "DELETE FROM tb_alunos WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                logger.info("Aluno deletado com sucesso: ID=" + id);
                return true;
            }
            
            logger.warning("Nenhum aluno encontrado com ID=" + id);
            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar aluno com ID=" + id, e);
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }

    public boolean updateAlunoBD(Aluno aluno) {
        if (aluno == null || aluno.getId() <= 0) {
            throw new IllegalArgumentException("Aluno inválido para atualização");
        }

        String sql = """
            UPDATE tb_alunos
            SET nome = ?, idade = ?, curso = ?, fase = ?
            WHERE id = ?
        """;

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setAlunoParameters(stmt, aluno, false);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                logger.info("Aluno atualizado com sucesso: ID=" + aluno.getId());
                return true;
            }
            
            logger.warning("Nenhum aluno encontrado para atualizar: ID=" + aluno.getId());
            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar aluno: " + aluno.getNome(), e);
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public Optional<Aluno> carregaAluno(int id) {
        if (id <= 0) {
            return Optional.empty();
        }

        String sql = "SELECT id, nome, idade, curso, fase FROM tb_alunos WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return Optional.of(mapResultSetToAluno(res));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao carregar aluno com ID=" + id, e);
            throw new RuntimeException("Erro ao carregar aluno", e);
        }

        return Optional.empty();
    }

    // Método auxiliar para mapear ResultSet para Aluno
    private Aluno mapResultSetToAluno(ResultSet res) throws SQLException {
        return new Aluno(
            res.getString("curso"),
            res.getInt("fase"),
            res.getInt("id"),
            res.getString("nome"),
            res.getInt("idade")
        );
    }

    // Método auxiliar para setar parâmetros do PreparedStatement
    private void setAlunoParameters(PreparedStatement stmt, Aluno aluno, boolean includeId) 
            throws SQLException {
        if (includeId) {
            stmt.setInt(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getIdade());
            stmt.setString(4, aluno.getCurso());
            stmt.setInt(5, aluno.getFase());
        } else {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getFase());
            stmt.setInt(5, aluno.getId());
        }
    }

    // Método para buscar alunos por nome (exemplo de novo recurso otimizado)
    public List<Aluno> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT id, nome, idade, curso, fase FROM tb_alunos WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    lista.add(mapResultSetToAluno(res));
                }
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao buscar alunos por nome: " + nome, ex);
            throw new RuntimeException("Erro ao buscar alunos por nome", ex);
        }

        return lista;
    }

    // Método para contar total de alunos
    public int contarAlunos() {
        String sql = "SELECT COUNT(*) AS total FROM tb_alunos";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("total");
            }
            return 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao contar alunos", ex);
            throw new RuntimeException("Erro ao contar alunos", ex);
        }
    }
    
}