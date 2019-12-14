package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getGroupHelper().gotoGroupsPage();
      int groupsBefore = app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("111", null, null));
    }
    app.getGroupHelper().selectGroup(groupsBefore - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().gotoGroupsPage();
      int groupsAfter = app.getGroupHelper().getGroupCount();
      Assert.assertEquals(groupsAfter, groupsBefore - 1);
    app.getSessionHelper().logout();
  }
}
