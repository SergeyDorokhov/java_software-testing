package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void insurePreconditions() {
        app.getGroupHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("111", null, null));
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        int index = groupsBefore.size() - 1;
        GroupData modificationGroup = new GroupData(groupsBefore.get(index).getId(),
                "111", "111", "222");
        app.getGroupHelper().modifyGroup(index, modificationGroup);
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.remove(index);
        groupsBefore.add(modificationGroup);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsAfter.sort(byId);
        groupsBefore.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }
}