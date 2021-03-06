package com.gdrivera.paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class Gestionar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración del nombre y el peso
    private EditText etNombre, etPeso;
    // Declaración de los Spiner
    private Spinner ubi, pai;


    // Declaración de objeto adaptador de arreglos
    ArrayAdapter<String> adapter, a1, a2, a3, a4, a5;

    //Arreglos de Continentes,America del sur,America centra,America norte,europa y asia.
    String[] Continentes = {
            "",
            "América del Norte",
            "América Central",
            "América del Sur",
            "Europa",
            "Asia"
    };

    String[] AN = {
            "Mexico",
            "Canada",
            "Estados unidos",
    };

    String[] AC = {
            "Salvador",
            "Costa rica",
            "Guatemala",
            "Nicaragua",
            "Honduras",
    };

    String[] AS = {
            "Colombia",
            "Chile",
            "Brasil",
            "Argentina",
            "Peru",
            "Uruguay"
    };

    String[] EURO = {
            "España",
            "Francia",
            "Inglaterra",
            "Italia",
            "Holanda",
            "Alemania"
    };

    String[] ASIA = {
            "China",
            "Japon",
            "Corea del sur",
            "Corea del norte",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);

        //Declaración de variables para obtener valores de los elementos.
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPeso = (EditText) findViewById(R.id.etPeso);
        ubi = (Spinner) findViewById(R.id.spContinente);
        pai = (Spinner) findViewById(R.id.spPais);

        ubi.setOnItemSelectedListener(this);
        pai.setOnItemSelectedListener(Gestionar.this);

        //Variables asignadas para cada arreglo
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Continentes);
        a1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AN);
        a2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AC);
        a3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AS);
        a4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, EURO);
        a5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ASIA);

        ubi.setAdapter(adapter);

    }

    // Método para anidar los spinner
    @Override
    public void onItemSelected(AdapterView<?> a, View v, int p, long l) {
        switch (a.getId()) {
            case R.id.spContinente:
                switch (p) {
                    case 1:
                        pai.setAdapter(a1);
                        break;
                    case 2:
                        pai.setAdapter(a2);
                        break;
                    case 3:
                        pai.setAdapter(a3);
                        break;
                    case 4:
                        pai.setAdapter(a4);
                        break;
                    case 5:
                        pai.setAdapter(a5);
                        break;
                }
                break;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}