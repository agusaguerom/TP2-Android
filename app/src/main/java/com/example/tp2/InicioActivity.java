package com.example.tp2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp2.Fragments.ProductsFragment;
import com.example.tp2.Fragments.UsersFragment;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class InicioActivity extends AppCompatActivity {
    TextView textView, secondText;
    TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String insercioncorrecta = getIntent().getStringExtra("mensajeproductoinsertado");
        String productoeliminado = getIntent().getStringExtra("mensajeproductoeliminado");
        if (insercioncorrecta != null){
            Toast.makeText(this, insercioncorrecta, Toast.LENGTH_SHORT).show();
        }else if(productoeliminado != null){
            Toast.makeText(this, productoeliminado, Toast.LENGTH_SHORT).show();

        }

        if (savedInstanceState == null) {
            Fragment fragmentSeleccionado = new ProductsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragmentSeleccionado);
            fragmentTransaction.commit();
        }

        Consola ps4 = new Consola(1, "PS4");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        tablayout = findViewById(R.id.tablayout);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragmentSeleccionado= null;
                switch (tab.getPosition()) {

                    case 0:
                        fragmentSeleccionado= new ProductsFragment();
                        Log.d("POSICIONES", "onTabSelected: SELECCIONO PRIMER TAB");
                        break;

                    case 1:
                        fragmentSeleccionado = new UsersFragment();
                        Log.d("POSICIONES", "onTabSelected: SELECCIONO SEGUNDO TAB");
                        break;
                }
                if(fragmentSeleccionado != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragmentSeleccionado);
                    fragmentTransaction.commit();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}