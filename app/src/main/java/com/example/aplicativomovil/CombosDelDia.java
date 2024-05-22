package com.example.aplicativomovil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CombosDelDia extends AppCompatActivity {
    LinearLayout llsopa, llpescado, llpasta;
    Button btnselec, btnregresar;
    int seleccion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combos_del_dia);
        llsopa=(LinearLayout)findViewById(R.id.llsopa);
        llpescado=(LinearLayout)findViewById(R.id.llpescado);
        llpasta=(LinearLayout)findViewById(R.id.llpasta);
        btnselec=(Button)findViewById(R.id.btnselec);
        btnregresar=(Button)findViewById(R.id.btnregresar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Intent intent2 = getIntent();
            String cedula = intent2.getStringExtra("cedula");


            llsopa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        llsopa.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        llpescado.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                        llpasta.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    } else {
                        llsopa.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    seleccion=1;
                    btnselec.setVisibility(View.VISIBLE);
                }
            });

            llpescado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        llpescado.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        llsopa.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                        llpasta.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    } else {
                        llpescado.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    seleccion=2;
                    btnselec.setVisibility(View.VISIBLE);
                }
            });

            llpasta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        llpasta.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borde_seleccionado));
                        llsopa.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                        llpescado.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    } else {
                        llpasta.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_background));
                    }
                    seleccion=3;
                    btnselec.setVisibility(View.VISIBLE);
                }
            });

            btnregresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(CombosDelDia.this, SeleccionMenu.class);
                    intent3.putExtra("cedula", cedula);
                    startActivity(intent3);
                }
            });


            btnselec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent(CombosDelDia.this, Factura.class);
                    intent3.putExtra("seleccion", seleccion);
                    intent3.putExtra("cedula", cedula);
                    startActivity(intent3);
                }
            });
            return insets;
        });
    }
}