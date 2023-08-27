package com.example.electronicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicionSesion extends AppCompatActivity {
    EditText txtUsuario, txtPassword;
    Button BotonIniciarSesion, BotonRegistrarse;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(this,"BDElectronics",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicion_sesion);

        txtUsuario = (EditText)findViewById(R.id.edtUsuario);
        txtPassword = (EditText)findViewById(R.id.edtPassword);

        BotonIniciarSesion = (Button)findViewById(R.id.btnIniciar);
        BotonRegistrarse = (Button)findViewById(R.id.btnRegistrarse);

        BotonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor = sqLiteHelper.ConsultaUsPass(txtUsuario.getText().toString(),txtPassword.getText().toString());
                    if (cursor.getCount()>0)
                    {
                        Intent i = new Intent(InicionSesion.this, Menu.class);
                        startActivity(i);
                        finish();

                    }
                    else if(txtUsuario.getText().toString().equals("Admin") && txtPassword.getText().toString().equals("1234"))
                    {
                        Intent i = new Intent(InicionSesion.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(InicionSesion.this,"Inicio exitoso",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(InicionSesion.this,"Usuario y Contraseña incorrecto",Toast.LENGTH_SHORT).show();
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
                txtPassword.setText("");
                txtUsuario.setText("");
            }
        });
        BotonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegistroUsuarios.class);
                startActivity(i);

            }
        });

    }
}
