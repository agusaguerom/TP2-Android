package com.example.tp2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;
import com.example.tp2.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

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
        db.execSQL("INSERT INTO usuario (nombre, apellido,email,password) VALUES ('Administrador', 'admin', 'admin@admin', 'admin')");


        db.execSQL("CREATE TABLE consola(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('PS4')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('PS5')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('Xbox')");
        db.execSQL("INSERT INTO consola (nombre) VALUES ('Nintendo Switch')");

        db.execSQL("CREATE TABLE producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio INT, descripcion TEXT, fecha_salida TEXT, fecha_publicacion DATE DEFAULT (CURRENT_DATE), stock INT,url_imagen TEXT, fk_consola, FOREIGN KEY(fk_consola) REFERENCES consola(id))");
        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('The Last of Us Part II', '60000', 'Una épica historia de supervivencia y venganza desarrollada por Naughty Dog.', '19-06-2020', 30,'tlou2', 2)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Ghost of Tsushima', '40000', 'Explora el Japón feudal y vive una historia de samuráis en un mundo abierto.', '17-07-2020', 25,'got', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Red Dead Redemption 2', '35000', 'Un vasto y detallado mundo abierto ambientado en el Salvaje Oeste.', '26-10-2-18', 40,'rdr2', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('The Legend of Zelda: Breath of the Wild', '55000', 'Una aventura épica en el reino de Hyrule con un enfoque en la exploración y la libertad.', '03-03-2017', 20, 'zelda_botw', 3)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Spider-Man', '45000', 'Sigue las aventuras de Peter Parker luchando contra criminales en Nueva York.', '07-09-2018', 35, 'spiderman', 2)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Horizon Zero Dawn', '50000', 'Aventura post-apocalíptica en un mundo lleno de criaturas mecánicas.', '28-02-2017', 30, 'horizon', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('FIFA 21', '35000', 'Disfruta del fútbol con licencias oficiales y nuevos modos de juego.', '09-10-2020', 50, 'fifa21', 2)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Minecraft', '30000', 'Explora, construye y sobrevive en un mundo de bloques.', '18-11-2011', 60, 'minecraft', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Assassins Creed Valhalla', '60000', 'Únete a los vikingos en una aventura llena de acción y exploración.', '10-11-2020', 25, 'ac_valhalla', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Animal Crossing: New Horizons', '50000', 'Construye tu propia isla en un relajante juego de simulación de vida.', '20-03-2020', 40, 'animal_crossing', 4)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Cyberpunk 2077', '70000', 'Explora Night City en un mundo futurista con una trama profunda.', '10-12-2020', 20, 'cyberpunk2077', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Gran Turismo 7', '45000', 'Vive la experiencia del automovilismo con gráficos realistas y pistas mundialmente conocidas.', '04-03-2022', 15, 'gt7', 1)");

        db.execSQL("INSERT INTO producto (nombre, precio, descripcion, fecha_salida, stock, url_imagen, fk_consola) VALUES ('Call of Duty: Warzone', '25000', 'Battle Royale gratuito con acción intensa y gráficos de alta calidad.', '10-03-2020', 45, 'cod_warzone', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }


    //CRUD PARA LOS PRODUCTOS
    public LinkedList<Producto> selectProducts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from producto", null);
        LinkedList<Producto> productosList = new LinkedList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date fecha_salida = null;
        Date fecha_publicacion = null;

        if(cursor.moveToFirst()){
            do{
                int id  = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int precio  = cursor.getInt(cursor.getColumnIndexOrThrow("precio"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));

                try{
                    fecha_salida = sdf.parse(cursor.getString(cursor.getColumnIndexOrThrow("fecha_salida")));
                    Date fecha_publicacionDB = formatoBD.parse(cursor.getString(cursor.getColumnIndexOrThrow("fecha_publicacion")));
                    String fecha_publicacionNuevoFormato = sdf.format(fecha_publicacionDB);
                    fecha_publicacion = sdf.parse(fecha_publicacionNuevoFormato);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String url_imagen = cursor.getString(cursor.getColumnIndexOrThrow("url_imagen"));
                int stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"));
                int consolaid = cursor.getInt(cursor.getColumnIndexOrThrow("fk_consola"));
                Consola consola = getConsolaById(consolaid);

                Producto producto = new Producto(id,nombre,precio,descripcion,fecha_salida,fecha_publicacion,stock, url_imagen, consola);
                productosList.add(producto);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return productosList;
    }

    public void insertProducts(String nombre, int precio, String descripcion, Date fecha_salida, int stock,String img_url, Consola consola){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("precio", precio);
        valores.put("descripcion", descripcion);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaSalidaStr = sdf.format(fecha_salida);
        valores.put("fecha_salida", fechaSalidaStr);
        valores.put("url_imagen", img_url);
        valores.put("stock", stock);
        valores.put("fk_consola", consola.getId());

        db.insert("producto", null, valores);
        db.close();
    }

    public void deleteProduct(int idProducto){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("producto", "id= " + idProducto, null);
        db.close();
        Log.d("DELETE PRODUCT", "Producto eliminado");
    }

    public void updateProduct(int idProducto, String nombre, int precio, String descripcion, Date fecha_salida, int stock,String url_imagen, Consola consola){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSalidaStr = sdf.format(fecha_salida);

        valores.put("nombre", nombre);
        valores.put("precio", precio);
        valores.put("descripcion", descripcion);
        valores.put("fecha_salida", fechaSalidaStr);
        valores.put("stock", stock);
        valores.put("fk_consola", consola.getId());

        db.update("producto", valores,"id = " + idProducto, null);
        db.close();
        Log.d("UPDATE PRODUCT","PRODUCTO ACTUALIZADO");
    }


    //CRUD PARA LOS USUARIOS

    public LinkedList<Usuario> selectUsuarios(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from usuario", null);
        LinkedList<Usuario> usuarioList = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                int id  = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String apellido  = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                Usuario usuario = new Usuario(id,nombre,apellido,email,password);
                usuarioList.add(usuario);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return usuarioList;
    }
    public Producto getProductById(int id){
        LinkedList<Producto> productos = selectProducts();

        for (Producto producto : productos){
            if(producto.getId() == id){
                return producto;
            }
        }
        return null;
    }


    public void insertUsuario(String nombre, String apellido, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("apellido", apellido);
        valores.put("email", email);
        valores.put("password", password);

        db.insert("usuario", null, valores);
        db.close();
    }

    public void deleteUsuario(int idUsuario){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("usuario", "id= " + idUsuario, null);
        db.close();
        Log.d("DELETE USUARIO", "USUARIO ELIMINADO");
    }

    public void updateUsuario(int idUsuario, String nombre, String apellido, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("apellido", apellido);
        valores.put("email", email);
        valores.put("password", password);

        db.update("usuario", valores, "id= "+ idUsuario, null);
        db.close();
        Log.d("UPDATE USUARIO", "updateUsuario: USUARIO ACTUALIZADO");
    }
    public Usuario getUsuarioById(int id){
        LinkedList<Usuario> usuarios = selectUsuarios();

        for (Usuario usuario : usuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }
    //CRUD PARA LAS CONSOLAS
    public LinkedList<Consola> selectConsola(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM consola" , null);
        LinkedList<Consola> listaconsolas = new LinkedList<>();

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));

                Consola consola = new Consola(id,nombre);
                listaconsolas.add(consola);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaconsolas;
    }

    public void insertConsola(String nombre){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);

        db.insert("consola", null, valores);
        db.close();
    }

    public void deleteConsola(int idConsola){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("consola", "id= " + idConsola, null);
        db.close();
        Log.d("DELETE CONSOLA", "CONSOLA ELIMINADA");
    }

    public void updateConsola(int idConsola , String nombre){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);

        db.update("consola", valores, "id= " +  idConsola, null);
        db.close();
        Log.d("UPDATE CONSOLA", "CONSOLA ACTUALIZADA");

    }

    public Consola getConsolaById(int id){
        LinkedList<Consola> consolas = selectConsola();

        for (Consola consola : consolas){
            if(consola.getId() == id){
                return consola;
            }
        }
        return null;
    }



}