package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

    private GroupData group;

    @BeforeMethod
    public void prepare() {
        app.goTo().groupPage();
        group = TestDataProvider.getNewGroupData();
        if (app.db().groups().size() == 0) {
            app.group().create(TestDataProvider.getNewGroupData());
        }
    }

    @Test
    public void testGroupEdit() {
        Groups groupsBefore = app.db().groups();
        GroupData modifiedGroup = groupsBefore.iterator().next();
        group.withId(modifiedGroup.getId());
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(groupsBefore.size()));
        Groups groupsAfter = app.db().groups();

        assertThat(groupsAfter, equalTo(groupsBefore.without(modifiedGroup).withAdded(group)));
    }
}
