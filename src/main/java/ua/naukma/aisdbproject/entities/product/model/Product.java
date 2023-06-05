package ua.naukma.aisdbproject.entities.product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Product {
    @NotNull(message = "Product id can't be empty")
    @Min(value = 0, message = "Product id should be positive number")
    private Integer idProduct;
    @NotNull(message = "Category number can't be empty")
    @Min(value = 0, message = "Category number should be positive number")
    private Integer categoryNumber;
    @NotEmpty(message = "Product name can't be empty")
    @Size(max = 50, message = "Product name should be less than 50 characters"  )
    private String productName;
    @NotEmpty(message = "Characteristics can't be empty")
    @Size(max = 100,message = "Characteristics should be less than 100 characters" )
    private String characteristics;

    public Product() {
        idProduct = 0;
        categoryNumber = 0;
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
