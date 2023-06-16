package ua.naukma.aisdbproject.entities.store_product.model;

public class StoreProductSearch {
    private String upc;
    private Float sellingPrice;
    private Integer productsNumber;
    private String productName;
    private String characteristics;

    public StoreProductSearch() {
    }

    public StoreProductSearch(String upc, Float sellingPrice, Integer productsNumber, String productName, String characteristics) {
        this.upc = upc;
        this.sellingPrice = sellingPrice;
        this.productsNumber = productsNumber;
        this.productName = productName;
        this.characteristics = characteristics;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
}
