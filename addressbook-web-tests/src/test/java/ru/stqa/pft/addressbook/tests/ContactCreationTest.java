package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.gotoContactCreationPage();
    app.fillContactCreationForm(new ContactData("AAAAA", "BBBBBB", "CCCCCC",
            "Z", "Moscow", "89999999999", "aaa@mail.ru"));
    app.submitContactCreation();
    app.gotoHomePage();
    app.logoutFromAddressbook();
  }
}