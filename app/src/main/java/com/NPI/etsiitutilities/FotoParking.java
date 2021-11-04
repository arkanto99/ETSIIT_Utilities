package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FotoParking extends AppCompatActivity {
    private TextView titulo;
    private ImageView imgParking;
    private int drawableResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_parking);

        titulo = findViewById(R.id.tituloFotoParking);
        imgParking = findViewById(R.id.imageParking);

        char opcion = getIntent().getCharExtra("opcion", 'N');

        switch (opcion) {
            case 'B':
                drawableResourceId = this.getResources().getIdentifier("pbicis", "drawable", this.getPackageName());
                break;
            case 'P':
                drawableResourceId = this.getResources().getIdentifier("ppatinetes", "drawable", this.getPackageName());
                break;
            case 'C':
                drawableResourceId = this.getResources().getIdentifier("pcoches", "drawable", this.getPackageName());
                break;
            case 'M':
                drawableResourceId = this.getResources().getIdentifier("pmotos", "drawable", this.getPackageName());
                break;
        }
        imgParking.setImageResource(drawableResourceId);
    }
}