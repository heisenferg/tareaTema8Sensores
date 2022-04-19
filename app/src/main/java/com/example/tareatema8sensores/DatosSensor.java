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
    int variables;
    String medidas="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensor);


        datos = findViewById(R.id.textViewDatosInfo);
      //  datos.setText("El sensor " + MainActivity.nombreSensor + " ofrece estos datos:\n"+ valorSensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Log.d("Snsor tipe:" , ""+sensorType);
        Log.d("Snsor tipe:" , ""+valorSensor);


        for (int i = 1; i<MAXIMOSENSORES; i++){
            if (sensorType==i){
                sensor = sensorManager.getDefaultSensor(i);

                if (sensorType == 1 || sensorType==4 || sensorType==9 || sensorType==2 || sensorType==10 || sensorType==11 || sensorType==14 || sensorType==15 || sensorType==16){
                    variables  = 3;
                }else{
                    variables =1;
                }
                if (sensorType == 13 || sensorType == 7){
                    medidas = " ºC";
                }
                if (sensorType == 9 || sensorType == 1 || sensorType == 10 || sensorType == 35){
                    medidas = " m/s2";
                }
                if (sensorType == 6){
                    medidas = " milibares";
                }
                if (sensorType == 2){
                    medidas = " microTeslas";
                }
                if (sensorType == 8){
                    medidas = " centímetros.";
                }
                if (sensorType == 12){
                    medidas = " %.";
                }
                if (sensorType == 19){
                    medidas = " pasos.";
                }
            }
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Sensores con 3 variables
        if (variables == 3) {
            float x,y,z;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            valorSensor = "X: " + x + " | Y: " + y + " | Z: " + z + medidas;
            //sensorManager.getSensorList(Sensor.TEM)
            //
        } if (variables == 1){
            float a;
            a = event.values[0];

            valorSensor ="Valor: " +a + medidas;
        }


        datos.setText("El sensor " + MainActivity.nombreSensor + " ofrece estos datos:\n"+ valorSensor);

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