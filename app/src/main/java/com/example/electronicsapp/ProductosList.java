package com.example.electronicsapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductosList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Productos> list;
    ProductosListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_list_activity);

        gridView = (GridView)findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ProductosListAdapter(this,R.layout.productos_items,list);
        gridView.setAdapter(adapter);

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM PRODUCTS");
        list.clear();
        while (cursor.moveToNext())
        {
            int id =cursor.getInt(0);
            String  code = cursor.getString(1);
            String  name = cursor.getString(2);
            String  brand = cursor.getString(3);
            String  description = cursor.getString(4);
            String location = cursor.getString(5);
            String  price = cursor.getString(6);
            byte[] image =cursor.getBlob(7);
            list.add(new Productos(id, code,name, brand ,description,location,price, image));

        }
        adapter.notifyDataSetChanged();

    }

}
