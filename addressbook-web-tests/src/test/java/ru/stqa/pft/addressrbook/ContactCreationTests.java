package ru.stqa.pft.addressrbook;

import java.time.Duration;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        login("admin", "secret");
    }

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("firstName", "lastName", "nickName", "Amazon",
                "333-55-00", "testmail@testorg.ru"));
        submitContactCreation();
        returnToHomePage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

    private void login(String user, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(user);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    private void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void submitContactCreation() {
        wd.findElement(By.xpath("//input[@name='submit'][2]")).click();
    }

    private void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
        wd.findElement(By.name("company")).sendKeys(contactData.getAmazon());
        wd.findElement(By.name("home")).sendKeys(contactData.getPhoneHome());
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    private void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }
}

