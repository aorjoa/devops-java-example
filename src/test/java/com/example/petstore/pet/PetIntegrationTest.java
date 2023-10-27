package com.example.petstore.pet;

import com.example.petstore.pet.model.Pet;
import com.example.petstore.pet.repository.PetRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Test
    void addPet() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                {
                                    "name": "KaNom",
                                    "owner": "AorJoa"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("KaNom"))
                .andReturn();

        // Extract JSON response body
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Use JsonPath to extract data
        Integer id = JsonPath.read(jsonResponse, "$.id");

        Optional<Pet> pet = petRepository.findById(id.longValue());
        assert pet.isPresent();
        assert pet.get().getName().equals("KaNom");
        assert pet.get().getOwner().equals("AorJoa");
    }
}