package com.gdrivera.complementario_paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Respuesta extends AppCompatActivity {
    private TextView ubi,pai,cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);


        ubi = (TextView) findViewById(R.id.ubicacion);
        pai = (TextView) findViewById(R.id.pais);
        cost = (TextView) findViewById(R.id.costo);



        String cos = getIntent().getStringExtra("costo");
        String zo = getIntent().getStringExtra("ubicacion");
        String pa = getIntent().getStringExtra("pais");


        ubi.setText(zo);
        cost.setText(cos);
        pai.setText(pa);
    }

    public void inicio(View v){
        Intent i = new Intent(Respuesta.this,MainActivity.class); // Creo intent que referencie la primera y segunda ventana
        startActivity(i);// Activo la intención que se va a enviar
    }
}