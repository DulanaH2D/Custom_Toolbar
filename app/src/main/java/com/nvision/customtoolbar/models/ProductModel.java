package com.nvision.customtoolbar.models;

public class ProductModel {
    private String productName;
    private String brandName;
    private int productPrice;

    // private boolean isChecked = false;



    /*public ProductModel(String productName, String brandName, int productPrice, boolean isChecked) {
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;
        this.isChecked = isChecked;
    }*/

    public ProductModel(String productName, String brandName, int productPrice) {
        this.productName = productName;
        this.brandName = brandName;
        this.productPrice = productPrice;
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

    /*public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }*/
}
