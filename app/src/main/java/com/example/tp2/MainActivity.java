package com.example.tp2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    TextView textView, secondText;

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

        Consola ps4 = new Consola(1, "PS4");

        textView = findViewById(R.id.textView);
        secondText = findViewById(R.id.secondText);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        databaseHelper.insertProducts("Fallout 4", 25000, "Fallout 4 es un juego de rol de mundo abierto desarrollado por Bethesda Game Studios.", "10-11-2015", 10, ps4);
        LinkedList<Producto> products = databaseHelper.selectProducts();

        databaseHelper.deleteUsuario(2);
        databaseHelper.deleteConsola(5);

        databaseHelper.deleteProduct(2);
    }
}