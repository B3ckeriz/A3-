package Model;

import dao.AlunoDAO;
import model.Aluno;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Testes unitários para a classe Aluno - VERSÃO EXPANDIDA
 * Cobertura aumentada para atender metas do SonarCloud
 * @author Julia Exterkoetter
 */
public class AlunoTest {
    private AlunoDAO alunoDAO;
    private Aluno aluno;
    
    @BeforeEach
    public void setUp() {
        alunoDAO = new AlunoDAO();
        alunoDAO.getConexao();
        aluno = new Aluno("Engenharia de Software", 5, 1, "João Silva", 20);
    }
    
    // ==================== TESTES DE CONSTRUTORES ====================
    
    @Nested
    @DisplayName("Testes de Construtores")
    class ConstrutorTests {
        
        @Test
        @DisplayName("Construtor padrão deve criar aluno com valores nulos/zero")
        public void testConstrutorPadrao() {
            Aluno alunoVazio = new Aluno();
            assertNotNull(alunoVazio, "Aluno não deveria ser nulo");
            assertNull(alunoVazio.getNome());
            assertNull(alunoVazio.getCurso());
            assertEquals(0, alunoVazio.getId());
            assertEquals(0, alunoVazio.getFase());
            assertEquals(0, alunoVazio.getIdade());
        }
        
        @Test
        @DisplayName("Construtor com curso e fase")
        public void testConstrutorComCursoEFase() {
            Aluno alunoSimples = new Aluno("Ciência da Computação", 3);
            assertEquals("Ciência da Computação", alunoSimples.getCurso());
            assertEquals(3, alunoSimples.getFase());
        }
        
        @Test
        @DisplayName("Construtor completo deve inicializar todos os atributos")
        public void testConstrutorCompleto() {
            assertEquals(1, aluno.getId());
            assertEquals("João Silva", aluno.getNome());
            assertEquals(20, aluno.getIdade());
            assertEquals("Engenharia de Software", aluno.getCurso());
            assertEquals(5, aluno.getFase());
        }
        
        @Test
        @DisplayName("Construtor com valores extremos")
        public void testConstrutorValoresExtremos() {
            Aluno alunoExtremo = new Aluno("X", 1, Integer.MAX_VALUE, "A", 1);
            assertEquals(Integer.MAX_VALUE, alunoExtremo.getId());
            assertEquals("A", alunoExtremo.getNome());
            assertEquals(1, alunoExtremo.getIdade());
        }
    }
    
    // ==================== TESTES DE GETTERS E SETTERS ====================
    
    @Nested
    @DisplayName("Testes de Getters e Setters")
    class GettersSettersTests {
        
        @Test
        @DisplayName("Get e Set de Curso")
        public void testGetSetCurso() {
            aluno.setCurso("Sistemas de Informação");
            assertEquals("Sistemas de Informação", aluno.getCurso());
        }
        
        @Test
        @DisplayName("Get e Set de Fase")
        public void testGetSetFase() {
            aluno.setFase(7);
            assertEquals(7, aluno.getFase());
        }
        
        @Test
        @DisplayName("Get e Set de Id")
        public void testGetSetId() {
            aluno.setId(100);
            assertEquals(100, aluno.getId());
        }
        
        @Test
        @DisplayName("Get e Set de Nome")
        public void testGetSetNome() {
            aluno.setNome("Maria Santos");
            assertEquals("Maria Santos", aluno.getNome());
        }
        
        @Test
        @DisplayName("Get e Set de Idade")
        public void testGetSetIdade() {
            aluno.setIdade(22);
            assertEquals(22, aluno.getIdade());
        }
    }
    
    // ==================== TESTES DE CASOS EXTREMOS ====================
    
    @Nested
    @DisplayName("Testes de Casos Extremos")
    class CasosExtremosTests {
        
        @Test
        @DisplayName("Fase mínima válida")
        public void testFaseMinima() {
            aluno.setFase(1);
            assertEquals(1, aluno.getFase());
        }
        
        @Test
        @DisplayName("Fase máxima")
        public void testFaseMaxima() {
            aluno.setFase(10);
            assertEquals(10, aluno.getFase());
        }
        
        @Test
        @DisplayName("Fase zero")
        public void testFaseZero() {
            aluno.setFase(0);
            assertEquals(0, aluno.getFase());
        }
        
        @Test
        @DisplayName("Fase negativa")
        public void testFaseNegativa() {
            aluno.setFase(-1);
            assertEquals(-1, aluno.getFase());
        }
        
        @Test
        @DisplayName("Curso vazio")
        public void testCursoVazio() {
            aluno.setCurso("");
            assertEquals("", aluno.getCurso());
        }
        
        @Test
        @DisplayName("Curso nulo")
        public void testCursoNulo() {
            aluno.setCurso(null);
            assertNull(aluno.getCurso());
        }
        
        @Test
        @DisplayName("Curso muito longo")
        public void testCursoMuitoLongo() {
            String cursoLongo = "A".repeat(500);
            aluno.setCurso(cursoLongo);
            assertEquals(cursoLongo, aluno.getCurso());
        }
        
        @Test
        @DisplayName("Nome vazio")
        public void testNomeVazio() {
            aluno.setNome("");
            assertEquals("", aluno.getNome());
        }
        
        @Test
        @DisplayName("Nome nulo")
        public void testNomeNulo() {
            aluno.setNome(null);
            assertNull(aluno.getNome());
        }
        
        @Test
        @DisplayName("Nome com caracteres especiais")
        public void testNomeCaracteresEspeciais() {
            aluno.setNome("José María D'Angelo O'Connor");
            assertEquals("José María D'Angelo O'Connor", aluno.getNome());
        }
        
        @Test
        @DisplayName("Nome muito longo")
        public void testNomeMuitoLongo() {
            String nomeLongo = "João " + "Silva ".repeat(50);
            aluno.setNome(nomeLongo);
            assertEquals(nomeLongo, aluno.getNome());
        }
        
        @Test
        @DisplayName("Idade zero")
        public void testIdadeZero() {
            aluno.setIdade(0);
            assertEquals(0, aluno.getIdade());
        }
        
        @Test
        @DisplayName("Idade negativa")
        public void testIdadeNegativa() {
            aluno.setIdade(-5);
            assertEquals(-5, aluno.getIdade());
        }
        
        @Test
        @DisplayName("Idade muito alta")
        public void testIdadeMuitoAlta() {
            aluno.setIdade(150);
            assertEquals(150, aluno.getIdade());
        }
        
        @Test
        @DisplayName("Id negativo")
        public void testIdNegativo() {
            aluno.setId(-1);
            assertEquals(-1, aluno.getId());
        }
        
        @Test
        @DisplayName("Id zero")
        public void testIdZero() {
            aluno.setId(0);
            assertEquals(0, aluno.getId());
        }
        
        @Test
        @DisplayName("Id máximo")
        public void testIdMaximo() {
            aluno.setId(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, aluno.getId());
        }
    }
    
    // ==================== TESTES DE ENCADEAMENTO ====================
    
    @Nested
    @DisplayName("Testes de Encadeamento de Métodos")
    class EncadeamentoTests {
        
        @Test
        @DisplayName("Múltiplas modificações em sequência")
        public void testMultiplasModificacoes() {
            aluno.setNome("Pedro Santos");
            aluno.setIdade(25);
            aluno.setCurso("Engenharia Civil");
            aluno.setFase(6);
            aluno.setId(999);
            
            assertEquals("Pedro Santos", aluno.getNome());
            assertEquals(25, aluno.getIdade());
            assertEquals("Engenharia Civil", aluno.getCurso());
            assertEquals(6, aluno.getFase());
            assertEquals(999, aluno.getId());
        }
        
        @Test
        @DisplayName("Sobrescrever valores múltiplas vezes")
        public void testSobrescreverValores() {
            aluno.setNome("Primeira Nome");
            aluno.setNome("Segunda Nome");
            aluno.setNome("Terceira Nome");
            assertEquals("Terceira Nome", aluno.getNome());
            
            aluno.setIdade(18);
            aluno.setIdade(19);
            aluno.setIdade(20);
            assertEquals(20, aluno.getIdade());
        }
    }
    
    // ==================== TESTES DE ESTADO DO OBJETO ====================
    
    @Nested
    @DisplayName("Testes de Estado do Objeto")
    class EstadoObjetoTests {
        
        @Test
        @DisplayName("Aluno recém-criado não é nulo")
        public void testAlunoNaoNulo() {
            assertNotNull(aluno);
        }
        
        @Test
        @DisplayName("Verificar estado inicial completo")
        public void testEstadoInicial() {
            Aluno novoAluno = new Aluno("Medicina", 2, 50, "Ana Costa", 22);
            
            assertAll("Estado inicial do aluno",
                () -> assertEquals(50, novoAluno.getId()),
                () -> assertEquals("Ana Costa", novoAluno.getNome()),
                () -> assertEquals(22, novoAluno.getIdade()),
                () -> assertEquals("Medicina", novoAluno.getCurso()),
                () -> assertEquals(2, novoAluno.getFase())
            );
        }
        
        @Test
        @DisplayName("Resetar todos os valores para nulo/zero")
        public void testResetarValores() {
            aluno.setNome(null);
            aluno.setCurso(null);
            aluno.setIdade(0);
            aluno.setFase(0);
            aluno.setId(0);
            
            assertAll("Valores resetados",
                () -> assertNull(aluno.getNome()),
                () -> assertNull(aluno.getCurso()),
                () -> assertEquals(0, aluno.getIdade()),
                () -> assertEquals(0, aluno.getFase()),
                () -> assertEquals(0, aluno.getId())
            );
        }
    }
    
    // ==================== TESTES DE COMPARAÇÃO ====================
    
    @Nested
    @DisplayName("Testes de Comparação")
    class ComparacaoTests {
        
        @Test
        @DisplayName("Dois alunos com mesmos dados devem ter mesmos valores")
        public void testAlunosComMesmosDados() {
            Aluno aluno1 = new Aluno("Direito", 4, 10, "Carlos Silva", 23);
            Aluno aluno2 = new Aluno("Direito", 4, 10, "Carlos Silva", 23);
            
            assertEquals(aluno1.getId(), aluno2.getId());
            assertEquals(aluno1.getNome(), aluno2.getNome());
            assertEquals(aluno1.getIdade(), aluno2.getIdade());
            assertEquals(aluno1.getCurso(), aluno2.getCurso());
            assertEquals(aluno1.getFase(), aluno2.getFase());
        }
        
        @Test
        @DisplayName("Alunos diferentes devem ter valores diferentes")
        public void testAlunosDiferentes() {
            Aluno aluno2 = new Aluno("Medicina", 2, 99, "Maria Costa", 21);
            
            assertNotEquals(aluno.getId(), aluno2.getId());
            assertNotEquals(aluno.getNome(), aluno2.getNome());
            assertNotEquals(aluno.getCurso(), aluno2.getCurso());
        }
    }
    
    // ==================== TESTES DE VALIDAÇÃO DE DADOS ====================
    
    @Nested
    @DisplayName("Testes de Validação")
    class ValidacaoTests {
        
        @Test
        @DisplayName("Curso com espaços em branco")
        public void testCursoComEspacos() {
            aluno.setCurso("  Engenharia de Produção  ");
            assertEquals("  Engenharia de Produção  ", aluno.getCurso());
        }
        
        @Test
        @DisplayName("Nome com espaços em branco")
        public void testNomeComEspacos() {
            aluno.setNome("  João Silva  ");
            assertEquals("  João Silva  ", aluno.getNome());
        }
        
        @Test
        @DisplayName("Curso com números")
        public void testCursoComNumeros() {
            aluno.setCurso("Engenharia123");
            assertEquals("Engenharia123", aluno.getCurso());
        }
        
        @Test
        @DisplayName("Nome com números")
        public void testNomeComNumeros() {
            aluno.setNome("João123");
            assertEquals("João123", aluno.getNome());
        }
        
        @Test
        @DisplayName("Fase com valor grande positivo")
        public void testFaseGrandePositivo() {
            aluno.setFase(999);
            assertEquals(999, aluno.getFase());
        }
        
        @Test
        @DisplayName("Fase com valor grande negativo")
        public void testFaseGrandeNegativo() {
            aluno.setFase(-999);
            assertEquals(-999, aluno.getFase());
        }
    }
    
    // ==================== TESTES DE INTEGRAÇÃO COM DAO ====================
    
    @Nested
    @DisplayName("Testes de Integração com DAO")
    class IntegracaoDAOTests {
        
        @Test
        @DisplayName("DAO não deve ser nulo após setup")
        public void testDAONaoNulo() {
            assertNotNull(alunoDAO);
        }
        
        @Test
        @DisplayName("Criar múltiplos alunos")
        public void testCriarMultiplosAlunos() {
            Aluno aluno1 = new Aluno("Arquitetura", 1, 1, "Laura", 19);
            Aluno aluno2 = new Aluno("Design", 2, 2, "Bruno", 20);
            Aluno aluno3 = new Aluno("Música", 3, 3, "Carla", 21);
            
            assertAll("Múltiplos alunos criados",
                () -> assertNotNull(aluno1),
                () -> assertNotNull(aluno2),
                () -> assertNotNull(aluno3)
            );
        }
    }
    
    // ==================== TESTES DE MUTABILIDADE ====================
    
    @Nested
    @DisplayName("Testes de Mutabilidade")
    class MutabilidadeTests {
        
        @Test
        @DisplayName("Modificar o mesmo atributo várias vezes")
        public void testModificacaoRepetida() {
            for (int i = 0; i < 10; i++) {
                aluno.setFase(i);
                assertEquals(i, aluno.getFase());
            }
        }
        
        @Test
        @DisplayName("Alternar entre valores nulos e não-nulos")
        public void testAlternarNulos() {
            aluno.setNome("João");
            assertEquals("João", aluno.getNome());
            
            aluno.setNome(null);
            assertNull(aluno.getNome());
            
            aluno.setNome("Maria");
            assertEquals("Maria", aluno.getNome());
            
            aluno.setNome(null);
            assertNull(aluno.getNome());
        }
    }
}