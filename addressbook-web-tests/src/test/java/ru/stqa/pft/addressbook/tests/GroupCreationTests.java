package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().gotoGroupsPage();
    List<GroupData> groupsBefore = app.getGroupHelper().getGrupList();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("111", null, null));
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> groupsAfter = app.getGroupHelper().getGrupList();
    Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
    app.getSessionHelper().logout();
  }
}
