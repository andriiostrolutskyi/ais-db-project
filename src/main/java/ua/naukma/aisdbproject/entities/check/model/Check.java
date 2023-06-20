package ua.naukma.aisdbproject.entities.check.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Check {
    @NotEmpty(message = "Check number can't be empty")
    @Size(max = 10, message = "Check number should be less than 10 characters")
    private String checkNumber;
    @NotEmpty(message = "Employee id can't be empty")
    @Size(max = 10, message = "Employee id should be less than 10 characters")
    private String idEmployee;
    @Size(max = 13, message = "Card number should be less than 13 characters")
    private String cardNumber;
    private Timestamp printDate;
    @NotNull(message = "Total sum can't be empty")
    @Min(value = 0, message = "Total sum should be more than 0")
    private Float sumTotal;
    private Float vat;

    public Check() {
        sumTotal = 0f;
    }

    public Check(String checkNumber, String idEmployee) {
        this.checkNumber = checkNumber;
        this.idEmployee = idEmployee;
        this.printDate = Timestamp.valueOf(LocalDateTime.now());
        sumTotal = 0f;
        this.vat = sumTotal * 0.2f;
    }

    public Check(String checkNumber, String idEmployee, String cardNumber, @NotNull(message = "Total sum can't be empty") Float sumTotal) {
        this.checkNumber = checkNumber;
        this.idEmployee = idEmployee;
        this.cardNumber = cardNumber;
        this.sumTotal = sumTotal;
        this.printDate = Timestamp.valueOf(LocalDateTime.now());
        this.vat = sumTotal * 0.2f;
    }

    public Check(String checkNumber, String idEmployee, String cardNumber, Timestamp printDate, Float sumTotal) {
        this.checkNumber = checkNumber;
        this.idEmployee = idEmployee;
        this.cardNumber = cardNumber;
        this.printDate = printDate;
        this.sumTotal = sumTotal;
        this.vat = sumTotal * 0.2f;
    }

    public Check(String checkNumber, String idEmployee, Timestamp printDate, Float sumTotal) {
        this.checkNumber = checkNumber;
        this.idEmployee = idEmployee;
        this.printDate = printDate;
        this.sumTotal = sumTotal;
        this.vat = sumTotal * 0.2f;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Timestamp printDate) {
        this.printDate = printDate;
    }

    public float getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(float sumTotal) {
        this.sumTotal = sumTotal;
    }

    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkNumber='" + checkNumber + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", printDate=" + printDate +
                ", sumTotal=" + sumTotal +
                ", vat=" + vat +
                '}';
    }
}
