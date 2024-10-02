package com.example.task_family;

public class Responsavel implements DatabaseTable {
    @Override
    public String getTableName() {
        return "responsavel";
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + getTableName() + " ("
                + "id integer primary key autoincrement not null,"
                + "nome text not null,"
                + "telefone text not null,"
                + "dependentes text)"; // A coleção de strings pode ser armazenada como uma única string
    }
}
