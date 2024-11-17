package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class InsertProductActivity extends AppCompatActivity {
    EditText inputNombreProduct, inputPrecioProduct, inputFechaSalidaProduct, InputStockProducts;
    TextInputEditText inputDescripcionProduct;
    Button btnAgregarProducto;
    Spinner spinnerConsolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper db = new DatabaseHelper(this);

        inputNombreProduct = findViewById(R.id.inputNombreProduct);
        inputPrecioProduct = findViewById(R.id.inputPrecioProduct);
        inputFechaSalidaProduct = findViewById(R.id.inputFechaSalidaProduct);
        InputStockProducts = findViewById(R.id.InputStockProducts);
        Spinner spinnerConsolas = findViewById(R.id.spinner1);
        inputDescripcionProduct = findViewById(R.id.InputDescripcionProduct);
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);

        LinkedList<Consola> listaConsolas = db.selectConsola();

        List<String> nombresConsolas = new ArrayList<>();
        for (Consola consola : listaConsolas) {
            nombresConsolas.add(consola.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresConsolas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConsolas.setAdapter(adapter);





        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
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

                db.insertProducts(nombre,precio,descripcion, finalFecha_salida,stock,imagen, consolaSeleccionada);

                Intent i = new Intent(InsertProductActivity.this, InicioActivity.class);
                i.putExtra("mensajeproductoinsertado", "Producto agregado exitosamente");
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