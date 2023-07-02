package ru.stqa.pft.addressbook.appmanager;

import org.apache.commons.lang3.RandomStringUtils;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    private static final int randomValueLength = 8;
    private static final int phoneNumberLength = 10;

    public static ContactData getNewContactData() {
        return new ContactData()
                .withFirstName(generateRandomString(randomValueLength))
                .withLastName(generateRandomString(randomValueLength))
                .withCompany(generateRandomString(randomValueLength))
                .withAddress(generateRandomString(randomValueLength))
                .withNickName(generateRandomString(randomValueLength))
                .withEmail(generateRandomString(randomValueLength) + "@mail.com")
                .withEmail2(generateRandomString(randomValueLength) + "@mail.com")
                .withEmail3(generateRandomString(randomValueLength) + "@mail.com")
                .withHomePhone("+7" + generateRandomTelephoneNumber(phoneNumberLength))
                .withMobilePhone("+7" + generateRandomTelephoneNumber(phoneNumberLength))
                .withWorkPhone("+7" + generateRandomTelephoneNumber(phoneNumberLength))
                .withPhoto(new File("src/test/resources/cat.png"));
    }

    public static GroupData getNewGroupData() {
        return new GroupData()
                .withName(generateRandomString(randomValueLength))
                .withHeader(generateRandomString(randomValueLength))
                .withFooter(generateRandomString(randomValueLength));
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
