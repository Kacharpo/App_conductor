package com.example.app_conductor.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_conductor.R;

public class Registro_nuevo_conductor extends AppCompatActivity {
private Button btn_prueba_toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_conductor);
        btn_prueba_toast = (Button)findViewById(R.id.btn_prueba_toast);

        btn_prueba_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Entrando prueba 1222", Toast.LENGTH_SHORT).show();
            }
        });



    }
}