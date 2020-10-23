package com.juannarvaez.almacenamiento;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdministrarBaseDeDatos extends SQLiteOpenHelper {
    public AdministrarBaseDeDatos (Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super (context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE asignaturas (codigo INTEGER PRIMARY KEY, nombre_curso TEXT, " +
                "creditos INTEGER, semestre INTEGER)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    public ArrayList llenar_listView(){
        String informacion;
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        String q ="SELECT * FROM  asignaturas";
        Cursor  registro = database.rawQuery(q, null);
        if(registro.moveToFirst()){
            do {
                informacion= "Codigo:    ->   "+registro.getString(0)+"\nAsignatura \t"+registro.getString(1);
                list.add(informacion);
            }while (registro.moveToNext());
        }
        return list;
    }

}
