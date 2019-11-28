package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupsPage();
    initGroupCreation();
    fillGroupForm(new GroupData("111", "111", "111"));
    returnToGroupPage();
    logoutFromAddressbook();
  }
}
