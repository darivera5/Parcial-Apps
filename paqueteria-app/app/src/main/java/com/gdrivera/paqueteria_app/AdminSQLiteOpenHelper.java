package com.gdrivera.paqueteria_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table paquetes(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_producto text, peso real, continente text, pais text, costo real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    }

}
