package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  private GroupData group;

  @BeforeMethod
  public void prepare() {
    group = TestDataProvider.getNewGroupData();
    app.goTo().groupPage();
  }

  @Test
  public void testGroupCreation() {
    Set<GroupData> groupListBefore = app.group().all();
    app.group().create(group);
    Set<GroupData> groupListAfter = app.group().all();
    Assert.assertEquals(groupListAfter.size(), groupListBefore.size() + 1);

    groupListBefore.add(group.withId(groupListAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    Assert.assertEquals(groupListBefore, groupListAfter);
  }
}
