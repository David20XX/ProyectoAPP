package com.miapp.planificador;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {
    EditText inputUsuario, inputContraseña;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       
    
super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inputUsuario = findViewById(R.id.inputUsuario);
        inputContraseña = findViewById(R.id.inputContraseña);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            String usuario = inputUsuario.getText().toString();
            String contraseña = inputContraseña.getText().toString();
            // Aquí puedes guardar en SharedPreferences o archivo local
            Toast.makeText(this, "Registrado: " + usuario, Toast.LENGTH_SHORT).show();
            finish(); // Vuelve al menú principal
        });
    }
}
