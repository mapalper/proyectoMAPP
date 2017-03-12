package com.example.rafa.gestorcitas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ClientesHelper extends SQLiteOpenHelper{

    public static final String BDCLIENTES = "dbClientes.sqlite";
    public static final int DB_VERSION = 1;

    public ClientesHelper (Context context){
        super(context,BDCLIENTES,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.NOMBRE_TABLA);
        onCreate(db);
    }
}
