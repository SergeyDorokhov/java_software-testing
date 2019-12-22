package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

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
        Set<ContactData> before = app.goToContacts().all();
        ContactData deletedContact = before.iterator().next();
        app.goToContacts().delete(deletedContact);
        app.goToNavigation().home();
        Set<ContactData> after = app.goToContacts().all();
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}