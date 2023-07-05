package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase{

    private ContactData contact;
    private GroupData group;

    @BeforeMethod
    public void prepare() {
        if (app.db().contacts().size() == 0) {
            contact = TestDataProvider.getNewContactData();
            app.contact().create(contact);
            contact.withId(app.db().contacts().stream().mapToInt((g) -> g.getId()).max().getAsInt());
        } else {
            contact = app.db().contacts().iterator().next();
        }

        if (app.db().groups().size() == 0) {
            group = TestDataProvider.getNewGroupData();
            app.group().create(group);
            group.withId(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt());
        }  else  {
            group = app.db().groups().iterator().next();
        }
        app.goTo().homePage();
        app.contact().addContactToGroup(contact.getId(), group.getId());
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        Contacts contactsInGroupBefore = app.db().groups().stream()
                .filter(g -> g.getId() == group.getId())
                .findFirst()
                .get()
                .getContacts();
        app.contact().deleteContactFromGroup(contact.getId(), group.getId());
        Contacts contactsInGroupAfter = app.db().groups().stream()
                .filter(g -> g.getId() == group.getId())
                .findFirst()
                .get()
                .getContacts();

        assertThat(contactsInGroupAfter, equalTo(contactsInGroupBefore.without(contact)));
    }
}
