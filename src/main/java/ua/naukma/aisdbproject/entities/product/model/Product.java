package ua.naukma.aisdbproject.entities.product.model;

public class Product {
    private Integer idProduct;
    private Integer categoryNumber;
    private String productName;
    private String characteristics;

    public Product() {
    }

    public Product(Integer idProduct, Integer categoryNumber, String productName, String characteristics) {
        this.idProduct = idProduct;
        this.categoryNumber = categoryNumber;
        this.productName = productName;
        this.characteristics = characteristics;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Integer categoryNumber) {
        this.categoryNumber = categoryNumber;
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

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", categoryNumber=" + categoryNumber +
                ", productName='" + productName + '\'' +
                ", characteristics='" + characteristics + '\'' +
                '}';
    }
}
