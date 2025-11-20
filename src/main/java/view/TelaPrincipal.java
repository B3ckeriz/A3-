package view;

import com.formdev.flatlaf.FlatDarkLaf;
import util.NavigationHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaPrincipal extends javax.swing.JFrame {

    private static final Logger LOGGER = Logger.getLogger(TelaPrincipal.class.getName());

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {

        bProfessores = new javax.swing.JButton();
        bAlunos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        arquivo = new javax.swing.JMenu();
        menuAlunos = new javax.swing.JMenuItem();
        menuProfessores = new javax.swing.JMenuItem();
        sobre = new javax.swing.JMenuItem();
        menuLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");
        setBackground(new java.awt.Color(51, 255, 51));
        setResizable(false);

        bProfessores.setFont(new java.awt.Font("Segoe UI", 0, 18));
        bProfessores.setText("Professores");
        bProfessores.setToolTipText("CTRL+P");
        bProfessores.setAlignmentX(0.5F);
        bProfessores.addActionListener(evt -> bProfessoresActionPerformed(evt));

        bAlunos.setFont(new java.awt.Font("Segoe UI", 0, 18));
        bAlunos.setText("Alunos");
        bAlunos.setToolTipText("CTRL+A");
        bAlunos.addActionListener(evt -> bAlunosActionPerformed(evt));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SisUni - Sistema de Gerenciamento Universitário");

        arquivo.setText("Arquivo");

        menuAlunos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuAlunos.setText("Gerenciamento de Alunos");
        menuAlunos.addActionListener(evt -> menuAlunosActionPerformed(evt));
        arquivo.add(menuAlunos);

        menuProfessores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuProfessores.setText("Gerenciamento de Professores");
        menuProfessores.addActionListener(evt -> menuProfessoresActionPerformed(evt));
        arquivo.add(menuProfessores);

        sobre.setText("Sobre");
        sobre.addActionListener(evt -> sobreActionPerformed(evt));
        arquivo.add(sobre);

        menuLeave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuLeave.setText("Sair");
        menuLeave.addActionListener(evt -> menuLeaveActionPerformed(evt));
        arquivo.add(menuLeave);

        jMenuBar1.add(arquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(bAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        bProfessores.getAccessibleContext().setAccessibleDescription("Cadastro de Professores");
        bAlunos.getAccessibleContext().setAccessibleDescription("Cadastro de Alunos");

        pack();
        setLocationRelativeTo(null);
    }
    
    // ✅ ATUALIZADO - Usa NavigationHelper
    private void bAlunosActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.goToGerenciaAlunos(this);
    }
    
    // ✅ ATUALIZADO - Usa NavigationHelper
    private void bProfessoresActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.goToGerenciaProfessores(this);
    }

    // ✅ ATUALIZADO - Usa NavigationHelper
    private void menuAlunosActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.goToGerenciaAlunos(this);
    }

    // ✅ ATUALIZADO - Usa NavigationHelper
    private void menuProfessoresActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.goToGerenciaProfessores(this);
    }
    
    // ✅ ATUALIZADO - Usa NavigationHelper
    private void menuLeaveActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.exitApplication();
    }

    // ✅ ATUALIZADO - Usa NavigationHelper
    private void sobreActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationHelper.showSobre();
    }

    public static void main(String args[]) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Não foi possível aplicar o tema FlatDarkLaf. Usando tema padrão.", e);
        }
        
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    private javax.swing.JMenu arquivo;
    private javax.swing.JButton bAlunos;
    private javax.swing.JButton bProfessores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuAlunos;
    private javax.swing.JMenuItem menuLeave;
    private javax.swing.JMenuItem menuProfessores;
    private javax.swing.JMenuItem sobre;
}