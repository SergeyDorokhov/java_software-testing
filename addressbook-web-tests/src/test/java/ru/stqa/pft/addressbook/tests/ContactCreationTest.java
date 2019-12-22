package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.goToContacts().all();
        ContactData newContact = new ContactData().withFirstName("AAAAA").withMiddleName("BBBBBB").
                withLastName("CCCCCC").withCompany("Z").withAddress("Moscow").
                withMobilePhone("89999999999").withEmail("aaa@mail.ru").withGroup("111");
        app.goToContacts().create(newContact);
        app.goToNavigation().home();
        Set<ContactData> after = app.goToContacts().all();
        int id =  after.stream().mapToInt((c) -> c.getId()).max().getAsInt();
        newContact.withId(id);
        before.add(newContact);
        Assert.assertEquals(before, after);
    }
}