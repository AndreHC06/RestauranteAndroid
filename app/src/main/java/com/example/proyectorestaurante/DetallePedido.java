package com.example.proyectorestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetallePedido extends AppCompatActivity {
    TextView txtSeleccion;
    Button btnVenta, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_pedido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        txtSeleccion = findViewById(R.id.txtSeleccion);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVenta = findViewById(R.id.btnVenta);

        // Usar la clave correcta para obtener los datos
        String pedido = getIntent().getStringExtra("seleccion");
        if (pedido != null) {
            txtSeleccion.setText(pedido);
        } else {
            txtSeleccion.setText("No se recibieron datos del pedido.");
        }



    }
    public void VolverInicio(View view){
        Intent intent = new Intent(DetallePedido.this, Inicio.class);
        startActivity(intent);

    }
    public void VolverMenus(View view){
        Intent intent = new Intent(DetallePedido.this, InicioMenus.class);
        startActivity(intent);

    }

}