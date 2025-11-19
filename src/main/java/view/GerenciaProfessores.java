package view;

import model.Professor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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

public class GerenciaProfessores extends javax.swing.JFrame {

    private final Professor objetoProfessor = new Professor();

    public GerenciaProfessores() {
        initComponents();
        carregaTabela();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        bCadastro = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        bDeletar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProfessores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        export = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuGerenciaAluno = new javax.swing.JMenuItem();
        menuExport = new javax.swing.JMenuItem();
        menuRefresh = new javax.swing.JMenuItem();
        sobre = new javax.swing.JMenuItem();
        menuLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerência de Professores");
        setResizable(false);

        bCadastro.setText("Cadastrar novo");
        bCadastro.addActionListener(evt -> bCadastroActionPerformed(evt));

        bEditar.setText("Editar");
        bEditar.addActionListener(evt -> bEditarActionPerformed(evt));

        bDeletar.setText("Deletar");
        bDeletar.addActionListener(evt -> bDeletarActionPerformed(evt));

        jTableProfessores.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nome", "Idade", "Campus", "CPF", "Contato", "Título", "Salário"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jTableProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProfessoresMouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(jTableProfessores);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Professores");

        refresh.setText("  Atualizar tabela");
        refresh.addActionListener(evt -> refreshActionPerformed(evt));

        export.setText("Exportar para Excel");
        export.addActionListener(evt -> exportActionPerformed(evt));

        menu.setText("Arquivo");

        menuGerenciaAluno.setText("Gerência de Alunos");
        menuGerenciaAluno.addActionListener(evt -> menuGerenciaAlunoActionPerformed(evt));
        menu.add(menuGerenciaAluno);

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
                                                .addGap(65, 65, 65)
                                                .addComponent(bCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                                                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                    for (int i = 0; i < this.jTableProfessores.getRowCount(); i++) {
                        Row row = sheet.createRow(i);
                        for (int j = 0; j < this.jTableProfessores.getColumnCount(); j++) {
                            Cell cell = row.createCell(j);
                            if (i == 0) {
                                cell.setCellValue(this.jTableProfessores.getColumnName(j));
                            }
                        }
                    }

                    int firstRow = 1;

                    for (int linha = 0; linha < this.jTableProfessores.getRowCount(); linha++) {
                        Row row2 = sheet.createRow(firstRow++);
                        for (int coluna = 0; coluna < this.jTableProfessores.getColumnCount(); coluna++) {

                            Cell cell2 = row2.createCell(coluna);
                            Object value = this.jTableProfessores.getValueAt(linha, coluna);

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

    private void menuGerenciaAlunoActionPerformed(java.awt.event.ActionEvent evt) {
        GerenciaAlunos tela = new GerenciaAlunos();
        tela.setVisible(true);
        this.dispose();
    }

    private void menuLeaveActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void bCadastroActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            CadastroProfessor tela = new CadastroProfessor();
            tela.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (this.jTableProfessores.getSelectedRow() != -1) {
                EditarProfessor tela = new EditarProfessor();
                tela.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cadastro para alterar");
            }
        } catch (ParseException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String listaDados[] = new String[8];

    private String validarFormatado(String input) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                str.append(input.charAt(i));
            }
        }
        return str.toString();
    }

    private void jTableProfessoresMouseClicked(java.awt.event.MouseEvent evt) {
        if (this.jTableProfessores.getSelectedRow() != -1) {

            listaDados[0] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 1).toString();
            listaDados[1] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 2).toString();
            listaDados[2] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 3).toString();
            listaDados[3] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 4).toString();
            listaDados[4] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 5).toString();
            listaDados[5] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 6).toString();
            listaDados[6] = validarFormatado(this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 7).toString());
            listaDados[7] = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 0).toString();
        }
    }

    private void bDeletarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id;

            if (this.jTableProfessores.getSelectedRow() == -1) {
                throw new Mensagens("Selecione um cadastro para deletar");
            } else {
                id = Integer.parseInt(this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 0).toString());
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
                if (this.objetoProfessor.DeleteProfessorBD(id)) {
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
            exportXls();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            exportXls();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sobreActionPerformed(java.awt.event.ActionEvent evt) {
        Sobre tela = new Sobre();
        tela.setVisible(true);
    }

    public void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableProfessores.getModel();
        modelo.setNumRows(0);

        List<Professor> minhalista = objetoProfessor.getMinhaLista();

        for (Professor p : minhalista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getIdade(),
                p.getCampus(),
                p.getCpf(),
                p.getContato(),
                p.getTitulo(),
                "R$" + p.getSalario() + ".00"
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
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new GerenciaProfessores().setVisible(true));
    }

    private javax.swing.JButton bCadastro;
    private javax.swing.JButton bDeletar;
    private javax.swing.JButton bEditar;
    private javax.swing.JButton export;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProfessores;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuExport;
    private javax.swing.JMenuItem menuGerenciaAluno;
    private javax.swing.JMenuItem menuLeave;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JButton refresh;
    private javax.swing.JMenuItem sobre;
}