package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityParking extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        context = this;

        // Almacena los gestos
        GestureLibrary gesturelib = GestureLibraries.fromRawResource(this, R.raw.gestures);

        if (!gesturelib.load()) {
            Log.w("GestureActivity", "Error en la carga de Gestos");
            finish();
        }

        // Vista para gestos
        GestureOverlayView gestureview = (GestureOverlayView) findViewById(R.id.gesture_view);

        // Añade el Listener
        gestureview.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                ArrayList<Prediction> predictions = gesturelib.recognize(gesture);
                char opcion;

                Prediction predict = predictions.get(0);

                if (predict.score >= 3.0) {
                    opcion = predict.name.charAt(0);

                    Intent intent = new Intent(context, FotoParking.class);
                    intent.putExtra("opcion", opcion);
                    startActivity(intent);
                }
                // Toast.makeText(context, "score: " + predict.score, Toast.LENGTH_SHORT).show();
            }
        });
    }
}