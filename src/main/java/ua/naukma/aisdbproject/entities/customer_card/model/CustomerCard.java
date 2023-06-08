package ua.naukma.aisdbproject.entities.customer_card.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerCard {
    @NotEmpty(message = "Card number can't be empty")
    @Size(max = 13, message = "Card number should be less than 13 characters")
    private String cardNumber;
    @NotEmpty(message = "Customer surname can't be empty")
    @Size(max = 50, message = "Customer surname should be less than 50 characters")
    private String customerSurname;
    @NotEmpty(message = "Customer name can't be empty")
    @Size(max = 50, message = "Customer name should be less than 50 characters")
    private String customerName;
    @Size(max = 50, message = "Customer patronymic should be less than 50 characters")
    private String customerPatronymic;
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

    public CustomerCard() {
        percent = 0;
    }

    public CustomerCard(String cardNumber, String customerSurname, String customerName,
                        String customerPatronymic, String phoneNumber, String city, String street,
                        String zipCode, Integer percent) {
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
