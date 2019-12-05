package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
        app.getSessionHelper().logoutFromAddressbook();
    }
}
