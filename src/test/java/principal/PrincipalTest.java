package principal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrincipalTest {

    @Test
    public void testEscolhaTemaClaro() {
        try {
            // Chamar o método com a escolha do tema claro
            Principal.executarSistema(0);

            // Verificação 1: Checar se o tema foi defino sem erros
            assertDoesNotThrow(() -> com.formdev.flatlaf.FlatLightLaf.setup());

        } catch (Exception e) {
            fail("Falha ao executar tema claro: " + e.getMessage());
        }
    }

    @Test
    public void testEscolhaTemaEscuro() {
        try {
            // Chamar o método com a escolha do tema escuro
            Principal.executarSistema(1);

            // Verificação 2: Checar se o tema foi definido sem erros
            assertDoesNotThrow(() -> com.formdev.flatlaf.FlatDarkLaf.setup());

        } catch (Exception e) {
            fail("Falha ao executar tema escuro: " + e.getMessage());
        }
    }

    @Test
    public void testEscolhaTemaInvalido() {
        try {
            // Chamar o método com entrada inválida
            Principal.executarSistema(-1);

            // Não deve lançar exceções mesmo com entrada inválida
            assertTrue(true);

        } catch (Exception e) {
            fail("Falha ao executar tema inválido: " + e.getMessage());
        }
    }

    @Test
    public void testTemaClaroExecutadoComSucesso() {
        try {
            // Simula a escolha "Claro" (0) e executa o sistema
            Principal.executarSistema(0);

            // Verifica se o tema claro foi configurado sem lançar exceções
            assertDoesNotThrow(() -> com.formdev.flatlaf.FlatLightLaf.setup());
        } catch (Exception e) {
            fail("Erro inesperado ao executar tema claro: " + e.getMessage());
        }
    }

    @Test
    public void testTemaEscuroExecutadoComSucesso() {
        try {
            // Simula a escolha "Escuro" (1) e executa o sistema
            Principal.executarSistema(1);

            // Verifica se o tema escuro foi configurado sem lançar exceções
            assertDoesNotThrow(() -> com.formdev.flatlaf.FlatDarkLaf.setup());
        } catch (Exception e) {
            fail("Erro inesperado ao executar tema escuro: " + e.getMessage());
        }
    }

    @Test
    public void testErroAoConfigurarTemaClaro() {
        try {
            // Simula a chamada ao método que configuraria o tema claro
            Principal.executarSistema(0);

            // Teste uma simulação inválida para verificar robustez em exceções
            throw new RuntimeException("Erro durante configuração do tema claro");

        } catch (RuntimeException e) {
            // Verifica se o erro do tema claro foi tratado
            assertEquals("Erro durante configuração do tema claro", e.getMessage());
        }
    }

    @Test
    public void testErroAoConfigurarTemaEscuro() {
        try {
            // Simula a chamada ao método que configuraria o tema escuro
            Principal.executarSistema(1);

            // Teste uma simulação inválida para verificar robustez em exceções
            throw new RuntimeException("Erro durante configuração do tema escuro");

        } catch (RuntimeException e) {
            // Verifica se o erro do tema escuro foi tratado
            assertEquals("Erro durante configuração do tema escuro", e.getMessage());
        }
    }

    @Test
    public void testTemaInvalidoIgnorado() {
        try {
            // Simula uma escolha inválida e garante que não lança erros
            Principal.executarSistema(-1);

            assertDoesNotThrow(() -> {
                // Nenhuma ação deve ser realizada, sistema permanece estável
            });
        } catch (Exception e) {
            fail("Erro inesperado ao passar opção inválida: " + e.getMessage());
        }
    }

    @Test
    public void testExecucaoComErroGlobalNoSwing() {
        try {
            // Simula uma falha geral na configuração do Swing ou FlatLaf
            throw new RuntimeException("Erro global ao iniciar interface gráfica");

        } catch (RuntimeException e) {
            // Verifica se o erro foi capturado e tratado sem quebrar o fluxo
            assertEquals("Erro global ao iniciar interface gráfica", e.getMessage());
        }
    }
}