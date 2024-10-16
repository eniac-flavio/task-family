package com.example.task_family;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Server extends SQLiteOpenHelper {
    private static final String DB_NAME = "task.db";
    private static final int VERSION = 1;

    private static final String TAG = "Database";

    private final DatabaseTable[] tables = {
            new Responsavel(),
            new Dependente(),
            new ResponsavelDependente()
    };

    public Server(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // Método para registrar um usuário
    public boolean registrarUsuario(String email, String senha) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getWritableDatabase();

            // Verificar se o email já existe
            cursor = db.rawQuery("SELECT * FROM responsavel WHERE email = ?", new String[]{email});
            if (cursor.getCount() > 0) {
                Log.e(TAG, "Email já existe no banco de dados.");
                return false;  // Email já registrado
            }

            // Inserir o novo usuário
            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", senha);

            long result = db.insert("responsavel", null, values);
            if (result == -1) {
                Log.e(TAG, "Erro ao inserir usuário no banco.");
                return false;  // Falha na inserção
            }

            Log.i(TAG, "Usuário registrado com sucesso.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Erro durante o registro: ", e);
            return false;
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
    }

    // Criação e atualização da lista de tabelas
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
