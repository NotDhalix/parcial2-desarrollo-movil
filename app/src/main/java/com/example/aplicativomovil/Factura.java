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

import java.util.Random;

public class Factura extends AppCompatActivity {
    TextView tvnombre, tvcomida, tvcedula, tvapellido, tvreferencia, tvtotal, tvdetalle;
    Button btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_factura);

        tvnombre = findViewById(R.id.tvnombre);
        tvcomida = findViewById(R.id.tvcomida);
        tvcedula = findViewById(R.id.tvcedula);
        tvapellido = findViewById(R.id.tvapellido);
        tvreferencia = findViewById(R.id.tvreferencia);
        tvtotal = findViewById(R.id.tvtotal);
        tvdetalle = findViewById(R.id.tvdetalle);
        btnsalir = findViewById(R.id.btnsalir);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Intent intent = getIntent();
            int seleccion = intent.getIntExtra("seleccion", 0);
            String cedula = intent.getStringExtra("cedula");


            DbLogIn dbLogIn = new DbLogIn(this);
            User user = dbLogIn.datosCedula(cedula);

            tvnombre.setText("Nombre: "+user.getNombre());
            tvapellido.setText("Apellido: "+user.getApellido());
            tvcedula.setText("Cedula: "+cedula);

            if (seleccion == 1) {
                tvcomida.setText("Comida: Menú estudiante");
                tvdetalle.setText("Sopa de costilla y arroz blanco.");
                tvtotal.setText("Total: 1.00 B./");
            } else if (seleccion==2) {
                tvcomida.setText("Comida: Menú #1");
                tvdetalle.setText("Pescado frito con patacones.");
                tvtotal.setText("Total: 2.00 B./");
            }
            else if (seleccion==2) {
                tvcomida.setText("Comida: Menú #2");
                tvdetalle.setText("Pasta carbonara.");
                tvtotal.setText("Total: 3.50 B./");
            }
            else{
                tvcomida.setText("Comida: Menú personzaliado");
                Intent intent11 = getIntent();
                String prote = intent11.getStringExtra("prote").toString();
                String carbo = intent11.getStringExtra("carbo").toString();
                String ensalada = intent11.getStringExtra("ensalada").toString();
                double total = intent11.getDoubleExtra("total", 0);
                tvdetalle.setText("Proteina: " + prote + "\n\nCarbohidrato: " + carbo + "\n\nEnsalada: "+ensalada);
                tvtotal.setText(String.format("Total : %.2f B./", total));
            }


            Random random = new Random();
            tvreferencia.setText("Referencia: 00"+random.nextInt(9000) + 1000);


            btnsalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent8 = new Intent(Factura.this, MainActivity.class);
                    startActivity(intent8);
                }
            });
            return insets;
        });
    }
}
