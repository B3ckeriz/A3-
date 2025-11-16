package Model;

import DAO.ProfessorDAO;
import java.sql.SQLException;
import java.util.*;

// Classe Professor herda as características de Pessoa
public class Professor extends Pessoa {
    
    // Atributos
    private String campus;
    private String cpf;
    private String contato;
    private String titulo;
    private int salario;
    private final ProfessorDAO dao;

    // Construtor padrão
    public Professor() {
        this.dao = new ProfessorDAO();
    }
    
    // Construtor completo da classe Professor
    public Professor(String campus, String cpf, String contato, String titulo, int salario) {
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.dao = new ProfessorDAO();
    }
    
    // Construtor completo da classe Professor + Superclasse Pessoa 
    public Professor(String campus, String cpf, String contato, String titulo, int salario, int id, String nome, int idade) {
        super(id, nome, idade);
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.dao = new ProfessorDAO();
    }
    
    
    // Getters and setters
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
    
    // Sobrescrevendo método toString() para adequar o retorno de acordo com o objeto que aciona o mesmo
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Campus: " + this.getCampus()
                + "\n CPF:" + this.getCpf()
                + "\n Contato:" + this.getContato()
                + "\n Título:" + this.getTitulo()
                + "\n Salário:" + this.getSalario()
                + "\n -----------";
    }
    
    /*
        • Métodos responsáveis pelas ações referentes ao banco de dados.
        • Atuam em conjunto com a classe DAO através da variável dao que recebe um objeto da referida classe.
    */
    
    // Retorna a lista de alunos do banco de dados
    public ArrayList getMinhaLista() {
        return dao.getMinhaLista();
    }
    
    // Cadastra novo professor
    public boolean InsertProfessorBD(String campus, String cpf, String contato, String titulo, int salario, String nome, int idade) throws SQLException {
        int id = this.maiorID() + 1;
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        dao.InsertProfessorBD(objeto);
        return true;
    }
    
    // Deleta um professor específico pelo seu campo ID
    public boolean DeleteProfessorBD(int id) {
        dao.DeleteProfessorBD(id);
        return true;
    }
    
    // Edita um professor específico pelo seu campo ID
    public boolean UpdateProfessorBD(String campus, String cpf, String contato, String titulo, int salario, int id, String nome, int idade) {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        dao.UpdateProfessorBD(objeto);
        return true;
    }
    
    // Carrega as informações de um professor específico com base no ID
    public Professor carregaProfessor(int id) {
        dao.carregaProfessor(id);
        return null;
    }
    
    // Retorna o maior ID do banco de dados
        public int maiorID() throws SQLException{
        return dao.maiorID();
    } 
}

