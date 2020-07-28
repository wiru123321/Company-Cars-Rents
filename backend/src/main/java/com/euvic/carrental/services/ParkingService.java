package com.euvic.carrental.services;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.repositories.ParkingRepository;
import com.euvic.carrental.responses.ParkingDTO;
import com.euvic.carrental.services.interfaces.ParkingServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService implements ParkingServiceInterface {

    private final ParkingRepository parkingRepository;

    public ParkingService(final ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Parking mapRestModel(final ParkingDTO parking) {
        return new Parking(null, parking.getTown(), parking.getPostalCode(), parking.getStreetName(), parking.getNumber(), parking.getComment(), parking.getIsActive());
    }

    @Override
    public List<ParkingDTO> getAllDTOsByTownName(final String name) {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingRepository.findByTown(name).forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Parking getEntityById(Long id) {
        return parkingRepository.findById(id).get();
    }

    @Override
    public ParkingDTO getDTOById(Long id) {
        return new ParkingDTO(getEntityById(id));
    }

    @Override
    public Long addEntityToDB(final Parking parking) {
        return parkingRepository.save(parking).getId();
    }

    @Override
    public List<ParkingDTO> getAllDTOs() {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingRepository.findAll().forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }

}
