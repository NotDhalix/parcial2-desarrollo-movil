package com.example.aplicativomovil;

import android.content.Intent;
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

import com.example.aplicativomovil.db.DbUsuarios;

public class RegistrarUsuario extends AppCompatActivity {
    EditText etnombre, etapellido, etcedula, etcontra;
    Button btnregistrar, btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_usuario);
        etnombre=(EditText)findViewById(R.id.etnombre);
        etapellido=(EditText)findViewById(R.id.etapellido);
        etcedula=(EditText)findViewById(R.id.etcedula);
        etcontra=(EditText)findViewById(R.id.etcontra);
        btnregistrar=(Button)findViewById(R.id.btnregistrar);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);




            btnregistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DbUsuarios dbUsuarios = new DbUsuarios(RegistrarUsuario.this);
                    long id = dbUsuarios.RegistrarUsuario(etnombre.getText().toString(), etapellido.getText().toString(), etcedula.getText().toString(), etcontra.getText().toString());

                    if (id>0) {
                        Toast.makeText(RegistrarUsuario.this, "Usuario registrado", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(RegistrarUsuario.this, "Error en el registro", Toast.LENGTH_LONG).show();
                    }
                }
        });


            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RegistrarUsuario.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            return insets;
        });
    }
    private void limpiar(){
        etnombre.setText("");
        etapellido.setText("");
        etcedula.setText("");
        etcontra.setText("");
    }

}