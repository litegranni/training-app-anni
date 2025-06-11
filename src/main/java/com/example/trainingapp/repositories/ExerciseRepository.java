package com.example.trainingapp.repositories;

import com.example.trainingapp.models.Exercise;
import org.springframework.stereotype.Repository;

import java.util.List;

//detta kommer i framtiden bytas ut mot den faktiska databasen, bara gjort en "låtsasdatabas" här

@Repository
public class ExerciseRepository {

    private final List<Exercise> allExercises = List.of(
            new Exercise("Knäböj", "Ben", "Ingen", "Lätt"),
            new Exercise("Utfall", "Ben", "Ingen", "Medel"),
            new Exercise("Marklyft", "Ben", "Stång", "Svår"),
            new Exercise("Plankan", "Core", "Ingen", "Medel"),
            new Exercise("Burpees", "Helkropp", "Ingen", "Svår"),
            new Exercise("Armhävningar", "Överkropp", "Ingen", "Lätt"),
            new Exercise("Bicepscurl", "Armar", "Hantlar", "Medel"),
            new Exercise("Crunches", "Core", "Ingen", "Lätt"),
            new Exercise("Russian Twist", "Core", "Ingen", "Medel"),
            new Exercise("Dips", "Armar", "Ingen", "Svår"),
            new Exercise("Latsdrag", "Rygg", "Maskin", "Medel"),
            new Exercise("Rodd", "Rygg", "Maskin", "Medel")
        

    );

    public List<Exercise> findAll() {
        return allExercises;
    }

    public List<Exercise> findByMuscleGroup(String muscleGroup) {
        return allExercises.stream()
                .filter(e -> e.getMuscleGroup().equalsIgnoreCase(muscleGroup))
                .toList();
    }

    public Exercise findByName(String name) {
        return allExercises.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
