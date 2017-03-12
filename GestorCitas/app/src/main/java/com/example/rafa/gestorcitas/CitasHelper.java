package com.example.rafa.gestorcitas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CitasHelper extends SQLiteOpenHelper {

    public static final String BDCITAS = "dbCitas.sqlite";
    public static final int DB_VERSION = 1;

    public CitasHelper (Context context){
        super(context,BDCITAS,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatabaseManager2.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager2.NOMBRE_TABLA);
        onCreate(db);
    }
}
