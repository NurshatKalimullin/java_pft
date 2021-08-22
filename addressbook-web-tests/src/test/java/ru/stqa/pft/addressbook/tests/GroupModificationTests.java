package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //following code works with database
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }

        //following code works with web interface
        /*
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        */
    }

    @Test(enabled = true)
    public void testGroupModification () {
        Groups before = app.db().groups(); //from database
        //Groups before = app.group().all(); //from web interface
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).
                withName("test 1").withHeader("test 2").withFooter("test 3");
        app.group().modify(group);

        //compare number of groups
        assertThat(app.group().count(), equalTo(before.size()));//we expect that after modification there will be equal number of contacts as before
        Groups after = app.db().groups(); //from database
        //Groups after = app.group().all(); //from web interface

        //compare insides of groups
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

        //compare
        verifyGroupListInUI();

    }
}


