package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.lms.model.Learner;
import com.springboot.lms.service.LearnerService;

import java.util.List;

@RestController
public class LearnerController {


    @Autowired
    private LearnerService learnerService;

    //    PATH: http://localhost:8080/api/learner/add
    @PostMapping("/api/learner/add")
    public Learner insertLearner(@RequestBody Learner learner) {
        return learnerService.insertLearner(learner);
    }

    //    PATH: http://localhost:8080/api/learner/getAll
    @GetMapping("/api/learner/getAll")
    public List<Learner> getAll() {
        return learnerService.getAll();
    }

    @DeleteMapping("/api/learner/delete/{id}")
    public void delete(@PathVariable int id){
        learnerService.deleteById(id);
    }

    @GetMapping("/api/learner/getById/{id}")
    public Learner getById(@PathVariable int id){
        return learnerService.getById(id);
    }

    @PutMapping("/api/learner/update/{id}")
    public Learner updateLearner(@PathVariable int id, @RequestBody Learner updatedLearner) {
        return learnerService.updateLearner(id,updatedLearner);
    }
}
