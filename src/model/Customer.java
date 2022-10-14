package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer (String firstName,String lastName,String email){
        this.checkEmail(email);
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;

    }
    public void checkEmail(String email){
        final String regxEmail="^(.+)@(.+).(.+)$";

       Pattern pattern= Pattern.compile(regxEmail);
        if(!pattern.matcher(email).matches()) {
            System.out.println("Please enter your email in right format.");
            throw new IllegalArgumentException("Email address is not valid.");
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
