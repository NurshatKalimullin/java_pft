package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testCreateContact() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Baggins",
                "Burglar",
                "The Shire, The Hill, Bag End",
                "+27796743437",
                "Bilbo",
                "BilboAdventurer@shire.com",
                "test1");
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();

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