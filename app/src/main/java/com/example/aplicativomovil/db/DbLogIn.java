package com.example.aplicativomovil.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbLogIn extends DbHelper {
    Context context;

    public DbLogIn(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("Range")
    public User IniciarSesion(String cedula, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getWritableDatabase();

            String query = "SELECT nombre, apellido, cedula FROM " + TABLE_USUARIOS + " WHERE cedula = ? AND password = ?";
            cursor = db.rawQuery(query, new String[]{cedula, password});

            if (cursor != null && cursor.moveToFirst()) {
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String apellido = cursor.getString(cursor.getColumnIndex("apellido"));
                String cedulaResult = cursor.getString(cursor.getColumnIndex("cedula"));
                return new User(nombre, apellido, cedulaResult);
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("DbLogIn", "Error al iniciar sesión", e);
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    public User datosCedula(String cedula){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User user = null;
        try {
            db = this.getWritableDatabase();
            if (db != null) {
                String query = "SELECT nombre, apellido FROM " + TABLE_USUARIOS + " WHERE cedula = ?";
                cursor = db.rawQuery(query, new String[]{cedula});
                if (cursor != null && cursor.moveToFirst()) {
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    @SuppressLint("Range") String apellido = cursor.getString(cursor.getColumnIndex("apellido"));
                    user = new User(nombre, apellido, cedula);
                }
            }
        } catch (Exception e) {
            Log.e("DbLogIn", "Error al obtener usuario por cédula", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return user;
    }
}
