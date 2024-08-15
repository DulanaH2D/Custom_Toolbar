package com.nvision.customtoolbar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvision.customtoolbar.R;
import com.nvision.customtoolbar.interfaces.RecyclerViewInterface;
import com.nvision.customtoolbar.models.ProductModel;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    // private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<ProductModel> productModels;

    private boolean multiSelectMode = false;
    ArrayList<ProductModel> selectedItemList = new ArrayList<>();

    /*public RecyclerViewAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<ProductModel> productModels) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.productModels = productModels;
    }*/
    public RecyclerViewAdapter( Context context, ArrayList<ProductModel> productModels) {

        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_recycler_row, parent, false);
        // return new RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    // public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, final int position) {
        // assigning values to the views we created in the product_recycler_row layout
        // based on the position of the recycler view
        holder.prName.setText(productModels.get(position).getProductName());
        holder.bdName.setText(productModels.get(position).getBrandName());
        holder.pPrice.setText(String.valueOf(productModels.get(position).getProductPrice()));



        holder.productRecyclerRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, productModels.get(position).getProductName() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

        ProductModel productModel = productModels.get(position);

        holder.itemView.setBackgroundColor(productModel.isSelected() ? Color.LTGRAY : Color.WHITE);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, productModels.get(position).getProductName() + " Selected", Toast.LENGTH_SHORT).show();
            if (multiSelectMode) {
                toggleSelection(productModel);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (!multiSelectMode) {
                multiSelectMode = true;
                toggleSelection(productModel);
                notifyDataSetChanged();
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return productModels.size();
    }

    private void toggleSelection(ProductModel productModel) {
        productModel.setSelected(!productModel.isSelected());
        notifyDataSetChanged();
    }

    public void deleteSelectedItems() {

        for (ProductModel item : productModels) {
            if (item.isSelected()) {
                selectedItemList.add(item);
            }
        }
        productModels.removeAll(selectedItemList);
        multiSelectMode = false;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from our product_recycler_row layout file
        // kinda like in the onCreate method
        TextView prName, bdName, pPrice;
        RelativeLayout productRecyclerRow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            prName = itemView.findViewById(R.id.product_name);
            bdName = itemView.findViewById(R.id.brand_name);
            pPrice = itemView.findViewById(R.id.product_price);

            productRecyclerRow = itemView.findViewById(R.id.product_recycler_row);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }

    // select all
    public void selectAll() {
        for (ProductModel product : productModels) {
            product.setSelected(true);
        }
        notifyDataSetChanged();
    }

    // unselect all
    public void unSelectAll() {
        for (ProductModel product : productModels) {
            product.setSelected(false);
        }
        notifyDataSetChanged();
    }

}

