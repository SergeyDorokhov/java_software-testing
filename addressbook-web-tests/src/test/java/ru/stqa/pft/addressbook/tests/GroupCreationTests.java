package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groups();
        Groups before = app.goTo().all();
        GroupData group = new GroupData().withName("111");
        app.goTo().create(group);
        assertThat(app.goTo().count(), equalTo(before.size() + 1));
        Groups after = app.goTo().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.
                stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}