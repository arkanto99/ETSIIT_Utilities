package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivitySelectRoute extends AppCompatActivity {

    public static final String RUTA_SELECCIONADA = "com.NPI.etsiitutilities.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_route);
    }

    public void elegirRuta(View view) {
        Intent intent = new Intent(this, ActivityMap.class);

        switch (view.getId()) {
            case R.id.banho:
                intent.putExtra(RUTA_SELECCIONADA, "Clase_Banho");
                startActivity(intent);
                break;
            case R.id.aula_3_3:
                intent.putExtra(RUTA_SELECCIONADA, "Banho_Clase");
                startActivity(intent);
                break;
            case R.id.escaleras_exteriores:
                intent.putExtra(RUTA_SELECCIONADA, "Clase_EscalerasExteriores");
                startActivity(intent);
                break;
            default:
                System.out.println("ERROR: Ruta no seleccionada correctamente");
        }

    }
}