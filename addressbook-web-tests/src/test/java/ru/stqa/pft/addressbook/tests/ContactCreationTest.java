package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoContactCreationPage();
        ContactData newContact = new ContactData("AAAAA", "BBBBBB", "CCCCCC",
                "Z", "Moscow", "89999999999", "aaa@mail.ru", "111");
        app.getContactHelper().fillContactCreationForm(newContact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        newContact.setId(after.get(after.size() - 1).getId());
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);
        app.getSessionHelper().logout();
    }
}