package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testCreateContact() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup("test1");
        app.contact().createContact(contact, true);
        Contacts after = app.contact().all();

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size() + 1));

        //compare insides of Contacts (here before is a copy)
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
}