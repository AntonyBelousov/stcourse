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
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(groupData);
        }

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        app.logout();
    }

}
