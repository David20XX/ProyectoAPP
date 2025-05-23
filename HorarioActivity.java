package com.proyecto.planificador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class HorarioActivity extends AppCompatActivity {

    TextView txtFecha, txtHora;
    EditText inputMensaje;
    Button btnFecha, btnHora, btnProgramar, btnBorrar;

    int año, mes, dia, hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        txtFecha = findViewById(R.id.txtFecha);
        txtHora = findViewById(R.id.txtHora);
        inputMensaje = findViewById(R.id.inputMensaje);
        btnFecha = findViewById(R.id.btnFecha);
        btnHora = findViewById(R.id.btnHora);
        btnProgramar = findViewById(R.id.btnProgramar);
        btnBorrar = findViewById(R.id.btnBorrar);

        Calendar calendar = Calendar.getInstance();

        btnFecha.setOnClickListener(v -> {
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                año = year;
                mes = month + 1;
                dia = dayOfMonth;
                txtFecha.setText(dia + "/" + mes + "/" + año);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnHora.setOnClickListener(v -> {
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                hora = hourOfDay;
                minuto = minute;
                txtHora.setText(String.format("%02d:%02d", hora, minuto));
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        });

        btnProgramar.setOnClickListener(v -> {
            String mensaje = inputMensaje.getText().toString();
            if (txtFecha.getText().toString().isEmpty() || txtHora.getText().toString().isEmpty()) {
                Toast.makeText(this, "Selecciona fecha y hora", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Evento programado:\n" + mensaje, Toast.LENGTH_SHORT).show();
        });

        btnBorrar.setOnClickListener(v -> {
            txtFecha.setText("");
            txtHora.setText("");
            inputMensaje.setText("");
        });
    }
}