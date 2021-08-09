package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;;
    private String lastName;
    private String nickname;
    private String homeAddress;
    private String homePhone;
    private String firstName;
    private String email;
    private String group;



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

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
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
