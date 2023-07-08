package ru.stqa.pft.mantis.tests;

import io.restassured.RestAssured;
import org.openqa.selenium.remote.Browser;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
        RestAssured.authentication = RestAssured.basic("b31e382ca8445202e66b03aaf31508a3", "");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    boolean isIssueOpenMantis(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String status = app.soap().getIssueStatus(BigInteger.valueOf(issueId));
        return !(status.equals("resolved") || status.equals("closed"));
    }

    public void skipIfNotFixedMantis(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpenMantis(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    boolean isIssueOpenBugify(int issueId) {
        String status = app.rest().getIssueStatusBugify(issueId);
        return status.equals("Open") || status.equals("In Progress");
    }

    public void skipIfNotFixedBugify(int issueId) {
        if (isIssueOpenBugify(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
