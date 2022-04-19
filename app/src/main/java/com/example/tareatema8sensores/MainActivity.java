package com.example.tareatema8sensores;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
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

public class MainActivity extends AppCompatActivity  {

    ListView listaSensores;
    TextView datos;
    SensorManager sensorManager;
    ArrayList<String> sensoresArray = new ArrayList<String>();
    public static String nombreSensor;
    public static String valorSensor;
    Sensor sensor;
    public static int sensorType;

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
                DatosSensor.sensorType = listadoSensores.get(position).getType();
                nombreSensor = listadoSensores.get(position).getName();
                Toast.makeText(MainActivity.this, listadoSensores.get(position).getName()+ "." , Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, DatosSensor.class);
                startActivity(i);
            }
        });



        // Tipo de sensor == Sensor.TYPE....
        Log.d("Sensor id: " , "" + listadoSensores.get(1).getType());




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




}