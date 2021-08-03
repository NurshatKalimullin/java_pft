package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private static int id;
    private final String lastName;
    private final String nickname;
    private final String homeAddress;
    private final String homePhone;
    private final String firstName;
    private final String email;
    private final String group;


    public ContactData(String lastName, String nickname, String homeAddress, String homePhone, String firstName, String email, String group) {
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeAddress = homeAddress;
        this.homePhone = homePhone;
        this.firstName = firstName;
        this.email = email;
        this.group = group;
    }


    public ContactData(int id, String lastName, String nickname, String homeAddress, String homePhone, String firstName, String email, String group) {
        this.id = id;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeAddress = homeAddress;
        this.homePhone = homePhone;
        this.firstName = firstName;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getLastName() { return lastName; }

    public String getNickname() { return nickname; }

    public String getHomeAddress() { return homeAddress; }

    public String getHomePhone() { return homePhone; }

    public String getFirstName() { return firstName; }

    public String getEmail() { return email; }

    public String getGroup() { return  group; }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "lastName='" + lastName + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(homeAddress, that.homeAddress) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, homeAddress, firstName);
    }
}
