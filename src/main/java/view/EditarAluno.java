package view;

import model.Aluno;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarAluno extends javax.swing.JFrame {

    // --- Constantes reutilizáveis ---
    private static final String[] ARRAY_CURSOS = {
        "-", 
        "Administração", 
        "Análise e Desenvolvimento de Sistemas",
        "Arquitetura e Urbanismo",
        "Ciências Contábeis",
        "Ciências da Computação",
        "Design",
        "Design de Moda",
        "Relações Internacionais",
        "Sistemas de Informação"
    };

    private static final int[] ARRAY_FASES = {1,2,3,4,5,6,7,8,9,10};
    private static final String[] ARRAY_FASES_STRING = {
        "1ª","2ª","3ª","4ª","5ª","6ª","7ª","8ª","9ª","10ª"
    };

    private static final String ERRO_NOME = "Nome deve conter ao menos 2 caracteres.";
    private static final String ERRO_IDADE = "Idade inválida";
    private static final String ERRO_CURSO = "Escolha um curso";

    private Aluno objetoAluno;
    private int idAluno;

    public EditarAluno() {
        initComponents();
        objetoAluno = new Aluno();
        preencheCampos();
        getRootPane().setDefaultButton(bConfirmar);
    }

    public EditarAluno(String id, String nome, String idade, String curso, String fase) {
        initComponents();
        objetoAluno = new Aluno();
        
        idAluno = Integer.parseInt(id);
        this.nome.setText(nome);
        this.idade.setText(idade);

        this.curso.setSelectedItem(curso);
        this.fase.setSelectedItem(fase + "ª");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        curso = new javax.swing.JComboBox<>();
        fase = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bCancelar = new javax.swing.JButton();
        bConfirmar = new javax.swing.JButton();
        idade = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Aluno");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Editar Aluno");

        curso.setModel(new DefaultComboBoxModel<>(ARRAY_CURSOS));
        fase.setModel(new DefaultComboBoxModel<>(ARRAY_FASES_STRING));

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Nome:");

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Curso:");

        jLabel4.setText("Idade:");
        jLabel5.setText("Fase:");

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(e -> dispose());

        bConfirmar.setText("Confirmar");
        bConfirmar.setToolTipText("ENTER");
        bConfirmar.addActionListener(this::bConfirmarActionPerformed);

        // --- Layout gerado (mantido para evitar problemas no NetBeans) ---
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nome)
                    .addComponent(curso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idade, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(idade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }

    // --- Métodos auxiliares ---

    private void preencheCampos() {
        this.nome.setText(GerenciaAlunos.listaDados2[1]);
        this.idade.setText(GerenciaAlunos.listaDados2[2]);

        curso.setSelectedItem(GerenciaAlunos.listaDados2[3]);

        int faseValor = Integer.parseInt(GerenciaAlunos.listaDados2[4]);
        fase.setSelectedItem(faseValor + "ª");
    }

    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nome = validarNome();
            int idade = validarIdade();
            String curso = validarCurso();
            int faseSelecionada = ARRAY_FASES[fase.getSelectedIndex()];

            boolean sucesso = objetoAluno.updateAluno(curso, faseSelecionada, idAluno, nome, idade);

            if (sucesso) {
                JOptionPane.showMessageDialog(rootPane, "Aluno alterado com sucesso!");
                this.dispose();
            }

        } catch (Mensagens e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Informe um número válido.");
        }
    }

    // --- Validações organizadas ---
    private String validarNome() throws Mensagens {
        String txt = nome.getText();
        if (txt.length() < 2) throw new Mensagens(ERRO_NOME);
        return txt;
    }

    private int validarIdade() throws Mensagens {
        int valor = Integer.parseInt(idade.getText());
        if (valor < 11) throw new Mensagens(ERRO_IDADE);
        return valor;
    }

    private String validarCurso() throws Mensagens {
        if (curso.getSelectedIndex() == 0) throw new Mensagens(ERRO_CURSO);
        return ARRAY_CURSOS[curso.getSelectedIndex()];
    }

    // --- Main ---
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EditarAluno.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new EditarAluno().setVisible(true));
    }

    // Variables declaration 
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.JComboBox<String> curso;
    private javax.swing.JComboBox<String> fase;
    private javax.swing.JTextField idade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nome;
}
