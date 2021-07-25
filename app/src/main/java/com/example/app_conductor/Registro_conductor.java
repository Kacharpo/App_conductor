package com.example.app_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_conductor.utils.InputValidation;

import java.util.Random;

public class Registro_conductor extends AppCompatActivity {

    private EditText et_nombre, et_apellido, et_fecha,et_numero , et_correo, et_contrasena, et_confirmar, et_licencia;
    private int codigo = codigo(999999);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_conductor);
        this.et_nombre = (EditText)findViewById(R.id.txt_nombre);
        this.et_apellido = (EditText)findViewById(R.id.txt_apellidos);
        this.et_fecha = (EditText)findViewById(R.id.txt_fnac);
        this.et_numero = (EditText)findViewById(R.id.txt_numero);
        this.et_correo = (EditText)findViewById(R.id.txt_correo);
        this.et_contrasena = (EditText)findViewById(R.id.txt_pass1);
        this.et_confirmar = (EditText)findViewById(R.id.txt_pass2);
        this.et_licencia = (EditText)findViewById(R.id.txt_licencia);


    }

    public void guardar(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);


        SQLiteDatabase db = admin.getWritableDatabase();
        String nombre = et_nombre.getText().toString();
        boolean nombre_b = InputValidation.isValidEditText(et_nombre, getString(R.string.field_is_required));
        String apellido = et_apellido.getText().toString();
        boolean apellido_b = InputValidation.isValidEditText(et_apellido, getString(R.string.field_is_required));
        String fecha = et_fecha.getText().toString();
        boolean fecha_b = InputValidation.isValidEditText(et_fecha, getString(R.string.field_is_required));
        String numero = et_numero.getText().toString();
        boolean numero_b = InputValidation.isValidEditText(et_numero, getString(R.string.field_is_required));
        String correo = et_correo.getText().toString();
        boolean correo_b = InputValidation.isValidEditText(et_correo, getString(R.string.field_is_required));
        String contrasena = et_contrasena.getText().toString();
        boolean contrasena_b = InputValidation.isValidEditText(et_contrasena, getString(R.string.field_is_required));
        String confirmar = et_confirmar.getText().toString();
        boolean confirmar_b = InputValidation.isValidEditText(et_confirmar, getString(R.string.field_is_required));
        String licencia = et_licencia.getText().toString();
        boolean licencia_b = InputValidation.isValidEditText(et_licencia, getString(R.string.field_is_required));

        if (nombre_b && apellido_b && fecha_b && numero_b && correo_b && contrasena_b && confirmar_b && licencia_b) {
            if (contrasena.equals(confirmar)) {
                //if (terminos == true) {
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombre);
                    registro.put("apellido", apellido);
                    registro.put("fecha", fecha);
                    registro.put("numero", numero);
                    registro.put("correo", correo);
                    registro.put("contrasena", contrasena);
                    registro.put("licencia", licencia);

                    db.insert("registro_conductor", null, registro);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();

               // } else {
                 //   Toast.makeText(getApplicationContext(), "Debes aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show();
               // }
            } else {
                et_contrasena.setText("");
                contrasena_b = InputValidation.isValidEditText(et_contrasena, getString(R.string.field_is_required));
                et_confirmar.setText("");
                confirmar_b = InputValidation.isValidEditText(et_confirmar, getString(R.string.field_is_required));
                Toast.makeText(getApplicationContext(), "Contraseñas incorrectas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(Registro_conductor.this, Log_in.class);
        startActivity(i);
        finish();
    }

    public void avanzar(View view){
        Intent i = new Intent(Registro_conductor.this, Mensaje.class);
        startActivity(i);
        finish();
    }

    private int codigo(int max){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        int numero = random.nextInt(max);
        return numero;
    }
}