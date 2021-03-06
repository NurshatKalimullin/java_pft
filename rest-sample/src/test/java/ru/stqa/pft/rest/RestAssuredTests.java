package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests extends TestBase {

    @BeforeClass
    public void init(){
        RestAssured.authentication =RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        try {
            skipIfNotFixed(oldIssues, 1288);
            Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");;
            int issueId = createIssue(newIssue);
            Set<Issue> newIssues = getIssues();
            oldIssues.add(newIssue.withId(issueId));
            Assert.assertEquals(newIssues,oldIssues);
        } catch (
                SkipException e) {
            System.out.println(e);
        }
    }


    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    }


    private int createIssue(Issue newIssue) throws IOException {
        String json= RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
