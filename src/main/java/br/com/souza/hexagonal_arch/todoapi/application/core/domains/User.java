package br.com.souza.hexagonal_arch.todoapi.application.core.domains;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean isValidEmail;
    private String street;
    private String locality;
    private String confirmationCode;

    public User() {
    }

    public User(String id, String firstName, String lastName, String password, String email, Boolean isValidEmail, String street, String locality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.isValidEmail = isValidEmail;
        this.street = street;
        this.locality = locality;
    }

    public User(String id, String firstName, String lastName, String email, String street, String locality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isValidEmail = false;
        this.street = street;
        this.locality = locality;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isValidEmail = false;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getValidEmail() {
        return isValidEmail;
    }

    public void setValidEmail(Boolean validEmail) {
        isValidEmail = validEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
