package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ActivityComedor extends AppCompatActivity {

    // Elementos que necesitamos
    // --------------------------------------------------------
    private ImageView box_lunes, box_martes, box_miercoles, box_jueves, box_viernes;
    private View telon_negro, telon_negro2, telon_negro3;
    private ImageView plato_l_1, plato_l_2, plato_l_3;
    private ImageView plato_m_1, plato_m_2, plato_m_3;
    private ImageView plato_x_1, plato_x_2, plato_x_3;
    private ImageView plato_j_1, plato_j_2, plato_j_3;
    private ImageView plato_v_1, plato_v_2, plato_v_3;



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
        // Martes
        box_martes = findViewById(R.id.box_martes);
        plato_m_1 = findViewById(R.id.plato_m_1);
        plato_m_2 = findViewById(R.id.plato_m_2);
        plato_m_3 = findViewById(R.id.plato_m_3);
        // Miércoles
        box_miercoles = findViewById(R.id.box_miercoles);
        plato_x_1 = findViewById(R.id.plato_x_1);
        plato_x_2 = findViewById(R.id.plato_x_2);
        plato_x_3 = findViewById(R.id.plato_x_3);
        // Jueves
        box_jueves = findViewById(R.id.box_jueves);
        plato_j_1 = findViewById(R.id.plato_j_1);
        plato_j_2 = findViewById(R.id.plato_j_2);
        plato_j_3 = findViewById(R.id.plato_j_3);
        // Viernes
        box_viernes = findViewById(R.id.box_viernes);
        plato_v_1 = findViewById(R.id.plato_v_1);
        plato_v_2 = findViewById(R.id.plato_v_2);
        plato_v_3 = findViewById(R.id.plato_v_3);



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

        // martes
        box_martes.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount(); // Detectamos cuantos dedos hay pulsados

                if (dedos==2){ // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_m_2.setAlpha((float) 1);
                    telon_negro2.setAlpha((float) 1);
                }else{
                    plato_m_2.setAlpha((float) 0);
                    telon_negro2.setAlpha((float) 0);
                }

                if (dedos >= 3) { // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_m_3.setAlpha((float) 1);
                    telon_negro3.setAlpha((float) 1);
                }else{
                    plato_m_3.setAlpha((float) 0);
                    telon_negro3.setAlpha((float) 0);
                }

                if(event.getAction()==MotionEvent.ACTION_DOWN){ // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                    plato_m_1.setAlpha((float) 1);
                    telon_negro.setAlpha((float) 1);
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP || dedos >= 2) {
                    plato_m_1.setAlpha((float) 0);
                    telon_negro.setAlpha((float) 0);
                    return true;
                }
                return true;
            }
        }); // Fin martes listener

        // miercoles
        box_miercoles.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount(); // Detectamos cuantos dedos hay pulsados

                if (dedos==2){ // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_x_2.setAlpha((float) 1);
                    telon_negro2.setAlpha((float) 1);
                }else{
                    plato_x_2.setAlpha((float) 0);
                    telon_negro2.setAlpha((float) 0);
                }

                if (dedos >= 3) { // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_x_3.setAlpha((float) 1);
                    telon_negro3.setAlpha((float) 1);
                }else{
                    plato_x_3.setAlpha((float) 0);
                    telon_negro3.setAlpha((float) 0);
                }

                if(event.getAction()==MotionEvent.ACTION_DOWN){ // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                    plato_x_1.setAlpha((float) 1);
                    telon_negro.setAlpha((float) 1);
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP || dedos >= 2) {
                    plato_x_1.setAlpha((float) 0);
                    telon_negro.setAlpha((float) 0);
                    return true;
                }
                return true;
            }
        }); // Fin miercoles listener

        // jueves
        box_jueves.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount(); // Detectamos cuantos dedos hay pulsados

                if (dedos==2){ // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_j_2.setAlpha((float) 1);
                    telon_negro2.setAlpha((float) 1);
                }else{
                    plato_j_2.setAlpha((float) 0);
                    telon_negro2.setAlpha((float) 0);
                }

                if (dedos >= 3) { // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_j_3.setAlpha((float) 1);
                    telon_negro3.setAlpha((float) 1);
                }else{
                    plato_j_3.setAlpha((float) 0);
                    telon_negro3.setAlpha((float) 0);
                }

                if(event.getAction()==MotionEvent.ACTION_DOWN){ // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                    plato_j_1.setAlpha((float) 1);
                    telon_negro.setAlpha((float) 1);
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP || dedos >= 2) {
                    plato_j_1.setAlpha((float) 0);
                    telon_negro.setAlpha((float) 0);
                    return true;
                }
                return true;
            }
        }); // Fin jueves listener

        // viernes
        box_viernes.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dedos=event.getPointerCount(); // Detectamos cuantos dedos hay pulsados

                if (dedos==2){ // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_v_2.setAlpha((float) 1);
                    telon_negro2.setAlpha((float) 1);
                }else{
                    plato_v_2.setAlpha((float) 0);
                    telon_negro2.setAlpha((float) 0);
                }

                if (dedos >= 3) { // Si se ha pulsado con 2 dedos, se mostrará el segundo plato
                    plato_v_3.setAlpha((float) 1);
                    telon_negro3.setAlpha((float) 1);
                }else{
                    plato_v_3.setAlpha((float) 0);
                    telon_negro3.setAlpha((float) 0);
                }

                if(event.getAction()==MotionEvent.ACTION_DOWN){ // Si se ha pulsado con un solo dedo, se mostrará el primer plato
                    plato_v_1.setAlpha((float) 1);
                    telon_negro.setAlpha((float) 1);
                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP || dedos >= 2) {
                    plato_v_1.setAlpha((float) 0);
                    telon_negro.setAlpha((float) 0);
                    return true;
                }
                return true;
            }
        }); // Fin viernes listener


    }



}