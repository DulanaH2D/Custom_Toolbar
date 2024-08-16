package com.nvision.customtoolbar.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvision.customtoolbar.R;
import com.nvision.customtoolbar.adapters.RecyclerViewAdapter;
import com.nvision.customtoolbar.models.ProductModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // private ProductModel productModel = new ProductModel();
    public RecyclerViewAdapter adapter;
    ArrayList<ProductModel> productModels = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // ----
        RecyclerView productRecycler = view.findViewById(R.id.product_recycler);
        setUpProductModels();

        // Use requireContext() or getContext() to get the context
        adapter = new RecyclerViewAdapter(getContext(), productModels);

        if (productRecycler != null) {
            productRecycler.setAdapter(adapter);
            // Use requireContext() or getContext() to get the context
            productRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            // Log error or handle the situation when RecyclerView is null
            Log.e("HomeFragment", "RecyclerView is not found in the layout");
        }


        return view;
    }


    private void setUpProductModels() {
        String[] productName = getResources().getStringArray(R.array.product_names);
        String[] productBrand = getResources().getStringArray(R.array.product_brands);
        int[] productPrice = getResources().getIntArray(R.array.product_prices);

        for (int i = 0; i < productName.length; i++) {
            productModels.add(new ProductModel(productName[i], productBrand[i], productPrice[i], false));
        }


    }

    // delete selected item
    public void deleteSelectedItems() {
        adapter.deleteSelectedItems();
    }


    // select all
    public void selectAllItems() {
        adapter.selectAll();
    }

    // Unselect all
    public void unSelectAllItems() {
        adapter.unSelectAll();
    }


    /*@Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Clicked Contact Us", Toast.LENGTH_SHORT).show();
    }*/


}

