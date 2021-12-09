package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.io.IOException;

public class ActivityHorarios extends AppCompatActivity {

    private CameraSource mCameraSource;
    private SurfaceView mCameraView;
    private TextView mTextoAulaDetectada;

    private boolean aulaEncontrada = false;
    private static final int CAMERA_REQUEST = 1888;

    public static final String AULA = "com.NPI.etsiitutilities.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        mCameraView = findViewById(R.id.camara_source);
        mTextoAulaDetectada = findViewById(R.id.texto_aula_detectada);

        detectarTexto();
    }

    @Override
    public void onResume(){
        super.onResume();

        aulaEncontrada = false;
        detectarTexto();
    }


    private void detectarTexto() {
        final com.google.android.gms.vision.text.TextRecognizer textRecognizer = new com.google.android.gms.vision.text.TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w("txtRcgnzr", "Aun no esta listo el detector");

        } else {
            // creo la camara
            mCameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(2.0f)
                    .build();

            // listener de ciclo de vida de la camara
            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        // verifico si el usuario dio los permisos para la camara
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ActivityHorarios.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    CAMERA_REQUEST);
                            return;
                        }
                        mCameraSource.start(mCameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    mCameraSource.stop();
                }
            });
        }

        // Procesamiento del texto
        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0 ){
                    mTextoAulaDetectada.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();

                            for(int i = 0; i<items.size() && !aulaEncontrada; i++){
                                if (items.valueAt(i).getValue().toUpperCase().equals("AULA 3.3")) {
                                    TextBlock itemAula = items.valueAt(i);
                                    stringBuilder.append(itemAula.getValue());
                                    aulaEncontrada = true;

                                    Intent intent = new Intent(ActivityHorarios.this, ActivityMostrarAsignatura.class);
                                    intent.putExtra(AULA, "Aula 3.3");
                                    startActivity(intent);
                                } else if (items.valueAt(i).getValue().toUpperCase().equals("AULA 1.5")) {
                                    TextBlock itemAula = items.valueAt(i);
                                    stringBuilder.append(itemAula.getValue());
                                    aulaEncontrada = true;

                                    Intent intent = new Intent(ActivityHorarios.this, ActivityMostrarAsignatura.class);
                                    intent.putExtra(AULA, "Aula 1.5");
                                    startActivity(intent);
                                }
                            }

                            mTextoAulaDetectada.setText(stringBuilder.toString());
                        }
                    });
                }
            }
        });
    }
}