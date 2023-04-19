package ru.stqa.pft.addressrbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String phoneHome;
    private final String email;

    public ContactData(String firstName, String lastName, String nickName, String company, String phoneHome, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.phoneHome = phoneHome;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getEmail() {
        return email;
    }
}
