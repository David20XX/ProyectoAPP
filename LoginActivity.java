package com.miapp.planificador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText inputUsuario, inputContraseña;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsuario = findviewById(R.id.inputUsuario);
        inputContraseña = findviewById(R.id.inputContraseña);
        btnIniciar = findviewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(v -> {
            String usuario = inputUsuario.getText().toString();
            String contraseña = inputContraseña.getText().toString();

            if(!usuario.isEmpty() && !contraseña.isEmpty()) {
                startActivity(new Intent(this, MenúActivity.class));
            } else {
                Toast.makeText(this, "campos vacíos", Toast.LENGTH_SHORT).show();
            }
        });

    }
    
}
