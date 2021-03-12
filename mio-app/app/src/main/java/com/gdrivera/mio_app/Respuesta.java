package com.gdrivera.mio_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Respuesta extends AppCompatActivity {
    private TextView tipo,porcentaje,descuento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);

        tipo = (TextView) findViewById(R.id.tip);
        porcentaje = (TextView) findViewById(R.id.por);
        descuento = (TextView) findViewById(R.id.desc);

        String ti = getIntent().getStringExtra("tipo");
        String po = getIntent().getStringExtra("porcentaje");
        String des = getIntent().getStringExtra("descuento");

        tipo.setText(ti);
        porcentaje.setText(po+"%");
        descuento.setText("$ "+des);
    }

    public void inicio(View v){
        Intent i = new Intent(Respuesta.this,MainActivity.class); // Creo intent que referencie la primera y segunda ventana
        startActivity(i);// Activo la intención que se va a enviar
    }
}