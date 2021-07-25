package com.example.app_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mensaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
    }
    public void avanzar(View view){
        Intent i = new Intent(Mensaje.this, Principal.class);
        startActivity(i);
        finish();
    }
}