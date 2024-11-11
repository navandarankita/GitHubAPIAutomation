package com.github.tests;

import com.github.POJO.CreateRepoRequest;
import com.github.POJO.UpdateRepoRequest;
import com.github.base.ApiHelper;
import com.github.base.BaseTest;
import com.github.utils.EnvironmentDetails;
import io.restassured.response.Response;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GithubApiTest extends BaseTest{

    private static ApiHelper apiHelper;
    
    @BeforeClass
    public void setup() {
        apiHelper = new ApiHelper();
    }
    
    @Test
    public void testGetSingleRepository() {
    	String owner = EnvironmentDetails.getProperty("owner");;
        String repo = "Test";
        Response response = apiHelper.sendGetRequest("/repos/" + owner + "/" + repo);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("full_name"), owner + "/" + repo);
        Assert.assertEquals(response.jsonPath().getString("default_branch"), "main");
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
    }

    @Test
    public void testGetSingleRepositoryWithNonExistingRepo() {
    	String owner = EnvironmentDetails.getProperty("owner");;
        String repo = "Testing123";
        Response response = apiHelper.sendGetRequest("/repos/" + owner + "/" + repo);
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "Not Found");
    }

    @Test
    public void testGetAllRepositories() {

    	Response response = apiHelper.sendGetRequest("/user/repos");

        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("Total repositories: " + response.jsonPath().getList("$").size());
        
        List<String> publicRepos = response.jsonPath().getList("findAll { it.private == false }.name");
        System.out.println("Public Repositories:");
        publicRepos.forEach(System.out::println);
        // Validate response header has the correct Content-Type
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
    }

    @Test
    public void testCreateRepo() {
        CreateRepoRequest repoRequest = new CreateRepoRequest();
        repoRequest.setName("Hello-World");
        repoRequest.setDescription("This is your first repo!");
        repoRequest.setHomepage("https://github.com");
        repoRequest.setPrivateRepo(false);

        Response response = apiHelper.sendPostRequest("/user/repos", repoRequest);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "Hello-World");
        Assert.assertEquals(response.jsonPath().getString("owner.login"), "navandarankita");
        Assert.assertEquals(response.jsonPath().getString("owner.type"), "User");
    }
  
    @Test(dependsOnMethods = "testCreateRepo")
    public void testCreateRepoWithExistingName() {
        CreateRepoRequest repoRequest = new CreateRepoRequest();
        repoRequest.setName("Hello-World"); // Existing repo name
        repoRequest.setDescription("This is a test");
        repoRequest.setHomepage("https://github.com");
        repoRequest.setPrivateRepo(false);

        Response response = apiHelper.sendPostRequest("/user/repos", repoRequest);
        Assert.assertEquals(response.statusCode(), 422);
        Assert.assertEquals(response.jsonPath().getString("errors[0].message"), "name already exists on this account");
    }

    @Test(dependsOnMethods = "testCreateRepo")
    public void testUpdateRepository() {
        // Create an instance of UpdateRepoRequest POJO
    	String owner = EnvironmentDetails.getProperty("owner");;
        String repo = "Hello-World";

        UpdateRepoRequest updateRequest = new UpdateRepoRequest();
        updateRequest.setName("Test1");
        updateRequest.setDescription("my repository created using apis after update");
        updateRequest.setPrivateRepo(false); 


        Response response = apiHelper.sendPatchRequest("/repos/" + owner + "/" + repo, updateRequest);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Test1");
        Assert.assertEquals(response.jsonPath().getString("description"), "my repository created using apis after update");
        Assert.assertFalse(response.jsonPath().getBoolean("private"), "False");
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
    }
    
    @Test(dependsOnMethods = "testUpdateRepository")
    public void testDeleteRepository() {
    	String owner = EnvironmentDetails.getProperty("owner");
        String repo = "Test1";  // Replace with the repository name to delete

        Response response = apiHelper.sendDeleteRequest("/repos/" + owner + "/" + repo);
        Assert.assertEquals(response.statusCode(), 204);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody == null || responseBody.isEmpty());

    }

    @Test
    public void testDeleteNonExistingRepository() {
    	String owner = EnvironmentDetails.getProperty("owner");;
        String repo = "NonExistingRepo";  // Replace with a non-existing repository

        Response response = apiHelper.sendDeleteRequest("/repos/" + owner + "/" + repo);

        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "Not Found");
    }

}

