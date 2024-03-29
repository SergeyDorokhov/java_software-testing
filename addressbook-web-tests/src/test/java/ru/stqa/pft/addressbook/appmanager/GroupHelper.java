package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
        click(By.cssSelector("input[type='submit']"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectGroup(GroupData group) {
        wd.findElement(By.cssSelector("input[value='" + group.getId() + "']")).click();
    }

    public void modificationSelectedGroup() {
        click(By.xpath("(//input[@name='edit'])[2]"));
    }

    public void groups() {
        click(By.linkText("groups"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        groupCache = null;
        click(By.linkText("groups"));
    }

    public void modify(int index, GroupData group) {
        selectGroup(index);
        modificationSelectedGroup();
        fillGroupForm(group);
        groups();
    }

    public void modify(GroupData group) {
        selectGroup(group);
        modificationSelectedGroup();
        fillGroupForm(group);
        groupCache = null;
        groups();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroup();
        groups();
    }

    public void delete(GroupData deletedGroup) {
        selectGroup(deletedGroup);
        deleteSelectedGroup();
        groupCache = null;
        groups();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(element.getText()));
        }
        return groups;
    }

    private Groups groupCache;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(element.getText()));
        }
        return new Groups(groupCache);
    }
}