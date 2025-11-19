package view;

import com.formdev.flatlaf.json.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.Professor;
import java.util.List;

public class EditarProfessor extends javax.swing.JFrame {
    
    // Constantes para evitar duplicação
    private static final String[] ARRAY_CAMPUS = {
        "-", "Continente", "Dib Mussi", "Ilha", "Pedra Branca", "Trajano", "Tubarão"
    };
    
    private static final String[] ARRAY_TITULO = {
        "-", "Graduação", "Especialização", "Mestrado", "Doutorado"
    };
    
    private static final String ERRO_NOME = "Nome deve conter ao menos 2 caracteres.";
    private static final String ERRO_CAMPUS = "Escolha o campus";
    private static final String ERRO_CPF_TAMANHO = "O campo CPF deve possuir 11 caracteres numéricos";
    private static final String ERRO_CPF_DUPLICADO = "CPF já cadastrado no sistema";
    private static final String ERRO_CONTATO = "O campo contato deve possuir 11 caracteres numéricos";
    private static final String ERRO_IDADE_INVALIDA = "Idade inválida";
    private static final String ERRO_IDADE_VAZIO = "Idade não pode ser vazio";
    private static final String ERRO_SALARIO = "O campo salário deve possuir no mínimo 4 caracteres numéricos";
    private static final String ERRO_TITULO = "Defina um título";
    
    private Professor objetoProfessor;
    
    public EditarProfessor() throws java.text.ParseException {
        initComponents();
        formatarCampos();
        preencheCampos();
        getRootPane().setDefaultButton(this.bConfirmar);
        this.objetoProfessor = new Professor();  
    }

    // [Código do initComponents gerado automaticamente - mantido como está]
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // ... código gerado pelo NetBeans ...
    }
    
    private void formatarCampos() throws java.text.ParseException {
        try {
            aplicarMascara(cpfFormatado, "###.###.###-##");
            aplicarMascara(contatoFormatado, "(##) # ####-####");
            aplicarMascara(salarioFormatado, "R$#####");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao formatar campos", "ERRO", JOptionPane.ERROR);
        }
    }
    
    private void aplicarMascara(javax.swing.JFormattedTextField campo, String pattern) throws java.text.ParseException {
        MaskFormatter mask = new MaskFormatter(pattern);
        mask.install(campo);
    }
    
    private boolean verificaCpf(String cpf) {
        List<Professor> minhalista = objetoProfessor.getMinhaLista();
        int idAtual = Integer.parseInt(GerenciaProfessores.listaDados[7]);
        
        return minhalista.stream()
            .anyMatch(p -> cpf.equals(p.getCpf()) && p.getId() != idAtual);
    }
    
    private String validarFormatado(String input) {
        return input.replaceAll("[^0-9]", "");
    }
    
    private String editSalario(String input) {
        return input.length() > 2 ? input.substring(0, input.length() - 2) : "";
    }
    
    private int encontrarIndice(String[] array, String valor) {
        for (int i = 0; i < array.length; i++) {
            if (valor.equalsIgnoreCase(array[i])) {
                return i;
            }
        }
        return 0;
    }
    
    private void preencheCampos() {
        int indexCampus = encontrarIndice(ARRAY_CAMPUS, GerenciaProfessores.listaDados[2]);
        int indexTitulo = encontrarIndice(ARRAY_TITULO, GerenciaProfessores.listaDados[5]);
        
        this.nome.setText(GerenciaProfessores.listaDados[0]);
        this.idade.setText(GerenciaProfessores.listaDados[1]);
        this.campus.setSelectedIndex(indexCampus);
        this.cpfFormatado.setText(GerenciaProfessores.listaDados[3]);
        this.contatoFormatado.setText(GerenciaProfessores.listaDados[4]);
        this.titulo.setSelectedIndex(indexTitulo);
        this.salarioFormatado.setText(editSalario(GerenciaProfessores.listaDados[6]));
    }
    
    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nome = validarNome();
            String campus = validarCampus();
            String cpf = validarCPF();
            String contato = validarContato();
            int idade = validarIdade();
            int salario = validarSalario();
            String titulo = validarTitulo();
            int id = Integer.parseInt(GerenciaProfessores.listaDados[7]);
            
            boolean sucesso = objetoProfessor.updateProfessor(
                campus, cpf, contato, titulo, salario, id, nome, idade
            );
            
            if (sucesso) {
                JOptionPane.showMessageDialog(rootPane, "Professor alterado com sucesso!");
                this.dispose();
            }
            
        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        } catch (NumberFormatException erro2) {
            JOptionPane.showMessageDialog(rootPane, "Informe um número.");
        }
    }
    
    // Métodos de validação
    
    private String validarNome() throws Mensagens {
        String nomeTexto = this.nome.getText();
        if (nomeTexto.length() < 2) {
            throw new Mensagens(ERRO_NOME);
        }
        return nomeTexto;
    }
    
    private String validarCampus() throws Mensagens {
        int indice = this.campus.getSelectedIndex();
        if (indice == 0) {
            throw new Mensagens(ERRO_CAMPUS);
        }
        return ARRAY_CAMPUS[indice];
    }
    
    private String validarCPF() throws Mensagens {
        String cpfTexto = this.cpfFormatado.getText();
        String cpfLimpo = validarFormatado(cpfTexto);
        
        if (cpfLimpo.length() != 11) {
            throw new Mensagens(ERRO_CPF_TAMANHO);
        }
        
        if (verificaCpf(cpfTexto)) {
            throw new Mensagens(ERRO_CPF_DUPLICADO);
        }
        
        return cpfTexto;
    }
    
    private String validarContato() throws Mensagens {
        String contatoTexto = this.contatoFormatado.getText();
        String contatoLimpo = validarFormatado(contatoTexto);
        
        if (contatoLimpo.length() != 11) {
            throw new Mensagens(ERRO_CONTATO);
        }
        
        return contatoTexto;
    }
    
    private int validarIdade() throws Mensagens {
        String idadeTexto = this.idade.getText();
        
        if (idadeTexto.isEmpty()) {
            throw new Mensagens(ERRO_IDADE_VAZIO);
        }
        
        int idadeValor = Integer.parseInt(idadeTexto);
        if (idadeValor < 11) {
            throw new Mensagens(ERRO_IDADE_INVALIDA);
        }
        
        return idadeValor;
    }
    
    private int validarSalario() throws Mensagens {
        String salarioTexto = validarFormatado(this.salarioFormatado.getText());
        
        if (salarioTexto.length() < 4) {
            throw new Mensagens(ERRO_SALARIO);
        }
        
        return Integer.parseInt(salarioTexto);
    }
    
    private String validarTitulo() throws Mensagens {
        int indice = this.titulo.getSelectedIndex();
        if (indice == 0) {
            throw new Mensagens(ERRO_TITULO);
        }
        return ARRAY_TITULO[indice];
    }
    
    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new EditarProfessor().setVisible(true);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(EditarProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.JComboBox<String> campus;
    private javax.swing.JFormattedTextField contatoFormatado;
    private javax.swing.JFormattedTextField cpfFormatado;
    private javax.swing.JTextField idade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nome;
    private javax.swing.JFormattedTextField salarioFormatado;
    private javax.swing.JComboBox<String> titulo;
}