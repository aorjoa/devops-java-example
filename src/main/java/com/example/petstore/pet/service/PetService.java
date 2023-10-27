package com.example.petstore.pet.service;

import com.example.petstore.pet.model.Pet;
import com.example.petstore.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> allPets() {
        return petRepository.findAll();
    }

    public Pet addPet(Pet pet) {
        return petRepository.saveAndFlush(pet);
    }
}
