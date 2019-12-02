package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }
    public void gotoHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void gotoGroupsPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }
}