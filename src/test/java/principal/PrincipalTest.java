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
}