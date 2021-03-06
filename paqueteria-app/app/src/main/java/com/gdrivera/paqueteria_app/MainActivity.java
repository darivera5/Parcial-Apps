package com.gdrivera.paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button miBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miBoton =(Button)findViewById(R.id.btnCalcular);
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,calcular_activity.class); // Creo intent que referencie la primera y segunda ventana
                startActivity(i);// Activo la intención que se va a enviar
            }
        });
    }
}