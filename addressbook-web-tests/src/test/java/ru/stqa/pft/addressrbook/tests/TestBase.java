package ru.stqa.pft.addressrbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressrbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(Browser.CHROME.browserName());

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
