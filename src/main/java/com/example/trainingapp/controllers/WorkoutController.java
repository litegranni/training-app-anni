package com.example.trainingapp.controllers;

import com.example.trainingapp.models.Exercise;
import com.example.trainingapp.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkoutController {

    private final ExerciseService exerciseService;

    public WorkoutController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/exercises/by-muscle")
    public List<Exercise> getByMuscle(@RequestParam String muscleGroup) {
        return exerciseService.getByMuscle(muscleGroup);
    }

    @GetMapping("/exercises/by-name")
    public Exercise getByName(@RequestParam String name) {
        return exerciseService.getByName(name);
    }
}
