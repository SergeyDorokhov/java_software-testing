package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensureContacts() {
        app.goToNavigation().home();
        if (!app.goToContacts().isThereAContact()) {
            app.goToContacts().create(new ContactData().withFirstName("AAAAA").withMiddleName("BBBBBB").
                    withLastName("CCCCCC").withCompany("Z").withAddress("Moscow").
                    withMobilePhone("89999999999").withEmail("aaa@mail.ru").withGroup("111"));
        }
    }

    @Test
    public void testContactModification() throws Exception {
        List<ContactData> before = app.goToContacts().list();
        ContactData newContact = new ContactData().withFirstName("zzz").withMiddleName("zzz").
                withLastName("zzz").withCompany("Z").withAddress("Moscow").
                withMobilePhone("77777777777").withEmail("edit@mail.ru").withGroup("777");
        app.goToContacts().modify(newContact);
        app.goToNavigation().home();
        List<ContactData> after = app.goToContacts().list();
        newContact.withId(before.get(0).getId());
        before.remove(0);
        before.add(newContact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before, after);
    }
}