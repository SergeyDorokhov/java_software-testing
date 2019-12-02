package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupsPage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.gotoGroupsPage();
    app.logoutFromAddressbook();
  }
}
