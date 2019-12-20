package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @Test(enabled = false)
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        ContactData firstContactData = new ContactData("AAAAA", "BBBBBB", "CCCCCC",
                "Z", "Moscow", "89999999999",
                "aaa@mail.ru", "111");
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(firstContactData);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectedContact();
        ContactData newContact = new ContactData("zzz", "zzz", "zzz",
                "Z", "Moscow", "77777777777", "edit@mail.ru", "777");
        app.getContactHelper().fillContactCreationForm((newContact), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        newContact.setId(before.get(0).getId());
        before.remove(0);
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);
        app.getSessionHelper().logout();
    }
}