package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    private GroupData group;

    @BeforeMethod
    public void prepare() {
        group = TestDataProvider.getNewGroupData();
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(TestDataProvider.getNewGroupData());
        }
    }

    @Test
    public void testGroupEdit() {
        Set<GroupData> groupListBefore = app.group().all();
        GroupData modifiedGroup = groupListBefore.iterator().next();
        group.withId(modifiedGroup.getId());
        app.group().modify(group);
        Set<GroupData> groupListAfter = app.group().all();

        Assert.assertEquals(groupListAfter.size(), groupListBefore.size());
        groupListBefore.remove(modifiedGroup);
        groupListBefore.add(group);
        Assert.assertEquals(groupListBefore, groupListAfter);
    }
}
