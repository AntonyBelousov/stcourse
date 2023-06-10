package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;
import ru.stqa.pft.addressrbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    private ContactData contact;

    @BeforeMethod
    public void prepare() {
        contact = TestDataProvider.getNewContactData();
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(contact);
        }
    }
    
    @Test
    public void testContactDeletion() {
        Contacts contactsBefore = app.contact().all();
        ContactData deletedContact = contactsBefore.iterator().next();
        app.contact().delete(deletedContact);
        Contacts contactsAfter = app.contact().all();

        assertThat(contactsAfter, equalTo(contactsBefore.without(deletedContact)));
    }
}