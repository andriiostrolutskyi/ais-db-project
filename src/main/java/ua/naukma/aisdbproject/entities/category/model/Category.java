package ua.naukma.aisdbproject.entities.category.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Category {
    @NotNull(message = "Category number can't be empty")
    @Min(value = 0, message = "Category number should be a positive number")
    private Integer categoryNumber;
    @NotEmpty(message = "Category name shouldn't be empty")
    @Size(max = 50, message = "Category name should be less than 50 characters")
    private String categoryName;
    private Integer productsNumber;

    public Category() {
        categoryNumber = 0;
    }

    public Category(Integer categoryNumber, String categoryName, Integer productsNumber) {
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
        this.productsNumber = productsNumber;
    }

    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Integer categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductsNumber() {
        return Objects.requireNonNullElse(productsNumber, 0);
    }

    public void setProductsNumber(Integer productsNumber) {
        this.productsNumber = productsNumber;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryNumber=" + categoryNumber +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
