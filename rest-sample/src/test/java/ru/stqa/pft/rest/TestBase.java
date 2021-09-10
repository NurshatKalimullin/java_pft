package ru.stqa.pft.rest;

import org.testng.SkipException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class TestBase {



    public boolean isIssueOpen(Set<Issue> issues, int badIssueId) throws IOException {
        Issue chosedIssue = issues.iterator().next();
        Iterator<Issue> iteratedIssues = issues.iterator();
        while (iteratedIssues.next().getId() != badIssueId) {
            chosedIssue = iteratedIssues.next();
        }
        if (chosedIssue.getState_name().equals("resolved")){
            return false;
        } else {
            return true;
        }


    }

    public void skipIfNotFixed(Set<Issue> oldIssues, int issueId) throws IOException {
        if (isIssueOpen(oldIssues, issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
