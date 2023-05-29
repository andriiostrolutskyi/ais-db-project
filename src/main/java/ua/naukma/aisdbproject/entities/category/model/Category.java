package ua.naukma.aisdbproject.entities.category.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Category {
    @NotEmpty
    @Min(value=0, message = "Category number should be a positive")
    private int categoryNumber;
    @NotEmpty
    @Size(max=50, message = "Category name should be less than 50 characters")
    private String categoryName;

    public Category(){}
    public Category(int categoryNumber, String categoryName){
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
    }

    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryNumber=" + categoryNumber +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
