package com.example.electronicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UbicaionesTiendas extends AppCompatActivity {
    Button btnsur, btnNorte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaiones_tiendas);
        btnsur = (Button)findViewById(R.id.btnElSur);

        btnsur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ElectronicasSur.class);
                startActivity(intent);
            }
        });
    }
}
