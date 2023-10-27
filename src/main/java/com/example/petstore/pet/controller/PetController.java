package com.example.petstore.pet.controller;

import com.example.petstore.pet.model.Pet;
import com.example.petstore.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/hi")
    public String sayHi() {
        return "hi";
    }

    @GetMapping
    public List<Pet> allPet() {
        return petService.allPets();
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petService.addPet(pet);
    }
}
