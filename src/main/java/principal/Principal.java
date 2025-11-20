package principal;

import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import view.TelaLogin;

public class Principal {
    public static void main(String[] args) {
        // Determina a escolha inicial com interação gráfica
        String[] options = {"Claro", "Escuro"};
        int n = JOptionPane.showOptionDialog(
                null,
                "Escolha um tema",
                "Sistema",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        executarSistema(n); // Chama o método com a lógica principal
    }

    // Método separado para execução do sistema com base na escolha do tema
    public static void executarSistema(int escolhaTema) {
        try {
            FlatDarkLaf.setup(); // Define um tema inicial seguro
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lógica condicional baseada no tema escolhido
        if (escolhaTema == 0) { // Tema Claro
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    FlatLightLaf.setup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new TelaLogin().setVisible(true);
            });
        } else { // Tema Escuro
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    FlatDarkLaf.setup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new TelaLogin().setVisible(true);
            });
        }
    }
}
