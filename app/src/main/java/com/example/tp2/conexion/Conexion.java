package com.example.tp2.conexion;

import com.google.firebase.firestore.FirebaseFirestore;

public class Conexion {

    public static FirebaseFirestore conexion(){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        return db;
    }
}
