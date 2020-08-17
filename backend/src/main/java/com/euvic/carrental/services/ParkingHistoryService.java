package com.euvic.carrental.services;

import com.euvic.carrental.model.ParkingHistory;
import com.euvic.carrental.repositories.ParkingHistoryRepository;
import com.euvic.carrental.responses.ParkingHistoryDTO;
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
    public ParkingHistory mapRestModel(final Long id, final ParkingHistoryDTO parkingHistoryDTO) {
        return new ParkingHistory(id, parkingHistoryDTO.getTown(), parkingHistoryDTO.getPostalCode(), parkingHistoryDTO.getStreetName(), parkingHistoryDTO.getNumber(), parkingHistoryDTO.getComment(), parkingHistoryDTO.getIsActive());
    }

    @Override
    public List<ParkingHistoryDTO> getAllDTOsByTownName(final String town) {
        final ArrayList<ParkingHistory> parkingHistoryArrayList = new ArrayList<>(parkingHistoryRepository.findByTown(town));

        return parkingHistoryArrayList.stream()
                .map(ParkingHistoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingHistory getEntityById(final Long id) {
        return parkingHistoryRepository.findById(id).get();
    }

    @Override
    public ParkingHistoryDTO getDTOById(final Long id) {
        return new ParkingHistoryDTO(this.getEntityById(id));
    }

    @Override
    public Long addEntityToDB(final ParkingHistory parkingHistory) {
        return parkingHistoryRepository.save(parkingHistory).getId();
    }

    @Override
    public List<ParkingHistoryDTO> getAllDTOs() {
        final ArrayList<ParkingHistory> parkingHistoryArrayList = new ArrayList<>();
        parkingHistoryRepository.findAll().forEach(parkingHistoryArrayList::add);

        return parkingHistoryArrayList.stream()
                .map(ParkingHistoryDTO::new)
                .collect(Collectors.toList());
    }
}
