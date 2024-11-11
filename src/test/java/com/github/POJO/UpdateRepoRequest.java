package com.github.POJO;

public class UpdateRepoRequest {

    private String name;
    private String description;
    private boolean privateRepo;

    // Getters and Setters

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

    public boolean isPrivateRepo() {
        return privateRepo;
    }

    public void setPrivateRepo(boolean privateRepo) {
        this.privateRepo = privateRepo;
    }

    @Override
    public String toString() {
        return "UpdateRepoRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", privateRepo=" + privateRepo +
                '}';
    }
}

