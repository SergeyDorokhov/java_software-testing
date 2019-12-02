package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupsPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().gotoGroupsPage();
    app.getSessionHelper().logoutFromAddressbook();
  }
}
