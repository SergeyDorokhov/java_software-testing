package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().gotoGroupsPage();
    List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    GroupData newGroup = new GroupData("111", null, null);
    app.getGroupHelper().fillGroupForm(newGroup);
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
    int max = 0;
    for (GroupData group: groupsAfter) {
      if (max < group.getId()) {
        max = group.getId();
      }
    }
    newGroup.setId(max);
    groupsBefore.add(newGroup);
    Assert.assertEquals(new HashSet<>(groupsAfter), new HashSet<>(groupsBefore));
    app.getSessionHelper().logout();
  }
}
