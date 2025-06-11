package com.example.trainingapp.service;

import com.example.trainingapp.models.WorkoutPlan;
import com.example.trainingapp.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanService {

    private final ExerciseRepository exerciseRepo;

    public WorkoutPlanService(ExerciseRepository exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    public List<WorkoutPlan> getAllPlans() {
        return List.of(
                new WorkoutPlan(
                        "Helkroppspass – utan redskap",
                        "30 minuter träning hemma utan utrustning",
                        List.of(
                                exerciseRepo.findByName("Knäböj"),
                                exerciseRepo.findByName("Armhävningar"),
                                exerciseRepo.findByName("Plankan"),
                                exerciseRepo.findByName("Burpees")
                        ).stream().filter(e -> e != null).toList()
                ),
                new WorkoutPlan(
                        "Corefokus – stabilitet",
                        "Perfekt för mage och bål",
                        List.of(
                                exerciseRepo.findByName("Plankan"),
                                exerciseRepo.findByName("Crunches"),
                                exerciseRepo.findByName("Russian Twist")
                        ).stream().filter(e -> e != null).toList()
                ),
                new WorkoutPlan(
                        "Gympass – Rygg & Armar",
                        "Styrkepass med vikter",
                        List.of(
                                exerciseRepo.findByName("Bicepscurl"),
                                exerciseRepo.findByName("Dips"),
                                exerciseRepo.findByName("Latsdrag"),
                                exerciseRepo.findByName("Rodd")
                        ).stream().filter(e -> e != null).toList()
                )
        );
    }
}
