package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "lastName", "nickName", "Amazon",
                "333-55-00", "testmail@testorg.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoToHomePage();
    }

}

