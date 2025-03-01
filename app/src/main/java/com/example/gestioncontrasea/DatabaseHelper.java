package com.example.gestioncontrasea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="BDContraseña.db"; //Nombre base de datos
    private static final int DATABASE_VERSION = 1; //Version de Data

    //Definimos tablas y columnas
    public static final String NAME_TABLE = "contraseña";
    public static final String ID_CONTRASEÑA = "idcontraseña";
    public static final String USUARIO = "nom_usuario";
    public static final String PROVEDOR = "nom_prove";
    public static final  String CONTRASEÑA = "contra_seña";


    //crear base de datos y tablas

    private static final String CREATE_TABLE_CONTRASEÑA =
            "CREATE TABLE " + NAME_TABLE + " (" +
                    ID_CONTRASEÑA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USUARIO + " TEXT, " +
                    PROVEDOR + " TEXT, " +
                    CONTRASEÑA + " TEXT);";

    //create table medicos (idmedico int primary key autoincremental,
    //ape_medico text , nombre_medico text, tel_medico text,

    //crear DB

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_CONTRASEÑA); //crear la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        db.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE);
        onCreate(db);
    }


}










