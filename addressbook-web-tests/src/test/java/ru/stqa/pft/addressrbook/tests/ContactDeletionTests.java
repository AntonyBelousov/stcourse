package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {
    
    @Test
    public void testContactDeletion() {
        ContactData contactData = TestDataProvider.getNewContactData();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(contactData);
        }
        app.getNavigationHelper().gotoToHomePage();
        List<ContactData> contactsBefore = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactInTable();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoToHomePage();

        contactsBefore.remove(0);
        List<ContactData> contactsAfter = app.getContactHelper().getContactList();
        Assert.assertEquals(new HashSet<>(contactsAfter), new HashSet<>(contactsBefore));
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
}