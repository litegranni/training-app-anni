package com.example.trainingapp.controllers;


import com.example.trainingapp.service.ExerciseService;
import com.example.trainingapp.service.WorkoutPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ViewController {

    private final WorkoutPlanService workoutPlanService;
    private final ExerciseService exerciseService;

    public ViewController(ExerciseService exerciseService, WorkoutPlanService workoutPlanService) {
        this.exerciseService = exerciseService;
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping("/plans")
    public String showWorkoutPlans(Model model) {
        model.addAttribute("plans", workoutPlanService.getAllPlans());
        return "plans";
    }

    @GetMapping("/exercises")
    public String showExercises(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        return "exercises";
    }

    @GetMapping("/training")
    public String showExerciseMenu() {
        return "exercise-menu";
    }

}
