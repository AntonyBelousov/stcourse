package ru.stqa.pft.addressrbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String amazon;
    private final String phoneHome;
    private final String email;

    public ContactData(String firstName, String lastName, String nickName, String amazon, String phoneHome, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.amazon = amazon;
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

    public String getAmazon() {
        return amazon;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getEmail() {
        return email;
    }
}
