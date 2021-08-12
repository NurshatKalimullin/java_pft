package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    private void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("Bilbo").withLastName("Baggins")
                    .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                    .withHomeAddress("The Shire, The Hill, Bag End").withHomePhone("+(277)290981265")
                    .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                    .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                    .withEmail3("Oldes-Hobbit@shire.com").withGroup("test1"), true);
        }
    }

    @Test(enabled = true)
    public void testContactModification () {
        ensurePreconditions();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =  new ContactData().withId(modifiedContact.getId()).withLastName("Baggins")
                .withFirstName("Bilbo").withNickname("Burglar").withCompanyType("LLC")
                .withCompanyName("The Fellowship of the Ring").withHomeAddress("The Shire, The Hill, Bag End")
                .withHomePhone("+(277)793478654").withMobilePhone("+7 962 123 09 11")
                .withWorkPhone("8-812-755-34-76").withEmail("Bilbo-Adventurer@shire.com")
                .withEmail2("The.Ring.Holder@shire.com").withEmail3("Oldes_Hobbit@shire.com").withGroup(null);
        app.contact().modify(contact);
        Contacts after = app.contact().all();

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size()));

        //compare insides of Contacts
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}