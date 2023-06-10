package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  private GroupData group;

  @BeforeMethod
  public void prepare() {
    group = TestDataProvider.getNewGroupData();
    app.goTo().groupPage();
  }

  @Test
  public void testGroupCreation() {
    List<GroupData> groupListBefore = app.group().list();
    app.group().create(group);
    List<GroupData> groupListAfter = app.group().list();
    Assert.assertEquals(groupListAfter.size(), groupListBefore.size() + 1);

    group.withId(groupListAfter.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    groupListBefore.add(group);
    Assert.assertEquals(new HashSet<Object>(groupListAfter), new HashSet<Object>(groupListBefore));
  }
}
