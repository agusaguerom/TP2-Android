package com.example.tp2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp2.conexion.Conexion;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;

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

        TextView titulo = findViewById(R.id.titulo);

        FirebaseFirestore db = Conexion.conexion();

        CollectionReference products = db.collection("products");
        Query query = products.whereEqualTo("titulo", "The Last of Us");

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    for (DocumentSnapshot document : querySnapshot) {
                        String tituloproduct = document.getString("titulo");
                        double precio = document.getDouble("precio");
                        String descripcion = document.getString("descripcion");
                        String fechaSalida = document.getString("fecha_salida");
                        String imagenUrl = document.getString("imagen");

                        String textoTitulo = getString(R.string.producto_titulo, tituloproduct);
                        String textoPrecio = getString(R.string.producto_precio, precio);
                        // Aquí ya puedes usar el objeto "producto", pero en el log se utiliza "titulo"
                        titulo.setText(textoTitulo + ", " + textoPrecio);                  }
                }
            }
        });

    }
}