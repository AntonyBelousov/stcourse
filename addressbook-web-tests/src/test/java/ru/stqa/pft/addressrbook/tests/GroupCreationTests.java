package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  private GroupData group;

  @BeforeMethod
  public void prepare() {
    group = TestDataProvider.getNewGroupData();
    app.getNavigationHelper().gotoGroupPage();
  }

  @Test
  public void testGroupCreation() {
    List<GroupData> groupListBefore = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(group);
    List<GroupData> groupListAfter = app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupListAfter.size(), groupListBefore.size() + 1);

    group.setId(groupListAfter.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    groupListBefore.add(group);
    Assert.assertEquals(new HashSet<Object>(groupListAfter), new HashSet<Object>(groupListBefore));
  }
}
