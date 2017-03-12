package com.example.rafa.gestorcitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DatabaseManager {

    public static final String NOMBRE_TABLA = "clientes";

    public static final String CN_ID = "_id";
    public static final String CN_NOMBRE = "nombre";
    public static final String CN_TELEFONO = "telefono";
    public static final String CN_EMAIL = "email";

    public static final String CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA + " ( "
            + CN_ID + " integer primary key autoincrement, "
            + CN_NOMBRE + " text not null, "
            + CN_TELEFONO + " text, "
            + CN_EMAIL + " text );";

    private ClientesHelper helper;
    private SQLiteDatabase db;

    public DatabaseManager(Context contexto){
        helper = new ClientesHelper(contexto);
        db = helper.getWritableDatabase();
    }

    public long insertar (String Nombre, String Telefono, String Email){
    ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,Nombre);
        valores.put(CN_TELEFONO,Telefono);
        valores.put(CN_EMAIL,Email);

    return db.insert(NOMBRE_TABLA,null,valores);
}


    public Cursor getClientes(){
        String[] columnas = new String[]{CN_ID,CN_NOMBRE,CN_TELEFONO,CN_EMAIL};
        return db.query(NOMBRE_TABLA,columnas,null,null,null,null,null);
    }

    public Cursor getBuscaCliente(String nombre){
        String[] columnas = new String[]{CN_ID,CN_NOMBRE,CN_TELEFONO};
        return db.query(NOMBRE_TABLA,columnas,CN_NOMBRE + "=?", new String[]{nombre},null,null,null);
    }

    public void eliminarCliente(String nombre){
        db.delete(NOMBRE_TABLA,CN_NOMBRE + "=?",new String[]{nombre});
    }

}

