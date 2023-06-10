package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testEditContactFromHomePage() {
        ContactData editedContact = TestDataProvider.getNewContactData();
        app.goTo().homePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(TestDataProvider.getNewContactData());
        }
        app.goTo().homePage();
        List<ContactData> contactsBefore = app.getContactHelper().getContactList();
        editedContact.setId(contactsBefore.get(0).getId());
        app.getContactHelper().goToContactEditPage();
        app.getContactHelper().fillContactForm(editedContact);
        app.getContactHelper().submitContactUpdate();
        app.goTo().homePage();

        List<ContactData> contactsAfter = app.getContactHelper().getContactList();
        contactsBefore.remove(0);
        contactsBefore.add(editedContact);
        Assert.assertEquals(new HashSet<>(contactsAfter), new HashSet<>(contactsBefore));
    }
}
