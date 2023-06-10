package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;
import ru.stqa.pft.addressrbook.model.Contacts;

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
    public void testEditContactFromHomePage() {
        Contacts contactsBefore = app.contact().all();
        ContactData modifiedContact = contactsBefore.iterator().next();
        contact.withId(modifiedContact.getId());
        app.contact().modify(contact);
        Contacts contactsAfter = app.contact().all();

        assertThat(contactsAfter, equalTo(contactsBefore.without(modifiedContact).withAdded(contact)));
    }

}
