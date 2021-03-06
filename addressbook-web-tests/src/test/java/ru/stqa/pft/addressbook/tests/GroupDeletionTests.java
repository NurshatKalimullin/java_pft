package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() throws Exception {
        Groups before = app.db().groups(); //from database
        //Groups before = app.group().all(); //from web interface
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups(); //from database
        //Groups after = app.group().all(); //from web interface
        before.remove(deletedGroup);//now group list containment after deletion is equal to before

        //following construction is able to compare insides of List
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

}
