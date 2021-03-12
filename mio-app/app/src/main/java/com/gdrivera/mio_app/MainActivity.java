package com.gdrivera.mio_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    // Declaración global de los editext
    private EditText etPrecioTiquete, etEdad;
    // Declaración del radio button
    private RadioButton rdEstudiante, rdFamilia;
    //Declaración del TextView donde se realiza la devolución de la respuesta
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtener valores de los elementos
        etPrecioTiquete = (EditText) findViewById(R.id.etPrecio);
        etEdad = (EditText) findViewById(R.id.etEdad);
        rdEstudiante = (RadioButton) findViewById(R.id.rdEstudiante);
        rdFamilia = (RadioButton) findViewById(R.id.rdFamilia);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
    }

    //Función para obtener los valores y verificar que no estén vacios
    public void getValues(View v){
        String precioTiqueteString = etPrecioTiquete.getText().toString();
        String edadString = etEdad.getText().toString();
        Boolean estudiante = rdEstudiante.isChecked();
        Boolean familia = rdFamilia.isChecked();


        if(precioTiqueteString.length() == 0){
            Toast notification = Toast.makeText(this,
                    "Ingresa el número del tiquete",
                    Toast.LENGTH_SHORT);
            notification.show();
        }else if(edadString.length() == 0){
            Toast notification = Toast.makeText(this,
                    "Ingresa una edad",
                    Toast.LENGTH_SHORT);
            notification.show();
        }else{
            double precio = Double.parseDouble(precioTiqueteString);
            int edad = Integer.parseInt(edadString);
            obtenerDescuento(precio, edad, estudiante, familia);
        }
    }

    //Funcion que realiza las operaciones matematicas para establecer el valor del descuento
    // ¿Si el descuento no es acumulativo, quiere decir que solo debe cumplir una condición?

    public void obtenerDescuento(Double precioTiquete, int edad, boolean estudiante, boolean familia){
        Double DESCUENTO40 = 0.4;
        Double DESCUENTO70 = 0.7;

        if(estudiante){
            // 40% de descuesto
            Double resultado = precioTiquete * DESCUENTO40;
            respuesta(40, resultado, " Por ser estudiante aplicas para un descuento");

        }else if(familia){
            // Familia númerosa 70% de descuento
            Double resultado = precioTiquete * DESCUENTO70;
            respuesta(70, resultado, " Por tener familia numerosa aplicas para un descuento");

        } else if(edad < 4 ){
            // Es gratis
            //tvResultado.setText("Menor de 4 años va gratis");
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this); //instancia de la alerta
            dialogBuilder.setMessage("Menor de 4 años va gratis"); //setiamos el mensaje a mostrar

            dialogBuilder.setCancelable(true).setTitle("Gratis");//Titulo de la ventana y que se pueda cancelar

            dialogBuilder.create().show(); // Mostrar Ventana

        }else if(edad >= 4 && edad <= 7){
            // 40% de descuento
            Double resultado = precioTiquete * DESCUENTO40;
            respuesta(40, resultado, " Por tener edad entre 4 y 7 años aplicas para un descuento");

        }else if(edad > 65){
            // 40% de descuento
            Double resultado = precioTiquete * DESCUENTO40;
            respuesta(40, resultado, "Por tener edad mayor de 65 aplicas par aun descuento");
        }else{
            // No aplica descuento
            String mensaje = "No aplicas para el descuento, precio del tiquete: $" + precioTiquete;
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this); //instancia de la alerta
            dialogBuilder.setMessage(mensaje); //setiamos el mensaje a mostrar

            dialogBuilder.setCancelable(true).setTitle("Cotización denegada");//Titulo de la ventana y que se pueda cancelar

            dialogBuilder.create().show(); // Mostrar Ventana

            //tvResultado.setText(mensaje);
        }
    }

    // Metodo que construye una respuesta basandose en las entradas
    public void respuesta(int porcentaje, Double descuento, String tipo){
        String mensaje;
       // mensaje = "por " + tipo + " se ha aplicado un descuento del " + porcentaje + "%" +
                //" tu tiquete te cuesta: $" + descuento;
        String porcen = String.valueOf(porcentaje);
        String descuent = String.valueOf(descuento);
        Intent i = new Intent(MainActivity.this,Respuesta.class);
        i.putExtra("tipo",tipo);
        i.putExtra("descuento",descuent);
        i.putExtra("porcentaje",porcen);
        startActivity(i);
        //tvResultado.setText(mensaje);
    }
}