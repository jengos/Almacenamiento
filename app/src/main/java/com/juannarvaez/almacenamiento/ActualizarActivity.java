package com.juannarvaez.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActualizarActivity extends AppCompatActivity {
    private EditText codigoAsignatura, nombreAsignatura, numeroCreditos, semestre;
    private Button botonActualizar, botonBuscar;
    private ImageButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        codigoAsignatura = (EditText) findViewById(R.id.codigoAsignatura);
        nombreAsignatura = (EditText) findViewById(R.id.nombreAsignatura);
        numeroCreditos = (EditText) findViewById(R.id.numeroCreditos);
        semestre = (EditText) findViewById(R.id.semestre);
        botonActualizar = (Button) findViewById(R.id.botonActualizar);
        botonBuscar = (Button) findViewById(R.id.botonBuscar);
        volver = (ImageButton) findViewById(R.id.botonVolver);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdministrarBaseDeDatos admindb = new AdministrarBaseDeDatos(getApplicationContext(), "dbasignaturas", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();
                String codigo = codigoAsignatura.getText().toString();
                String nombre = nombreAsignatura.getText().toString();
                String creditos = numeroCreditos.getText().toString();
                String nivel = semestre.getText().toString();
                ContentValues guardar = new ContentValues();
                guardar.put("codigo", codigo);
                guardar.put("nombre_curso", nombre);
                guardar.put("creditos", creditos);
                guardar.put("semestre", nivel);
                int actualizar = db.update("asignaturas", guardar, "codigo ="+ codigo, null);
                db.close();
                if (actualizar !=0){
                    Toast.makeText(getApplicationContext(),"Información Asignatura Actualizada", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"La Asignatura No Está Registrada", Toast.LENGTH_SHORT).show();
                }
            }

    });
       botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                AdministrarBaseDeDatos admindb = new AdministrarBaseDeDatos(getApplicationContext(), "dbasignaturas", null, 1);
                SQLiteDatabase db = admindb.getWritableDatabase();
                String codigo = codigoAsignatura.getText().toString();
                Cursor registro = db.rawQuery("SELECT nombre_curso, creditos, semestre FROM asignaturas WHERE codigo = " + codigo, null);
                if (registro.moveToFirst()) {
                    nombreAsignatura.setText(registro.getString(0));
                    numeroCreditos.setText(registro.getString(1));
                    semestre.setText(registro.getString(2));
                    db.close();
                } else {
                    Toast.makeText(getApplicationContext(), "La Asignatura No Está Registrada", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarActivity.this, MainActivity.class);
               startActivity(intent);

            }
        });
}
}