package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeleteFromGroupTests extends TestBase {

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
    public void testDropContactFromGroup () {
        ensureGroupExistence();
        ensureContactExistence();
        Groups groups = app.db().groups();
        GroupData modifiedGroup = groups.iterator().next();
        ContactData modifiedContact = null;
        Contacts before = null;
        int modifiedGroupId = 0;
        GroupData max = groups.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get();
        for (GroupData group : groups){
            if (group.getContacts().size() != 0) {
                modifiedGroup = group;
                modifiedGroupId = group.getId();
                modifiedContact = group.getContacts().iterator().next();
                break;
            }
            else if (group == max) {
                Contacts contactsFromDB = app.db().contacts();
                modifiedContact = contactsFromDB.iterator().next();
                modifiedGroup = group;
                modifiedGroupId = group.getId();
                String groupName = group.getName();
                app.goTo().homePage();
                app.contact().addContactToGroup(modifiedContact, modifiedGroupId, groupName);
            }
        }
        before = modifiedGroup.getContacts();
        String groupName = modifiedGroup.getName();
        app.goTo().homePage();
        app.contact().dropContactFromGroup(modifiedContact, groupName, modifiedGroupId);
        Groups newGroups = app.db().groups();
        for (GroupData newGroup : newGroups) {
            if (newGroup.getId() == modifiedGroupId) {
                Contacts after = newGroup.getContacts();
                assertThat(after, equalTo(before.without(modifiedContact)));
            }
        }
    }

}
