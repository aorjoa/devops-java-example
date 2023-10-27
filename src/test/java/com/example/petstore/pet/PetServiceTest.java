package com.example.petstore.pet;

import com.example.petstore.pet.model.Pet;
import com.example.petstore.pet.repository.PetRepository;
import com.example.petstore.pet.service.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void listPet() {
        when(petRepository.findAll()).thenReturn(List.of(new Pet("KaNom", "AorJoa")));
        assert  petService.allPets().get(0).getName().equals("KaNom");
        assert  petService.allPets().get(0).getOwner().equals("AorJoa");
    }
}
