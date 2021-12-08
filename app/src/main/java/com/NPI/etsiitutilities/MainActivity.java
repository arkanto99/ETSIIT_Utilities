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


// Imports de speech-to-text -------------------------------------
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
// ----------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    // Base de datos. Se declara como Static para poder usarla en cualquier Activity con los datos cargados
    protected static IndicacionesDbHelper database;
    private static final int CAMERA_REQUEST = 1888;

    private SensorManager sm;
    private ShakeDetector sd;


    // Variables para el speech-to-text
    private static final int REQUEST_CODE = 1234;
    Button speakButton;

    public static final String RUTA = "com.NPI.etsiitutilities.MESSAGE";

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


        // Elementos de speech-to-text -------------------------------------------------------------
        speakButton = (Button) findViewById(R.id.speakButton);

        // Deshabilitamos el botón si el servicio de reconocimiento de voz no está disponible.
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            //speakButton.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Recognizer Not Found",
                    1000).show();
        }

        // Si se está disponible, lo activamos en la pulsación del botón.
        speakButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognitionActivity();
            }
        });
        // -----------------------------------------------------------------------------------------
    }

    // Métodos para Speech-to-text -----------------------------------------------------------------

    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "¿A que pantalla quieres navegar?");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


            // resultList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, matches));


            if ( matches.get(0).contains("mapa") ) {
                startActivity(new Intent(this, ActivitySelectRoute.class));
            } else if ( matches.get(0).contains("aula") ){
                Intent intent = new Intent(this, ActivityMap.class);
                intent.putExtra(RUTA, "Banho_Clase");
                startActivity(intent);
            }else if ( matches.get(0).contains("baño") ){
                Intent intent = new Intent(this, ActivityMap.class);
                intent.putExtra(RUTA, "Clase_Banho");
                startActivity(intent);
            }else if ( matches.get(0).contains("escaleras") ){
                Intent intent = new Intent(this, ActivityMap.class);
                intent.putExtra(RUTA, "Clase_EscalerasExteriores");
                startActivity(intent);
            }else if ( matches.get(0).contains("horario") ){
                startActivity(new Intent(this, ActivityHorarios.class));
            } else if ( matches.get(0).contains("parking") ){
                startActivity(new Intent(this, ActivityParking.class));
            } else if ( matches.get(0).contains("comedor") ){
                startActivity(new Intent(this, ActivityComedor.class));
            } else{
                Toast.makeText(getApplicationContext(), "No hay ninguna pantalla " +
                "relacionada con: " + matches.get(0) + " - Vuelve a intentarlo",1000).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // ---------------------------------------------------------------------------------------------



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
    public void goTest(View view) {
        Intent intent = new Intent(this, ActivityTest.class);
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