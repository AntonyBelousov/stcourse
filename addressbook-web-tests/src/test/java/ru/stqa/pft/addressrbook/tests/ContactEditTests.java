package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;

public class ContactEditTests extends TestBase {

    @Test
    public void testEditContactFromHomePage() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(TestDataProvider.getNewContactData());
        }
        app.getNavigationHelper().gotoToHomePage();
        app.getContactHelper().goToContactEditPage();
        app.getContactHelper().fillContactForm(TestDataProvider.getNewContactData());
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoToHomePage();
    }
}
