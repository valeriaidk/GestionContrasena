package com.example.gestioncontrasea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Contraseña {

    //agregamos clases del helper
    private SQLiteDatabase db;
    private DatabaseHelper dbhelper;

    public Contraseña(Context context){
        dbhelper = new DatabaseHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    //GENERAMOS EL PROCESO CRUD.

    //insertar medico
    public long insertarContraseña(String usuario, String provedor, String contraseña){
        ContentValues contenido = new ContentValues();
        contenido.put(DatabaseHelper.USUARIO, usuario);
        contenido.put(DatabaseHelper.PROVEDOR, provedor);
        contenido.put(DatabaseHelper.CONTRASEÑA,contraseña);
        return db.insert(DatabaseHelper.NAME_TABLE, null, contenido);
    }

    //listar medicos
    public List<String> obtenerContraseña(){
        List<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.NAME_TABLE, null);

        while(cursor.moveToNext()){
            String contraseña = cursor.getInt(0) + " - " +
                    cursor.getString(1) + " - " +
                    cursor.getString(2) +  " - " +
                    cursor.getString(3);
            lista.add(contraseña);
        }
        cursor.close();
        return lista;

    }
    //editarmedico
    public int actualizarContraseña(int id, String usuario, String provedor, String contraseña){
        ContentValues contenido = new ContentValues();
        contenido.put(DatabaseHelper.USUARIO,usuario);
        contenido.put(DatabaseHelper.PROVEDOR,provedor);
        contenido.put(DatabaseHelper.CONTRASEÑA,contraseña);
        return db.update(DatabaseHelper.NAME_TABLE,contenido,"idcontraseña=?",new String[]{String.valueOf(id)});
    }
    //eliminar medico
    public int eliminarContraseña(int id){
        return db.delete(DatabaseHelper.NAME_TABLE,"idcontraseña=?",new String[]{String.valueOf(id)});
    }
}
