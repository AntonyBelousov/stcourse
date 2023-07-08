package ru.stqa.pft.mantis.model;

public class IssueMantis {

    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public IssueMantis withId(int id) {
        this.id = id;
        return this;
    }

    public IssueMantis withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public IssueMantis withDescription(String description) {
        this.description = description;
        return this;
    }

    public IssueMantis withProject(Project project) {
        this.project = project;
        return this;
    }
}
