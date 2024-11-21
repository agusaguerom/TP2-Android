package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.OperacionesCrud.UpdateProductActivity;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView productonombre , productoprecio, productodescripcion,productolanzamiento,publicacionproducto,productoStock,consolaproducto;
        ImageView productoImageView, volver;
        Button modificarProducto, eliminarProducto;
        ProgressBar progressBar;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper db = new DatabaseHelper(this);
        int id = getIntent().getIntExtra("id", 0);
        Producto producto = db.getProductById(id);

        String nombre = producto.getNombre();
        int precio = producto.getPrecio();
        String descripcion = producto.getDescripcion();
        Date fecha_salida = producto.getFecha_salida();
        Date fecha_publicacion = producto.getFecha_publicacion();
        int stock = producto.getStock();
        Consola consola = producto.getConsola();
        String imagen =producto.getUrl_imagen();

        progressBar = findViewById(R.id.progressBar);
        productoImageView = findViewById(R.id.productoImageView);
        productonombre = findViewById(R.id.productonombre);
        productoprecio = findViewById(R.id.productoprecio);
        productodescripcion = findViewById(R.id.productodescripcion);
        productolanzamiento = findViewById(R.id.productolanzamiento);
        publicacionproducto = findViewById(R.id.publicacionproducto);
        productoStock = findViewById(R.id.productoStock);
        consolaproducto = findViewById(R.id.consolaproducto);
        eliminarProducto = findViewById(R.id.eliminarProducto);
        modificarProducto = findViewById(R.id.modificarProducto);
        volver = findViewById(R.id.volver);


        int resID = getResources().getIdentifier(imagen, "drawable", getPackageName());
        productoImageView.setImageResource(resID);
        productonombre.setText(nombre);
        productoprecio.setText("$" + String.valueOf(precio));
        productodescripcion.setText(descripcion);

        SimpleDateFormat formatousa = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        String fechaSalidaStr = sdf.format(fecha_salida);
        String fechaPublicacionStr = sdf.format(fecha_publicacion);

        productolanzamiento.setText("Fecha de Lanzamiento: " + fechaSalidaStr);
        publicacionproducto.setText("Fecha de Publicacion del Producto: " + fechaPublicacionStr);
        productoStock.setText("Stock Disponible: " + String.valueOf(stock));
        consolaproducto.setText("Consola : " + consola.getNombre());

        eliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDetailActivity.this, InicioActivity.class);
                new MiAsyncTask(db, "delete", "Producto", progressBar).execute(producto);
                i.putExtra("mensajeproductoeliminado", "Producto eliminado exitosamente");
                startActivity(i);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDetailActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

        modificarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDetailActivity.this, UpdateProductActivity.class);
                i.putExtra("id", producto.getId());
                startActivity(i);
            }
        });



    }
}