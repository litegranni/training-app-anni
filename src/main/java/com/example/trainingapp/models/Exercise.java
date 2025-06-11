package com.example.trainingapp.models;

public class Exercise {
    private String name;
    private String muscleGroup;
    private String equipment;
    private String difficulty;

    public Exercise(String name, String muscleGroup, String equipment, String difficulty) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.difficulty = difficulty;
    }

    // lite goa getters
    public String getName() { return name; }
    public String getMuscleGroup() { return muscleGroup; }
    public String getEquipment() { return equipment; }
    public String getDifficulty() { return difficulty; }

    @Override
    public String toString() {
        return name + " (" + muscleGroup + ", " + equipment + ", " + difficulty + ")";
    }
}
