package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;

public class UpdateConsola extends AppCompatActivity {
    ImageView volver;
    EditText inputActualizarConsola;
    Button actualizarConsola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_consola);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int id = getIntent().getIntExtra("id", 0);
        DatabaseHelper db = new DatabaseHelper(this);

        Consola consola = db.getConsolaById(id);

        inputActualizarConsola = findViewById(R.id.inputActualizarConsola);
        actualizarConsola = findViewById(R.id.actualizarConsola);
        volver = findViewById(R.id.volver);


        inputActualizarConsola.setText(consola.getNombre());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateConsola.this, InicioActivity.class);
                startActivity(i);
            }
        });

        actualizarConsola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputActualizarConsola.getText().toString();
                if (nombre.isEmpty()) {
                    Toast.makeText(UpdateConsola.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
                }else{
                    db.updateConsola(consola.getId(), nombre);
                    Intent i = new Intent(UpdateConsola.this, InicioActivity.class);
                    i.putExtra("consolaActualizada", "Usuario Actualizado exitosamente");
                    startActivity(i);
                }
            }
        });


    }
}