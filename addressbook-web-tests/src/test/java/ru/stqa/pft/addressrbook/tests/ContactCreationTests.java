package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = TestDataProvider.getNewContactData();

        List<ContactData> contactsBefore = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(contactData);
        app.getContactHelper().submitContactCreation();
        app.goTo().homePage();
        contactsBefore.add(contactData);
        List<ContactData> contactsAfter = app.getContactHelper().getContactList();

        Assert.assertEquals(new HashSet<>(contactsAfter), new HashSet<>(contactsBefore));
    }

    @Test
    public void testEmptyContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().submitContactCreation();
        app.goTo().homePage();
    }

}

