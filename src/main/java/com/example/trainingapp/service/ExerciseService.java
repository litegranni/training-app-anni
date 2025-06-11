package com.example.trainingapp.service;

import com.example.trainingapp.models.Exercise;
import com.example.trainingapp.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<Exercise> getAllExercises() {
        return repository.findAll();
    }

    public List<Exercise> getByMuscle(String muscleGroup) {
        return repository.findByMuscleGroup(muscleGroup);
    }

    public Exercise getByName(String name) {
        return repository.findByName(name);
    }
}

