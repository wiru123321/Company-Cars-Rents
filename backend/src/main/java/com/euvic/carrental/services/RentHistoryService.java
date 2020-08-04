package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.RentHistoryRepository;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.services.interfaces.RentHistoryServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentHistoryService implements RentHistoryServiceInterface {

    final private RentHistoryRepository rentHistoryRepository;
    final private UserService userService;
    final private CarService carService;
    final private ParkingHistoryService parkingHistoryService;

    public RentHistoryService(final RentHistoryRepository rentHistoryRepository, final UserService userService, final CarService carService, final ParkingHistoryService parkingHistoryService) {
        this.rentHistoryRepository = rentHistoryRepository;
        this.userService = userService;
        this.carService = carService;
        this.parkingHistoryService = parkingHistoryService;
    }

    @Override
    public Long addEntityToDB(RentHistory rentHistory) {
        return rentHistoryRepository.save(rentHistory).getId();
    }

    @Override
    public RentHistory getEntityById(Long id) {
        return rentHistoryRepository.findById(id).get();
    }

    @Override
    public RentHistory getEntityByCarAndDateFrom(Car car, LocalDateTime dateFrom) {
        return rentHistoryRepository.findByCarAndDateFrom(car, dateFrom);
    }

    @Override
    public RentHistoryDTO getDTOById(Long id) {
        final RentHistory rentHistory = rentHistoryRepository.findById(id).get();
        final User user = rentHistory.getUser();
        final Car car = rentHistory.getCar();
        final ParkingHistory parkingHistoryFrom = rentHistory.getParkingHistoryFrom();
        final ParkingHistory parkingHistoryTo = rentHistory.getParkingHistoryTo();
        final RentHistoryDTO rentHistoryDTO = new RentHistoryDTO(rentHistory, userService.getDTOByLogin(user.getLogin()), carService.getDTOByLicensePlate(car.getLicensePlate())
                , parkingHistoryService.getDTOById(parkingHistoryFrom.getId()), parkingHistoryService.getDTOById(parkingHistoryTo.getId()));

        return rentHistoryDTO;
    }

    @Override
    public RentHistoryDTO getDTOByCarDTOAndDateFrom(CarDTO carDTO, LocalDateTime dateFrom) {
        final Car car = carService.getEntityByLicensePlate(carDTO.getLicensePlate());
        final RentHistory rentHistory = rentHistoryRepository.findByCarAndDateFrom(car, dateFrom);
        final RentHistoryDTO rentHistoryDTO = new RentHistoryDTO(rentHistory, userService.getDTOByLogin(rentHistory.getUser().getLogin()), carDTO
                    , parkingHistoryService.getDTOById(rentHistory.getParkingHistoryFrom().getId()), parkingHistoryService.getDTOById(rentHistory.getParkingHistoryTo().getId()));

        return rentHistoryDTO;
    }

    @Override
    public RentHistory mapRestModel(Long id, RentHistoryDTO rentHistoryDTO, Long parkingHistoryFromId, Long parkingHisotryToId) {
        final RentHistory rentHistory = new RentHistory(id, userService.getEntityByLogin(rentHistoryDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentHistoryDTO.getCarDTO().getLicensePlate())
                , rentHistoryDTO.getDateFrom(), rentHistoryDTO.getDateTo(), parkingHistoryService.getEntityById(parkingHistoryFromId), parkingHistoryService.getEntityById(parkingHisotryToId), rentHistoryDTO.getIsActive());

        return rentHistory;
    }

    @Override
    public List<RentHistoryDTO> getAllDTOs() {
        final ArrayList<RentHistory> rentHistoryArrayList = new ArrayList<>();
        rentHistoryRepository.findAll().forEach(rentHistoryArrayList::add);
        final ArrayList<RentHistoryDTO> rentHistoryDTOArrayList = new ArrayList<>();

        rentHistoryArrayList.stream().forEach(rentHistory -> {
            final RentHistoryDTO rentHistoryDTO = new RentHistoryDTO(rentHistory, userService.getDTOByLogin(rentHistory.getUser().getLogin()), carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate())
                    , new ParkingHistoryDTO(rentHistory.getParkingHistoryFrom()), new ParkingHistoryDTO((rentHistory.getParkingHistoryTo())));
            rentHistoryDTOArrayList.add(rentHistoryDTO);
        });

        return rentHistoryDTOArrayList;
    }
}
