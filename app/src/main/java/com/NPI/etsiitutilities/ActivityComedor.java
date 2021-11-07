package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ActivityComedor extends AppCompatActivity {

    // Elementos que necesitamos
    // --------------------------------------------------------
    private ImageView box_lunes, plato_l_1;




    // Generación de la vista
    // --------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Creación
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedor);

        // Capturamos los elementos
        box_lunes = findViewById(R.id.box_lunes);
        plato_l_1 = findViewById(R.id.plato_l_1);






        // Aplicamos eventos necesarios a nuestros elementos
        box_lunes.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount();

                if (dedos==2){
                        // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                        plato_l_1.setAlpha((float) 1);
                }else{
                    plato_l_1.setAlpha((float) 0);
                }

                return true;
            }


        });
    }



}