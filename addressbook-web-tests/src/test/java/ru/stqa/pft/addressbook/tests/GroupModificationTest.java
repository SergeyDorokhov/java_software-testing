package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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
        Set<GroupData> before = app.goTo().all();
        GroupData modyfiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modyfiedGroup.getId()).
                withName("111").withHeader("111").withFooter("222");
        app.goTo().modify(group);
        Set<GroupData> after = app.goTo().all();
        before.remove(modyfiedGroup);
        before.add(group);
        Assert.assertEquals(after, before);
    }
}