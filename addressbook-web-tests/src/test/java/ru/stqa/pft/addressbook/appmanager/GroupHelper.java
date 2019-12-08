package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void modificationSelectedGroup() {
        click(By.xpath("(//input[@name='edit'])[2]"));
    }

    public void checkExistingGroup() {
        if (!isElementPresent(By.name("selected[]"))) {
            initGroupCreation();
            fillGroupForm(new GroupData("123", "123", "123"));
            click(By.linkText("groups"));
        }
    }
}