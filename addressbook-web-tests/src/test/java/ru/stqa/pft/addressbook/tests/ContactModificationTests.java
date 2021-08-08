package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test()
    public void testContactModification () {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "Baggins",
                    "Burglar",
                    "The Shire, The Hill, Bag End",
                    "+27796743437",
                    "Bilbo",
                    "BilboAdventurer@shire.com",
                    "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact =  new ContactData(
                "Baggins",
                "Burglar",
                "The Shire, The Hill, Bag End",
                "+27796743437",
                "Bilbo",
                "BilboAdventurer@shire.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size());

        //compare insides of Contacts
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
