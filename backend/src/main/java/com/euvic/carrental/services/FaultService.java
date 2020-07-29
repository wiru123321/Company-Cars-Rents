package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.repositories.FaultRepository;
import com.euvic.carrental.responses.FaultDTO;
import com.euvic.carrental.services.interfaces.FaultServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaultService implements FaultServiceInterface {

    private final CarService carService;
    private final FaultRepository faultRepository;

    @Autowired
    public FaultService(final CarService carService, final FaultRepository faultRepository) {
        this.carService = carService;
        this.faultRepository = faultRepository;
    }

    @Override
    public Fault mapRestModel(final Long id, final FaultDTO faultDTO) {
        return new Fault(id, carService.getEntityByLicensePlate(faultDTO.getCarDTO().getLicensePlate()), faultDTO.getDescribe(), faultDTO.getIsActive());
    }

    @Override
    public List<Fault> getAllEntitiesByCar(final Car car) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findByCar(car));

        return faultArrayList;
    }

    public List<FaultDTO> getAllDTOsByCar(final Car car) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findByCar(car));

        final ArrayList<FaultDTO> faultDTOArrayList = new ArrayList<>();
        faultArrayList.stream().forEach(fault -> {
            final FaultDTO faultDTO = new FaultDTO(carService.getDTOByLicensePlate(fault.getCar().getLicensePlate()), fault.getDescribe(), fault.getIsActive());
            faultDTOArrayList.add(faultDTO);
        });

        return faultDTOArrayList;
    }

    @Override
    public Fault getEntityById(final Long id) {
        return faultRepository.findById(id).get();
    }

    @Override
    public FaultDTO getDTOById(final Long id) {
        final Fault fault = this.getEntityById(id);
        final Car car = fault.getCar();
        return new FaultDTO(carService.getDTOByLicensePlate(car.getLicensePlate()), fault.getDescribe(), fault.getIsActive());
    }

    @Override
    public Long addEntityToDB(final Fault fault) {
        return faultRepository.save(fault).getId();
    }

    @Override
    public List<FaultDTO> getAllDTOs() {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultRepository.findAll().forEach(faultArrayList::add);

        final ArrayList<FaultDTO> faultDTOArrayList = new ArrayList<>();
        faultArrayList.stream().forEach(fault -> {
            final FaultDTO faultDTO = new FaultDTO(carService.getDTOByLicensePlate(fault.getCar().getLicensePlate()), fault.getDescribe(), fault.getIsActive());
            faultDTOArrayList.add(faultDTO);
        });

        return faultDTOArrayList;
    }
}
