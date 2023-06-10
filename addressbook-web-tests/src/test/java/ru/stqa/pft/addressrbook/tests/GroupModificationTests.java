package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

    private GroupData group;
    private GroupData editedGroup;

    @BeforeMethod
    public void prepare() {
        group = TestDataProvider.getNewGroupData();
        editedGroup = TestDataProvider.getNewGroupData();
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(group);
        }
    }

    @Test
    public void testGroupEdit() {
        List<GroupData> groupListBefore = app.group().list();
        int index = groupListBefore.size()-1;
        editedGroup.withId(groupListBefore.get(index).getId());
        app.group().modify(index, editedGroup);
        List<GroupData> groupListAfter = app.group().list();

        Assert.assertEquals(groupListAfter.size(), groupListBefore.size());
        groupListBefore.remove(index);
        groupListBefore.add(editedGroup);
        Assert.assertEquals(new HashSet<Object>(groupListBefore), new HashSet<Object>(groupListAfter));
    }
}
