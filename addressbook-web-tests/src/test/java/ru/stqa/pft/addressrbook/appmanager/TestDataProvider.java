package ru.stqa.pft.addressrbook.appmanager;

import org.apache.commons.lang3.RandomStringUtils;
import ru.stqa.pft.addressrbook.model.ContactData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestDataProvider {

    public static ContactData getNewContactData() {
        int randomValueLength = 9;
        int phoneNumberLength = 10;
        ContactData contact = new ContactData();
        contact.setFirstName(generateRandomString(randomValueLength));
        contact.setLastName(generateRandomString(randomValueLength));
        contact.setCompany(generateRandomString(randomValueLength));
        contact.setNickName(generateRandomString(randomValueLength));
        contact.setEmail(generateRandomString(randomValueLength));
        contact.setPhoneHome(generateRandomTelephoneNumber(phoneNumberLength));
        return contact;
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }

    public static String generateRandomTelephoneNumber(int length) {
        return RandomStringUtils.randomNumeric(length).toLowerCase();
    }

    public static List<ContactData> getNewContactDataList(int contactsCount) {
        List<ContactData> contactDataList = new ArrayList<>();
        for (int i = 0; i < contactsCount; i++) {
            contactDataList.add(TestDataProvider.getNewContactData());
        }
        return contactDataList;
    }
}
