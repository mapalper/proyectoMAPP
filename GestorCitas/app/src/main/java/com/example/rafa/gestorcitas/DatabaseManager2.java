package com.example.rafa.gestorcitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseManager2 {
    public static final String NOMBRE_TABLA = "citas";

    public static final String CN_ID = "_id";
    public static final String CN_CLIENTE = "cliente";
    public static final String CN_FECHA = "Fecha";
    public static final String CN_HORAINICIO = "HoraInicio";
    public static final String CN_HORAFIN = "HoraFin";
    public static final String CN_DESCRIPCION = "Descripcion";

    public static final String CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA + " ( "
            + CN_ID + " integer primary key autoincrement, "
            + CN_CLIENTE + " text not null, "
            + CN_FECHA + " text not null, "
            + CN_HORAINICIO + " text not null, "
            + CN_HORAFIN + " tex, "
            + CN_DESCRIPCION + " text );";

    private CitasHelper helper;
    private SQLiteDatabase db;

    public DatabaseManager2(Context contexto){
        helper = new CitasHelper(contexto);
        db = helper.getWritableDatabase();
    }

    public long insertar (String Cliente, String Fecha, String HoraInicio, String HoraFin, String Descripcion){
        ContentValues valores = new ContentValues();
        valores.put(CN_CLIENTE,Cliente);
        valores.put(CN_FECHA,Fecha);
        valores.put(CN_HORAINICIO,HoraInicio);
        valores.put(CN_HORAFIN,HoraFin);
        valores.put(CN_DESCRIPCION,Descripcion);
        return db.insert(NOMBRE_TABLA,null,valores);
    }


    public Cursor getCitas(){
        String[] columnas = new String[]{CN_ID,CN_CLIENTE,CN_FECHA,CN_HORAINICIO,CN_HORAFIN,CN_DESCRIPCION};
        return db.query(NOMBRE_TABLA,columnas,null,null,null,null,null);
    }

    public Cursor getCitasdia(String fecha){
        String[] columnas = new String[]{CN_FECHA};
        return db.query(NOMBRE_TABLA,columnas,CN_FECHA + "=?", new String[]{fecha},null,null,null);
    }


    public void eliminarCitaConcreta(String Cliente, String Fecha, String HoraInicio){
        db.delete(NOMBRE_TABLA,CN_CLIENTE +","+ CN_FECHA + "," + CN_HORAINICIO + "=?",new String[]{Cliente,Fecha,HoraInicio});      // COMPROBAR !!!!!!!
    }

}
