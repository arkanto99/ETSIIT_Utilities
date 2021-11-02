package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivitySelectRoute extends AppCompatActivity {

    public static final String RUTA_SELECCIONADA = "com.NPI.etsiitutilities.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_route);
    }
}