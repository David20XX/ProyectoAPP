package com.proyecto.planificador;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    EditText inputNota;
    Button btnGuardarNota, btnBorrarNota, btnExportar;
    ListView listaNotas;
    ArrayList<String> notasArray;
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        inputNota = findViewById(R.id.inputNota);
        btnGuardarNota = findViewById(R.id.btnGuardarNota);
        btnBorrarNota = findViewById(R.id.btnBorrarNota);
        btnExportar = findViewById(R.id.btnExportar);
        listaNotas = findViewById(R.id.listaNotas);

        db = openOrCreateDatabase("NotasDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas(id INTEGER PRIMARY KEY AUTOINCREMENT, contenido TEXT)");

        notasArray = new ArrayList<>();
        cargarNotas();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notasArray);
        listaNotas.setAdapter(adapter);

        btnGuardarNota.setOnClickListener(v -> {
            String nota = inputNota.getText().toString();
            if (!nota.isEmpty()) {
                ContentValues valores = new ContentValues();
                valores.put("contenido", nota);
                db.insert("notas", null, valores);
                notasArray.add(nota);
                adapter.notifyDataSetChanged();
                inputNota.setText("");
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
            }
        });

        btnBorrarNota.setOnClickListener(v -> {
            db.execSQL("DELETE FROM notas");
            notasArray.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Todas las notas borradas", Toast.LENGTH_SHORT).show();
        });

        btnExportar.setOnClickListener(v -> exportarNotas());
    }

    private void cargarNotas() {
        Cursor cursor = db.rawQuery("SELECT contenido FROM notas", null);
        while (cursor.moveToNext()) {
            notasArray.add(cursor.getString(0));
        }
        cursor.close();
    }

    private void exportarNotas() {
        try {
            FileWriter writer = new FileWriter(getExternalFilesDir(null) + "/notas_exportadas.txt");
            for (String nota : notasArray) {
                writer.write(nota + "\n");
            }
            writer.close();
            Toast.makeText(this, "Notas exportadas correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al exportar notas", Toast.LENGTH_SHORT).show();
        }
    }
}