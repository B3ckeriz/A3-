package Model;

import model.Professor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Professor
 * @author Julia Exterkoetter
 */
public class ProfessorTest {
    
    private Professor professor;
    
    @BeforeEach
    public void setUp() {
        // Inicializa um professor antes de cada teste
        professor = new Professor("Pedra Branca", "123.456.789-00", "(48) 99999-9999", 
                                  "Doutor", 8000, 1, "Dr. Carlos Silva", 45);
    }
    
    @Test
    public void testConstrutorPadrao() {
        Professor profVazio = new Professor();
        assertNotNull(profVazio, "Professor não deveria ser nulo");
    }
    
    @Test
    public void testConstrutorComAtributos() {
        Professor profSimples = new Professor("Tubarão", "987.654.321-00", "(48) 88888-8888", 
                                              "Mestre", 6000);
        assertEquals("Tubarão", profSimples.getCampus());
        assertEquals("987.654.321-00", profSimples.getCpf());
        assertEquals("(48) 88888-8888", profSimples.getContato());
        assertEquals("Mestre", profSimples.getTitulo());
        assertEquals(6000, profSimples.getSalario());
    }
    
    @Test
    public void testConstrutorCompleto() {
        assertEquals(1, professor.getId());
        assertEquals("Dr. Carlos Silva", professor.getNome());
        assertEquals(45, professor.getIdade());
        assertEquals("Pedra Branca", professor.getCampus());
        assertEquals("123.456.789-00", professor.getCpf());
        assertEquals("(48) 99999-9999", professor.getContato());
        assertEquals("Doutor", professor.getTitulo());
        assertEquals(8000, professor.getSalario());
    }
    
    @Test
    public void testGetSetCampus() {
        professor.setCampus("Grande Florianópolis");
        assertEquals("Grande Florianópolis", professor.getCampus());
    }
    
    @Test
    public void testGetSetCpf() {
        professor.setCpf("111.222.333-44");
        assertEquals("111.222.333-44", professor.getCpf());
    }
    
    @Test
    public void testGetSetContato() {
        professor.setContato("(48) 77777-7777");
        assertEquals("(48) 77777-7777", professor.getContato());
    }
    
    @Test
    public void testGetSetTitulo() {
        professor.setTitulo("PhD");
        assertEquals("PhD", professor.getTitulo());
    }
    
    @Test
    public void testGetSetSalario() {
        professor.setSalario(10000);
        assertEquals(10000, professor.getSalario());
    }
    
    @Test
    public void testGetSetId() {
        professor.setId(50);
        assertEquals(50, professor.getId());
    }
    
    @Test
    public void testGetSetNome() {
        professor.setNome("Dra. Ana Paula");
        assertEquals("Dra. Ana Paula", professor.getNome());
    }
    
    @Test
    public void testGetSetIdade() {
        professor.setIdade(38);
        assertEquals(38, professor.getIdade());
    }
    
    @Test
    public void testSalarioZero() {
        professor.setSalario(0);
        assertEquals(0, professor.getSalario());
    }
    
    @Test
    public void testCpfVazio() {
        professor.setCpf("");
        assertEquals("", professor.getCpf());
    }
    
    @Test
    public void testContatoVazio() {
        professor.setContato("");
        assertEquals("", professor.getContato());
    }
    
    @Test
    public void testCampusVazio() {
        professor.setCampus("");
        assertEquals("", professor.getCampus());
    }
    
    @Test
    public void testTituloVazio() {
        professor.setTitulo("");
        assertEquals("", professor.getTitulo());
    }
}
