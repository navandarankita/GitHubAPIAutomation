package com.github.POJO;

public class CreateRepoRequest {

    private String name;
    private String description;
    private String homepage;
    private boolean privateRepo;

    // Default constructor
    public CreateRepoRequest() {}

    // Parameterized constructor
    public CreateRepoRequest(String name, String description, String homepage, boolean privateRepo) {
        this.name = name;
        this.description = description;
        this.homepage = homepage;
        this.privateRepo = privateRepo;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public boolean isPrivateRepo() {
        return privateRepo;
    }

    public void setPrivateRepo(boolean privateRepo) {
        this.privateRepo = privateRepo;
    }

    @Override
    public String toString() {
        return "CreateRepoRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", homepage='" + homepage + '\'' +
                ", privateRepo=" + privateRepo +
                '}';
    }
}

