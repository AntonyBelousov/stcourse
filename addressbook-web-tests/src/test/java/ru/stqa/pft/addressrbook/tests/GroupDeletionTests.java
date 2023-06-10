package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;
import ru.stqa.pft.addressrbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups groupsBefore = app.group().all();
        GroupData deletedGroup = groupsBefore.iterator().next();
        app.group().delete(deletedGroup);
        Groups groupsAfter = app.group().all();

        assertEquals(groupsAfter.size(), groupsBefore.size() - 1);
        assertThat(groupsAfter, equalTo(groupsBefore.without(deletedGroup)));
    }
}
