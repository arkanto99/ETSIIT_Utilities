package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import data.IndicacionesDbHelper;

public class MainActivity extends AppCompatActivity {

    // Base de datos. Se declara como Static para poder usarla en cualquier Activity con los datos cargados
    protected static IndicacionesDbHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new IndicacionesDbHelper(this);
        setContentView(R.layout.activity_main);

    }

    public void goComedor(View view) {
        Intent intent = new Intent(this, ActivityComedor.class);
        startActivity(intent);
    }

    public void goHorarios(View view) {
        Intent intent = new Intent(this, ActivityHorarios.class);
        startActivity(intent);
    }

    public void goMap(View view) {
        Intent intent = new Intent(this, ActivitySelectRoute.class);
        startActivity(intent);
    }

    public void goParking(View view) {
        Intent intent = new Intent(this, ActivityParking.class);
        startActivity(intent);
    }
}