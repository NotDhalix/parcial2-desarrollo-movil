package com.example.aplicativomovil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper {
    Context context;
    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long RegistrarUsuario(String nombre, String apellido, String cedula, String password) {

        long id = 0;

        ContentValues values = null;
        SQLiteDatabase db = null;
        try {
            DbHelper dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();

            values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("cedula", cedula);
            values.put("password", password);
        } catch (Exception e) {
            e.toString();
        }
        id = db.insert(TABLE_USUARIOS, null, values);
        return id;
    }
}
