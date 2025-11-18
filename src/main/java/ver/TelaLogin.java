package ver;


import DAO.AlunoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Tela de Login do sistema SisUni
 * Valida credenciais e estabelece conexão com o banco de dados
 */
public class TelaLogin extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(TelaLogin.class.getName());
    
    // Construtor
    public TelaLogin() {
        initComponents();
        getRootPane().setDefaultButton(this.login);
        setLocationRelativeTo(null); // Centraliza a janela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(51, 255, 51));
        setResizable(false);

        login.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        login.setText("LOGIN");
        login.setToolTipText("ENTER");
        login.setAlignmentX(0.5F);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SisUni - Sistema de Gerenciamento Universitário");

        password.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DIGITE A SENHA (MySQL)");

        user.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        user.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DIGITE O USUÁRIO (MySQL)");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(password)
                        .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2)))
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        login.getAccessibleContext().setAccessibleDescription("Cadastro de Professores");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Valida as credenciais fornecidas pelo usuário
     * @return true se usuário e senha foram informados, false caso contrário
     */
    private boolean validarCredenciais() {
        String usuario = user.getText().trim();
        String senha = String.valueOf(password.getPassword());
        
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "Por favor, informe o usuário!", 
                "Campo obrigatório", 
                JOptionPane.WARNING_MESSAGE
            );
            user.requestFocus();
            return false;
        }
        
        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "Por favor, informe a senha!", 
                "Campo obrigatório", 
                JOptionPane.WARNING_MESSAGE
            );
            password.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Testa a conexão com o banco de dados
     * @return true se a conexão for bem-sucedida, false caso contrário
     */
    private boolean testarConexao() {
        
AlunoDAO teste = new AlunoDAO();
if (teste.testarConexao()) {
    JOptionPane.showMessageDialog(rootPane, "Conexão efetuada com sucesso!");
    return true;
} else {
    JOptionPane.showMessageDialog(rootPane, "Conexão falhou!");
    return false;
}
        
        
    }
    
    /**
     * Action do botão LOGIN
     * Valida credenciais e abre a tela principal se tudo estiver correto
     */
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        
        // Valida se os campos foram preenchidos
        if (!validarCredenciais()) {
            return;
        }
        
        // Desabilita o botão durante o processo
        login.setEnabled(false);
        login.setText("Conectando...");
        
        try {
            // Testa a conexão com o banco
            if (testarConexao()) {
                logger.info("Login realizado com sucesso pelo usuário: " + user.getText());
                
                // Abre a tela principal
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                
                // Fecha a tela de login
                this.dispose();
            }
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro durante o processo de login", ex);
            JOptionPane.showMessageDialog(
                this, 
                "Erro ao realizar login:\n" + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            // Reabilita o botão
            login.setEnabled(true);
            login.setText("LOGIN");
        }
        
    }//GEN-LAST:event_loginActionPerformed

    /**
     * Método main - ponto de entrada da aplicação
     */
    public static void main(String args[]) {
        /* Set the System look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Não foi possível definir Look and Feel Nimbus", ex);
        }

        /* Cria e exibe a tela de login */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}