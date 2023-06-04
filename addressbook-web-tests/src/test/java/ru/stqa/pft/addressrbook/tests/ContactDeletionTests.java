package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(TestDataProvider.getNewContactData());
        }
        app.getNavigationHelper().gotoToHomePage();
        app.getContactHelper().selectContactInTable();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoToHomePage();
    }

    @Test
    public void testAllContactsDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().addContactsInTable(TestDataProvider.getNewContactDataList(4));
        }
        app.getNavigationHelper().gotoToHomePage();
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