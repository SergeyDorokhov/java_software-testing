package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.goToContacts().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData newContact = new ContactData().withId(modifiedContact.getId()).withFirstName("zzz")
                .withMiddleName("zzz").withLastName("zzz").withCompany("Z").withAddress("Moscow").
                        withMobilePhone("77777777777").withEmail("edit@mail.ru").withGroup("777");
        app.goToContacts().modify(newContact);
        app.goToNavigation().home();
        Contacts after = app.goToContacts().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(newContact)));
    }
}