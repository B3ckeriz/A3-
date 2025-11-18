package DAO;

import java.util.*;

import model.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Retorna a lista de professores do banco de dados

public ArrayList getMinhaLista() {

    MinhaLista2.clear(); // Limpa o arrayList

    try (Statement stmt = this.getConexao().createStatement();
         ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores")) {

        while (res.next()) {

            String campus = res.getString("campus");
            String cpf = res.getString("cpf");
            String contato = res.getString("contato");
            String titulo = res.getString("titulo");
            int salario = res.getInt("salario");
            int id = res.getInt("id");
            String nome = res.getString("nome");
            int idade = res.getInt("idade");

            Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);

            MinhaLista2.add(objeto);
        }

    } catch (SQLException ex) {
    }

    return MinhaLista2;
}

        // Deleta um professor específico pelo seu campo ID
        public boolean DeleteProfessorBD(int id) {
            try (Statement stmt = this.getConexao().createStatement()) {
                stmt.executeUpdate("DELETE FROM tb_professores WHERE id = " + id);
            } catch (SQLException erro) {
            }

            return true;
        }

        // Carrega as informações de um professor específico com base no ID
        public Professor carregaProfessor(int id) {

            Professor objeto = new Professor();
            objeto.setId(id);

            try (Statement stmt = this.getConexao().createStatement();
                 ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores WHERE id = " + id)) {

                if (res.next()) {
                    objeto.setNome(res.getString("nome"));
                    objeto.setIdade(res.getInt("idade"));
                    objeto.setCampus(res.getString("campus"));
                    objeto.setCpf(res.getString("cpf"));
                    objeto.setContato(res.getString("contato"));
                    objeto.setTitulo(res.getString("titulo"));
                    objeto.setSalario(res.getInt("salario"));
                }

            } catch (SQLException erro) {
            }

            return objeto;
        }
