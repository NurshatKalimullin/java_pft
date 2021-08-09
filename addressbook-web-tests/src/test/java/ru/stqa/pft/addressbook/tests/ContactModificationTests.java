package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withLastName("Baggins").
                    withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                    withHomePhone("+27796743437").withFirstName("Bilbo").
                    withEmail("BilboAdventurer@shire.com").withGroup("test1"), true);
        }
    }

    @Test()
    public void testContactModification () {
        ensurePreconditions();
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =  new ContactData().withId(modifiedContact.getId()).withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup(null);
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();

        //compare number of Contacts
        Assert.assertEquals(after.size(), before.size());

        //compare insides of Contacts
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }


}
