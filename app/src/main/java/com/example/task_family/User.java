package com.example.task_family;

import android.widget.EditText;

public abstract class User implements DatabaseTable {
    // Atributos para login
    private int id;
    private String email;
    private String password;

    // Atributos de dados
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;
    private String nome;
    private String tipo;

    public User() {
        this.id = 0;
        this.email = "";
        this.password = "";
        this.nome = "";
        this.tipo = "";
    }

    public User(int id, String email, String password, String nome, String tipo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters e Setters para login
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters e Setters específicos de dados
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + getTableName() + " (" +
                "id integer primary key autoincrement not null," +
                "email text not null," +
                "password text not null," +
                "nome text not null," +
                "tipo text not null)";
    }

    @Override
    public String getTableName() {
        return "user"; // Defina o nome da tabela, se necessário
    }
}
