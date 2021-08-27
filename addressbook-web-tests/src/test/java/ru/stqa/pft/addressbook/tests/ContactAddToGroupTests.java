package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactAddToGroupTests extends TestBase {

    public void ensureGroupExistence(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    private void ensureContactExistence() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/icon.png");
            app.contact().createContact(new ContactData().withFirstName("Frodo").withLastName("Baggins")
                    .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                    .withHomeAddress("The Shire, The Hill, Bag End #1").withHomePhone("+(277)290981265")
                    .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                    .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                    .withEmail3("Oldest-Hobbit@shire.com").withPhoto(photo), true);
        }
    }



    @Test
    public void testAddContactToGroup() {
        ensureGroupExistence();
        ensureContactExistence();
        Contacts contactsFromDB = app.db().contacts();
        ContactData modifiedContact = contactsFromDB.iterator().next();
        ContactData max = contactsFromDB.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get();
        for (ContactData contact : contactsFromDB) {
            if (contact.getGroups().size() == 0){
                modifiedContact = contact;
                break;
            }
            else if (contact == max) {
                File photo = new File("src/test/resources/icon.png");
                modifiedContact = new ContactData().withFirstName("Frodo").withLastName("Baggins")
                        .withNickname("Burglar").withCompanyType("LLC").withCompanyName("The Fellowship of the Ring")
                        .withHomeAddress("The Shire, The Hill, Bag End #1").withHomePhone("+(277)290981265")
                        .withMobilePhone("+7 962 534 45 12").withWorkPhone("8-812-264-54-77")
                        .withEmail("Bilbo-Adventurer@shire.com").withEmail2("The.Ring.Holder@shire.com")
                        .withEmail3("Oldest-Hobbit@shire.com").withPhoto(photo);
                app.contact().createContact(modifiedContact.withId((contactsFromDB.stream().mapToInt(g -> g.getId()).max().getAsInt()) + 1), true);
            }
        }
        Groups before = modifiedContact.getGroups(); //определили во сколько групп входит контакт
        Groups groups = app.db().groups(); //вытащили все группы
        GroupData group = groups.iterator().next(); // вытащили группу
        System.out.println(before);
        String groupName = group.getName(); //вытащили название этой группы
        app.goTo().homePage();
        app.contact().addContactToGroup(modifiedContact, groupName); // добавили контакт в выбранную группу
        Groups after = app.db().getContactsFromDB(modifiedContact).getGroups(); //определяем в какие группы входит контак после модификации
        //System.out.println(after);
        assertThat(after, equalTo(before.withAdded(group)));
    }
}
