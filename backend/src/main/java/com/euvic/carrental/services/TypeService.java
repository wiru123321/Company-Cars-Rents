package com.euvic.carrental.services;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.repositories.TypeRepository;
import com.euvic.carrental.responses.TypeDTO;
import com.euvic.carrental.services.interfaces.TypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService implements TypeServiceInterface {


    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(final TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Long addEntityToDB(final Type type) {
        return typeRepository.save(type).getId();
    }

    @Override
    public Type mapRestModel(final Long id, final TypeDTO model) {
        return new Type(id, model.getName());
    }

    @Override
    public Type getEntityByName(final String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public TypeDTO getDTOByName(final String name) {
        final Type type = typeRepository.findByName(name);
        return new TypeDTO(type);
    }

    @Override
    public Long updateTypeInDB(final String oldTypeName, final TypeDTO typeDTO) {
        final Type oldType = this.getEntityByName(oldTypeName);
        oldType.setName(typeDTO.getName());
        return typeRepository.save(oldType).getId();
    }

    @Override
    public List<TypeDTO> getAllDTOs() {
        final ArrayList<Type> typeList = new ArrayList<>();
        typeRepository.findAll().forEach(typeList::add);

        return typeList.stream().map(TypeDTO::new).collect(Collectors.toList());
    }
}
