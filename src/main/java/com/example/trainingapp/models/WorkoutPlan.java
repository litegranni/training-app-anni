package com.example.trainingapp.models;

import java.util.List;

public class WorkoutPlan {
    private String title;
    private String description;
    private List<Exercise> exercises;

    public WorkoutPlan(String title, String description, List<Exercise> exercises) {
        this.title = title;
        this.description = description;
        this.exercises = exercises;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<Exercise> getExercises() { return exercises; }
}
