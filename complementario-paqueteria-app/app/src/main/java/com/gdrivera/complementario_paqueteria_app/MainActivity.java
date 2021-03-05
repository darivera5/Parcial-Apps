package com.gdrivera.complementario_paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText zon,pes;
    private Spinner ubi,pai;
    private TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zon = (EditText)findViewById(R.id.zona);
        pes = (EditText)findViewById(R.id.peso);
        ubi = (Spinner) findViewById(R.id.ubicacion);
        pai = (Spinner)findViewById(R.id.pais);
        res = (TextView)findViewById(R.id.respuesta);

        //Para la ubicación
        String []opciones={
                "América del Norte",
                "América Central",
                "América del Sur",
                "Europa",
                "Asia"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                opciones
        );

        ubi.setAdapter(adapter);

    }

    public void calcular(View v){
        int zona = Integer.parseInt(zon.getText().toString());
        int peso = Integer.parseInt(pes.getText().toString());
        int respuesta ;

        if(peso > 5){
            Toast.makeText(this,"La entrega es rechazada no se puede transportar con peso mayor de 5 kg",Toast.LENGTH_SHORT);
        }else{
            if(zona == 1 ){
                respuesta = 3800*peso;
            }else if(zona==2){
                respuesta = 3100*peso;
            }else if(zona==3){
                respuesta = 2900*peso;
            }else if(zona ==4){
                respuesta = 4200*peso;
            }else{
                respuesta = 5300*peso;
            }
            String valor = String.valueOf(respuesta);
            res.setText(valor);
        }
    }
}