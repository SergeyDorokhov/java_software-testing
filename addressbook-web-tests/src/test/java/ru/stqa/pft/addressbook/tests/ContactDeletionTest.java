package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("AAAAA", "BBBBBB", "CCCCCC",
                    "Z", "Moscow", "89999999999",
                    "aaa@mail.ru", "111"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logout();
    }
}