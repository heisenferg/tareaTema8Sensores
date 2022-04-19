package com.example.tareatema8sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listaSensores;
    SensorManager sensorManager;
    ArrayList<String> sensoresArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSensores = findViewById(R.id.listview);
        listaSensores.setBackgroundColor(Color.GRAY);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listadoSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listarSensores(listadoSensores);
    }


    public void listarSensores(List<Sensor> listadoSensores) {
        ArrayAdapter<String> adaptador;
        int cantidadSensores = listadoSensores.size();

        Log.w("Cantidad total de sensores: ", "" + cantidadSensores);

        for (int i = 0; i < listadoSensores.size(); i++) {
            String nombreSensor = listadoSensores.get(i).getName();
            sensoresArray.add("Sensor " + i + ": " + nombreSensor);
            adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sensoresArray);
            listaSensores.setAdapter(adaptador);

        }
    }
}