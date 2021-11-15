package com.NPI.etsiitutilities;


import static com.NPI.etsiitutilities.MainActivity.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import data.Indicacion;


public class ActivityMap extends AppCompatActivity implements SensorEventListener {

    private final int PHYISCAL_ACTIVITY = 0;

    //Atributos para las indicaciones
    private ArrayList<Indicacion> indicaciones;
    private TextView titleIndicacion;
    private ImageView imgMapa;
    private TextView textoIndicacion;

    private Sensor mSensorAcc;
    private SensorManager mSensorManager;

    private Podometro podometro;
    public static float mStepCounter = 0;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PHYISCAL_ACTIVITY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //////////////////////////////

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //String sensor_error = getResources().getString(R.string.error_no_sensor);

        if (mSensorAcc == null) {
            // mTextSensorPodometer.setText(sensor_error);
        } else {
            // Rate suitable for the user interface
            mSensorManager.registerListener(this, mSensorAcc, SensorManager.SENSOR_DELAY_UI);
        }

        podometro = new Podometro(mSensorAcc);

        //////////////////////////////////

        //Obtenemos las referencias a los objetos a modificar en la pantalla
        titleIndicacion = findViewById(R.id.titleIndicacion);
        imgMapa = findViewById(R.id.imageIndicacion);
        textoIndicacion = findViewById(R.id.textoIndicacion);

        //Obtenemos la ruta seleccionada por el usuario y la cargamos
        Intent intent = getIntent();
        String ruta = intent.getStringExtra(ActivitySelectRoute.RUTA_SELECCIONADA);
        inicializarIndicaciones(ruta);

    }


    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    //El objecto SensorEvent contiene información sobre el sensor y los datos que captura
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSensorChanged(SensorEvent event) {

        updateIndicacion();
        podometro.calculateSteps(event);
        mStepCounter = podometro.getStepCounter();

    }

    private void inicializarIndicaciones(String ruta){
        System.out.println("Ruta seleccionada: "+ruta);
        if(ruta.equals("Clase_Banho")) {
            System.out.println("Obteniendo ruta Clase_Banho...");
            this.indicaciones = database.obtenerRuta("Clase_Banho");
            titleIndicacion.setText("Ruta de clase al baño");
        }
        else if(ruta.equals("Banho_Clase")){
            System.out.println("Obteniendo ruta Banho_Clase...");
            this.indicaciones = database.obtenerRuta("Banho_Clase");
            titleIndicacion.setText("Ruta del baño a clase");
        }

        else if(ruta.equals("Clase_EscalerasExteriores")){
            System.out.println("Obteniendo ruta Clase_EscalerasExteriores...");
            this.indicaciones = database.obtenerRuta("Clase_EscalerasExteriores");
            titleIndicacion.setText("Ruta de clase a las escaleras");
        }

        Indicacion primeraIndicacion = this.indicaciones.get(0);
        //Cargamos la primera imagen de la ruta
        int drawableResourceId = this.getResources().getIdentifier(primeraIndicacion.getImagen(), "drawable", this.getPackageName());
        imgMapa.setImageResource(drawableResourceId);
        //Escribimos la primera indicacion
        textoIndicacion.setText(primeraIndicacion.getTextoIndicacion());
    }



    private void updateIndicacion(){

        Indicacion indicacionActual = indicaciones.get(0);

        //Si se supera el numero de pasos de la indicacion y esta no es la ultima (existe mas de una indicacion pendiente)
        if(mStepCounter > indicacionActual.getPasos() && indicaciones.size()>1){
            //Eliminamos la indicacion actual
            indicaciones.remove(0);
            //Obtenemos la nueva indicacion
            indicacionActual = indicaciones.get(0);
            //Recargamos la imagen
            int drawableResourceId = this.getResources().getIdentifier(indicacionActual.getImagen(), "drawable", this.getPackageName());
            imgMapa.setImageResource(drawableResourceId);
            //Actualizamos el texto
            textoIndicacion.setText(indicacionActual.getTextoIndicacion());
            //Reseteamos el contador de pasos
            mStepCounter = 0;
            podometro.setStepCounter(0);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}