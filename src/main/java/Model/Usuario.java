package model;

import java.util.*;
import DAO.UsuarioDAO;
import java.sql.SQLException;

// Classe Usuario herda as características de Pessoa
public class Usuario extends Pessoa {

    // Atributos
    private String username;
    private String senha;
    private final UsuarioDAO dao; // Apontador para a Classe responsável pela interação com o banco de dados

    // Construtor padrão
    public Usuario() {
        this.dao = new UsuarioDAO(); // Instanciando objeto da Classe responsável pela interação com o banco de dados
    }

    // Construtor completo da classe Usuario
    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
        this.dao = new UsuarioDAO(); // Instanciando objeto da classe responsável pela interação com o banco de dados
    }

    // Construtor completo da classe Usuario + Superclasse Pessoa
    public Usuario(String username, String senha, int id, String nome, int idade) {
        super(id, nome, idade);
        this.username = username;
        this.senha = senha;
        this.dao = new UsuarioDAO(); // Instanciando objeto da Classe responsável pela interação com o banco de dados
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    // Sobrescrevendo método toString() para adequar o retorno de acordo com o objeto que aciona o mesmo
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Username: " + this.getUsername()
                + "\n -----------";
    }
    
    /*
        • Métodos responsáveis pelas ações referentes ao banco de dados.
        • Atuam em conjunto com a Classe DAO através da variável dao que recebe um objeto da referida classe.
    */
    
    // Retorna a lista de usuarios do banco de dados
   public List<Usuario> getMinhaLista() {
    return dao.getMinhaLista(); // Chama o DAO
}
    // Cadastra novo usuario
    public boolean insertUsuarioBD(String username, String senha, String nome, int idade) throws SQLException {
        int id = this.maiorID() + 1;
        Usuario usuario = new Usuario(username, senha, id, nome, idade);
        dao.insertUsuarioBD(usuario);
        return true;

    }

    // Deleta um usuario específico pelo seu campo ID
    public boolean deleteUsuarioBD(int id) {
        dao.deleteUsuarioBD(id);
        return true;
    }

    // Edita um usuario específico pelo seu campo ID
    public boolean updateUsuario(String username, String senha, int id, String nome, int idade) {
        Usuario usuario = new Usuario(username, senha, id, nome, idade);
        dao.updateUsuarioBD(usuario);
        return true;
    }

    // Carrega as informações de um usuario específico com base no ID
    public Usuario carregaUsuario(int id) {
        dao.carregaUsuario(id);
        return null;
    }
    
    // Retorna o maior ID do banco de dados
    public int maiorID() throws SQLException{
        return dao.maiorID();
    }   
}