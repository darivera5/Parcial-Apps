package com.gdrivera.mio_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
            respuesta(40, resultado, "Ser estudiante");

        }else if(familia){
            // Familia númerosa 70% de descuento
            Double resultado = precioTiquete * DESCUENTO70;
            respuesta(70, resultado, "tener familia numerosa");

        } else if(edad < 4){
            // Es gratis
            tvResultado.setText("Menor de 4 años va gratis");

        }else if(edad > 4 && edad < 7){
            // 40% de descuento
            Double resultado = precioTiquete * DESCUENTO40;
            respuesta(40, resultado, "tener edad entre 4 y 7 años");

        }else if(edad > 65){
            // 40% de descuento
            Double resultado = precioTiquete * DESCUENTO40;
            respuesta(40, resultado, "tener edad mayor de 65");
        }else{
            // No aplica descuento
            String mensaje = "No aplicas para el descuento, precio del tiquete: $" + precioTiquete;
            tvResultado.setText(mensaje);
        }
    }

    // Metodo que construye una respuesta basandose en las entradas
    public void respuesta(int porcentaje, Double descuento, String tipo){
        String mensaje;
        mensaje = "por " + tipo + " se ha aplicado un descuento del " + porcentaje + "%" +
                " tu tiquete te cuesta: $" + descuento;
        tvResultado.setText(mensaje);
    }
}