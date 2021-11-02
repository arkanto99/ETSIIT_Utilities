package com.NPI.etsiitutilities;

import static com.NPI.etsiitutilities.ActivitySelectRoute.RUTA_SELECCIONADA;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ActivityQRreader extends AppCompatActivity implements View.OnClickListener{

    Button scanBtn;
    TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreader);

        scanBtn = findViewById(R.id.scanBtn);
        messageText = findViewById(R.id.textContent);

        // adding listener to the button
        scanBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // we need to create the object of IntentIntegrator class which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Escanea el código QR");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                //if the intentResult is not null we'll set the content of scan message
                messageText.setText(intentResult.getContents());
                Intent intent = new Intent(this, ActivityMap.class);
                intent.putExtra(RUTA_SELECCIONADA,intentResult.getContents());
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}