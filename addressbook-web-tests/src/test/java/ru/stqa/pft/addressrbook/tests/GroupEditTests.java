package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.List;

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
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(groupData);
        }

        List<GroupData> groupListBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(groupListBefore.size()-1);
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().fillGroupForm(editedGroupData);
        app.getContactHelper().submitContactUpdate();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> groupListAfter = app.getGroupHelper().getGroupList();
        Assert.assertEquals(groupListAfter.size(), groupListBefore.size());
    }
}
