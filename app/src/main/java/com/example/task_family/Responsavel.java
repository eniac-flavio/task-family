package com.example.task_family;

public class Responsavel extends User {
    private String telefone;

    // Construtores sem parâmetros (padrão) e com parâmetros (opcional)
    public Responsavel() {
        super();
    }
    public Responsavel(int id, String email, String password, String nome, String tipo, String telefone) {
        super(id, email, password, nome, tipo);
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getTableName() {
        return "responsavel";
    }

    @Override
    public String getCreateTableSQL() {
        return super.getCreateTableSQL().replace(")", ", telefone text)");
    }
}
