package dao;

import model.Aluno;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunoDAOTest {

    private static AlunoDAO dao;

    @BeforeAll
    static void setupDatabase() throws Exception {

        dao = new AlunoDAO();
        dao.setTestDatabase("jdbc:sqlite:memory:testdb?mode=memory&cache=shared");

        try (Connection conn = dao.getConexao()) {
            conn.createStatement().execute("""
                CREATE TABLE tb_alunos (
                    id INTEGER PRIMARY KEY,
                    nome TEXT,
                    idade INTEGER,
                    curso TEXT,
                    fase INTEGER
                )
            """);
        }
    }


    @Test
    @Order(1)
    void testInsertAluno() {
        Aluno a = new Aluno();
        a.setNome("Kaio");
        a.setIdade(25);
        a.setCurso("SI");
        a.setFase(5);

        assertTrue(dao.insertAluno(a));
    }

    @Test
    @Order(2)
    void testListarAlunos() {
        List<Aluno> lista = dao.getMinhaLista();
        assertEquals(1, lista.size());
        assertEquals("Kaio", lista.get(0).getNome());
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        int id = dao.getMinhaLista().get(0).getId();
        Aluno buscado = dao.carregaAluno(id);

        assertEquals("Kaio", buscado.getNome());
    }

    @Test
    @Order(4)
    void testUpdateAluno() {
        int id = dao.getMinhaLista().get(0).getId();

        Aluno novo = new Aluno();
        novo.setId(id);
        novo.setNome("Kaio Atualizado");
        novo.setIdade(30);
        novo.setCurso("Engenharia");
        novo.setFase(7);

        assertTrue(dao.updateAluno(novo));

        Aluno atualizado = dao.carregaAluno(id);
        assertEquals("Kaio Atualizado", atualizado.getNome());
    }

    @Test
    @Order(5)
    void testDeleteAluno() {
        int id = dao.getMinhaLista().get(0).getId();
        assertTrue(dao.deleteAluno(id));
        assertEquals(0, dao.getMinhaLista().size());
    }
}