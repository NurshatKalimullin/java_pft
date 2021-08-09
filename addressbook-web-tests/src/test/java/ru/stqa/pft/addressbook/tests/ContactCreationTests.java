package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testCreateContact() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup("test1");
        app.contact().createContact(contact, true);
        Set<ContactData> after = app.contact().all();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size() + 1);

        //compare insides of Contacts
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}