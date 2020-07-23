package com.euvic.carrental.services;

import com.euvic.carrental.model.GearboxType;
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
        return new Parking(null, parking.getTown(), parking.getPostalCode(), parking.getStreet(), parking.getNumber(), parking.getComment(), parking.getIsActive());
    }

    // TODO getEntity i DTO muszą zwracać 1 wynik / zmienić getByTown na findByTown w get dto
    @Override
    public Parking getEntityByTown(final String name) {
        return parkingRepository.findByTown(name);
    }

    @Override
    public ParkingDTO getDTOByTown(final String town) {
        return new ParkingDTO(parkingRepository.getByTown(town));
    }

    @Override
    public Long add(final ParkingDTO parking) {
        return parkingRepository.save(this.mapRestModel(parking)).getId();
    }

    @Override
    public List<ParkingDTO> getAll() {
        final ArrayList<Parking> parkingArrayList = new ArrayList<>();
        parkingRepository.findAll().forEach(parkingArrayList::add);

        return parkingArrayList.stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }


}
