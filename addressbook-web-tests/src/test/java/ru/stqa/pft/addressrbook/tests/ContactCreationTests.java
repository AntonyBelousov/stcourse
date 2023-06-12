package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.ContactData;
import ru.stqa.pft.addressrbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        File photo = new File("src/test/resources/cat.png");
        ContactData contact = TestDataProvider.getNewContactData().withPhoto(photo);
        app.goTo().homePage();
        Contacts contactsBefore = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(contactsBefore.size()+1));
        Contacts contactsAfter = app.contact().all();

        assertThat(contactsAfter, equalTo(
                contactsBefore.withAdded(contact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File dir = new File(".");
        System.out.println(dir.getAbsolutePath());
    }
}

