package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditTests extends TestBase{

    private GroupData group;
    private GroupData editedGroup;

    @BeforeMethod
    public void prepare() {
        group = TestDataProvider.getNewGroupData();
        editedGroup = TestDataProvider.getNewGroupData();
    }

    @Test
    public void testGroupEdit() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(group);
        }

        List<GroupData> groupListBefore = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(groupListBefore.size()-1);
        editedGroup.setId(groupListBefore.get(groupListBefore.size()-1).getId());
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().fillGroupForm(editedGroup);
        app.getContactHelper().submitContactUpdate();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> groupListAfter = app.getGroupHelper().getGroupList();

        Assert.assertEquals(groupListAfter.size(), groupListBefore.size());
        groupListBefore.remove(groupListBefore.size()-1);
        groupListBefore.add(editedGroup);
        Assert.assertEquals(new HashSet<Object>(groupListBefore), new HashSet<Object>(groupListAfter));
    }
}
