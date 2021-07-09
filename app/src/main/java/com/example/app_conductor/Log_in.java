package com.example.app_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


    }

    public void ingresar(View view){
        Intent i = new Intent(Log_in.this, Registro_conductor.class);
        startActivity(i);
        finish();
    }
}