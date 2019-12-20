package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getGroupHelper().gotoGroupsPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("111", null, null));
    }
      List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(groupsBefore.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().gotoGroupsPage();
      List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
      Assert.assertEquals(groupsAfter.size(), groupsBefore.size() - 1);
      groupsBefore.remove(groupsBefore.size() - 1);
      Assert.assertEquals(groupsAfter, groupsBefore);
  }
}
