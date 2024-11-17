package com.example.tp2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView productonombre , productoprecio, productodescripcion,productolanzamiento,publicacionproducto,productoStock,consolaproducto;
        ImageView productoImageView;
        Button modificarProducto, eliminarProducto;


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
        String nombre = getIntent().getStringExtra("nombre");
        int precio = getIntent().getIntExtra("precio", 0);
        String descripcion = getIntent().getStringExtra("descripcion");

        String fechasalidaStr = getIntent().getStringExtra("fecha_salida");
        String fechaPublicacionStr = getIntent().getStringExtra("fecha_publicacion");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        Date fecha_publicacion = null;
        Date fecha_salida = null;
        try {
            fecha_publicacion = sdf.parse(fechaPublicacionStr);
            fecha_salida = sdf.parse(fechasalidaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int stock = getIntent().getIntExtra("stock", 0);
        int consolaid = getIntent().getIntExtra("consola", 0);
        String imagen = getIntent().getStringExtra("imagen");

        productoImageView = findViewById(R.id.productoImageView);
        productonombre = findViewById(R.id.productonombre);
        productoprecio = findViewById(R.id.productoprecio);
        productodescripcion = findViewById(R.id.productodescripcion);
        productolanzamiento = findViewById(R.id.productolanzamiento); // Asumiendo que la vista tiene este id
        publicacionproducto = findViewById(R.id.publicacionproducto); // Asumiendo que la vista tiene este id
        productoStock = findViewById(R.id.productoStock); // Asumiendo que la vista tiene este id
        consolaproducto = findViewById(R.id.consolaproducto);
        eliminarProducto = findViewById(R.id.eliminarProducto);
        modificarProducto = findViewById(R.id.modificarProducto);


        Consola consola = db.getConsolaById(consolaid);

        int resID = getResources().getIdentifier(imagen, "drawable", getPackageName());
        productoImageView.setImageResource(resID);
        productonombre.setText(nombre);
        productoprecio.setText(String.valueOf(precio));
        productodescripcion.setText(descripcion);
        productolanzamiento.setText("Fecha de Salida: " + fecha_salida);
        publicacionproducto.setText("Fecha de publicación: " + fecha_publicacion);
        productoStock.setText("Stock Disponible: " + String.valueOf(stock));
        consolaproducto.setText("Consola : " + consola.getNombre());

        eliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDetailActivity.this, InicioActivity.class);
                db.deleteProduct(id);
                i.putExtra("mensajeproductoeliminado", "Producto eliminado exitosamente");
                startActivity(i);
            }
        });



    }
}