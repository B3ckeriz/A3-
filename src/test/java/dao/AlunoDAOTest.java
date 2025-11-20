package dao;

import model.Aluno;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoDAOTest {

    private static AlunoDAO dao;

    @BeforeEach
    public void prepararBancoDeTestes() {
        dao = new AlunoDAO();
        dao.setTestDatabase("jdbc:sqlite:database_test.db");

        // Garantir que a tabela esteja criada e limpa
        try (Connection conn = dao.getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS tb_alunos");
            stmt.execute("""
            CREATE TABLE tb_alunos (
                id INTEGER PRIMARY KEY,
                nome TEXT NOT NULL,
                idade INTEGER,
                curso TEXT,
                fase INTEGER
            )
        """);

            // Inserir registro inicial para o teste testRegistroInicial
            stmt.executeUpdate("""
            INSERT INTO tb_alunos (id, nome, idade, curso, fase)
            VALUES (1, 'Aluno Teste', 20, 'Curso', 1)
        """);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao preparar banco de testes", e);
        }
    }



    @Test
    public void testConfiguracaoBanco() {
        try (Connection conn = dao.getConexao();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='tb_alunos'");
            assertTrue(rs.next(), "A tabela tb_alunos deveria existir.");
        } catch (SQLException e) {
            fail("Erro ao validar configuração do banco: " + e.getMessage());
        }
    }

    @Test
    public void testRegistroInicial() {
        Aluno aluno = dao.carregaAluno(1);
        assertNotNull(aluno, "Aluno com ID 1 deveria existir.");
        assertEquals("Aluno Teste", aluno.getNome());
    }

    @Test
    public void testInsertAluno() {
        // Criando objeto Aluno com construtor válido
        Aluno aluno = new Aluno("Engenharia", 2, 123, "Novo Aluno", 21);

        // Inserção do aluno no banco de testes
        assertTrue(dao.insertAluno(aluno), "A inserção do aluno deveria ter sucesso.");
    }

    @Test
    public void testGetMinhaLista() {
        // Inserir registros de teste
        assertTrue(dao.insertAluno(new Aluno("Engenharia", 3, 2, "João Silva", 22)), "A inserção do aluno deveria ter sucesso.");

        // Testar se os registros foram retornados corretamente
        List<Aluno> alunos = dao.getMinhaLista();
        assertEquals(2, alunos.size(), "A lista deveria conter dois registros."); // Inclui o registro de ID 1 do @BeforeEach
        assertEquals("Aluno Teste", alunos.get(0).getNome());
        assertEquals("João Silva", alunos.get(1).getNome());
    }
}
