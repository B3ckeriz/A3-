package model;

import dao.ProfessorDAO;
import java.sql.SQLException;
import java.util.List;

public class Professor extends Pessoa {

    private String campus;
    private String cpf;
    private String contato;
    private String titulo;
    private double salario;    // DOUBLE agora compatível com DAO
    private final ProfessorDAO dao;

    // Construtor padrão
    public Professor() {
        this.dao = new ProfessorDAO();
    }

    // Construtor resumido
    public Professor(String campus, String cpf, String contato, String titulo, double salario) {
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.dao = new ProfessorDAO();
    }

    // Construtor completo
    public Professor(String campus, String cpf, String contato, String titulo, double salario,
                     int id, String nome, int idade) {
        super(id, nome, idade);
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.dao = new ProfessorDAO();
    }

    // Getters e Setters
    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public String toString() {
        return "\n ID: " + getId()
                + "\n Nome: " + getNome()
                + "\n Idade: " + getIdade()
                + "\n Campus: " + getCampus()
                + "\n CPF: " + getCpf()
                + "\n Contato: " + getContato()
                + "\n Título: " + getTitulo()
                + "\n Salário: " + getSalario()
                + "\n -----------";
    }

    /* Métodos de CRUD */

    public List<Professor> getMinhaLista() {
        return dao.getMinhaLista();
    }

    public boolean insertProfessor(String campus, String cpf, String contato, String titulo,
                                   double salario, String nome, int idade) throws SQLException {

        int id = this.maiorID() + 1;

        Professor p = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        return dao.insertProfessor(p);
    }

    public boolean updateProfessor(String campus, String cpf, String contato, String titulo,
                                   double salario, int id, String nome, int idade) {

        Professor p = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        return dao.updateProfessor(p);
    }

    public boolean deleteProfessor(int id) {
        return dao.deleteProfessor(id);
    }

    public Professor carregaProfessor(int id) {
        return dao.carregaProfessor(id);
    }

    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
}