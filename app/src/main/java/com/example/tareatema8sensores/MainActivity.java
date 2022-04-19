package com.example.tareatema8sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ListView listaSensores;
    SensorManager sensorManager;
    ArrayList<String> sensoresArray = new ArrayList<String>();
    String nombreSensor;
    String valorSensor;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSensores = findViewById(R.id.listview);
        listaSensores.setBackgroundColor(Color.rgb(124,157,173));
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listadoSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);


        listarSensores(listadoSensores);

        listaSensores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Toast " + listadoSensores.get(position).getName()+ " con valores: " + valorSensor, Toast.LENGTH_SHORT).show();
            }
        });


        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }


    public void listarSensores(List<Sensor> listadoSensores) {
        ArrayAdapter<String> adaptador;
        int cantidadSensores = listadoSensores.size();

        Log.w("Cantidad total de sensores: ", "" + cantidadSensores);

        for (int i = 0; i < listadoSensores.size(); i++) {
            nombreSensor = listadoSensores.get(i).getName();
            sensoresArray.add("Sensor " + i + ": " + nombreSensor.toUpperCase());
            adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sensoresArray);
            listaSensores.setAdapter(adaptador);

        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        String valoresString = "X: " + x + " | Y: " + y + " | Z: " + z;
        valorSensor = valoresString;
    }

    /**
     * El método onAccuracyChanged() es invocado cuando un sensor cambia su precisión
     * Parámetros:
     * - Sensor sensor: referencia al objeto de tipo Sensor que ha cambiado de precisión
     * - int accuracy: la nueva precisión del sensor
     */
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