package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getNavigationHelper().gotoGroupsPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modificationSelectedGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("000","111","222"));
        app.getNavigationHelper().gotoGroupsPage();
        app.getSessionHelper().logout();
    }
}
