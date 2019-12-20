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
        app.goTo().groups();
        if (app.goTo().list().size() == 0) {
            app.goTo().create(new GroupData().withName("111"));
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        List<GroupData> before = app.goTo().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).
                withName("111").withHeader("111").withFooter("222");
        app.goTo().modify(index, group);
        List<GroupData> after = app.goTo().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(after, before);
    }
}