package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groups();
        if (app.goTo().all().size() == 0) {
            app.goTo().create(new GroupData().withName("111"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.goTo().all();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().delete(deletedGroup);
        assertThat(app.goTo().count(), equalTo(before.size() - 1));
        Groups after = app.goTo().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}