package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManagePage() {
        click(By.cssSelector("[href='/mantisbt/manage_overview_page.php']"));
    }

    public void goToManageUsersPage() {
        click(By.cssSelector("[href='/mantisbt/manage_user_page.php']"));
    }

    public void goToManageUserEditPage(UserData user) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId())));
    }

    public void goTo(String url) {
        wd.get(url);
    }
}
