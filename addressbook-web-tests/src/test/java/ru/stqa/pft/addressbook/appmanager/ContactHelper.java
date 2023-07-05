package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    private Contacts contactCache = null;

    public void returnToContactPage() {
        click(By.partialLinkText("home"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit'][2]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void goToContactEditPage(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("input[name='selected[]']")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row: rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withAllEmail(allEmail)
                    .withAllPhones(allPhones)
            );
        }
        return new Contacts(contactCache);
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        acceptAlert();
        contactCache = null;
        returnToContactPage();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
    }

    public void modify(ContactData contact) {
        goToContactEditPage(contact.getId());
        fillContactForm(contact);
        submitContactUpdate();
        contactCache = null;
        returnToContactPage();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        goToContactEditPage(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        return new ContactData()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withNickName(nickName)
                .withCompany(company)
                .withAddress(address)
                .withHomePhone(homePhone)
                .withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    public void addContactToGroup(int contactId, int groupId) {
        selectContactById(contactId);
        selectGroupById(groupId);
        click(By.name("add"));
    }

    private void selectGroupById(int groupId) {
        click(By.cssSelector("select[name='to_group']"));
        wd.findElement(By.name("to_group")).findElement(By.cssSelector(String.format("option[value='%s']", groupId))).click();
    }

    public void sortContactsByGroup(int groupId) {
        click(By.cssSelector("select[name='group']"));
        wd.findElement(By.name("group")).findElement(By.cssSelector(String.format("option[value='%s']", groupId))).click();
    }

    public void deleteContactFromGroup(int contactId, int groupId) {
        sortContactsByGroup(groupId);
        selectContactById(contactId);
        click(By.name("remove"));
    }
}

