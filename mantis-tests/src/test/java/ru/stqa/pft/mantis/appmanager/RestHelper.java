package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import ru.stqa.pft.mantis.model.IssueBugify;

import java.util.Set;

public class RestHelper extends BaseHelper{

    public RestHelper(ApplicationManager app) {
        super(app);
    }

    public int createIssueBugify(IssueBugify newIssue) {
        String json = RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json")
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public String getIssueStatusBugify(int issueId) {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issueArray = parsed.getAsJsonObject().getAsJsonArray("issues");
        return issueArray.get(0).getAsJsonObject().get("state_name").getAsString();
    }

    public Set<IssueBugify> getIssuesBugify() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=300").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<IssueBugify>>() {}.getType());
    }
}
