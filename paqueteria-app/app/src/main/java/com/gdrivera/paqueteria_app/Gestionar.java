package com.gdrivera.paqueteria_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        private EditText etNombre, etZona, etPeso, etCosto;

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
        public void onNothingSelected(AdapterView<?> parent) {
        }


        //Método para consultar un registro
        public void consulta(View view) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                    this,
                    "paqueteria",
                    null,
                    1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            String nombre = etNombre.getText().toString();

            Cursor fila = bd.rawQuery("SELECT nombre_producto, zona, peso, continente, pais, costo" +
                    " FROM paquetes" +
                    " WHERE nombre_producto =" + nombre, null);

            if (fila.moveToFirst()) {
                etNombre.setText(fila.getString(0));
                etZona.setText(fila.getString(1));
                etPeso.setText(fila.getString(2));
                etCosto.setText(fila.getString(5));
            } else {
                Toast.makeText(this, "No existe el producto con ese nombre",
                        Toast.LENGTH_SHORT).show();
                bd.close();
            }

        }

        //Método para eliminar un registro
        public void eliminar(View view) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                    this,
                    "paqueteria",
                    null,
                    1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            String nombre = etNombre.getText().toString();

            if (nombre.length() == 0) {
                Toast.makeText(this, "Deja el campo vacío",
                        Toast.LENGTH_SHORT).show();
            } else {
                int cant = bd.delete("paquetes", "nombre_producto=" + nombre, null);

                bd.close();

                etNombre.setText("");
                etCosto.setText("");
                etPeso.setText("");
                etZona.setText("");

                if (cant == 1) {
                    Toast.makeText(this, "Se la paqueteria con el nombre: " + nombre,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No existe el paquete con dicho nombre",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }

        // Método modificar
        public void modificar(View view) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                    this,
                    "paqueteria",
                    null,
                    1);

            SQLiteDatabase bd = admin.getWritableDatabase();

            String nombre = etNombre.getText().toString();
            String peso = etPeso.getText().toString();
            String zona = etZona.getText().toString();
            String costo = etCosto.getText().toString();

            ContentValues registro = new ContentValues();
            /*
             * nombre_producto text, peso real, zona text,continente text, pais text, costo real
             * */
            registro.put("nombre_producto", nombre);
            registro.put("peso", peso);
            registro.put("zona", zona);
            registro.put("costo", costo);

            int cant = bd.update("paquetes",
                    registro,
                    "nombre_producto=" + nombre,
                    null);

            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "no se modificaron los datos", Toast.LENGTH_SHORT).show();
            }

        }

    }