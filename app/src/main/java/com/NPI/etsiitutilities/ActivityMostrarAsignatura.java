package com.NPI.etsiitutilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.NPI.etsiitutilities.databinding.ActivityMostrarAsignaturaBinding;
import com.panoramagl.PLImage;
import com.panoramagl.PLManager;
import com.panoramagl.PLSphericalPanorama;
import com.panoramagl.structs.PLRotation;
import com.panoramagl.utils.PLUtils;

public class ActivityMostrarAsignatura extends AppCompatActivity
        implements SensorEventListener {

    // System sensor manager instance.
    private SensorManager mSensorManager;

    // Rotation vector sensor, as retrieved from the
    // sensor manager.
    private Sensor mSensorRotationVector;

    private Display mDisplay;

    private PLManager plManager;

    private ActivityMostrarAsignaturaBinding binding;
    PLSphericalPanorama panorama = new PLSphericalPanorama();

    float azimuthDegrees = 0.0f;
    float pitchDegrees = 0.0f;
    float rollDegrees = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMostrarAsignaturaBinding.inflate(this.getLayoutInflater());
        ViewGroup view = binding.getRoot();

        setContentView(view);

        // Get rotation vector sensor from the sensor manager.
        // The getDefaultSensor() method returns null if the sensor
        // is not available on the device.
        mSensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        mSensorRotationVector = mSensorManager.getDefaultSensor(
                Sensor.TYPE_GAME_ROTATION_VECTOR);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();

        plManager = new PLManager(this);
        plManager.setContentView(view);
        plManager.onCreate();
        plManager.setAccelerometerEnabled(false);
        plManager.setInertiaEnabled(false);
        plManager.setZoomEnabled(false);
        plManager.setAcceleratedTouchScrollingEnabled(false);
        PLRotation rotationInicial = new PLRotation(pitchDegrees, azimuthDegrees, rollDegrees);
        panorama.getCamera().setInitialLookAt(rotationInicial);

        panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.drawable.img_fondo_pasillo_360), false));
        plManager.setPanorama(panorama);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Listeners for the sensors are registered in this callback and
        // can be unregistered in onStop().
        //
        // Check to ensure sensors are available before registering listeners.
        // Both listeners are registered with a "normal" amount of delay
        // (SENSOR_DELAY_NORMAL).
        if (mSensorRotationVector != null) {
            mSensorManager.registerListener(this, mSensorRotationVector,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister all sensor listeners in this callback so they don't
        // continue to use resources when the app is stopped.
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        plManager.onResume();
    }

    @Override
    protected void onPause() {
        plManager.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        plManager.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return plManager.onTouchEvent(event);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] rotationMatrix = new float[9];
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(
                    rotationMatrix , sensorEvent.values);
        }

        float[] rotationMatrixAdjusted = new float[9];

        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_X,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_X, SensorManager.AXIS_Y,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_MINUS_X,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X, SensorManager.AXIS_Y,
                        rotationMatrixAdjusted);
                break;
        }

        float orientationValues[] = new float[3];
        SensorManager.getOrientation(rotationMatrixAdjusted,
                orientationValues);

        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        azimuthDegrees = (float) Math.toDegrees(azimuth);
        pitchDegrees = (float) Math.toDegrees(pitch);
        rollDegrees = (float) Math.toDegrees(roll);

        PLRotation rotation = new PLRotation(pitchDegrees, azimuthDegrees, rollDegrees);
        panorama.getCamera().lookAt(plManager, rotation);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}