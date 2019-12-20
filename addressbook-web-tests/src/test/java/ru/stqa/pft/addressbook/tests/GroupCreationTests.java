package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groups();
        List<GroupData> before = app.goTo().list();
        GroupData group = new GroupData().withName("111");
        app.goTo().create(group);
        List<GroupData> after = app.goTo().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        after.sort(byId);
        before.add(after.get(after.size() - 1));
        before.sort(byId);
        Assert.assertEquals(before, after);
    }
}