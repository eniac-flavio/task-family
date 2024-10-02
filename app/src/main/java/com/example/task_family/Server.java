package com.example.task_family;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Server extends SQLiteOpenHelper {
    private static final String DB_NAME = "task.db";
    private static final int VERSION = 0;

    private DatabaseTable[] tables = {
            new Responsavel(),
    };

    public Server(Context context) {
        super(context, DB_NAME, null, VERSION);
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