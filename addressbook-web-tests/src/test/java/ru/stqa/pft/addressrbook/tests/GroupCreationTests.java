package ru.stqa.pft.addressrbook.tests;

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
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(groupData);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    app.logout();
  }
}
