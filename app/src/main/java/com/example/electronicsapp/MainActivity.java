package com.example.electronicsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText edtCode,edtName,edtBrand,edtDescription, edtLocations,edtPrice;
    Button btnChoose,btnAdd,btnList;
    ImageView imageView;
    public static SQLiteHelper sqLiteHelper;
    final int REQUEST_CODE_GALERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sqLiteHelper =  new SQLiteHelper(this,"BDElectronics",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS PRODUCTS (Id INTEGER PRIMARY KEY AUTOINCREMENT, code VARCHAR,name VARCHAR, brand VARCHAR, description VARCHAR, location VARCHAR,price VARCHAR,image BLOB)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALERY);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.insertData(edtCode.getText().toString().trim(), edtName.getText().toString().trim(),edtBrand.getText().toString().trim(), edtDescription.getText().toString().trim(),edtLocations.getText().toString().trim(),edtPrice.getText().toString().trim(),imageViewToByte(imageView));
                    Toast.makeText(getApplicationContext(),"Se agrego exitosamente",Toast.LENGTH_SHORT).show();
                    edtCode.setText("");
                    edtName.setText("");
                    edtBrand.setText("");
                    edtDescription.setText("");
                    edtLocations.setText("");
                    edtPrice.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosList.class);
                startActivity(intent);
            }
        });
    }
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_GALERY)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALERY);

            }
        }else {
            Toast.makeText(getApplicationContext(),"no puede acceder",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtCode = (EditText)findViewById(R.id.textCodigos);
        edtName = (EditText)findViewById(R.id.txtNombre);
        edtBrand = (EditText)findViewById(R.id.txtMarca);
        edtDescription = (EditText)findViewById(R.id.txtDescripcion);
        edtLocations = (EditText)findViewById(R.id.txtUbicacion);
        edtPrice = (EditText)findViewById(R.id.txtPrecio);


        btnChoose = (Button)findViewById(R.id.BotonEscoger);
        btnAdd = (Button)findViewById(R.id.Botonagregar);
        btnList = (Button)findViewById(R.id.btnVerLista);
        imageView = (ImageView)findViewById(R.id.imageView2);

    }
}
