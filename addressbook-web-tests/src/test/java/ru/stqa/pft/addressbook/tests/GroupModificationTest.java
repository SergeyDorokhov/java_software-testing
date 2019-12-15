package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
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
        GroupData modificationGroup = new GroupData(groupsBefore.get(groupsBefore.size() - 1).getId(),
                "111","111","222");
        app.getGroupHelper().fillGroupForm(modificationGroup);
        app.getGroupHelper().gotoGroupsPage();
        List<GroupData> groupsAfter = app.getGroupHelper().getGrupList();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.remove(groupsBefore.size() - 1);
        groupsBefore.add(modificationGroup);
        Assert.assertEquals(new HashSet<>(groupsAfter), new HashSet<>(groupsBefore));
        app.getSessionHelper().logout();
    }
}