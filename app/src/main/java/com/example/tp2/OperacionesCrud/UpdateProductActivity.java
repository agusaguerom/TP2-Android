package com.example.tp2.OperacionesCrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.InicioActivity;
import com.example.tp2.MiAsyncTask;
import com.example.tp2.R;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UpdateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText inputNombreProduct, inputPrecioProduct, inputFechaSalidaProduct, InputStockProducts;
        TextInputEditText inputDescripcionProduct;
        Button btnActualizarProducto;
        ImageView volver;
        ProgressBar progressBar;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper db = new DatabaseHelper(this);
        int id = getIntent().getIntExtra("id", 0);
        Producto producto = db.getProductById(id);

        progressBar = findViewById(R.id.progressBar);
        inputNombreProduct = findViewById(R.id.inputNombreProduct);
        inputPrecioProduct = findViewById(R.id.inputPrecioProduct);
        inputFechaSalidaProduct = findViewById(R.id.inputFechaSalidaProduct);
        InputStockProducts = findViewById(R.id.InputStockProducts);
        Spinner spinnerConsolas = findViewById(R.id.spinner1);
        inputDescripcionProduct = findViewById(R.id.InputDescripcionProduct);
        btnActualizarProducto = findViewById(R.id.btnActualizarProducto);
        volver = findViewById(R.id.volver);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaSalidaStr = sdf.format(producto.getFecha_salida());

        inputNombreProduct.setText(producto.getNombre());
        inputPrecioProduct.setText(String.valueOf(producto.getPrecio()));
        inputFechaSalidaProduct.setText(fechaSalidaStr);
        InputStockProducts.setText(String.valueOf(producto.getStock()));
        inputDescripcionProduct.setText(producto.getDescripcion());

        LinkedList<Consola> listaConsolas = db.selectConsola();

        List<String> nombresConsolas = new ArrayList<>();
        for (Consola consola : listaConsolas) {
            nombresConsolas.add(consola.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresConsolas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConsolas.setAdapter(adapter);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProductActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFechaValida(inputFechaSalidaProduct.getText().toString())) {
                    inputFechaSalidaProduct.setError("La fecha debe tener el formato dd-MM-yyyy");
                    return;
                }
                int posicionSeleccionada = spinnerConsolas.getSelectedItemPosition();
                Consola consolaSeleccionada = listaConsolas.get(posicionSeleccionada);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha_salida = null;

                String nombre = inputNombreProduct.getText().toString();
                int precio = Integer.parseInt(inputPrecioProduct.getText().toString());
                String descripcion = inputDescripcionProduct.getText().toString();
                String inputfecha = inputFechaSalidaProduct.getText().toString();
                try{
                    fecha_salida = sdf.parse(inputfecha);
                }catch (ParseException e){
                    e.printStackTrace();
                }
                int stock = Integer.parseInt(InputStockProducts.getText().toString());
                String imagen = "notimagefound";
                Date finalFecha_salida = fecha_salida;

                new MiAsyncTask(db, "update", "Producto", progressBar).execute(producto.getId(),nombre, precio, descripcion, finalFecha_salida, stock, imagen, consolaSeleccionada);

                Intent i = new Intent(UpdateProductActivity.this, InicioActivity.class);
                i.putExtra("mensajeproductoactualizado", "Producto actualizado exitosamente");
                startActivity(i);
            }
        });
    }

    private boolean isFechaValida(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Desactivar el modo permisivo
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}