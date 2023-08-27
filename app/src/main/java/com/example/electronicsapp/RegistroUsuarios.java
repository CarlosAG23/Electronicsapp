package com.example.electronicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuarios extends AppCompatActivity {

    EditText txtUserName, txtApellido, txtNombre, txtPassword;
    Button BotonRegistrarUsuario, BotonCancelar;

    SQLiteHelper helper = new SQLiteHelper(this, "BDElectronics",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        txtUserName = (EditText)findViewById(R.id.edtUserName);
        txtNombre = (EditText)findViewById(R.id.edtRNombre);
        txtApellido = (EditText)findViewById(R.id.edtRApellido);
        txtPassword = (EditText)findViewById(R.id.edtRPass);

        BotonRegistrarUsuario = (Button) findViewById(R.id.btnIngresarUser);
        BotonCancelar =(Button)findViewById(R.id.btnCancelar);

        BotonRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.ArbirBD();
                helper.insertarReg(String.valueOf(txtUserName.getText()), String.valueOf(txtNombre.getText()), String.valueOf(txtApellido.getText()), String.valueOf(txtPassword.getText()));
                helper.CerrarBD();

                Toast.makeText(getApplicationContext(),"Registro existoso",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegistroUsuarios.this,InicionSesion.class);
                startActivity(i);
                finish();

            }
        });
        BotonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroUsuarios.this,InicionSesion.class);
                startActivity(intent);
            }
        });

    }
}
