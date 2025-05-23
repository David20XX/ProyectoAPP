package com.proyecto.planificador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnHorario, btnPerfil, btnAsistente, btnNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnHorario = findViewById(R.id.btnHorario);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnAsistente = findViewById(R.id.btnAsistente);
        btnNotas = findViewById(R.id.btnNotas);

        btnHorario.setOnClickListener(v -> startActivity(new Intent(this, HorarioActivity.class)));
        btnPerfil.setOnClickListener(v -> startActivity(new Intent(this, PerfilActivity.class)));
        btnAsistente.setOnClickListener(v -> startActivity(new Intent(this, AsistenteActivity.class)));
        btnNotas.setOnClickListener(v -> startActivity(new Intent(this, NotasActivity.class)));
    }
}