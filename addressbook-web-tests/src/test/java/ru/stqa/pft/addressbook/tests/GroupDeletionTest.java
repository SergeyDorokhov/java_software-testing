package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groups();
        if (app.goTo().list().size() == 0) {
            app.goTo().create(new GroupData().withName("111"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Set<GroupData> before = app.goTo().all();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().delete(deletedGroup);
        Set<GroupData> after = app.goTo().all();
        before.remove(deletedGroup);
        Assert.assertEquals(after, before);
    }
}