package util;

import javax.swing.JFrame;
import view.GerenciaAlunos;
import view.GerenciaProfessores;
import view.Sobre;

/**
 * Classe utilitária para navegação entre telas do sistema.
 * Centraliza a lógica de navegação, eliminando código duplicado.
 */
public class NavigationHelper {
    
    /**
     * Navega para a tela de gerenciamento de alunos.
     * Fecha a tela atual e abre a nova tela.
     * 
     * @param currentFrame A janela atual que será fechada
     */
    public static void goToGerenciaAlunos(JFrame currentFrame) {
        GerenciaAlunos tela = new GerenciaAlunos();
        tela.setVisible(true);
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
    
    /**
     * Navega para a tela de gerenciamento de professores.
     * Fecha a tela atual e abre a nova tela.
     * 
     * @param currentFrame A janela atual que será fechada
     */
    public static void goToGerenciaProfessores(JFrame currentFrame) {
        GerenciaProfessores tela = new GerenciaProfessores();
        tela.setVisible(true);
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
    
    /**
     * Abre a janela "Sobre" com informações do sistema.
     * Não fecha a janela atual.
     */
    public static void showSobre() {
        Sobre tela = new Sobre();
        tela.setVisible(true);
    }
    
    /**
     * Fecha o aplicativo completamente.
     */
    public static void exitApplication() {
        System.exit(0);
    }
}