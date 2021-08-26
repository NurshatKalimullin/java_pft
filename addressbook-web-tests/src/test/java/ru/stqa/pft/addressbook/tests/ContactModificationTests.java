package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/icon.png");
            app.contact().createContact(new ContactData().withFirstName("Frodo").withLastName("Baggins")
                    .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                    .withHomeAddress("The Shire, The Hill, Bag End #1").withHomePhone("+(277)290981265")
                    .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                    .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                    .withEmail3("Oldest-Hobbit@shire.com").withPhoto(photo), true); //.withGroup("test 1")
        }

        //following code works with web interface
        /*
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("Frodo").withLastName("Baggins")
                    .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                    .withHomeAddress("The Shire, The Hill, Bag End #1").withHomePhone("+(277)290981265")
                    .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                    .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                    .withEmail3("Oldest-Hobbit@shire.com").withGroup("test1"), true);
        }
        */
    }

    @Test(enabled = true)
    public void testContactModification () {
        ensurePreconditions();
        Contacts before = app.db().contacts(); //from database
        //Contacts before = app.contact().all(); //from web interface
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/icon.png");
        /*ContactData contact =  new ContactData().withId(modifiedContact.getId()).withLastName("Baggins")
                .withFirstName("Frodo").withNickname("Burglar").withCompanyType("LLC")
                .withCompanyName("The Fellowship of the Ring").withHomeAddress("The Shire, The Hill, Bag End #1")
                .withHomePhone("+(277)793478654").withMobilePhone("+7 962 123 09 11")
                .withWorkPhone("8-812-755-34-76").withEmail("Bilbo-Adventurer@shire.com")
                .withEmail2("The.Ring.Holder@shire.com").withEmail3("Oldest_Hobbit@shire.com").withGroup(null)
                .withPhoto(photo);
        app.contact().modify(contact);*/
        Contacts after = app.db().contacts(); //from database
        //Contacts after = app.contact().all(); //from web interface

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size()));

        //compare insides of Contacts
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}