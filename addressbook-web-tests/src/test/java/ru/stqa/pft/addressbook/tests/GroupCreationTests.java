package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getGroupHelper().gotoGroupsPage();
        List<GroupData> groupsBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().initGroupCreation();
        GroupData newGroup = new GroupData("111", null, null);
        app.getGroupHelper().fillGroupForm(newGroup);
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> groupsAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsAfter.sort(byId);
        groupsBefore.add(groupsAfter.get(groupsAfter.size() - 1));
        groupsBefore.sort(byId);
        Assert.assertEquals(groupsBefore, groupsAfter);
    }
}