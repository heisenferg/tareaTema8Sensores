package com.example.tareatema8sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DatosSensor extends AppCompatActivity implements SensorEventListener {

    TextView datos;
    public static String nombreSensor;
    String valorSensor;
    public static int sensorType;
    SensorManager sensorManager;
    Sensor sensor;
    static final int MAXIMOSENSORES = 36;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensor);


        datos = findViewById(R.id.textViewDatosInfo);
      //  datos.setText("El sensor " + MainActivity.nombreSensor + " ofrece estos datos:\n"+ valorSensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Log.d("Snsor tipe:" , ""+sensorType);
        Log.d("Snsor tipe:" , ""+valorSensor);

     /*   if (sensorType==1){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensorType==2){

        }*/
        for (int i = 1; i<MAXIMOSENSORES; i++){
            if (sensorType==i){
                sensor = sensorManager.getDefaultSensor(i);
            }
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Sensores con 3 variables
        if (sensorType == 1 || sensorType==Sensor.TYPE_MAGNETIC_FIELD) {
            float x,y,z;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            valorSensor = "X: " + x + " | Y: " + y + " | Z: " + z;
          //  sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)
            //
        } if (sensorType==Sensor.TYPE_ACCELEROMETER_UNCALIBRATED){
            valorSensor ="HOLA";
        }
        datos.setText("El sensor " + MainActivity.nombreSensor + " ofrece estos datos:\n"+ valorSensor);

        //settext con un textview nuevo con valorsensor
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}