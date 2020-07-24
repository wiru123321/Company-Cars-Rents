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
    public Parking mapRestModel(final ParkingDTO parking) {
        return new Parking(null, parking.getTown(), parking.getPostalCode(), parking.getStreet(), parking.getNumber(), parking.getComment(), parking.getIsActive());
    }

    @Override
    public List<ParkingDTO> getDTOByTown(final String town) {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingHistoryRepository.findByTown(town).forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Long add(final ParkingDTO parking) {
        return parkingHistoryRepository.save(this.mapRestModel(parking)).getId();
    }

    @Override
    public List<ParkingDTO> getAll() {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingHistoryRepository.findAll().forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }
}
