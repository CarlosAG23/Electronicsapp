package com.example.electronicsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.view.View;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

    }
    public void insertData(String code, String name, String brand, String description, String location,String price, byte[]image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO PRODUCTS VALUES(NULL, ?, ?, ?, ?, ?, ?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,code);
        statement.bindString(2,name);
        statement.bindString(3,brand);
        statement.bindString(4,description);
        statement.bindString(5,location);
        statement.bindString(6,price);
        statement.bindBlob(7,image);
        statement.executeInsert();

    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Usuarios(id integer primary key autoincrement, Usuario text, Nombre text, Apellido text, Password text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //abrir base de datos
    public void ArbirBD()
    {
        this.getWritableDatabase();
    }

    //cerrar la base de datos
    public void CerrarBD()
    {
        this.close();
    }

    //metodo insertar registros
    public void insertarReg(String usuario, String nombre, String apellido, String password)
    {
        ContentValues valores = new ContentValues();
        valores.put("Usuario",usuario);
        valores.put("Nombre",nombre);
        valores.put("Apellido",apellido);
        valores.put("Password",password);
        this.getWritableDatabase().insert("Usuarios",null,valores);
    }

    public Cursor ConsultaUsPass(String user, String pass)
    {
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query("Usuarios", new String[]{"Usuario", "Nombre", "Apellido", "Password"},"Usuario Like'"+user+"' and Password Like '"+pass+"'",null,null,null,null);
        return mcursor;
    }
}
