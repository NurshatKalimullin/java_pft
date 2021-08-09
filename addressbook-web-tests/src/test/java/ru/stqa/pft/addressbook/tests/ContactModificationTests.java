package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData().withLastName("Baggins").
                    withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                    withHomePhone("+27796743437").withFirstName("Bilbo").
                    withEmail("BilboAdventurer@shire.com").withGroup("test1"), true);
        }
    }

    @Test()
    public void testContactModification () {
        ensurePreconditions();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact =  new ContactData().withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup(null);
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
