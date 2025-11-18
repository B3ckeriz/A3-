package principal;

import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import view.TelaLogin;

// MAIN CLASS
public class Principal {
    public static void main(String[] args) {
        // Bloco try catch para capturar errors provenientes de tentativa de executar o tema escuro
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Opções de tema
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

        // Execução condicional com base no tema escolhido pelo usuário
        if (n == 0) { // Tema Claro
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        FlatLightLaf.setup();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new TelaLogin().setVisible(true);
                }
            });
        } else { // Tema Escuro
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        FlatDarkLaf.setup();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new TelaLogin().setVisible(true);
                }
            });
        }
    }
}