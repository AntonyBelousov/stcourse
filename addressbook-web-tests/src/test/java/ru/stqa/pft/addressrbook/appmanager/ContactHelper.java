package ru.stqa.pft.addressrbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressrbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(ChromeDriver wd) {
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
}
