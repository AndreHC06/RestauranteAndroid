package com.example.proyectorestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    Button btnInicioEspeciales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnInicioEspeciales = findViewById(R.id.btnInicioEspeciales);
        ImageSlider imageSlider =findViewById(R.id.slider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.ceviche, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.lomosaltado, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.papa, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);




    }
    public void IrEspeciales(View view) {
        Intent intent = new Intent(Inicio.this, IncioPlatosEspeciales.class);
        startActivity(intent);
    }

    public void IrClientes(View view) {
        Intent intent = new Intent(Inicio.this, InicioClientes.class);
        startActivity(intent);
    }
    public void IrMenus(View view) {
        Intent intent = new Intent(Inicio.this, InicioMenus.class);
        startActivity(intent);
    }



}