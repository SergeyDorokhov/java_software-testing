package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getGroupHelper().gotoGroupsPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("111", null, null));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        int index = groupsBefore.size() - 1;
        app.getGroupHelper().deleteGroup(index);
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupsAfter.size(), index);
        groupsBefore.remove(index);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }
}