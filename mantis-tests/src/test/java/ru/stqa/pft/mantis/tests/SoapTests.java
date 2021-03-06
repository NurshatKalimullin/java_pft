package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws com.google.protobuf.ServiceException, MalformedURLException, ServiceException, RemoteException {
        try {
            skipIfNotFixed(0000002);
            Set<Project> projects = app.soap().getProjects();
            System.out.println(projects.size());
            for (Project project : projects) {
                System.out.println(project.getName());
            }
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException, com.google.protobuf.ServiceException {
        try {
            skipIfNotFixed(0000001);
            Set<Project> projects = app.soap().getProjects();
            Issue issue = new Issue().withSummary("Test issue")
                    .withDescription("Test issue description").withProject(projects.iterator().next());
            Issue created = app.soap().addIssue(issue);
            assertEquals(issue.getSummary(), created.getSummary());
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

}
