package com.gdrivera.paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnGestion;
    private Button miBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciar la actividad gestión.
        btnGestion = (Button)findViewById(R.id.btnGestionar);
        btnGestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Gestionar.class);
                startActivity(i);
            }
        });

        miBoton =(Button)findViewById(R.id.btnCalcular);
        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Creo intent que referencie la primera y segunda ventana
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,calcular_activity.class);
                startActivity(i);// Activo la intención que se va a enviar
            }
        });
    }

}