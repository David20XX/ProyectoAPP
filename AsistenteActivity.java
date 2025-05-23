package com.proyecto.planificador;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;

import org.json.JSONObject;

import java.io.IOException;

public class AsistenteActivity extends AppCompatActivity {

    EditText inputPregunta;
    Button btnEnviar;
    TextView txtRespuesta;

    final OkHttpClient cliente = new OkHttpClient();
    final String API_KEY = "tu_clave_api_aqui"; // Sustituye por tu clave real

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistente);

        inputPregunta = findViewById(R.id.inputPregunta);
        btnEnviar = findViewById(R.id.btnEnviar);
        txtRespuesta = findViewById(R.id.txtRespuesta);

        btnEnviar.setOnClickListener(v -> {
            String pregunta = inputPregunta.getText().toString();
            if (!pregunta.isEmpty()) {
                enviarPreguntaAI(pregunta);
            }
        });
    }

    private void enviarPreguntaAI(String pregunta) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        JSONObject json = new JSONObject();
        try {
            json.put("model", "gpt-3.5-turbo");
            json.put("messages", new org.json.JSONArray()
                .put(new JSONObject().put("role", "user").put("content", pregunta))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> txtRespuesta.setText("Error: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String respuestaJSON = response.body().string();
                    try {
                        JSONObject obj = new JSONObject(respuestaJSON);
                        String respuestaIA = obj
                            .getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content");

                        runOnUiThread(() -> txtRespuesta.setText(respuestaIA.trim()));
                    } catch (Exception e) {
                        runOnUiThread(() -> txtRespuesta.setText("Error procesando respuesta."));
                    }
                } else {
                    runOnUiThread(() -> txtRespuesta.setText("Fallo: " + response.message()));
                }
            }
        });
    }
}