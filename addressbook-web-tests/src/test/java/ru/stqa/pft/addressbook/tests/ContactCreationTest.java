package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.goToContacts().list();
        ContactData newContact = new ContactData().withFirstName("AAAAA").withMiddleName("BBBBBB").
                withLastName("CCCCCC").withCompany("Z").withAddress("Moscow").
                withMobilePhone("89999999999").withEmail("aaa@mail.ru").withGroup("111");
        app.goToContacts().create(newContact);
        app.goToNavigation().home();
        List<ContactData> after = app.goToContacts().list();
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        after.sort(byId);
        newContact.withId(after.get(after.size() - 1).getId());
        before.add(newContact);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }
}