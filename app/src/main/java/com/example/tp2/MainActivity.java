package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Usuario;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin, btnRegistro;
    EditText inputEmail, inputPassword;
    TextView errorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        buttonLogin = findViewById(R.id.login);
        errorLogin = findViewById(R.id.errorInputs);
        btnRegistro = findViewById(R.id.btnRegistro);



        DatabaseHelper db = new DatabaseHelper(this);
        LinkedList<Usuario>usuarios = db.selectUsuarios();



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean encontrado = false;

                for (Usuario usuario : usuarios) {
                    if (usuario.getEmail().equals(inputEmail.getText().toString()) && usuario.getPassword().equals(inputPassword.getText().toString())) {
                        Intent i = new Intent(MainActivity.this, InicioActivity.class);
                        startActivity(i);
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    errorLogin.setVisibility(View.VISIBLE);
                }
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AvisoRegistro.class);
                startActivity(i);
            }
        });
    }
}