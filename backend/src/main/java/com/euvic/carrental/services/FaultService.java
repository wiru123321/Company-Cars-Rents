package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Fault;
import com.euvic.carrental.repositories.FaultRepository;
import com.euvic.carrental.responses.*;
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
        return new Fault(id, carService.getOnCompanyEntityByLicensePlate(faultDTO.getCarLicensePlate()), faultDTO.getDescription(), faultDTO.getSetCarInactive(), true);
    }

    @Override
    public List<Fault> getAllEntitiesByCar(final Car car) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findAllByCar(car));

        return faultArrayList;
    }

    public List<FaultDTO> getAllDTOsByCar(final Car car) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findAllByCar(car));

        return mapEntityList(faultArrayList);
    }

    @Override
    public List<FaultDTO> getAllActiveFaultDTOs() {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findAllByIsActive(true));
        return mapEntityList(faultArrayList);
    }

    @Override
    public List<FaultDTO> getAllActiveFaultDTOsByCarLicensePlate(String licensePlate) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultArrayList.addAll(faultRepository.findAllByIsActiveAndCarLicensePlate(true, licensePlate));
        return mapEntityList(faultArrayList);
    }

    @Override
    public Boolean checkIfCarFaultWithDescriptionExists(Car car, String description) {
        return faultRepository.existsByIsActiveAndCarAndDescription(true, car, description);
    }

    @Override
    public Long setInactiveCarFaultWithDescription(Car car, String description) {
        Fault fault = faultRepository.findByIsActiveAndCarAndDescription(true, car, description);
        fault.setIsActive(false);
        return faultRepository.save(fault).getId();
    }

    @Override
    public Fault getEntityById(final Long id) {
        return faultRepository.findById(id).get();
    }

    @Override
    public FaultDTO getDTOById(final Long id) {
        final Fault fault = this.getEntityById(id);
        final Car car = fault.getCar();
        return new FaultDTO(fault, car.getLicensePlate());
    }

    @Override
    public Long addEntityToDB(final Fault fault) {
        return faultRepository.save(fault).getId();
    }

    @Override
    public List<FaultDTO> getAllDTOs() {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultRepository.findAll().forEach(faultArrayList::add);

        return mapEntityList(faultArrayList);
    }

    private List<FaultDTO> mapEntityList(final List<Fault> faultList) {
        final ArrayList<FaultDTO> faultDTOList = new ArrayList<>();
        faultList.forEach((fault) -> {
            final FaultDTO faultDTO = new FaultDTO(fault, fault.getCar().getLicensePlate());
            faultDTOList.add(faultDTO);
        });
        return faultDTOList;
    }
}
