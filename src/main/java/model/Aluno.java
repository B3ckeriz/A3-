package model;

import java.util.*;
import dao.AlunoDAO;
import java.sql.SQLException;

// Classe Aluno herda as características de Pessoa
public class Aluno extends Pessoa {

    // Atributos
    private String curso;
    private int fase;
    private final AlunoDAO dao; // Apontador para a Classe responsável pela interação com o banco de dados

    // Construtor padrão
    public Aluno() {
        this.dao = new AlunoDAO(); // Instanciando objeto da Classe responsável pela interação com o banco de dados
    }

    // Construtor completo da classe Aluno
    public Aluno(String curso, int fase) {
        this.curso = curso;
        this.fase = fase;
        this.dao = new AlunoDAO(); // Instanciando objeto da classe responsável pela interação com o banco de dados
    }

    // Construtor completo da classe Aluno + Superclasse Pessoa
    public Aluno(String curso, int fase, int id, String nome, int idade) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
        this.dao = new AlunoDAO(); // Instanciando objeto da Classe responsável pela interação com o banco de dados
    }

    // Getters and setters
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }
    
    // Sobrescrevendo método toString() para adequar o retorno de acordo com o objeto que aciona o mesmo
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Curso: " + this.getCurso()
                + "\n Fase:" + this.getFase()
                + "\n -----------";
    }
    
    /*
        • Métodos responsáveis pelas ações referentes ao banco de dados.
        • Atuam em conjunto com a Classe DAO através da variável dao que recebe um objeto da referida classe.
    */
    
    // Retorna a lista de alunos do banco de dados
   public List<Aluno> getMinhaLista() {
    return dao.getMinhaLista(); // Chama o DAO
}
    // Cadastra novo aluno
    public boolean insertAlunoBD(String curso, int fase, String nome, int idade) throws SQLException {
        int id = this.maiorID() + 1;
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        dao.insertAluno(objeto);
        return true;

    }

    // Deleta um aluno específico pelo seu campo ID
    public boolean deleteAlunoBD(int id) {
        dao.deleteAlunoBD(id);
        return true;
    }

    // Edita um aluno específico pelo seu campo ID
    public boolean updateAluno(String curso, int fase, int id, String nome, int idade) {
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        dao.updateAluno(objeto);
        return true;
    }

    // Carrega as informações de um aluno específico com base no ID
    public Aluno carregaAluno(int id) {
        dao.carregaAluno(id);
        return null;
    }
    
    // Retorna o maior ID do banco de dados
    public int maiorID() throws SQLException{
        return dao.maiorID();
    }   
}
