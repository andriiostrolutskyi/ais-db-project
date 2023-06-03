package ua.naukma.aisdbproject.entities.store_product.model;

public class StoreProduct {
    private String upc;
    private String upcProm;
    private Integer idProduct;
    private Float sellingPrice;
    private Integer productsNumber;
    private Boolean promotionalProduct;

    public StoreProduct() {
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
