package com.example.app_conductor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DisponibilidadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilidad);
    }
    public void avanzar(View view){
        Intent i = new Intent(DisponibilidadActivity.this, Mensaje.class);
        startActivity(i);
        finish();
    }
}