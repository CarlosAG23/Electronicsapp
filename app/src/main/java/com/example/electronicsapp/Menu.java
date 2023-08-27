package com.example.electronicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu extends AppCompatActivity {

    Button componentes, ubicaiones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        componentes  = (Button)findViewById(R.id.btnComponentes);
        ubicaiones = (Button)findViewById(R.id.btnUbicaciones);

        componentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,ProductosListAdapter.class);
                startActivity(intent);
            }
        });
        ubicaiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,UbicaionesTiendas.class);
                startActivity(intent);
            }
        });

    }
}
