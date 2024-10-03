package com.example.task_family;

public abstract class User extends Account implements DatabaseTable {
    private String nome;
    private String tipo;

    public User() {
        super(0, "", "");
        this.nome = "";
        this.tipo = "";
    }

    public User(int id, String email, String password, String nome, String tipo) {
        super(id, email, password);
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters e Setters
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
        return "CREATE TABLE " + getTableName() + " ("
                + "id integer primary key autoincrement not null,"
                + "email text not null,"
                + "password text not null,"
                + "nome text not null,"
                + "tipo text not null)";
    }
}
