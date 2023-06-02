package ua.naukma.aisdbproject.entities.customer_card.model;

import jakarta.validation.constraints.NotNull;

public class CustomerCard {
    @NotNull
    private String cardNumber;
    @NotNull
    private String customerSurname;
    @NotNull
    private String customerName;
    private String customerPatronymic;
    @NotNull
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    @NotNull
    private int percent;

    public CustomerCard() {
    }

    public CustomerCard(@NotNull String cardNumber, @NotNull String customerSurname, @NotNull String customerName,
                        String customerPatronymic, @NotNull String phoneNumber, String city, String street,
                        String zipCode, @NotNull int percent) {
        this.cardNumber = cardNumber;
        this.customerSurname = customerSurname;
        this.customerName = customerName;
        this.customerPatronymic = customerPatronymic;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.percent = percent;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPatronymic() {
        return customerPatronymic;
    }

    public void setCustomerPatronymic(String customerPatronymic) {
        this.customerPatronymic = customerPatronymic;
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

    @Override
    public String toString() {
        return "CustomerCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPatronymic='" + customerPatronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", percent=" + percent +
                '}';
    }
}
