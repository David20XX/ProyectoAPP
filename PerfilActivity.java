
package com.proyecto.planificador;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    EditText inputNombre, inputEdad, inputCumple, inputOcupacion, inputPasatiempos;
    Button btnGuardar, btnBorrar;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        prefs = getSharedPreferences("perfil_usuario", MODE_PRIVATE);

        inputNombre = findViewById(R.id.inputNombre);
        inputEdad = findViewById(R.id.inputEdad);
        inputCumple = findViewById(R.id.inputCumple);
        inputOcupacion = findViewById(R.id.inputOcupacion);
        inputPasatiempos = findViewById(R.id.inputPasatiempos);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);

        // Cargar datos guardados si existen
        inputNombre.setText(prefs.getString("nombre", ""));
        inputEdad.setText(prefs.getString("edad", ""));
        inputCumple.setText(prefs.getString("cumple", ""));
        inputOcupacion.setText(prefs.getString("ocupacion", ""));
        inputPasatiempos.setText(prefs.getString("pasatiempos", ""));

        btnGuardar.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", inputNombre.getText().toString());
            editor.putString("edad", inputEdad.getText().toString());
            editor.putString("cumple", inputCumple.getText().toString());
            editor.putString("ocupacion", inputOcupacion.getText().toString());
            editor.putString("pasatiempos", inputPasatiempos.getText().toString());
            editor.apply();
            Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show();
        });

        btnBorrar.setOnClickListener(v -> {
            inputNombre.setText("");
            inputEdad.setText("");
            inputCumple.setText("");
            inputOcupacion.setText("");
            inputPasatiempos.setText("");
        });
    }
}