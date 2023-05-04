package br.com.souza.hexagonal_arch.todoapi.application.core.dtos;

public class ZipCode {

    private String street;
    private String locality;

    public ZipCode() {
    }

    public ZipCode(String street, String locality) {
        this.street = street;
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
