package ua.naukma.aisdbproject.entities.employee.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class Employee {
    @NotEmpty(message = "Employee id cant be empty")
    @Size(max = 10, message = "Employee id should be less than 10 characters")
    private String idEmployee;
    @NotEmpty(message = "Employee surname can't be empty")
    @Size(max = 50, message = "Employee surname should be less than  50 characters")
    private String employeeSurname;
    @NotEmpty(message = "Employee name can't be empty")
    @Size(max = 50, message = "Employee name should be less than  50 characters")
    private String employeeName;
    @Size(max = 50, message = "Employee patronymic should be less than  50 characters")
    private String employeePatronymic;
    @NotEmpty(message = "Employee role can't be empty")
    @Size(max = 10, message = "Employee role should be less than 10 characters")
    private String employeeRole;
    @NotNull(message = "Salary can't be empty")
    private Float salary;
    @NotEmpty(message = "Date of birth can't be empty")
    private Date dateOfBirth;
    @NotEmpty(message = "Date of start can't be empty")
    private Date dateOfStart;
    @NotEmpty(message = "Phone number can't be empty")
    @Size(max = 13, message = "Phone number should be less than 13 characters")
    private String phoneNumber;
    @NotEmpty(message = "City can't be empty")
    @Size(max = 50, message = "City should be less than 50 characters")
    private String city;
    @NotEmpty(message = "Street can't be empty")
    @Size(max = 50, message = "Street should be less than 50 characters")
    private String street;
    @NotEmpty(message = "Zip code can't be empty")
    @Size(max = 9, message = "Zip code should be less than 9 characters")
    private String zipCode;

    public Employee() {
        salary = 0f;
    }

    public Employee(String idEmployee, String employeeSurname, String employeeName, String employeeRole,
                    Float salary, Date dateOfBirth, Date dateOfStart, String phoneNumber, String city,
                    String street, String zipCode) {
        this.idEmployee = idEmployee;
        this.employeeSurname = employeeSurname;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Employee(String idEmployee, String employeeSurname, String employeeName, String employeePatronymic,
                    String employeeRole, Float salary, Date dateOfBirth, Date dateOfStart, String phoneNumber,
                    String city, String street, String zipCode) {
        this.idEmployee = idEmployee;
        this.employeeSurname = employeeSurname;
        this.employeeName = employeeName;
        this.employeePatronymic = employeePatronymic;
        this.employeeRole = employeeRole;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePatronymic() {
        return employeePatronymic;
    }

    public void setEmployeePatronymic(String employeePatronymic) {
        this.employeePatronymic = employeePatronymic;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
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

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", employeeSurname='" + employeeSurname + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeePatronymic='" + employeePatronymic + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", salary=" + salary +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfStart=" + dateOfStart +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
