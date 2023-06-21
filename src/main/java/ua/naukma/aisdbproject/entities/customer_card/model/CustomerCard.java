package ua.naukma.aisdbproject.entities.customer_card.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CustomerCard {
    @NotEmpty(message = "Card number can't be empty")
    @Size(max = 13, message = "Card number should be less than 13 characters")
    private String cardNumber;
    @NotEmpty(message = "Customer surname can't be empty")
    @Size(max = 50, message = "Customer surname should be less than 50 characters")
    private String custSurname;
    @NotEmpty(message = "Customer name can't be empty")
    @Size(max = 50, message = "Customer name should be less than 50 characters")
    private String custName;
    @Size(max = 50, message = "Customer patronymic should be less than 50 characters")
    private String custPatronymic;
    @NotEmpty(message = "Customer phone number can't be empty")
    @Size(max = 13, message = "Phone number should be less than 13 characters")
    private String phoneNumber;
    @Size(max = 50, message = "City should be less than 50 characters")
    private String city;
    @Size(max = 50, message = "Street should be less than 50 characters")
    private String street;
    @Size(max = 9, message = "Zip code should be less than 9 characters")
    private String zipCode;
    @NotNull(message = "Customer discount percent can't be empty")
    private Integer percent;
    private Integer boughtNumber;

    public CustomerCard() {
        percent = 0;
    }

    public CustomerCard(String cardNumber, String customerSurname, String customerName,
                        String customerPatronymic, String phoneNumber, String city, String street,
                        String zipCode, Integer percent, Integer boughtNumber) {
        this.cardNumber = cardNumber;
        this.custSurname = customerSurname;
        this.custName = customerName;
        this.custPatronymic = customerPatronymic;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.percent = percent;
        this.boughtNumber = boughtNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getCustSurname() {
        return custSurname;
    }

    public void setCustSurname(String custSurname) {
        this.custSurname = custSurname;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPatronymic() {
        return custPatronymic;
    }

    public void setCustPatronymic(String custPatronymic) {
        this.custPatronymic = custPatronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Integer getBoughtNumber() {
        return Objects.requireNonNullElse(boughtNumber, 0);
    }

    public void setBoughtNumber(Integer boughtNumber) {
        this.boughtNumber = boughtNumber;
    }

    @Override
    public String toString() {
        return "CustomerCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", customerSurname='" + custSurname + '\'' +
                ", customerName='" + custName + '\'' +
                ", customerPatronymic='" + custPatronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", percent=" + percent +
                '}';
    }
}
