package ru.stqa.pft.addressrbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressrbook.appmanager.TestDataProvider;
import ru.stqa.pft.addressrbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  private GroupData groupData;

  @BeforeMethod
  public void prepare() {
    groupData = TestDataProvider.getNewGroupData();
  }

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(groupData);
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }
}
