package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectedContact();
        app.getContactHelper().fillContactCreationForm(new ContactData("zzz", "zzz", "zzz",
                "Z", "Moscow", "77777777777", "edit@mail.ru", "777"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logout();
    }
}
