package dao;

import model.Professor;
import org.junit.jupiter.api.*;
//import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorDAOTest {

    static ProfessorDAO dao;
/*
    @BeforeAll
    static void setupDatabase() throws Exception {
        dao = new ProfessorDAO();
        dao.setTestDatabase("jdbc:sqlite::memory:");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            conn.createStatement().execute(
                "CREATE TABLE tb_professores (" +
                "id INTEGER PRIMARY KEY, " +
                "nome TEXT, idade INTEGER, campus TEXT, cpf TEXT, contato TEXT, titulo TEXT, salario REAL)"
            );
        }
    }*/

    @Test
    @Order(1)
    void testInsertProfessor() {
        Professor p = new Professor();
        p.setId(1);
        p.setNome("Kaio");
        p.setIdade(25);
        p.setCampus("SI");
        p.setCpf("00011122233");
        p.setContato("99999999");
        p.setTitulo("Mestre");
        p.setSalario(120000);

        assertTrue(dao.insertProfessor(p));
    }

    @Test
    @Order(2)
    void testUpdateProfessor() {
        Professor p = dao.getMinhaLista().get(0);
        p.setNome("Kaio Atualizado");

        assertTrue(dao.updateProfessor(p));
    }

    @Test
    @Order(3)
    void testListar() {
        var lista = dao.getMinhaLista();
        assertFalse(lista.isEmpty());
        assertEquals("Kaio Atualizado", lista.get(0).getNome());
    }

    @Test
    @Order(4)
    void testDeleteProfessor() {
        assertTrue(dao.deleteProfessor(1));
    }
}