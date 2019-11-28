package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    gotoContactCreationPage();
    fillContactCreationForm(new ContactData("AAAAA", "BBBBBB", "CCCCCC",
            "Z", "Moscow", "89999999999", "aaa@mail.ru"));
    submitContactCreation();
    gotoHomePage();
    logoutFromAddressbook();
  }
}