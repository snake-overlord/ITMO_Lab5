package models;

public class Address {
    private String zipCode;

    public Address(String postalAddress) {
        this.zipCode = postalAddress;
    }

    public String getZipCode() {
        return zipCode;
    }
}
