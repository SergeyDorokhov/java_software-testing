package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getGroupHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("111", null, null));
        }
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(groupsBefore.size() - 1);
        app.getGroupHelper().modificationSelectedGroup();
        GroupData modificationGroup = new GroupData(groupsBefore.get(groupsBefore.size() - 1).getId(),
                "111", "111", "222");
        app.getGroupHelper().fillGroupForm(modificationGroup);
        app.getGroupHelper().gotoGroupsPage();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.remove(groupsBefore.size() - 1);
        groupsBefore.add(modificationGroup);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsAfter.sort(byId);
        groupsBefore.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
        app.getSessionHelper().logout();
    }
}