package com.example.aplicativomovil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPersonalizado extends AppCompatActivity {
    LinearLayout protalitas, protsalchicha, carbarroz, carbpure, ensaladapapa, ensaladarepollo;
    ScrollView sv1;
    Button btnpedir, btnatras;
    Double proteina=0.0, carbo=0.0, ensalada=0.0, total=0.0;
    String sproteina="", scarbo="", sensalada="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_personalizado);
        protalitas=(LinearLayout)findViewById(R.id.protalitas);
        protsalchicha=(LinearLayout)findViewById(R.id.protsalchicha);
        carbarroz=(LinearLayout)findViewById(R.id.carbarroz);
        carbpure=(LinearLayout)findViewById(R.id.carbpure);
        ensaladapapa=(LinearLayout)findViewById(R.id.ensaladapapa);
        ensaladarepollo=(LinearLayout)findViewById(R.id.ensaladarepollo);
        sv1=(ScrollView)findViewById(R.id.sv1);
        btnpedir=(Button)findViewById(R.id.btnpedir);
        btnatras=(Button)findViewById(R.id.btnatras);

        sv1.post(() -> sv1.scrollTo(0, 0));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Intent intent6 = getIntent();
            String cedula = intent6.getStringExtra("cedula");

            protalitas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        protalitas.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        protsalchicha.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                    } else {
                        protalitas.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    proteina=0.50;
                    sproteina="Alitas";
                }
            });

            protsalchicha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        protsalchicha.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        protalitas.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                    } else {
                        protsalchicha.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    proteina=0.50;
                    sproteina="Salchicha";
                }
            });

            carbarroz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        carbarroz.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        carbpure.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                    } else {
                        carbarroz.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    carbo=0.30;
                    scarbo="Arroz blanco";
                }
            });

            carbpure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        carbpure.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        carbarroz.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                    } else {
                        carbpure.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    carbo=0.60;
                    scarbo="Puré de papa";
                }
            });


            ensaladapapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ensaladapapa.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        ensaladarepollo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                    } else {
                        ensaladapapa.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    ensalada=0.70;
                    sensalada="Ensalada de papa";
                }
            });

                ensaladarepollo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ensaladarepollo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                            ensaladapapa.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));

                        } else {
                            ensaladarepollo.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                        }
                        ensalada=0.40;
                        sensalada="Ensalada de repollo";
                    }
            });

            btnatras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent9 = new Intent(MenuPersonalizado.this, SeleccionMenu.class);
                    startActivity(intent9);
                }
            });




            btnpedir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    total = carbo + ensalada + proteina;
                    Intent intent10 = new Intent(MenuPersonalizado.this, Factura.class);
                    intent10.putExtra("total", total);
                    intent10.putExtra("cedula", cedula);
                    intent10.putExtra("ensalada", sensalada);
                    intent10.putExtra("carbo", scarbo);
                    intent10.putExtra("prote", sproteina);
                    startActivity(intent10);
                }
            });

            return insets;
        });
    }
}