package com.euvic.carrental.services;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.repositories.TypeRepository;
import com.euvic.carrental.responses.TypeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeRepository typeRepository;

    @AfterEach
    void tearDown(){
        typeRepository.deleteAll();
    }


    @Test
    void whenTypeGiven_thenReturnTypeDTO(){
        final Type type = new Type(null,"Sedan");
        final TypeDTO typeDTO = new TypeDTO("Sedan");
        assertAll(() -> {
            assertEquals(typeService.mapRestModel(typeDTO).getName(), type.getName());
            assertEquals(typeService.mapRestModel(typeDTO).getId(), type.getId());
        });
    }


    @Test
    void returnDBType(){
        final Type type = new Type(null, "Sedan");
        assertEquals(0, typeRepository.count());
        typeRepository.save(type);
        assertEquals(1, typeRepository.count());

        TypeDTO serviceTypeDTO = typeService.getByName("Sedan");

        assertEquals(type.getName(), serviceTypeDTO.getName());
    }

    @Test
    void returnAllDBTypes(){
        final Type type1 = new Type(null, "Sedan");
        final Type type2 = new Type(null, "Coupe");
        final Type type3 = new Type(null, "Van");
        assertEquals(0, typeRepository.count());
        typeRepository.save(type1);
        typeRepository.save(type2);
        typeRepository.save(type3);
        assertEquals(3, typeRepository.count());

        List<TypeDTO> typeDTOList = typeService.getAll();

        assertEquals(typeRepository.count(), typeDTOList.size());
    }

}
