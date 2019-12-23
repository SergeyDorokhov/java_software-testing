package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.goToContacts().all();
        ContactData newContact = new ContactData().withFirstName("AAAAA").withMiddleName("BBBBBB").
                withLastName("CCCCCC").withCompany("Z").withAddress("Moscow").
                withMobilePhone("89999999999").withEmail("aaa@mail.ru").withGroup("111");
        app.goToContacts().create(newContact);
        app.goToNavigation().home();
        assertThat(app.goTo().count(), equalTo(before.size() + 1));
        Contacts after = app.goToContacts().all();
        assertThat(after, equalTo(before.withAdded(newContact.
                withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}