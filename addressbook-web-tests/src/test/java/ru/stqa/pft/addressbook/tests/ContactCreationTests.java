package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testCreateContact() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withLastName("Baggins")
                .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                .withHomeAddress("The Shire, The Hill, Bag End").withHomePhone("+(277)290981265")
                .withMobilePhone("+7 962 534 45 12").withWorkPhone("+8-812-264-54-77").withFirstName("Bilbo")
                .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                .withEmail3("Oldes_Hobbit@shire.com").withGroup("test1");
        app.contact().createContact(contact, true);
        Contacts after = app.contact().all();

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size() + 1));

        //compare insides of Contacts (here before is a copy)
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }
}