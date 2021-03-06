package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact") // указываем, что в xml будет использоваться тег contact
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField //это означает, что поле не будет записано в xml
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "lastname")
    private String lastName;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "nickname")
    private String nickname;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "company")
    private String companyType;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "title")
    private String companyName;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "address")
    @Type(type = "text")
    private String homeAddress;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "firstname")
    private String firstName;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Transient //аннотация используется, если поле не нужно из базы извлекать для HBConnectionTest
    private String allEmails;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Transient //аннотация используется, если поле не нужно из базы извлекать для HBConnectionTest
    private String allPhones;

    @Expose //Expose даст знать, что боле обязательно в json файле
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER) //FetchType.EAGER позволяет извлекать максимум информации за заход
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
            , inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

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

    public String getMobilePhone() { return  mobilePhone; }

    public String getWorkPhone() { return  workPhone; }

    public String getAllPhones() { return allPhones; }

    public String getAllEmails() { return allEmails; }

    public File getPhoto() { return new File(photo); }

    public Groups getGroups() { return new Groups(groups); }


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
        this.photo = photo.getPath();
        return this;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", companyType='" + companyType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", groups=" + groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(lastName, that.lastName) && Objects.equals(nickname, that.nickname) && Objects.equals(companyType, that.companyType) && Objects.equals(companyName, that.companyName) && Objects.equals(homeAddress, that.homeAddress) && Objects.equals(homePhone, that.homePhone) && Objects.equals(firstName, that.firstName) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, nickname, companyType, companyName, homeAddress, homePhone, firstName, email, email2, email3, mobilePhone, workPhone);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

}
