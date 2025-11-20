package util;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.function.Function;

/**
 * Classe utilitária para operações comuns em tabelas (JTable).
 * Elimina código duplicado no carregamento de dados.
 */
public class TableHelper {
    
    /**
     * Carrega dados em uma tabela de forma genérica.
     * Limpa a tabela e adiciona todos os dados da lista.
     * 
     * @param <T> Tipo dos objetos na lista
     * @param model Modelo da tabela (DefaultTableModel)
     * @param data Lista de dados a serem carregados
     * @param mapper Função que converte cada objeto em um array de valores para a tabela
     * 
     * @example
     * // Exemplo de uso com Alunos:
     * TableHelper.loadTable(modelo, listaAlunos, aluno -> new Object[]{
     *     aluno.getId(),
     *     aluno.getNome(),
     *     aluno.getIdade()
     * });
     */
    public static <T> void loadTable(
            DefaultTableModel model, 
            List<T> data, 
            Function<T, Object[]> mapper) {
        
        // Limpa todas as linhas da tabela
        model.setNumRows(0);
        
        // Adiciona cada item da lista como uma nova linha
        for (T item : data) {
            Object[] row = mapper.apply(item);
            model.addRow(row);
        }
    }
}