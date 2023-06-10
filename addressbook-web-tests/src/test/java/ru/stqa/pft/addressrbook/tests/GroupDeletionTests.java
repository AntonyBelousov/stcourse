package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

    private GroupData groupData;

    @BeforeMethod
    public void prepare() {
        groupData = TestDataProvider.getNewGroupData();
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(groupData);
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> groupListBefore = app.group().all();
        GroupData deletedGroup = groupListBefore.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> groupListAfter = app.group().all();
        Assert.assertEquals(groupListAfter.size(), groupListBefore.size() - 1);

        groupListBefore.remove(deletedGroup);
        Assert.assertEquals(groupListBefore, groupListAfter);
    }
}
