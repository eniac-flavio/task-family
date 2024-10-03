package com.example.task_family;

public class ResponsavelDependente implements DatabaseTable {
    @Override
    public String getTableName() {
        return "responsavel_dependente";
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + getTableName() + " ("
                + "responsavel_id integer not null,"
                + "dependente_id integer not null,"
                + "PRIMARY KEY(responsavel_id, dependente_id),"
                + "FOREIGN KEY(responsavel_id) REFERENCES responsavel(id) ON DELETE CASCADE,"
                + "FOREIGN KEY(dependente_id) REFERENCES dependente(id) ON DELETE CASCADE)";
    }
}
