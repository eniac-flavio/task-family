package com.example.task_family;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarefa implements DatabaseTable {
    // Constante estática para o nome da tabela
    public static final String TABLE_NAME = "tarefa";

    // Atributos
    private int id, idDependente;
    private String descricao, titulo;
    private Date abertura, fechamento;

    // Construtor padrão e com parâmetros
    public Tarefa() {
        this(0, 0, "", "", new Date(), new Date());
    }

    public Tarefa(int id, int idDependente, String descricao, String titulo, Date abertura, Date fechamento) {
        this.id = id;
        this.idDependente = idDependente;
        this.descricao = descricao;
        this.titulo = titulo;
        this.abertura = abertura;
        this.fechamento = fechamento;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(int idDependente) {
        this.idDependente = idDependente;
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

    // Implementação do DatabaseTable
    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_dependente INTEGER NOT NULL," +
                "descricao TEXT NOT NULL," +
                "titulo TEXT NOT NULL," +
                "abertura INTEGER NOT NULL," +
                "fechamento INTEGER NOT NULL)";
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    // Método para inserir a tarefa no banco de dados usando Server
    public long inserir(Server server) {
        try (SQLiteDatabase db = server.getWritableDatabase()) {
            ContentValues values = toContentValues();
            return db.insert(TABLE_NAME, null, values);
        }
    }

    // Método para buscar todas as tarefas no banco de dados usando Server
    public static List<Tarefa> buscarTodas(Server server) {
        List<Tarefa> tarefas = new ArrayList<>();

        try (SQLiteDatabase db = server.getReadableDatabase();
             Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null)) {

            while (cursor.moveToNext()) {
                tarefas.add(fromCursor(cursor));
            }
        }
        return tarefas;
    }

    // Converte a instância de Tarefa em ContentValues para inserção no banco de dados
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id_dependente", idDependente);
        values.put("descricao", descricao);
        values.put("titulo", titulo);
        values.put("abertura", abertura.getTime());
        values.put("fechamento", fechamento.getTime());
        return values;
    }

    // Cria uma instância de Tarefa a partir de um Cursor
    public static Tarefa fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        int idDependente = cursor.getInt(cursor.getColumnIndexOrThrow("id_dependente"));
        String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
        Date abertura = new Date(cursor.getLong(cursor.getColumnIndexOrThrow("abertura")));
        Date fechamento = new Date(cursor.getLong(cursor.getColumnIndexOrThrow("fechamento")));

        return new Tarefa(id, idDependente, descricao, titulo, abertura, fechamento);
    }
}
