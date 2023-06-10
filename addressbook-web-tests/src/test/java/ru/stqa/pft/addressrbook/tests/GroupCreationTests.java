package ru.stqa.pft.addressrbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;
import ru.stqa.pft.addressrbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  private GroupData group;

  @BeforeMethod
  public void prepare() {
    group = TestDataProvider.getNewGroupData();
    app.goTo().groupPage();
  }

  @Test
  public void testGroupCreation() {
    Groups groupsBefore = app.group().all();
    app.group().create(group);
    Groups groupsAfter = app.group().all();

    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
    assertThat(groupsAfter, equalTo(
            groupsBefore.withAdded(group.withId(groupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
