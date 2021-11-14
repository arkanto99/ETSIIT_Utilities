package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityFotoParking extends AppCompatActivity {
    private TextView titulo, texto;
    private ImageView imgParking;
    private int drawableResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_parking);

        titulo = findViewById(R.id.tituloFotoParking);
        texto = findViewById(R.id.textoFotoParking);
        imgParking = findViewById(R.id.imageParking);

        char opcion = getIntent().getCharExtra("opcion", 'N');

        switch (opcion) {
            case 'B':
                titulo.setText("Parking Bicicletas");
                texto.setText("0 Plazas libres");
                texto.setTextColor(Color.RED);
                drawableResourceId = this.getResources().getIdentifier("pbicis", "drawable", this.getPackageName());
                break;
            case 'P':
                titulo.setText("Parking Patinetes");
                texto.setText("9 Plazas libres");
                texto.setTextColor(Color.GREEN);
                drawableResourceId = this.getResources().getIdentifier("ppatinetes", "drawable", this.getPackageName());
                break;
            case 'C':
                titulo.setText("Parking Coches");
                texto.setText("2 Plazas libres");
                texto.setTextColor(Color.GREEN);
                drawableResourceId = this.getResources().getIdentifier("pcoches", "drawable", this.getPackageName());
                break;
            case 'M':
                titulo.setText("Parking Motos");
                texto.setText("15 Plazas libres");
                texto.setTextColor(Color.GREEN);
                drawableResourceId = this.getResources().getIdentifier("pmotos", "drawable", this.getPackageName());
                break;
        }
        imgParking.setImageResource(drawableResourceId);
    }
}