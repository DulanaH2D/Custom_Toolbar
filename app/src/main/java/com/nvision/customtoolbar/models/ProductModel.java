package com.nvision.customtoolbar.models;

public class ProductModel {
    private String productName;
    private String brandName;
    private int productPrice;

    private boolean isSelected = false;

    public ProductModel(String productName, String brandName, int productPrice, boolean isSelected) {
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;

        this.isSelected = isSelected;
    }

    public ProductModel() {

    }

    public String getProductName() {
        return productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
