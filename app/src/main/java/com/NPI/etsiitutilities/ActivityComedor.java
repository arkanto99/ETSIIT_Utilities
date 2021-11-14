package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ActivityComedor extends AppCompatActivity {

    // Elementos que necesitamos
    // --------------------------------------------------------
    private ImageView box_lunes;
    private View telon_negro, telon_negro2, telon_negro3;
    private ImageView plato_l_1, plato_l_2, plato_l_3;




    // Generación de la vista
    // --------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Creación
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedor);

        // Capturamos los elementos
        // -----------------------------------------------------------------------------------------
        telon_negro = findViewById(R.id.telon_negro);
        telon_negro2 = findViewById(R.id.telon_negro2);
        telon_negro3 = findViewById(R.id.telon_negro3);

        // Lunes
        box_lunes = findViewById(R.id.box_lunes);
        plato_l_1 = findViewById(R.id.plato_l_1);
        plato_l_2 = findViewById(R.id.plato_l_2);
        plato_l_3 = findViewById(R.id.plato_l_3);



        // Aplicamos eventos de multitouch
        // -----------------------------------------------------------------------------------------

        // Lunes
        box_lunes.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount(); // Detectamos cuantos dedos hay pulsados

                if (dedos==2){ // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_l_2.setAlpha((float) 1);
                    telon_negro2.setAlpha((float) 1);
                }else{
                    plato_l_2.setAlpha((float) 0);
                    telon_negro2.setAlpha((float) 0);
                }

                if (dedos >= 3) { // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_l_3.setAlpha((float) 1);
                    telon_negro3.setAlpha((float) 1);
                }else{
                    plato_l_3.setAlpha((float) 0);
                    telon_negro3.setAlpha((float) 0);
                }

                if(event.getAction()==MotionEvent.ACTION_DOWN){ // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                    plato_l_1.setAlpha((float) 1);
                    telon_negro.setAlpha((float) 1);
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP || dedos >= 2) {
                    plato_l_1.setAlpha((float) 0);
                    telon_negro.setAlpha((float) 0);
                    return true;
                }
                return true;
            }
        }); // Fin boton_lunes listener


    }



}