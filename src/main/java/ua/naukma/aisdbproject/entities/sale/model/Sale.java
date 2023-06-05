package ua.naukma.aisdbproject.entities.sale.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Sale {
    @NotEmpty(message = "UPC can't be empty")
    @Size(max = 12, message = "UPC should be less than 12 characters")
    private String upc;
    @NotEmpty(message = "Check number can't be empty")
    @Size(max = 10 ,message = "Check number should be less than 10 characters")
    private String checkNumber;
    @NotNull(message = "Product number can't be empty")
    private Integer productNumber;
    @NotNull(message = "Selling price can't be empty")
    private Float sellingPrice;

    public Sale() {
        productNumber = 0;
        sellingPrice = 0f;
    }

    public Sale(String upc, String checkNumber, Integer productNumber, Float sellingPrice) {
        this.upc = upc;
        this.checkNumber = checkNumber;
        this.productNumber = productNumber;
        this.sellingPrice = sellingPrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "upc='" + upc + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", productNumber=" + productNumber +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
