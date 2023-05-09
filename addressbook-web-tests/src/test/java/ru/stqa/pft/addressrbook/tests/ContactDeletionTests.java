package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void prepare() {
        app.getContactHelper().addContactsInTable(TestDataProvider.getNewContactDataList(4));
        app.getNavigationHelper().gotoToHomePage();
    }

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContactInTable();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoToHomePage();
    }

    @Test
    public void testAllContactsDeletion() {
        app.getContactHelper().selectAllContactsInTable();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoToHomePage();
    }

    @Test
    public void testPressingDeleteButtonWithoutSelectedContacts() {
        app.getContactHelper().deleteContact();
        app.getContactHelper().checkAlertText("No participants selected.");
    }
}