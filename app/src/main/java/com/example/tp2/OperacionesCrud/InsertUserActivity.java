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

public class InsertUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText inputmail, inputapellido, inputnombre, inputcontrasena;
        Button btnagregarUsuario;
        ImageView volver;
        ProgressBar progressBar;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper db = new DatabaseHelper(this);

        progressBar = findViewById(R.id.progressBar);
        inputmail = findViewById(R.id.inputmail);
        inputapellido = findViewById(R.id.inputapellido);
        inputnombre = findViewById(R.id.inputnombre);
        inputcontrasena = findViewById(R.id.inputcontrasena);
        btnagregarUsuario = findViewById(R.id.agregarUsuario);
        volver = findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InsertUserActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

        btnagregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputnombre.getText().toString();
                String apellido = inputapellido.getText().toString();
                String mail = inputmail.getText().toString();
                String contrasena = inputcontrasena.getText().toString();
                if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(InsertUserActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {

                    Intent i = new Intent(InsertUserActivity.this, InicioActivity.class);
                    new MiAsyncTask(db, "insert", "Usuario", progressBar).execute(nombre, apellido, mail, contrasena);
                    i.putExtra("mensajeusuarioinsertado", "Usuario registrado exitosamente");
                    startActivity(i);
                }
            }
        });
    }
}