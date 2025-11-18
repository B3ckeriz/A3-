package ver;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import model.Aluno;

/**
 * Tela de Edição de Alunos
 * Permite editar informações de alunos existentes
 */
public class EditarAluno extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(EditarAluno.class.getName());
    private Aluno objetoAluno;
    private int alunoId;
    
    // Arrays de dados para os ComboBoxes
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
    
    private static final int[] ARRAY_FASES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    
    /**
     * Construtor que recebe o ID do aluno a ser editado
     * @param alunoId ID do aluno
     */
    public EditarAluno(int alunoId) {
        this.alunoId = alunoId;
        this.objetoAluno = new Aluno();
        
        initComponents();
        getRootPane().setDefaultButton(this.bConfirmar);
        setLocationRelativeTo(null);
        
        carregarDadosAluno();
    }
    
    /**
     * Construtor padrão (compatibilidade com código legado)
     * Usa dados do array estático GerenciaAlunos.listaDados2
     */
    public EditarAluno() {
        this.objetoAluno = new Aluno();
        
        initComponents();
        getRootPane().setDefaultButton(this.bConfirmar);
        setLocationRelativeTo(null);
        
        preencheCamposLegado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Aluno");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editar Aluno");

        curso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Administração", "Análise e Desenvolvimento de Sistemas", "Arquitetura e Urbanismo", "Ciências Contábeis", "Ciências da Computação", "Design", "Design de Moda", "Relações Internacionais", "Sistemas de Informação" }));
        curso.setName(""); // NOI18N

        fase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1ª", "2ª", "3ª", "4ª", "5ª", "6ª", "7ª", "8ª", "9ª", "10ª" }));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nome:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Curso:");

        jLabel4.setText("Idade:");

        jLabel5.setText("Fase:");

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bConfirmar.setText("Confirmar");
        bConfirmar.setToolTipText("ENTER");
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nome)
                    .addComponent(curso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idade, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Carrega dados do aluno do banco de dados (método moderno)
     */
    private void carregarDadosAluno() {
        try {
            Optional<Aluno> alunoOpt = Optional.ofNullable(objetoAluno.carregaAluno(alunoId));
            
            if (alunoOpt.isPresent()) {
                Aluno aluno = alunoOpt.get();
                
                this.nome.setText(aluno.getNome());
                this.idade.setText(String.valueOf(aluno.getIdade()));
                
                // Encontrar índice do curso
                int indexCurso = 0;
                for (int i = 0; i < ARRAY_CURSOS.length; i++) {
                    if (ARRAY_CURSOS[i].equalsIgnoreCase(aluno.getCurso())) {
                        indexCurso = i;
                        break;
                    }
                }
                this.curso.setSelectedIndex(indexCurso);
                
                // Encontrar índice da fase
                int indexFase = aluno.getFase() - 1; // Fase 1 = índice 0
                if (indexFase >= 0 && indexFase < ARRAY_FASES.length) {
                    this.fase.setSelectedIndex(indexFase);
                }
                
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Aluno não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                this.dispose();
            }
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao carregar dados do aluno", ex);
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar dados: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            this.dispose();
        }
    }
    
    /**
     * Preenche campos usando array estático (compatibilidade legado)
     */
    private void preencheCamposLegado() {
        try {
            if (GerenciaAlunos.listaDados2 == null || GerenciaAlunos.listaDados2[0] == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nenhum aluno selecionado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                this.dispose();
                return;
            }
            
            this.alunoId = Integer.parseInt(GerenciaAlunos.listaDados2[0]);
            
            // Encontrar índice do curso
            int indexCurso = 0;
            for (int i = 0; i < ARRAY_CURSOS.length; i++) {
                if (ARRAY_CURSOS[i].equalsIgnoreCase(GerenciaAlunos.listaDados2[3])) {
                    indexCurso = i;
                    break;
                }
            }
            
            // Encontrar índice da fase
            int indexFase = Integer.parseInt(GerenciaAlunos.listaDados2[4]) - 1;
            
            this.nome.setText(GerenciaAlunos.listaDados2[1]);
            this.idade.setText(GerenciaAlunos.listaDados2[2]);
            this.curso.setSelectedIndex(indexCurso);
            this.fase.setSelectedIndex(indexFase);
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao preencher campos", ex);
            JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar dados do aluno",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            this.dispose();
        }
    }
    
    /**
     * Valida os campos do formulário
     * @return true se todos os campos estão válidos
     * @throws Mensagens Se algum campo estiver inválido
     */
    private boolean validarCampos() throws Mensagens {
        // Validar nome
        String nomeAluno = this.nome.getText().trim();
        if (nomeAluno.length() < 2) {
            this.nome.requestFocus();
            throw new Mensagens("Nome deve conter ao menos 2 caracteres.");
        }
        
        // Validar idade
        try {
            int idadeAluno = Integer.parseInt(this.idade.getText().trim());
            if (idadeAluno < 11) {
                this.idade.requestFocus();
                throw new Mensagens("Idade deve ser maior ou igual a 11 anos.");
            }
            if (idadeAluno > 150) {
                this.idade.requestFocus();
                throw new Mensagens("Idade inválida.");
            }
        } catch (NumberFormatException ex) {
            this.idade.requestFocus();
            throw new Mensagens("Idade deve ser um número válido.");
        }
        
        // Validar curso
        if (this.curso.getSelectedIndex() == 0) {
            this.curso.requestFocus();
            throw new Mensagens("Selecione um curso.");
        }
        
        return true;
    }
    
    /**
     * Action: confirmar a atualização com as informações preenchidas
     */
    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed
        try {
            // Validar campos
            validarCampos();
            
            // Coletar dados validados
            String nomeAluno = this.nome.getText().trim();
            int idadeAluno = Integer.parseInt(this.idade.getText().trim());
            String cursoAluno = ARRAY_CURSOS[this.curso.getSelectedIndex()];
            int faseAluno = ARRAY_FASES[this.fase.getSelectedIndex()];
            
            // Atualizar objeto Aluno
            objetoAluno.setId(alunoId);
            objetoAluno.setNome(nomeAluno);
            objetoAluno.setIdade(idadeAluno);
            objetoAluno.setCurso(cursoAluno);
            objetoAluno.setFase(faseAluno);
            
            // Atualizar no banco de dados
            if (objetoAluno.updateAlunoBD(cursoAluno, faseAluno, faseAluno, cursoAluno, faseAluno)) {
                logger.info("Aluno atualizado: ID=" + alunoId + ", Nome=" + nomeAluno);
                JOptionPane.showMessageDialog(
                    this,
                    "Aluno alterado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                this.dispose();
            }
            
        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(
                this,
                erro.getMessage(),
                "Validação",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao atualizar aluno", ex);
            JOptionPane.showMessageDialog(
                this,
                "Erro ao atualizar aluno: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_bConfirmarActionPerformed
    
    /**
     * Action: cancelar a ação de editar aluno
     */
    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    /**
     * Main method para testes
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EditarAluno.class.getName()).log(Level.WARNING, "Não foi possível aplicar Nimbus L&F", ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    // End of variables declaration//GEN-END:variables
}