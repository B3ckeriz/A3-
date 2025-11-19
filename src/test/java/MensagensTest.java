
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import view.Mensagens;   // AJUSTE PARA O PACOTE

public class MensagensTest {

    @Test
    void testMensagem() {
        Mensagens m = new Mensagens("Erro encontrado");
        assertEquals("Erro encontrado", m.getMessage());
    }
}