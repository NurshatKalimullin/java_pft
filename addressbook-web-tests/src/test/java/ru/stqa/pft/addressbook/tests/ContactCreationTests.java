package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testCreateContact() throws Exception {
        List<ContactData> before = app.contact().getContactList();
        ContactData contact = new ContactData().withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup("test1");
        app.contact().createContact(contact, true);
        List<ContactData> after = app.contact().getContactList();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size() + 1);

        //compare insides of Contacts
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}