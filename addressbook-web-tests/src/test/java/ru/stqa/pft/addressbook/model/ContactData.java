package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String lastName;
    private final String nickname;
    private final String homeAddress;
    private final String homePhone;
    private final String firstName;
    private final String email;

    public ContactData(String lastName, String nickname, String homeAddress, String homePhone, String firstName, String email) {
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeAddress = homeAddress;
        this.homePhone = homePhone;
        this.firstName = firstName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
