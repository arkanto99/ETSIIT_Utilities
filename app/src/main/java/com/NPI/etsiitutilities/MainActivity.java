package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tyagiabhinav.dialogflowchatlibrary.Chatbot;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotActivity;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotSettings;
import com.tyagiabhinav.dialogflowchatlibrary.DialogflowCredentials;

import java.util.UUID;

import data.IndicacionesDbHelper;

public class MainActivity extends AppCompatActivity {

    // Base de datos. Se declara como Static para poder usarla en cualquier Activity con los datos cargados
    protected static IndicacionesDbHelper database;
    private static final int CAMERA_REQUEST = 1888;

    private SensorManager sm;
    private ShakeDetector sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new IndicacionesDbHelper(this);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST);
            return;
        }

        // Detectar agitación
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(() -> {
            Intent intent = new Intent(this, ActivityTouchParking.class);
            startActivity(intent);
        });
        sd.start(sm);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sd.start(sm);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sd.stop();
    }

    public void goComedor(View view) {
        Intent intent = new Intent(this, ActivityComedor.class);
        startActivity(intent);
    }

    public void goHorarios(View view) {
        Intent intent = new Intent(this, ActivityHorarios.class);
        startActivity(intent);
    }

    public void goMap(View view) {
        Intent intent = new Intent(this, ActivitySelectRoute.class);
        startActivity(intent);
    }

    public void goParking(View view) {
        Intent intent = new Intent(this, ActivityParking.class);
        startActivity(intent);
    }

    public void openChatbot(View view) {
        // provide your Dialogflow's Google Credential JSON saved under RAW folder in resources
        DialogflowCredentials.getInstance().setInputStream(getResources().openRawResource(R.raw.etsiit_utilities_chatbot));

        ChatbotSettings.getInstance().setChatbot( new Chatbot.ChatbotBuilder()
                .setDoAutoWelcome(false)
                .setChatBotAvatar(getDrawable(R.drawable.pajaro))
                .setShowMic(true).build());
        Intent intent = new Intent(this, ChatbotActivity.class);
        Bundle bundle = new Bundle();

        // provide a UUID for your session with the Dialogflow agent
        bundle.putString(ChatbotActivity.SESSION_ID, UUID.randomUUID().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}