package com.example.aplicativomovil;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicativomovil.db.DbHelper;
import com.example.aplicativomovil.db.DbLogIn;
import com.example.aplicativomovil.db.User;

public class MainActivity extends AppCompatActivity {
    EditText etced, etpass;
    Button btniniciarse, btncrear, btncreardb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etced = findViewById(R.id.etced);
        etpass = findViewById(R.id.etpass);
        btniniciarse = findViewById(R.id.btniniciarse);
        btncrear = findViewById(R.id.btncrear);
        btncreardb = findViewById(R.id.btncreardb);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btncreardb.setOnClickListener(v -> {
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.InsertarPrueba();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA CON ÉXITO.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS.", Toast.LENGTH_LONG).show();
            }
        });

        btniniciarse.setOnClickListener(v -> {
            DbLogIn dbLogIn = new DbLogIn(MainActivity.this);
            User user = dbLogIn.IniciarSesion(etced.getText().toString(), etpass.getText().toString());

            if (user != null) {
                Toast.makeText(MainActivity.this, "Inicio de sesion correcto", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SeleccionMenu.class);
                intent.putExtra("nombre", user.getNombre());
                intent.putExtra("cedula", user.getCedula());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
            }
        });

        btncrear.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrarUsuario.class);
            startActivity(intent);
        });
    }
}
