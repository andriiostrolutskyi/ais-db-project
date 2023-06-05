package ua.naukma.aisdbproject.entities.store_product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StoreProduct {
    @NotEmpty(message = "UPC can't be empty")
    @Size(max = 12, message = "UPC should be less than 12 characters")
    private String upc;
    @Size(max = 12, message = "Promotional UPC should be less than 12 characters")
    private String upcProm;
    @NotNull(message = "Product id can't be empty")
    @Min(value = 0, message = "Product id should be positive number")
    private Integer idProduct;
    @NotNull(message = "Selling price can't be empty")
    @Min(value = 0, message = "Selling price should be positive number")
    private Float sellingPrice;
    @NotNull(message = "Number of products can't be empty")
    @Min(value = 0, message = "Number of products should be positive number")
    private Integer productsNumber;
    @NotNull(message = "You should inform whether the product in promotional")
    private Boolean promotionalProduct;

    public StoreProduct() {
        idProduct = 0;
        sellingPrice = 0f;
        productsNumber = 0;
        promotionalProduct = false;
    }

    public StoreProduct(String upc, Integer idProduct, Float sellingPrice, Integer productsNumber, Boolean promotionalProduct) {
        this.upc = upc;
        this.idProduct = idProduct;
        this.sellingPrice = sellingPrice;
        this.productsNumber = productsNumber;
        this.promotionalProduct = promotionalProduct;
        if(Boolean.TRUE.equals(this.promotionalProduct))
            this.sellingPrice = sellingPrice * 0.8f;
    }

    public StoreProduct(String upc, String upcProm, Integer idProduct, Float sellingPrice, Integer productsNumber, Boolean promotionalProduct) {
        this.upc = upc;
        this.upcProm = upcProm;
        this.idProduct = idProduct;
        this.sellingPrice = sellingPrice;
        this.productsNumber = productsNumber;
        this.promotionalProduct = promotionalProduct;
        if(Boolean.TRUE.equals(this.promotionalProduct))
            this.sellingPrice = sellingPrice * 0.8f;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getUpcProm() {
        return upcProm;
    }

    public void setUpcProm(String upcProm) {
        this.upcProm = upcProm;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(Integer productsNumber) {
        this.productsNumber = productsNumber;
    }

    public Boolean getPromotionalProduct() {
        return promotionalProduct;
    }

    public void setPromotionalProduct(Boolean promotionalProduct) {
        this.promotionalProduct = promotionalProduct;
    }

    @Override
    public String toString() {
        return "StoreProduct{" +
                "upc='" + upc + '\'' +
                ", upcProm='" + upcProm + '\'' +
                ", idProduct=" + idProduct +
                ", sellingPrice=" + sellingPrice +
                ", productsNumber=" + productsNumber +
                ", promotionalProduct=" + promotionalProduct +
                '}';
    }
}
