package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactCreationForm(ContactData contactData, Boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }


    public void selectContact(ContactData contact) {
        click(By.cssSelector("input[value='" + contact.getId() + "']"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void editSelectedContact(ContactData contact) {
        click(By.cssSelector("a[href*='edit.php?id="+contact.getId()+"']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contactData) {
        gotoContactCreationPage();
        fillContactCreationForm(contactData, true);
        submitContactCreation();
        contactsCache = null;
        click(By.linkText("home"));
    }

    public void delete() {
        selectContact();
        deleteSelectedContact();
        wd.switchTo().alert().accept();
    }

    public void delete(ContactData contact) {
        selectContact(contact);
        deleteSelectedContact();
        contactsCache = null;
        wd.switchTo().alert().accept();
    }

    public void modify(ContactData contact) {
        editSelectedContact(contact);
        fillContactCreationForm((contact), false);
        contactsCache = null;
        submitContactModification();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> names = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(names.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(names.get(2).getText()).
                    withLastName(names.get(1).getText());
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactsCache;

    public Contacts all() {
        if (contactsCache == null) {
            contactsCache = new Contacts();
            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements) {
                List<WebElement> names = element.findElements(By.tagName("td"));
                int id = Integer.parseInt(names.get(0).findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData().withId(id).withFirstName(names.get(2).getText()).
                        withLastName(names.get(1).getText());
                contactsCache.add(contact);
            }
        }
        return new Contacts(contactsCache);
    }
}