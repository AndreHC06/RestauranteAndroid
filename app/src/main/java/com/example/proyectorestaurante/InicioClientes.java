package com.example.proyectorestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InicioClientes extends AppCompatActivity {

    public EditText documento, nombre, correo, telefono, edtIp;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        requestQueue = Volley.newRequestQueue(this);

        documento = findViewById(R.id.edtDocumento);
        nombre = findViewById(R.id.edtNombre);
        correo = findViewById(R.id.edtCorreo);
        telefono = findViewById(R.id.edtTelefono);
        edtIp = findViewById(R.id.edtIp);

    }

    public void consultarcliente(View view){
        Intent intent = new Intent(this, ConsultarCliente.class);
        startActivity(intent);
    }

    public void GuardarCliente(View view) {

        String ip = IPGlobal.getInstance().getIpAddress();
        if (ip == null || ip.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa una IP válida primero.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construye la URL dinámica
        String urlServidor = "http://" + ip + "/empresa/insertar.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                urlServidor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(InicioClientes.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InicioClientes.this, "Error al registrar el cliente", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("documento", documento.getText().toString());
                params.put("nombre", nombre.getText().toString());
                params.put("correo", correo.getText().toString());
                params.put("telefono", telefono.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void GuardarIp (View view){
        String ip = edtIp.getText().toString();
        IPGlobal.getInstance().setIpAddress(ip);

    }

}