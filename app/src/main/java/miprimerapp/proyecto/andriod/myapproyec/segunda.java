package miprimerapp.proyecto.andriod.myapproyec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class segunda extends SQLiteOpenHelper {


    public segunda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table agencia (codigo integer primary key unique, user text unique,marca text, año text, modelo text,color text, descripcion text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("drop table if exists usuarios");
        db.execSQL("create table agencia (codigo integer primary key unique, user text unique,marca text, año text, modelo text,color text, descripcion text) ");

    }
}

