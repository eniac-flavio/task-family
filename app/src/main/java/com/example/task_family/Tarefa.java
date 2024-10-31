package com.example.task_family;
import java.util.Date;

public class Tarefa implements DatabaseTable {
    // Atributos básicos
    private int id, id_dependente;
    private String descricao, titulo;
    private Date abertura, fechamento;

    // Método construtor sem parâmetro (padrão) e com parâmetro (opcional)
    public Tarefa() {
        this.id = 0;
        this.id_dependente = 0;
        this.descricao = "";
        this.titulo = "";
        this.abertura = new Date();
        this.fechamento = new Date();
    }

    public Tarefa(int _id, int _id_dependente, String _descricao, String _titulo, Date _abertura, Date _fechamento) {
        this.id = _id;
        this.id_dependente = _id_dependente;
        this.descricao = _descricao;
        this.titulo = _titulo;
        this.abertura = _abertura;
        this.fechamento = _fechamento;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dependente() {
        return id_dependente;
    }

    public void setId_dependente(int id_dependente) {
        this.id_dependente = id_dependente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    // Sobreescrita do DatabaseTable
    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + getTableName() + " (" +
                "id integer primary key autoincrement not null," +
                "id_dependente integer not null," +
                "descricao text not null," +
                "titulo text not null," +
                "abertura date not null," +
                "fechamento date not null)";
    }

    @Override
    public String getTableName() {
        return "tarefa";
    }
}
