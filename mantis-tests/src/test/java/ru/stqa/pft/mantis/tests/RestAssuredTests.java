package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.IssueBugify;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestAssuredTests extends TestBase {

    @BeforeMethod
    public void checkIssueStatus() {
        skipIfNotFixedBugify(1);
    }

    @Test
    public void testCreateIssue() {
        Set<IssueBugify> oldIssues = app.rest().getIssuesBugify();
        IssueBugify newIssue = new IssueBugify().withSubject("Test Issue").withDescription("New Test Issue");
        int issueId = app.rest().createIssueBugify(newIssue);
        Set<IssueBugify> newIssues = app.rest().getIssuesBugify();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
