package view;

import model.Aluno;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class GerenciaAlunos extends javax.swing.JFrame {

    private final Aluno objetoAluno = new Aluno();

    public GerenciaAlunos() {
        initComponents();
        carregaTabela();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        bCadastro = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        bDeletar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAlunos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        export = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuGerenciaProfessores = new javax.swing.JMenuItem();
        menuExport = new javax.swing.JMenuItem();
        menuRefresh = new javax.swing.JMenuItem();
        sobre = new javax.swing.JMenuItem();
        menuLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerência de Alunos");
        setBackground(new java.awt.Color(80, 80, 80));
        setResizable(false);

        bCadastro.setText("Cadastrar novo");
        bCadastro.addActionListener(evt -> bCadastroActionPerformed(evt));

        bEditar.setText("Editar");
        bEditar.addActionListener(evt -> bEditarActionPerformed(evt));

        bDeletar.setText("Deletar");
        bDeletar.addActionListener(evt -> bDeletarActionPerformed(evt));

        jTableAlunos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "ID", "Nome", "Idade", "Curso", "Fase"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jTableAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunosMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jTableAlunos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Alunos");

        refresh.setText("  Atualizar tabela");
        refresh.addActionListener(evt -> refreshActionPerformed(evt));

        export.setText("Exportar para Excel");
        export.addActionListener(evt -> exportActionPerformed(evt));

        menu.setText("Arquivo");

        menuGerenciaProfessores.setText("Gerência de Professores");
        menuGerenciaProfessores.addActionListener(evt -> menuGerenciaProfessoresActionPerformed(evt));
        menu.add(menuGerenciaProfessores);

        menuExport.setText("Exportar para Excel");
        menuExport.addActionListener(evt -> menuExportActionPerformed(evt));
        menu.add(menuExport);

        menuRefresh.setText("Atualizar tabela");
        menuRefresh.addActionListener(evt -> menuRefreshActionPerformed(evt));
        menu.add(menuRefresh);

        sobre.setText("Sobre");
        sobre.addActionListener(evt -> sobreActionPerformed(evt));
        menu.add(sobre);

        menuLeave.setText("Sair");
        menuLeave.addActionListener(evt -> menuLeaveActionPerformed(evt));
        menu.add(menuLeave);

        jMenuBar1.add(menu);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(64, 64, 64)
                                                .addComponent(bCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bCadastro)
                                        .addComponent(bEditar)
                                        .addComponent(bDeletar)
                                        .addComponent(refresh)
                                        .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void exportXls() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos Excel", "xls");

        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Salvar arquivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            String path = chooser.getSelectedFile().toString().concat(".xls");

            try {
                File fileXLS = new File(path);

                if (fileXLS.exists()) {
                    boolean deleted = fileXLS.delete();
                    if (!deleted) {
                        throw new IOException("Não foi possível substituir o arquivo existente.");
                    }
                }

                if (!fileXLS.createNewFile()) {
                    throw new IOException("Não foi possível criar o arquivo.");
                }

                try (Workbook book = new HSSFWorkbook();
                     FileOutputStream fileOut = new FileOutputStream(fileXLS)) {

                    Sheet sheet = book.createSheet("Minha folha de trabalho 1");
                    sheet.setDisplayGridlines(true);

                    for (int i = 0; i < this.jTableAlunos.getRowCount(); i++) {
                        Row row = sheet.createRow(i);
                        for (int j = 0; j < this.jTableAlunos.getColumnCount(); j++) {
                            Cell cell = row.createCell(j);
                            if (i == 0) {
                                cell.setCellValue(this.jTableAlunos.getColumnName(j));
                            }
                        }
                    }

                    int firstRow = 1;

                    for (int linha = 0; linha < this.jTableAlunos.getRowCount(); linha++) {
                        Row row2 = sheet.createRow(firstRow++);
                        for (int coluna = 0; coluna < this.jTableAlunos.getColumnCount(); coluna++) {

                            Cell cell2 = row2.createCell(coluna);
                            Object value = this.jTableAlunos.getValueAt(linha, coluna);

                            if (value instanceof Double) {
                                cell2.setCellValue((Double) value);
                            } else if (value instanceof Float) {
                                cell2.setCellValue((Float) value);
                            } else if (value instanceof Integer) {
                                cell2.setCellValue((Integer) value);
                            } else {
                                cell2.setCellValue(value != null ? value.toString() : "");
                            }
                        }
                    }

                    book.write(fileOut);
                }

            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao exportar para Excel: " + e.getMessage());
            }
        }
    }

    private void menuGerenciaProfessoresActionPerformed(java.awt.event.ActionEvent evt) {
        GerenciaProfessores tela = new GerenciaProfessores();
        tela.setVisible(true);
        this.dispose();
    }

    private void menuLeaveActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void bCadastroActionPerformed(java.awt.event.ActionEvent evt) {
        CadastroAluno tela = new CadastroAluno();
        tela.setVisible(true);
    }

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int linha = jTableAlunos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um cadastro para alterar");
            return;
        }

        String id = jTableAlunos.getValueAt(linha, 0).toString();
        String nome = jTableAlunos.getValueAt(linha, 1).toString();
        String idade = jTableAlunos.getValueAt(linha, 2).toString();
        String curso = jTableAlunos.getValueAt(linha, 3).toString();
        String fase = jTableAlunos.getValueAt(linha, 4).toString().substring(0, 1);

        EditarAluno tela = new EditarAluno(id, nome, idade, curso, fase);
        tela.setVisible(true);
    }

    public static String listaDados2[] = new String[5];

    private void jTableAlunosMouseClicked(java.awt.event.MouseEvent evt) {
        if (this.jTableAlunos.getSelectedRow() != -1) {

            String nome = this.jTableAlunos.getValueAt(this.jTableAlunos.getSelectedRow(), 1).toString();
            String idade = this.jTableAlunos.getValueAt(this.jTableAlunos.getSelectedRow(), 2).toString();
            String curso = this.jTableAlunos.getValueAt(this.jTableAlunos.getSelectedRow(), 3).toString();
            String fase = this.jTableAlunos.getValueAt(this.jTableAlunos.getSelectedRow(), 4).toString();
            String id = this.jTableAlunos.getValueAt(this.jTableAlunos.getSelectedRow(), 0).toString();

            listaDados2[0] = id;
            listaDados2[1] = nome;
            listaDados2[2] = idade;
            listaDados2[3] = curso;
            listaDados2[4] = String.valueOf(fase.charAt(0));
        }
    }

    private void bDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id;

            if (this.jTableAlunos.getSelectedRow() == -1) {
                throw new Mensagens("Selecione um cadastro para deletar");
            } else {
                id = Integer.parseInt(
                        this.jTableAlunos.getValueAt(
                                this.jTableAlunos.getSelectedRow(), 0
                        ).toString()
                );
            }

            String[] options = {"Sim", "Não"};
            int respostaUsuario = JOptionPane.showOptionDialog(
                    null,
                    "Tem certeza que deseja apagar este cadastro?",
                    "Confirmar exclusão",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[1]
            );

            if (respostaUsuario == 0) {
                if (this.objetoAluno.deleteAlunoBD(id)) {
                    JOptionPane.showMessageDialog(rootPane, "Cadastro apagado com sucesso!");
                }
            }
        } catch (Mensagens erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } finally {
            carregaTabela();
        }
    }

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {
        carregaTabela();
    }

    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        carregaTabela();
    }

    private void menuExportActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.exportXls();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.exportXls();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sobreActionPerformed(java.awt.event.ActionEvent evt) {
        Sobre tela = new Sobre();
        tela.setVisible(true);
    }

    private final void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableAlunos.getModel();
        modelo.setNumRows(0);

        List<Aluno> minhalista = objetoAluno.getMinhaLista();

        for (Aluno a : minhalista) {
            modelo.addRow(new Object[]{
                a.getId(),
                a.getNome(),
                a.getIdade(),
                a.getCurso(),
                a.getFase() + "ª",
            });
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GerenciaAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new GerenciaAlunos().setVisible(true));
    }

    private javax.swing.JButton bCadastro;
    private javax.swing.JButton bDeletar;
    private javax.swing.JButton bEditar;
    private javax.swing.JButton export;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAlunos;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuExport;
    private javax.swing.JMenuItem menuGerenciaProfessores;
    private javax.swing.JMenuItem menuLeave;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JButton refresh;
    private javax.swing.JMenuItem sobre;
}