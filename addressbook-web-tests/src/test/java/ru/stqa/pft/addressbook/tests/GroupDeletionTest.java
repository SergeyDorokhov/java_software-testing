package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

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
        List<GroupData> before = app.goTo().list();
        int index = before.size() - 1;
        app.goTo().delete(index);
        List<GroupData> after = app.goTo().list();
        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(after, before);
    }
}