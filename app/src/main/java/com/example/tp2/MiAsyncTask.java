package com.example.tp2;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;
import com.example.tp2.model.Usuario;

import java.util.Date;


public class MiAsyncTask extends AsyncTask<Object, Integer, String> {

    private DatabaseHelper db;
    private String operacion;
    private String tipoObjeto;
    private ProgressBar progressBar;
    TextView textView;

    public MiAsyncTask(DatabaseHelper db, String operacion, String tipoObjeto, ProgressBar progressBar) {
        this.db = db;
        this.operacion = operacion;
        this.tipoObjeto = tipoObjeto;
        this.progressBar = progressBar;
    }


    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        if (progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Object... params) {
        Object objeto = params[0];

        //Deletes
        if(operacion.equals("delete")){
            switch (tipoObjeto){
                case "Producto":
                    Producto producto = (Producto) objeto;
                    db.deleteProduct(producto.getId());
                    break;
                case "Consola":
                    Consola consola = (Consola) objeto;
                    db.deleteConsola(consola.getId());
                    break;
                case "Usuario":
                    Usuario usuario = (Usuario) objeto;
                    db.deleteUsuario(usuario.getId());
                    break;
            }
            }else if(operacion.equals("insert")){
                switch (tipoObjeto){
                    case "Producto":
                        String nombre = (String) params[0];
                        int precio = (int) params[1];
                        String descripcion = (String) params[2];
                        Date fechaSalida = (Date) params[3];
                        int stock = (int) params[4];
                        String imagen = (String) params[5];
                        Consola consolaSeleccionada = (Consola) params[6];
                        db.insertProducts(nombre, precio, descripcion, fechaSalida, stock, imagen, consolaSeleccionada);
                        break;
                    case "Usuario":
                        String nombreUsuario = (String) params[0];
                        String apellido = (String) params[1];
                        String mail = (String) params[2];
                        String contrasena = (String) params[3];
                        db.insertUsuario(nombreUsuario,apellido,mail,contrasena);
                        break;
                    case "Consola":
                        String nombreConsola = (String) params[0];
                        db.insertConsola(nombreConsola);
                        break;
                }
        } else if (operacion.equals("update")) {
            switch (tipoObjeto){
                case "Producto":
                    int id = (int) params[0];
                    String nombre = (String) params[1];
                    int precio = (int) params[2];
                    String descripcion = (String) params[3];
                    Date fechaSalida = (Date) params[4];
                    int stock = (int) params[5];
                    String imagen = (String) params[6];
                    Consola consolaSeleccionada = (Consola) params[7];
                    db.updateProduct(id, nombre, precio, descripcion, fechaSalida, stock, imagen, consolaSeleccionada);
                    break;
                case "Usuario":
                    int idUsuario = (int) params[0];
                    String nombreUsuario = (String) params[1];
                    String apellido = (String) params[2];
                    String mail = (String) params[3];
                    String contrasena = (String) params[4];
                    db.updateUsuario(idUsuario, nombreUsuario,apellido,mail,contrasena);
                    break;
                case "Consola":
                    int idConsola = (int) params[0];
                    String nombreConsola = (String) params[1];
                    db.updateConsola(idConsola, nombreConsola);
                    break;
            }
        }
        return "Operacion realizada";
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        super.onProgressUpdate();
        progressBar.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Log.d("HilosLog", "Hilo realizado correctamente");
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
