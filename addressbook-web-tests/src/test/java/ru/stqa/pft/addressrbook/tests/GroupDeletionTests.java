package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    private GroupData groupData;

    @BeforeMethod
    public void prepare() {
        groupData = TestDataProvider.getNewGroupData();
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(groupData);
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> groupListBefore = app.group().list();
        int index = groupListBefore.size()-1;
        app.group().delete(index);
        List<GroupData> groupListAfter = app.group().list();
        Assert.assertEquals(groupListAfter.size(), groupListBefore.size() - 1);

        groupListBefore.remove(index);
        Assert.assertEquals(groupListBefore, groupListAfter);
    }
}
