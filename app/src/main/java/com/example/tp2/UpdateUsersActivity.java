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
import com.example.tp2.model.Usuario;

public class UpdateUsersActivity extends AppCompatActivity {
    EditText inputmail, inputapellido, inputnombre, inputcontrasena;
    Button btnActualizarUsuario;
    ImageView volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int id = getIntent().getIntExtra("id", 0);
        DatabaseHelper db = new DatabaseHelper(this);

        Usuario usuario = db.getUsuarioById(id);

        inputmail = findViewById(R.id.inputmail);
        inputapellido = findViewById(R.id.inputapellido);
        inputnombre = findViewById(R.id.inputnombre);
        inputcontrasena = findViewById(R.id.inputcontrasena);
        btnActualizarUsuario = findViewById(R.id.btnActualizarUsuario);
        volver = findViewById(R.id.volver);

        inputmail.setText(usuario.getEmail());
        inputapellido.setText(usuario.getApellido());
        inputnombre.setText(usuario.getNombre());

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateUsersActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

        btnActualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputnombre.getText().toString();
                String apellido = inputapellido.getText().toString();
                String mail = inputmail.getText().toString();
                String contrasena = inputcontrasena.getText().toString();

                if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(UpdateUsersActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    db.updateUsuario(usuario.getId(), nombre, apellido, mail, contrasena);
                    Intent i = new Intent(UpdateUsersActivity.this, InicioActivity.class);
                    i.putExtra("usuarioActualizado", "Usuario Actualizado exitosamente");
                    startActivity(i);
                }
            }
        });
    }
}