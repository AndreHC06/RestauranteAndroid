package com.example.proyectorestaurante;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsultarCliente extends AppCompatActivity {

    public EditText txtdocumento, txtnombre, txttelfono, txtcorreo;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtdocumento = findViewById(R.id.documento);
        txtnombre = findViewById(R.id.nombre);
        txttelfono = findViewById(R.id.telefono);
        txtcorreo = findViewById(R.id.correo);

        requestQueue = Volley.newRequestQueue(this);

    }

    public  void verDatos(View view){
        String ip = IPGlobal.getInstance().getIpAddress();
        if (ip == null || ip.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa una IP válida primero.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construye la URL dinámica
        String docbus = txtdocumento.getText().toString().trim();

        String urlServidor = "http://" + ip + "/empresa/consultar.php?documento=" + docbus;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlServidor,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String txtnom, txttel, txtcor, txtsex, txtest;
                        try {
                            txtnom = response.getString("nombre");
                            txttel = response.getString("telefono");
                            txtcor = response.getString("correo");

                            txtnombre.setText(txtnom);
                            txttelfono.setText(txttel);
                            txtcorreo.setText(txtcor);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }

}