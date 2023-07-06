package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {

    private UserData user;

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
        user = app.db().users().stream()
                .filter(u -> !u.getUsername().equals("administrator"))
                .collect(Collectors.toList()).iterator().next();
    }

    @Test
    public void testChangeUserPasswordByAdmin() throws IOException {
        String password = "newPassword";

        app.uiHelper().login(app.getProperty("adminLogin"),app.getProperty("adminPassword"));
        app.navigation().goToManagePage();
        app.navigation().goToManageUsersPage();
        app.navigation().goToManageUserEditPage(user);
        app.uiHelper().resetUserPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        app.navigation().goTo(app.mail().findConfirmationLink(mailMessages, user.getEmail()));
        app.uiHelper().setNewUserPassword(password);

        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
