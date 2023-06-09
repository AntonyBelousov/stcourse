package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    private GroupData groupData;

    @BeforeMethod
    public void prepare() {
        groupData = TestDataProvider.getNewGroupData();
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(groupData);
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups groupsBefore = app.db().groups();
        GroupData deletedGroup = groupsBefore.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(groupsBefore.size() - 1));
        Groups groupsAfter = app.db().groups();

        assertThat(groupsAfter, equalTo(groupsBefore.without(deletedGroup)));
        verifyGroupListInUI();
    }
}
