package com.example.tp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tp2.model.Producto;

import java.util.LinkedList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tienda.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT,apellido TEXT, email TEXT, password TEXT);");
        db.execSQL("INSERT INTO usuario (nombre, apellido,email,password) VALUES ('Jose', 'Perez', 'josep@gmail.com', 'cont123')");

        db.execSQL("CREATE TABLE consola(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('PS4')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('PS5')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('Xbox')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('Nintendo Switch')");

        db.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio TEXT, descripcion TEXT, fecha_salida TEXT, fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, stock INT, fk_consola, FOREIGN KEY(fk_consola) REFERENCES consola(id))");
        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, fk_consola) VALUES ('The Last of Us Part II', '60000', 'Una épica historia de supervivencia y venganza desarrollada por Naughty Dog.', '19-06-2020', 30, 2)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, fk_consola) VALUES ('Ghost of Tsushima', '40000', 'Explora el Japón feudal y vive una historia de samuráis en un mundo abierto.', '17-07-2020', 25, 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, fk_consola) VALUES ('Red Dead Redemption 2', '35000', 'Un vasto y detallado mundo abierto ambientado en el Salvaje Oeste.', '26-10-2-18', 40, 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, fk_consola) VALUES ('The Legend of Zelda: Breath of the Wild', '55000', 'Una aventura épica en el reino de Hyrule con un enfoque en la exploración y la libertad.', '03-03-2017', 20, 3)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }

    public void insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", "Jose");
        valores.put("email", "jose@gmail.com");

        db.insert("usuario", null, valores);
        db.close();
    }

    public LinkedList<Producto> selectProducts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from producto", null);
        LinkedList<Producto> productosList = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                int id  = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int precio  = cursor.getInt(2);
                String descripcion = cursor.getString(3);
                String fecha_salida = cursor.getString(4);
                int stock = cursor.getInt(5);

                Producto producto = new Producto(id,nombre,precio,descripcion,fecha_salida,"2020",stock);
                productosList.add(producto);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return productosList;
    }


}