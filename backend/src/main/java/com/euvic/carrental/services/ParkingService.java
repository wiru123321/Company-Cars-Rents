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
    public Long addEntityToDB(final Parking parking) {
        return parkingRepository.save(parking).getId();
    }

    @Override
    public void updateParkingInDb(final Long oldParkingId, final ParkingDTO newParkingDTO) {
        final Parking updatedParking = this.mapRestModel(oldParkingId, newParkingDTO);
        parkingRepository.save(updatedParking);
    }

    @Override
    public void deleteParkingById(final Long id) {
        parkingRepository.deleteById(id);
    }

    @Override
    public Parking mapRestModel(final Long id, final ParkingDTO parking) {
        return new Parking(id, parking.getTown(), parking.getPostalCode(), parking.getStreetName(), parking.getNumber(), parking.getComment(), true);
    }

    @Override
    public Parking getEntityById(final Long id) {
        return parkingRepository.findById(id).get();
    }

    @Override
    public ParkingDTO getDTOById(final Long id) {
        return new ParkingDTO(this.getEntityById(id));
    }

    @Override
    public List<ParkingDTO> getAllDTOsByTownName(final String name) {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>(parkingRepository.findByTown(name));

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
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
