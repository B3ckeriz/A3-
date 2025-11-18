package DAO;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());
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

    public UsuarioDAO() {
        criarTabelaSeNecessario();
    }

    public Connection getConexao() throws SQLException {
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
        String sqlUsuarios = """
            CREATE TABLE IF NOT EXISTS tb_usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                idade INTEGER,
                username TEXT,
                senha INTEGER
            )
        """;

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlUsuarios);

            // Criar índice para melhorar performance de buscas
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_nome ON tb_usuarios(nome)");

            logger.info("Tabela tb_usuarios verificada/criada com sucesso!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao criar tabela tb_usuarios", e);
            throw new RuntimeException("Erro ao criar tabela tb_usuarios", e);
        }
    }

    public int maiorID() {
        String sql = "SELECT COALESCE(MAX(id), 0) AS id FROM tb_usuarios";

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

    public List<Usuario> getMinhaLista() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, username, nome, idade, FROM tb_usuarios ORDER BY id";

        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                lista.add(mapResultSetToUsuario(res));
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Erro ao carregar lista de usuarios", ex);
            throw new RuntimeException("Erro ao carregar lista de usuarios", ex);
        }

        return lista;
    }

    public boolean insertUsuarioBD(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        }

        String sql = "INSERT INTO tb_usuarios (id, username, senha, nome, idade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setUsuarioParameters(stmt, usuario, true);
            stmt.executeUpdate();

            logger.info("Usuario inserido com sucesso: ID=" + usuario.getId());
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir usuario: " + usuario.getNome(), e);
            throw new RuntimeException("Erro ao inserir usuario", e);
        }
    }

    public boolean deleteUsuarioBD(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }

        String sql = "DELETE FROM tb_usuarios WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Usuario deletado com sucesso: ID=" + id);
                return true;
            }

            logger.warning("Usuario usuario encontrado com ID=" + id);
            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar usuario com ID=" + id, e);
            throw new RuntimeException("Erro ao deletar usuario", e);
        }
    }

    public boolean updateUsuarioBD(Usuario usuario) {
        if (usuario == null || usuario.getId() <= 0) {
            throw new IllegalArgumentException("Usuario inválido para atualização");
        }

        String sql = """
            UPDATE tb_usuarios
            SET nome = ?, idade = ?, username = ?, senha = ?
            WHERE id = ?
        """;

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setUsuarioParameters(stmt, usuario, false);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Usuario atualizado com sucesso: ID=" + usuario.getId());
                return true;
            }

            logger.warning("Nenhum usuario encontrado para atualizar: ID=" + usuario.getId());
            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar usuario: " + usuario.getNome(), e);
            throw new RuntimeException("Erro ao atualizar usuario", e);
        }
    }

    public Optional<Usuario> carregaUsuario(int id) {
        if (id <= 0) {
            return Optional.empty();
        }

        String sql = "SELECT id, nome, idade, username FROM tb_usuarios WHERE id = ?";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return Optional.of(mapResultSetToUsuario(res));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao carregar usuario com ID=" + id, e);
            throw new RuntimeException("Erro ao carregar usuario", e);
        }

        return Optional.empty();
    }

    // Método auxiliar para mapear ResultSet para Usuario
    private Usuario mapResultSetToUsuario(ResultSet res) throws SQLException {
        return new Usuario(
                res.getString("username"),
                res.getString("senha"),
                res.getInt("id"),
                res.getString("nome"),
                res.getInt("idade")
        );
    }

    // Método auxiliar para setar parâmetros do PreparedStatement
    private void setUsuarioParameters(PreparedStatement stmt, Usuario usuario, boolean includeId)
            throws SQLException {
        if (includeId) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setInt(3, usuario.getIdade());
            stmt.setString(4, usuario.getUsername());
            stmt.setString(5, usuario.getSenha());
        } else {
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getIdade());
            stmt.setString(3, usuario.getUsername());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, usuario.getId());
        }
    }





}