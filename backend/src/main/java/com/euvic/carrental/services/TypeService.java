package com.euvic.carrental.services;

import com.euvic.carrental.model.Type;
import com.euvic.carrental.repositories.TypeRepository;
import com.euvic.carrental.responses.RoleDTO;
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

    public TypeService(final TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public TypeDTO getByName(final String name) {
        final Type type = typeRepository.findByName(name);
        return new TypeDTO(type);
    }

    @Override
    public Type mapRestModel(final TypeDTO model) {
        return new Type(null, model.getName());
    }

    @Override
    public List<TypeDTO> getAll() {
        final ArrayList<Type> typeList = new ArrayList<>();
        typeRepository.findAll().forEach(typeList::add);

        return typeList.stream().map(TypeDTO::new).collect(Collectors.toList());
    }
}
