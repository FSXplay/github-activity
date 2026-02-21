package api;

import org.json.JSONObject;

public class APItest {
    private static final String USERNAME = "fsxplay";
    private static final String REPO = "roadmapsh-task-tracker-cli";

    public static void main(String[] args) {
        testCompareTwoCommits();
    }

    private static void testCompareTwoCommits() {
        JSONObject response = GitHubClientAPI.compareTwoCommits(USERNAME, REPO, "12b0d1e79157e9f870df0907de059cff1e6a509b", "286e10d20eb76a69df11b9bfc88d28e3871be648");
        System.out.println(response.toString());
    }
}
