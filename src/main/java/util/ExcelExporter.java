package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Classe utilitária para exportação de tabelas JTable para arquivos Excel (.xls).
 * Elimina código duplicado entre GerenciaAlunos e GerenciaProfessores.
 */
public class ExcelExporter {
    
    private static final Logger LOGGER = Logger.getLogger(ExcelExporter.class.getName());
    
    /**
     * Exporta uma JTable para um arquivo Excel.
     * 
     * @param table A tabela a ser exportada
     * @param parentComponent Componente pai para exibir diálogos (pode ser null)
     * @throws IOException se houver erro ao criar ou escrever no arquivo
     */
    public static void exportToExcel(JTable table, java.awt.Component parentComponent) throws IOException {
        JFileChooser chooser = createFileChooser();

        if (chooser.showSaveDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
            String filePath = ensureExcelExtension(chooser.getSelectedFile().toString());

            try {
                File fileXLS = prepareFile(filePath);
                writeTableToExcel(table, fileXLS);
                showSuccessMessage(parentComponent, filePath);
            } catch (IOException e) {
                handleIOException(parentComponent, e);
            } catch (NumberFormatException e) {
                handleNumberFormatException(parentComponent, e);
            }
        }
    }

    private static JFileChooser createFileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Salvar arquivo");
        chooser.setAcceptAllFileFilterUsed(false);
        return chooser;
    }

    private static String ensureExcelExtension(String path) {
        if (!path.toLowerCase().endsWith(".xls")) {
            path += ".xls";
        }
        return path;
    }

    private static File prepareFile(String path) throws IOException {
        File fileXLS = new File(path);

        if (fileXLS.exists() && !fileXLS.delete()) {
            throw new IOException("Não foi possível substituir o arquivo existente.");
        }

        if (!fileXLS.createNewFile()) {
            throw new IOException("Não foi possível criar o arquivo.");
        }

        return fileXLS;
    }

    private static void writeTableToExcel(JTable table, File fileXLS) throws IOException {
        try (Workbook book = new HSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fileXLS)) {

            Sheet sheet = book.createSheet("Dados");
            sheet.setDisplayGridlines(true);

            writeHeaderRow(table, sheet);
            writeDataRows(table, sheet);

            book.write(fileOut);
        }
    }

    private static void writeHeaderRow(JTable table, Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int j = 0; j < table.getColumnCount(); j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(table.getColumnName(j));
        }
    }

    private static void writeDataRows(JTable table, Sheet sheet) {
        for (int row = 0; row < table.getRowCount(); row++) {
            Row dataRow = sheet.createRow(row + 1);
            for (int col = 0; col < table.getColumnCount(); col++) {
                writeCellValue(table, dataRow, row, col);
            }
        }
    }

    private static void writeCellValue(JTable table, Row dataRow, int row, int col) {
        Cell cell = dataRow.createCell(col);
        Object value = table.getValueAt(row, col);

        if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else {
            cell.setCellValue(value != null ? value.toString() : "");
        }
    }

    private static void showSuccessMessage(java.awt.Component parentComponent, String path) {
        JOptionPane.showMessageDialog(
                parentComponent,
                "Arquivo Excel exportado com sucesso em:\n" + path,
                "Exportação Concluída",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void handleIOException(java.awt.Component parentComponent, IOException e) {
        LOGGER.log(Level.SEVERE, "Erro de IO ao exportar dados para Excel", e);
        JOptionPane.showMessageDialog(
                parentComponent,
                "Não foi possível exportar o arquivo.\nVerifique se você tem permissão de escrita no local selecionado.",
                "Erro na Exportação",
                JOptionPane.ERROR_MESSAGE
        );
        throw new RuntimeException("Erro ao exportar para Excel", e);
    }

    private static void handleNumberFormatException(java.awt.Component parentComponent, NumberFormatException e) {
        LOGGER.log(Level.SEVERE, "Erro ao formatar números durante exportação para Excel", e);
        JOptionPane.showMessageDialog(
                parentComponent,
                "Erro ao processar dados numéricos da tabela.\nVerifique se todos os valores estão formatados corretamente.",
                "Erro de Formatação",
                JOptionPane.ERROR_MESSAGE
        );
        throw new RuntimeException("Erro de formatação ao exportar para Excel", e);
    }

}