package ua.naukma.aisdbproject.entities.sale.model;

public class SaleSearch {
    private String upc;
    private String productName;
    private Integer productNumber;
    private Float sellingPrice;
    private String idEmployee;

    public SaleSearch() {
    }

    public SaleSearch(String upc, String productName, Integer productNumber, Float sellingPrice, String idEmployee) {
        this.upc = upc;
        this.productName = productName;
        this.productNumber = productNumber;
        this.sellingPrice = sellingPrice;
        this.idEmployee = idEmployee;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String employeeId) {
        this.idEmployee = employeeId;
    }

    @Override
    public String toString() {
        return "SaleSearch{" +
                "upc='" + upc + '\'' +
                ", productName='" + productName + '\'' +
                ", productNumber=" + productNumber +
                ", sellingPrice=" + sellingPrice +
                ", idEmployee='" + idEmployee + '\'' +
                '}';
    }
}
