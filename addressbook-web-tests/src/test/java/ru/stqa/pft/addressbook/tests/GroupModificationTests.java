package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification () {
        List<GroupData> before = app.group().list();
        int indexOfModificatedGroup = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(indexOfModificatedGroup).getId()).
                withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(indexOfModificatedGroup, group);
        List<GroupData> after = app.group().list();

        //compare number of groups
        Assert.assertEquals(after.size(), before.size());//we expect that after modification there will be equal number of contacts as before


        //compare insides of groups
        before.remove(indexOfModificatedGroup);//we have modified last group so we need to remove it from list of contacts that we had before modification
        before.add(group);//and add group that we got after modification
        //Comparator declares rules to compare
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
