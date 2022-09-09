package com.example.spaced;

public class TicketModel {

    private int id;
    private String Customer;
    private String CustomerName;
    private String age;
    private String email;
    private String phoneNumber;
    private String creditCard;
    private String debitCard;
    private String dateOfExpiration;
    private String Cvc;
    private String Planet;
    private String LaunchPad;


    public TicketModel(int id, String customer, String customerName, String age, String email, String phoneNumber, String creditCard, String debitCard, String dateOfExpiration, String cvc, String planet, String launchPad) {
        this.id = id;
        this.Customer = customer;
        this.CustomerName = customerName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.debitCard = debitCard;
        this.dateOfExpiration = dateOfExpiration;
        this.Cvc = cvc;
        this.Planet = planet;
        this.LaunchPad = launchPad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCard) {
        this.debitCard = debitCard;
    }

    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public String getCvc() {
        return Cvc;
    }

    public void setCvc(String cvc) {
        Cvc = cvc;
    }

    public String getPlanet() {
        return Planet;
    }

    public void setPlanet(String planet) {
        Planet = planet;
    }

    public String getLaunchPad() {
        return LaunchPad;
    }

    public void setLaunchPad(String launchPad) {
        LaunchPad = launchPad;
    }

    @Override
    public String toString() {
        return "TicketModel{" +
                "id=" + id +
                ", Customer='" + Customer + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", debitCard='" + debitCard + '\'' +
                ", dateOfExpiration='" + dateOfExpiration + '\'' +
                ", Cvc='" + Cvc + '\'' +
                ", Planet='" + Planet + '\'' +
                ", LaunchPad='" + LaunchPad + '\'' +
                '}';
    }
}
