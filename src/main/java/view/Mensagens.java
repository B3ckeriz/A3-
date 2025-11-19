package view;

// Classe Mensagens herda as características de Exception
public class Mensagens extends Exception {
    // Construtor: imprime mensagem de erro sem crash no sistema
    public Mensagens(String msg) {
        super(msg);
    }
}
