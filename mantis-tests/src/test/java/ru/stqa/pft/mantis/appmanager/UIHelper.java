package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UIHelper extends BaseHelper{

    public UIHelper(ApplicationManager app) {
        super(app);
    }

    public void setNewUserPassword(String newPassword) {
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("[type='submit']"));
    }

    public void resetUserPassword(){
        click(By.id("manage-user-reset-form"));
    }

    public void login(String username, String password) {
        type(By.name("username"),username);
        click(By.cssSelector("[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("[type='submit']"));
    }

    public void manageUsers() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }
}
