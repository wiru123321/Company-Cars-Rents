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
    public Long addEntityToDB(final Fault fault) {
        return faultRepository.save(fault).getId();
    }

    @Override
    public Long setInactiveCarFaultWithDescription(final Car car, final String description) {
        final Fault fault = faultRepository.findByIsActiveAndCarAndDescription(true, car, description);
        fault.setIsActive(false);
        return faultRepository.save(fault).getId();
    }

    @Override
    public Boolean checkIfCarFaultWithDescriptionExists(final Car car, final String description) {
        return faultRepository.existsByIsActiveAndCarAndDescription(true, car, description);
    }

    @Override
    public Fault mapRestModel(final Long id, final FaultDTO faultDTO) {
        return new Fault(id, carService.getOnCompanyEntityByLicensePlate(faultDTO.getCarLicensePlate()), faultDTO.getDescription(), faultDTO.getFaultDate(), faultDTO.getSetCarInactive(), true);
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
    public List<Long> setAllFaultsAsInactiveForCertainCar(final String licensePlate) {
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
        final List<Fault> faultList = this.getAllActiveEntitiesByCar(car);
        final List<Long> deletedFaultIdList = new ArrayList<>();
        faultList.forEach(fault -> {
            fault.setIsActive(false);
            deletedFaultIdList.add(faultRepository.save(fault).getId());
        });
        return deletedFaultIdList;
    }

    @Override
    public List<Fault> getAllActiveEntitiesByCar(final Car car) {
        return new ArrayList<>(faultRepository.findAllByIsActiveAndCar(true, car));
    }

    @Override
    public List<FaultDTO> getAllActiveDTOsByCar(final Car car) {
        return this.mapEntityList(this.getAllActiveEntitiesByCar(car));
    }

    @Override
    public List<FaultDTO> getAllActiveFaultDTOs() {
        final ArrayList<Fault> faultArrayList = new ArrayList<>(faultRepository.findAllByIsActive(true));
        return this.mapEntityList(faultArrayList);
    }

    @Override
    public List<FaultDTO> getAllActiveFaultDTOsByCarLicensePlate(final String licensePlate) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>(faultRepository.findAllByIsActiveAndCarLicensePlate(true, licensePlate));
        return this.mapEntityList(faultArrayList);
    }

    @Override
    public List<FaultDTO> getAllDTOs() {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultRepository.findAll().forEach(faultArrayList::add);

        return this.mapEntityList(faultArrayList);
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
