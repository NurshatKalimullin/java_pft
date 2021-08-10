package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().HomePage();
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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =  new ContactData().withId(modifiedContact.getId()).withLastName("Baggins").
                withNickname("Burglar").withHomeAddress("The Shire, The Hill, Bag End").
                withHomePhone("+27796743437").withFirstName("Bilbo").
                withEmail("BilboAdventurer@shire.com").withGroup(null);
        app.contact().modify(contact);
        Contacts after = app.contact().all();

        //compare number of Contacts
        MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()));

        //compare insides of Contacts
        before.remove(modifiedContact);
        before.add(contact);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before));
    }


}
