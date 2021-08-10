package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withLastName("Baggins").
                    withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                    withHomePhone("+27796743437").withFirstName("Bilbo").
                    withEmail("BilboAdventurer@shire.com").withGroup("test1"), true);
        }
    }

    @Test(enabled = false)
    public void testContactDeletion () {
        ensurePreconditions();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size() - 1));

        //compare insides of Contacts
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
