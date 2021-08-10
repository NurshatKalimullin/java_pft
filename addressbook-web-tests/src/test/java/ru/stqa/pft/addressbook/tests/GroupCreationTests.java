package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    /*
    * int max = 0;
    * for (GroupData g : after){
    *  if (g.getId() > max){
    *    max = g.getId();
    *  }
    * }
    */
    //following line works as previous 'for code' to find maximum
    // following code in comment counts max identifier, but we no longer need it
    // int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    //here we compare copy of before object
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = true)
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}
