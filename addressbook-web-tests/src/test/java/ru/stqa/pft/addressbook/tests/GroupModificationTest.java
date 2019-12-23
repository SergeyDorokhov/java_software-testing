package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void insurePreconditions() {
        app.goTo().groups();
        if (app.goTo().all().size() == 0) {
            app.goTo().create(new GroupData().withName("111"));
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        Groups before = app.goTo().all();
        GroupData modyfiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modyfiedGroup.getId()).
                withName("111").withHeader("111").withFooter("222");
        app.goTo().modify(group);
        assertThat(app.goTo().count(), equalTo(before.size()));
        Groups after = app.goTo().all();
        assertThat(after, equalTo(before.without(modyfiedGroup).withAdded(group)));
    }
}