package com.euvic.carrental.services;

import com.euvic.carrental.model.Parking;
import com.euvic.carrental.repositories.ParkingHistoryRepository;
import com.euvic.carrental.responses.ParkingDTO;
import com.euvic.carrental.services.interfaces.ParkingHistoryServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingHistoryService implements ParkingHistoryServiceInterface {

    private final ParkingHistoryRepository parkingHistoryRepository;

    public ParkingHistoryService(final ParkingHistoryRepository parkingHistoryRepository) {
        this.parkingHistoryRepository = parkingHistoryRepository;
    }

    @Override
    public Parking mapRestModel(final Long id, final ParkingDTO parking) {
        return new Parking(id, parking.getTown(), parking.getPostalCode(), parking.getStreetName(), parking.getNumber(), parking.getComment(), parking.getIsActive());
    }

    @Override
    public List<ParkingDTO> getAllDTOsByTownName(final String town) {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingArrayList.addAll(parkingHistoryRepository.findByTown(town));

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Parking getEntityById(final Long id) {
        return parkingHistoryRepository.findById(id).get();
    }

    @Override
    public ParkingDTO getDTOById(final Long id) {
        return new ParkingDTO(this.getEntityById(id));
    }

    @Override
    public Long addEntityToDB(final Parking parking) {
        return parkingHistoryRepository.save(parking).getId();
    }

    @Override
    public List<ParkingDTO> getAllDTOs() {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingHistoryRepository.findAll().forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }
}
