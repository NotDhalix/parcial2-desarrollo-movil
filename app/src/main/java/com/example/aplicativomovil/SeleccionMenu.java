package com.example.aplicativomovil;

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

import com.example.aplicativomovil.db.DbLogIn;
import com.example.aplicativomovil.db.User;


public class SeleccionMenu extends AppCompatActivity {

    TextView tv10;
    Button btncombos, btnperso, btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_menu);
        tv10=(TextView)findViewById(R.id.tv10);
        btncombos=(Button)findViewById(R.id.btncombos);
        btnperso=(Button)findViewById(R.id.btnperso);
        btnlogout=(Button)findViewById(R.id.btnlogout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


            Intent intent = getIntent();
            if (intent.getStringExtra("nombre") == null){
              String cedula = intent.getStringExtra("cedula");
                DbLogIn dbLogIn = new DbLogIn(this);
                User user = dbLogIn.datosCedula(cedula);
                tv10.setText("Bienvenido " + user.getNombre());
            }
            else {
                tv10.setText("Bienvenido " + intent.getStringExtra("nombre"));
            }

            btncombos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(SeleccionMenu.this, CombosDelDia.class);
                    Intent intent = getIntent();
                    String cedula = intent.getStringExtra("cedula");
                    intent1.putExtra("cedula", cedula);
                    startActivity(intent1);
                }
            });

            btnperso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(SeleccionMenu.this, MenuPersonalizado.class);
                    Intent intent = getIntent();
                    String cedula = intent.getStringExtra("cedula");
                    intent1.putExtra("cedula", cedula);
                    startActivity(intent1);
                }
            });

            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(SeleccionMenu.this, MainActivity.class);
                    startActivity(intent1);
                }
            });

            return insets;
        });
    }
}