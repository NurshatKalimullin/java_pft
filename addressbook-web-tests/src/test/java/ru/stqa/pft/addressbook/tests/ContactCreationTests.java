package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testCreateContact() throws Exception {
        app.getContactHelper().createContact(new ContactData("Baggins",
                "Burglar",
                "The Shire, The Hill, Bag End",
                "+27796743437",
                "Bilbo",
                "BilboAdventurer@shire.com",
                "test1"), true);
    }


}