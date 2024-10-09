package com.example.task_family;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Server extends SQLiteOpenHelper {
    private static final String DB_NAME = "task.db";
    private static final int VERSION = 1;

    private final DatabaseTable[] tables = {
            new Responsavel(),
            new Dependente(),
            new ResponsavelDependente()
    };

    public Server(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // Método para associar um dependente a um responsável
    public void addDependenteToResponsavel(int responsavelId, int dependenteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO responsavel_dependente (responsavel_id, dependente_id) VALUES (?, ?)", new Object[]{responsavelId, dependenteId});
        db.close();
    }

    // Método para remover um dependente de um responsável (opcional)
    public void removeDependenteFromResponsavel(int responsavelId, int dependenteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM responsavel_dependente WHERE responsavel_id = ? AND dependente_id = ?", new Object[]{responsavelId, dependenteId});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DatabaseTable table : tables) {
            db.execSQL(table.getCreateTableSQL());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (DatabaseTable table : tables) {
            db.execSQL("DROP TABLE IF EXISTS " + table.getTableName());
        }
        onCreate(db);
    }
}
