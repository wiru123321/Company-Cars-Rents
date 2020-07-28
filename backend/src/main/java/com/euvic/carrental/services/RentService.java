package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.repositories.RentRepository;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.RentDTO;
import com.euvic.carrental.services.interfaces.RentServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service //TODO uzupelnic metody
public class RentService implements RentServiceInterface {

    final private RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Long addEntityToDB(Rent rent) {
        return rentRepository.save(rent).getId();
    }

    @Override
    public Rent getEntityById(Long id) {
        return rentRepository.findById(id).get();
    }

    @Override
    public Rent getEntityByCarAndDateFrom(Car car, Date dateFrom) {
        return null;
    }

    @Override
    public RentDTO getDTOById(Long id) {
        return null;
    }

    @Override
    public RentDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, Date dateFrom) {
        return null;
    }

    @Override
    public Rent mapRestModel(RentDTO rentDTO) {
        return null;
    }

    @Override
    public List<RentDTO> getAllDTOs() {
        return null;
    }
}
