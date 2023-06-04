package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  private GroupData groupData;

  @BeforeMethod
  public void prepare() {
    groupData = TestDataProvider.getNewGroupData();
  }

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> groupListBefore = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(groupData);
    List<GroupData> groupListAfter = app.getGroupHelper().getGroupList();
    Assert.assertEquals(groupListAfter.size(), groupListBefore.size() + 1);
  }
}
