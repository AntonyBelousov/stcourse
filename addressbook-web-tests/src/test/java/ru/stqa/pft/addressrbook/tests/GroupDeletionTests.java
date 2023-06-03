package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    private GroupData groupData;

    @BeforeMethod
    public void prepare() {
        groupData = TestDataProvider.getNewGroupData();
    }

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();

        app.getGroupHelper().selectGroup(groupData.getName());
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        app.logout();
    }

}
