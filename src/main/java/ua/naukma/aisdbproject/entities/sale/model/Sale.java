package ua.naukma.aisdbproject.entities.sale.model;

public class Sale {
    private String upc;
    private String checkNumber;
    private Integer productNumber;
    private Float sellingPrice;

    public Sale() {
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
