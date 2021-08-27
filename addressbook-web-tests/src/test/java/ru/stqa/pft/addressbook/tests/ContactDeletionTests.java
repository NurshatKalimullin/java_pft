package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            File photo = new File("src/test/resources/icon.png");
            Groups groups = app.db().groups();
            ContactData newContact = new ContactData().withFirstName("Frodo").withLastName("Baggins")
                    .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                    .withHomeAddress("The Shire, The Hill, Bag End #1").withHomePhone("+(277)290981265")
                    .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                    .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                    .withEmail3("Oldest-Hobbit@shire.com").withPhoto(photo).inGroup(groups.iterator().next());
            app.contact().createContact(newContact, true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion () {
        ensurePreconditions();
        Contacts before = app.db().contacts(); //from database
        //Contacts before = app.contact().all(); //from web interface
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts(); //from database
        //Contacts after = app.contact().all(); //from web interface

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size() - 1));

        //compare insides of Contacts
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
