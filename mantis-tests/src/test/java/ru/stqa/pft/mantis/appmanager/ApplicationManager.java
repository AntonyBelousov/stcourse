package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private RegistrationHelper registrationHelper;
    private MailHelper mailHelper;

    private String browser;
    private FtpHelper ftpHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if(browser.equals(Browser.CHROME.browserName())) {
                wd = new ChromeDriver();
            } else if(browser.equals(Browser.FIREFOX.browserName())) {
                wd = new FirefoxDriver();
            } else if(browser.equals(Browser.EDGE.browserName())) {
                wd = new EdgeDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            wd.get(properties.getProperty("baseUrl"));
        }
        return wd;
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper =  new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftpHelper == null) {
            ftpHelper =  new FtpHelper(this);
        }
        return ftpHelper;
    }

    public MailHelper mail(){
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
}
