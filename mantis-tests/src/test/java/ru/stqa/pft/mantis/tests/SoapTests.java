package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.IssueMantis;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase {

    @BeforeMethod
    public void checkIssueStatus() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixedMantis(1);
    }


    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        IssueMantis issueMantis = new IssueMantis()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());
        IssueMantis created = app.soap().addIssue(issueMantis);
        assertEquals(issueMantis.getSummary(), created.getSummary());
    }
}
