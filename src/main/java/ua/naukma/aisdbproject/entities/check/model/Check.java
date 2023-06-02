package ua.naukma.aisdbproject.entities.check.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public class Check {
    @Size(max = 10, message = "Check number should be less than 10 symbols")
    @NotNull
    private String checkNumber;
    @Size(max = 10, message = "Check number should be less than 10 symbols")
    @NotNull
    private String idEmployee;
    @Size(max = 13, message = "Check number should be less than 10 symbols")
    private String cardNumber;
    @NotNull
    private Timestamp printDate;
    @NotNull
    private Float sumTotal;
    @NotNull
    private Float vat;

    public Check() {
    }

    public Check(@NotNull String checkNumber, @NotNull String idEmployee, String cardNumber, @NotNull Timestamp printDate, @NotNull Float sumTotal) {
        this.checkNumber = checkNumber;
        this.idEmployee = idEmployee;
        this.cardNumber = cardNumber;
        this.printDate = printDate;
        this.sumTotal = sumTotal;
        this.vat = sumTotal * 0.2f;
    }

    public Check(@NotNull String checkNumber, @NotNull String idEmployee,@NotNull Timestamp printDate, @NotNull float sumTotal) {
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

    public float getVat() {
        return vat;
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
