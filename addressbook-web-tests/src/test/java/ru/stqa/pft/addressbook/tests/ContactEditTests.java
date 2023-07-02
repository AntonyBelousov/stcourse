package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditTests extends TestBase {

    private ContactData contact;

    @BeforeMethod
    public void prepare() {
        contact = TestDataProvider.getNewContactData();
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(contact);
        }
    }

    @Test
    public void testEditContact() {
        Contacts contactsBefore = app.contact().all();
        ContactData modifiedContact = contactsBefore.iterator().next();
        contact.withId(modifiedContact.getId());
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(contactsBefore.size()));
        Contacts contactsAfter = app.contact().all();

        assertThat(contactsAfter, equalTo(contactsBefore.without(modifiedContact).withAdded(contact)));
    }

}
