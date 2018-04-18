package com.example.hotcakes.tienda_mascotas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Base extends SQLiteOpenHelper {
    private static final String TABLA_ANIMALES="create table animales (idMascota integer primary key, Nombre text, Padecimiento text, Color text)";

    public Base (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_ANIMALES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
