package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().gotoGroupsPage();
    int groupsBefore = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("111", null, null));
    app.getGroupHelper().returnToGroupPage();
    int groupsAfter = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(groupsAfter, groupsBefore + 1);
    app.getSessionHelper().logout();
  }
}
