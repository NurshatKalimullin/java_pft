package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withLastName("Baggins").
                    withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                    withHomePhone("+27796743437").withFirstName("Bilbo").
                    withEmail("BilboAdventurer@shire.com").withGroup("test1"), true);
        }
    }

    @Test()
    public void testContactDeletion () {
        ensurePreconditions();
        List<ContactData> before = app.contact().getContactList();
        app.contact().delete(before);
        List<ContactData> after = app.contact().getContactList();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size() - 1);

        //compare insides of Contacts
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }


}
