package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion () {
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
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.goTo().acceptChanges();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size() - 1);

        //compare insides of Contacts
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
