package com.nvision.customtoolbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvision.customtoolbar.models.ProductModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductModel> productModels;

    public RecyclerViewAdapter(Context context, ArrayList<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_recycler_row, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the product_recycler_row layout
        // based on the position of the recycler view
        holder.prName.setText(productModels.get(position).getProductName());
        holder.bdName.setText(productModels.get(position).getBrandName());
        holder.pPrice.setText(String.valueOf(productModels.get(position).getProductPrice()));


    }


    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed

        return productModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from our product_recycler_row layout file
        // kinda like in the onCreate method

        TextView prName, bdName, pPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            prName = itemView.findViewById(R.id.product_name);
            bdName = itemView.findViewById(R.id.brand_name);
            pPrice = itemView.findViewById(R.id.product_price);
        }
    }
}

