package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

public class GroupEditTests extends TestBase{

    private GroupData groupData;
    private GroupData editedGroupData;

    @BeforeMethod
    public void prepare() {
        groupData = TestDataProvider.getNewGroupData();
        editedGroupData = TestDataProvider.getNewGroupData();
    }

    @Test
    public void testGroupEdit() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();

        app.getGroupHelper().selectGroup(groupData.getName());
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().fillGroupForm(editedGroupData);
        app.getGroupHelper().returnToGroupPage();
        app.logout();
    }
}
