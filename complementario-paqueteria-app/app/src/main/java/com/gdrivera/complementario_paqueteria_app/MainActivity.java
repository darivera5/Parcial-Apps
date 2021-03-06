package com.gdrivera.complementario_paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaración de variables widget
    private EditText zon,pes;
    private Spinner ubi,pai;
    private TextView res;

    // Declaración de objeto adaptador de arreglos
    ArrayAdapter<String> adapter,a1,a2,a3,a4,a5;

    //Arreglos de Continentes,America del sur,America centra,America norte,europa y asia.
    String []Continentes={
            "",
            "América del Norte",
            "América Central",
            "América del Sur",
            "Europa",
            "Asia"
    };

    String []AN={
            "Mexico",
            "Canada",
            "Estados unidos",
    };

    String []AC={
            "Salvador",
            "Costa rica",
            "Guatemala",
            "Nicaragua",
            "Honduras",
    };

    String []AS={
            "Colombia",
            "Chile",
            "Brasil",
            "Argentina",
            "Peru",
            "Uruguay"
    };

    String []EURO={
            "España",
            "Francia",
            "Inglaterra",
            "Italia",
            "Holanda",
            "Alemania"
    };

    String []ASIA={
            "China",
            "Japon",
            "Corea del sur",
            "Corea del norte",
    };

    // Metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaración de variables para obtener valores de los elementos.
        zon = (EditText)findViewById(R.id.zona);
        pes = (EditText)findViewById(R.id.peso);
        ubi = (Spinner) findViewById(R.id.ubicacion);
        pai = (Spinner)findViewById(R.id.pais);



        ubi.setOnItemSelectedListener(this);
        pai.setOnItemSelectedListener(this);

        //Variables asignadas para cada arreglo
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Continentes);
        a1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,AN);
        a2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,AC);
        a3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,AS);
        a4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,EURO);
        a5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,ASIA);

        ubi.setAdapter(adapter);

    }

    //Función para obtener los valores y verificar que no estén vacios
    public void getValues(View v){
        String zona  = zon.getText().toString();
        String peso = pes.getText().toString();
        int zo = Integer.parseInt(zona);

        if(zona.length() == 0 || zo >  5){
            Toast notification = Toast.makeText(this,
                    "Ingresa el número de la zona del 1 al 5",
                    Toast.LENGTH_SHORT);
            notification.show();
        }else if(peso.length() == 0){
            Toast notification = Toast.makeText(this,
                    "Ingresa el peso del paquete",
                    Toast.LENGTH_SHORT);
            notification.show();
        }else{
            int pe = Integer.parseInt(peso);
            calcular(zo, pe);
        }
    }

    //Metodo para calcular el costo del transporte al paquete
    public void calcular(int zo,int pe){

        int respuesta ;

        if(pe > 5){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this); //instancia de la alerta
            dialogBuilder.setMessage("La entrega es rechazada no se puede transportar con peso mayor de 5 kg"); //setiamos el mensaje a mostrar

            dialogBuilder.setCancelable(true).setTitle("Cotización Denegada");//Titulo de la ventana y que se pueda cancelar

            dialogBuilder.create().show(); // Mostrar Ventana
        }else{
            if(zo == 1 ){
                respuesta = 3800*pe;
            }else if(zo==2){
                respuesta = 3100*pe;
            }else if(zo==3){
                respuesta = 2900*pe;
            }else if(zo ==4){
                respuesta = 4200*pe;
            }else{
                respuesta = 5300*pe;
            }
            String valor = String.valueOf(respuesta);
            //res.setText("El valor por la entrega del paquete es de: "+valor);
            Intent i = new Intent(MainActivity.this,Respuesta.class); // Creo intent que referencie la primera y segunda ventana
            i.putExtra("ubicacion",ubi.getSelectedItem()+"");
            i.putExtra("pais",pai.getSelectedItem()+"");
            i.putExtra("costo",valor+"");// Paso el parametro nombre con el metodo putExtra
            startActivity(i);// Activo la intención que se va a enviar
        }
    }

    //Metodo para anidar los Spinner
    @Override
    public void onItemSelected(AdapterView<?> a, View v, int p, long l) {
        switch(a.getId()){
            case R.id.ubicacion:
                switch (p){
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}