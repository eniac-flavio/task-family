package com.example.task_family;

public class Responsavel extends User {
    private String telefone;
    private String tipoUsuario; // Novo campo para tipo de usuário

    // Construtores sem parâmetros (padrão) e com parâmetros (opcional)
    public Responsavel() {
        super();
    }

    public Responsavel(int id, String email, String password, String nome, String tipo, String telefone, String tipoUsuario) {
        super(id, email, password, nome, tipo);
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario; // Inicializa o novo campo
    }

    // Getters e Setters
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String getTableName() {
        return "responsavel";
    }

    @Override
    public String getCreateTableSQL() {
        return super.getCreateTableSQL().replace(")", ", telefone text, tipo_usuario text)");
    }
}
