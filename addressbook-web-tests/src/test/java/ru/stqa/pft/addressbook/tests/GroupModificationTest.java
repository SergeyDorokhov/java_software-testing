package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getGroupHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("111", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modificationSelectedGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("111","111","222"));
        app.getGroupHelper().gotoGroupsPage();
        app.getSessionHelper().logout();
    }
}
