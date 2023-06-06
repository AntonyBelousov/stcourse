package ru.stqa.pft.addressrbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void addNextContact() {
        click(By.linkText("add next"));
    }

    public void selectContactInTable() {
        click(By.xpath("//input[@type='checkbox']"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectAllContactsInTable() {
        click(By.id("MassCB"));
    }

    public void addContactsInTable(List<ContactData> contactDataList) {
        initContactCreation();
        contactDataList.forEach(contactData -> {
            fillContactForm(contactData);
            submitContactCreation();
            addNextContact();
        });
    }

    public void setContactId(ContactData contactData) {
        contactData.setId(wd.findElement(By.xpath(format("//input[@accept='%s']", contactData.getEmail()))).getAttribute("id"));
    }

    public void goToContactEditPage() {
        click(By.cssSelector("[alt='Edit']"));
    }

    public void goToContactEditPageById(String contactId) {
        click(By.xpath(format("//a[@href='edit.php?id=%s']", contactId)));
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            ContactData contact = new ContactData();
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}

