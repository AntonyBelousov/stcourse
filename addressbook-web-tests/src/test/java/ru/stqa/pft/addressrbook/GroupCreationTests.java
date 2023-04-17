package ru.stqa.pft.addressrbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class GroupCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    login("admin", "secret");
  }

  @Test
  public void testCroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("groupName", "groupHeader", "groupFooter"));
    submitGroupCreation();
    returnToGroupPage();
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

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }

  private void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  private void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  private void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
