package com.example.proyectorestaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class InicioMenus extends AppCompatActivity {
    CheckBox cbxCeviche, cbxPapa, cbxLomo,cbxAdobo, cbxLimonada, cbxChicha, cbxMaracuya, cbxNaranja;
    RadioButton rbDomicilio, rbParaLlevar;
    Button btnDetalle, btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_menus);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageSlider imageSlider =findViewById(R.id.sliderMenus);
        ImageSlider imageSlider2 =findViewById(R.id.sliderMenus2);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        ArrayList<SlideModel> slideModels2= new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.ceviche, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.lomosaltado, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.papa, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.adobo, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        slideModels2.add(new SlideModel(R.drawable.naranja, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.limonada, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.chicha, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.maracuya, ScaleTypes.FIT));
        imageSlider2.setImageList(slideModels2, ScaleTypes.FIT);

        cbxCeviche = findViewById(R.id.cbxCeviche);
        cbxPapa = findViewById(R.id.cbxPapa);
        cbxLomo = findViewById(R.id.cbxLomo);
        cbxAdobo = findViewById(R.id.cbxAdobo);

        cbxLimonada = findViewById(R.id.cbxLimonada);
        cbxChicha = findViewById(R.id.cbxChicha);
        cbxNaranja = findViewById(R.id.cbxNaranja);
        cbxMaracuya = findViewById(R.id.cbxMaracuya);

        rbDomicilio = findViewById(R.id.rbDomicilio);
        rbParaLlevar = findViewById(R.id.rbParaLlevar);




    }
    public void Limpiar(View view){
        cbxCeviche.setChecked(false);
        cbxPapa.setChecked(false);
        cbxLomo.setChecked(false);
        cbxAdobo.setChecked(false);
        cbxLimonada.setChecked(false);
        cbxChicha.setChecked(false);
        cbxMaracuya.setChecked(false);
        cbxNaranja.setChecked(false);
        rbParaLlevar.setChecked(false);
        rbDomicilio.setChecked(false);
        Toast.makeText(InicioMenus.this, "Selección limpiada", Toast.LENGTH_SHORT).show();
    }

    public void IrDetalle(View view){
        double total = 0;
        StringBuilder seleccion = new StringBuilder("Pedido:\n");

        // Verificar selección del tipo de servicio
        if (!rbDomicilio.isChecked() && !rbParaLlevar.isChecked()) {
            Toast.makeText(InicioMenus.this, "Debe seleccionar el tipo de servicio", Toast.LENGTH_LONG).show();
            return; // Termina el proceso si no se selecciona un tipo de servicio
        }

        // Verificar selección de platos/jugos
        if (cbxCeviche.isChecked()) {
            total += 40;
            seleccion.append("Ceviche - S/40\n");
        }
        if (cbxPapa.isChecked()) {
            total += 20;
            seleccion.append("Papa a la Huancaína - S/20\n");
        }
        if (cbxLomo.isChecked()) {
            total += 50;
            seleccion.append("Lomo Saltado - S/50\n");
        }
        if (cbxAdobo.isChecked()) {
            total += 40;
            seleccion.append("Adobo de Cerdo - S/40\n");
        }
        if (cbxLimonada.isChecked()) {
            total += 10;
            seleccion.append("Limonada - S/10\n");
        }
        if (cbxChicha.isChecked()) {
            total += 9;
            seleccion.append("Chicha Morada - S/9\n");
        }
        if (cbxMaracuya.isChecked()) {
            total += 7;
            seleccion.append("Maracuyá - S/7\n");
        }
        if (cbxNaranja.isChecked()) {
            total += 8;
            seleccion.append("Jugo de Naranja - S/8\n");
        }

        // Verificar que al menos un plato/jugo haya sido seleccionado
        if (total == 0) {
            Toast.makeText(InicioMenus.this, "Debe seleccionar al menos un plato o jugo", Toast.LENGTH_LONG).show();
            return; // Termina el proceso si no se seleccionan platos/jugos
        }

        // Agregar el costo del tipo de servicio seleccionado
        if (rbDomicilio.isChecked()) {
            total += 15;
            seleccion.append("Entrega a Domicilio - S/15\n");
        } else if (rbParaLlevar.isChecked()) {
            seleccion.append("Para Llevar - S/0\n");
        }

        // Mostrar el total y proceder a la siguiente actividad
        seleccion.append("\nTotal: S/").append(total);

        Intent intent = new Intent(InicioMenus.this, DetallePedido.class);
        intent.putExtra("seleccion", seleccion.toString());
        startActivity(intent);



    }
}