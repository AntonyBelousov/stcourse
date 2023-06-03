package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.Collections;

public class ContactEditTests extends TestBase {

    private ContactData contactData;

    @BeforeMethod
    public void prepare() {
        contactData = TestDataProvider.getNewContactData();
        app.getContactHelper().addContactsInTable(Collections.singletonList(contactData));
        app.getNavigationHelper().gotoToHomePage();
        app.getContactHelper().setContactId(contactData);
    }

    @Test
    public void testEditContactFromHomePage() {
        ContactData editedContactData = TestDataProvider.getNewContactData();
        app.getContactHelper().goToContactEditPage(contactData.getId());
        app.getContactHelper().fillContactForm(editedContactData);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoToHomePage();
    }
}
