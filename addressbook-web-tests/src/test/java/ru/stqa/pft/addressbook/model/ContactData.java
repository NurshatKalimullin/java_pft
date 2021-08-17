package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;;
    @Expose
    private String lastName;
    @Expose
    private String nickname;
    @Expose
    private String companyType;
    @Expose
    private String companyName;
    @Expose
    private String homeAddress;
    @Expose
    private String homePhone;
    @Expose
    private String firstName;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String allEmails;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    @Expose
    private String allPhones;
    @Expose
    private String group;
    @Expose
    private File photo;



    public int getId() {
        return id;
    }

    public String getLastName() { return lastName; }

    public String getNickname() { return nickname; }

    public String getCompanyType() { return companyType; }

    public String getCompanyName() { return companyName; }

    public String getHomeAddress() { return homeAddress; }

    public String getHomePhone() { return homePhone; }

    public String getFirstName() { return firstName; }

    public String getEmail() { return email; }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getGroup() { return  group; }

    public String getMobilePhone() { return  mobilePhone; }

    public String getWorkPhone() { return  workPhone; }

    public String getAllPhones() { return allPhones; }

    public String getAllEmails() { return allEmails; }

    public File getPhoto() { return photo; }



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

    public ContactData withCompanyType(String companyType) {
        this.companyType = companyType;
        return this;
    }

    public ContactData withCompanyName(String companyName) {
        this.companyName = companyName;
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

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
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
        return id == that.id && Objects.equals(lastName, that.lastName) && Objects.equals(homeAddress, that.homeAddress) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, homeAddress, firstName);
    }



}
