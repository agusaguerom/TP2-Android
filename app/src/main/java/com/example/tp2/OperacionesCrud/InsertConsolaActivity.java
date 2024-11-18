package com.example.tp2.OperacionesCrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.InicioActivity;
import com.example.tp2.MiAsyncTask;
import com.example.tp2.R;
import com.example.tp2.db.DatabaseHelper;

public class InsertConsolaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button agregarConsola;
        ImageView volver;
        EditText nuevaconsola;
        ProgressBar progressBar;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_consola);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DatabaseHelper db = new DatabaseHelper(this);

        progressBar = findViewById(R.id.progressBar);
        agregarConsola = findViewById(R.id.agregarConsola);
        volver = findViewById(R.id.volver);
        nuevaconsola = findViewById(R.id.nuevaconsola);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InsertConsolaActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

        agregarConsola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nuevaconsola.getText().toString();
                if (nombre.isEmpty()) {
                    Toast.makeText(InsertConsolaActivity.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
                }else{
                    new MiAsyncTask(db, "insert", "Consola", progressBar).execute(nombre);
                    Intent i = new Intent(InsertConsolaActivity.this, InicioActivity.class);
                    i.putExtra("consolaIngresada", "Consola Ingresada exitosamente");
                    startActivity(i);
                }
            }
        });

    }
}