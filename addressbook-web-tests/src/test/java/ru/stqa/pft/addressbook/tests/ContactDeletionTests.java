package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        app.getNavigationHelper().goToHomePage();
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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().acceptChanges();
        app.getContactHelper().returnToHomePage();
    }
}
