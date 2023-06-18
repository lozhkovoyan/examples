package ru.fssp.odpea.controller;

import jakarta.persistence.ManyToOne;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/model/api1")
@RestController
public class ModelController {

    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/")
    public void create(/*@RequestBody ModelName modelName*/) {
        /*if (modelName != null) {
            modelService.create(modelName);*/
        System.out.println("hallo");
        }
    }
