package com.example.test2bpc;

public class Inventory {
private final int ProductID;
private final String ProductName;
private final int ProductPrice;
private final int ProductQuantity;

    public Inventory(int ProductID, String ProductName, int ProductPrice, int ProductQuantity) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductQuantity = ProductQuantity;
    }
    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }
}
