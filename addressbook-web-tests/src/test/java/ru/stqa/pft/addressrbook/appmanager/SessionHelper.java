package ru.stqa.pft.addressrbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(ChromeDriver wd) {
        super(wd);
    }

    public void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
