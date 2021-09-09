package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static com.sun.javafx.runtime.async.BackgroundExecutor.getExecutor;

public class TestBase {


    /*
    public boolean isIssueOpen(Set<Issue> issues, int badIssueId) throws IOException {

        String json= getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> oldIssues = new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
        Issue chosedIssue = oldIssues.iterator().next();
        Iterator<Issue> iteratedIssues = oldIssues.iterator();
        while (iteratedIssues.next().getId() != badIssueId) {
            chosedIssue = iteratedIssues.next();
        }
        if (chosedIssue.getState_name().equals("resolved")){
            return false;
        } else {
            return true;
        }


    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

     */
}
