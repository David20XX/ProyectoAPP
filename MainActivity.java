package com.miapp.planificador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRegistro, btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistro = findviewById(R.id.btnRegistro);
        btnAcceder = findviewById(R.id.btnAcceder);

        btnRegistro.SetOnClickListener(v ->
           startActivity(new Intent(this, RegistroActivity.class))
        );

        btnAcceder.SetOnClickListener(v ->
           startActivity(new Intent(this, LoginActivity.class))
        );
    }

}

