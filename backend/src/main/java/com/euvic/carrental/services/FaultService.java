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
    public Fault mapRestModel(final FaultDTO faultDTO) {
        return new Fault(carService.getEntityByLicensePlate(faultDTO.getCarDTO().getLicensePlate()), faultDTO.getDescribe(), faultDTO.getIsActive());
    }

    @Override
    public List<Fault> getEntitiesByCar(final Car car) {
        return faultRepository.findByCar(car);
    }

    @Override
    public List<FaultDTO> getDTOByCar(final Car car) {
        final ArrayList<Fault> faultArrayList = new ArrayList<>();
        faultRepository.findByCar(car).forEach(faultArrayList::add);

        final ArrayList<FaultDTO> faultDTOArrayList = new ArrayList<>();
        faultArrayList.stream().forEach(fault -> {
            final FaultDTO faultDTO = new FaultDTO(carService.getDTOByLicensePlate(fault.getCar().getLicensePlate()), fault.getDescribe(), fault.getIsActive());
            faultDTOArrayList.add(faultDTO);
        });

        return faultDTOArrayList;
    }

    @Override
    public void add(final FaultDTO faultDTO) {
        faultRepository.save(this.mapRestModel(faultDTO));
    }

    @Override
    public List<FaultDTO> getAll() {
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
