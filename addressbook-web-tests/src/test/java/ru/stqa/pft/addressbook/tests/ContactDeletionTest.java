package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {
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
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.goToContacts().list();
        app.goToContacts().delete();
        app.goToNavigation().home();
        List<ContactData> after = app.goToContacts().list();
        before.remove(0);
        Assert.assertEquals(before, after);
    }
}