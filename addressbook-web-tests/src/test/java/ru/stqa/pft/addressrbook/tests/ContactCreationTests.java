package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = TestDataProvider.getNewContactData();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contactData);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoToHomePage();
    }

    @Test
    public void testEmptyContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoToHomePage();
    }

}

