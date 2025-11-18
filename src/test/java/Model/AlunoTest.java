package Model;

import model.Aluno;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Aluno
 * @author Julia Exterkoetter
 */
public class AlunoTest {
    
    private Aluno aluno;
    
    @BeforeEach
    public void setUp() {
        // Inicializa um aluno antes de cada teste
        aluno = new Aluno("Engenharia de Software", 5, 1, "João Silva", 20);
    }
    
    @Test
    public void testConstrutorPadrao() {
        Aluno alunoVazio = new Aluno();
        assertNotNull(alunoVazio, "Aluno não deveria ser nulo");
    }
    
    @Test
    public void testConstrutorComCursoEFase() {
        Aluno alunoSimples = new Aluno("Ciência da Computação", 3);
        assertEquals("Ciência da Computação", alunoSimples.getCurso());
        assertEquals(3, alunoSimples.getFase());
    }
    
    @Test
    public void testConstrutorCompleto() {
        assertEquals(1, aluno.getId());
        assertEquals("João Silva", aluno.getNome());
        assertEquals(20, aluno.getIdade());
        assertEquals("Engenharia de Software", aluno.getCurso());
        assertEquals(5, aluno.getFase());
    }
    
    @Test
    public void testGetSetCurso() {
        aluno.setCurso("Sistemas de Informação");
        assertEquals("Sistemas de Informação", aluno.getCurso());
    }
    
    @Test
    public void testGetSetFase() {
        aluno.setFase(7);
        assertEquals(7, aluno.getFase());
    }
    
    @Test
    public void testGetSetId() {
        aluno.setId(100);
        assertEquals(100, aluno.getId());
    }
    
    @Test
    public void testGetSetNome() {
        aluno.setNome("Maria Santos");
        assertEquals("Maria Santos", aluno.getNome());
    }
    
    @Test
    public void testGetSetIdade() {
        aluno.setIdade(22);
        assertEquals(22, aluno.getIdade());
    }
    
    @Test
    public void testFaseMinima() {
        aluno.setFase(1);
        assertEquals(1, aluno.getFase());
    }
    
    @Test
    public void testFaseMaxima() {
        aluno.setFase(10);
        assertEquals(10, aluno.getFase());
    }
    
    @Test
    public void testCursoVazio() {
        aluno.setCurso("");
        assertEquals("", aluno.getCurso());
    }
    
    @Test
    public void testNomeVazio() {
        aluno.setNome("");
        assertEquals("", aluno.getNome());
    }
    
    @Test
    public void testIdadeZero() {
        aluno.setIdade(0);
        assertEquals(0, aluno.getIdade());
    }
    
    @Test
    public void testIdNegativo() {
        aluno.setId(-1);
        assertEquals(-1, aluno.getId());
    }
}
