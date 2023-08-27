package com.example.electronicsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductosListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Productos> productosLis;

    public ProductosListAdapter(Context context, int layout, ArrayList<Productos> productosLis) {
        this.context = context;
        this.layout = layout;
        this.productosLis = productosLis;
    }

    @Override
    public int getCount() {
        return  productosLis.size();
    }

    @Override
    public Object getItem(int position) {
        return productosLis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtCode,txtName, txtBrandt, txtDescriptiont, txtlocations,txtPrice;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtCode = (TextView) row.findViewById(R.id.txtcode);
            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtBrandt = (TextView) row.findViewById(R.id.txtBrands);
            holder.txtDescriptiont = (TextView) row.findViewById(R.id.txtDescriptions);
            holder.txtlocations = (TextView) row.findViewById(R.id.txtLocation);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }
        Productos productos = productosLis.get(position);
        holder.txtCode.setText(productos.getCode());
        holder.txtName.setText(productos.getName());
        holder.txtBrandt.setText(productos.getBrand());
        holder.txtDescriptiont.setText(productos.getDescription());
        holder.txtlocations.setText(productos.getLocation());
        holder.txtPrice.setText(productos.getPrice());

        byte[] foodimage = productos.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
