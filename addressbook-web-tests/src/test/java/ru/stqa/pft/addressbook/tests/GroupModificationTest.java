package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getGroupHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("111", null, null));
        }
        List<GroupData> groupsBefore = app.getGroupHelper().getGrupList();
        app.getGroupHelper().selectGroup(groupsBefore.size() - 1);
        app.getGroupHelper().modificationSelectedGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("111","111","222"));
        app.getGroupHelper().gotoGroupsPage();
        List<GroupData> groupsAfter = app.getGroupHelper().getGrupList();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        app.getSessionHelper().logout();
    }
}
