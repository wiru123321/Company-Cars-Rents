package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.RentHistoryRepository;
import com.euvic.carrental.responses.CarDTO;
import com.euvic.carrental.responses.ParkingHistoryDTO;
import com.euvic.carrental.responses.RentHistoryDTO;
import com.euvic.carrental.responses.RentHistoryEndPendingDTO;
import com.euvic.carrental.responses.User.UserRentInfo;
import com.euvic.carrental.services.interfaces.RentHistoryServiceInterface;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Long addEntityToDB(final RentHistory rentHistory) {
        return rentHistoryRepository.save(rentHistory).getId();
    }

    @Override
    public void setToInactiveByLicensePlate(final String licensePlate) {
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
        final List<RentHistory> rentHistoryList = rentHistoryRepository.findAllByCar(car);
        for (final RentHistory temp : rentHistoryList) {
            temp.getParkingHistoryFrom().setIsActive(false);
            temp.getParkingHistoryTo().setIsActive(false);
            parkingHistoryService.addEntityToDB(temp.getParkingHistoryFrom());
            parkingHistoryService.addEntityToDB(temp.getParkingHistoryTo());
            temp.setIsActive(false);
            rentHistoryRepository.save(temp);
        }
    }

    @Override //TODO Test it
    public void addNewRentHistoryWhenRentEnd(final String adminResponse, final Rent rent, final ParkingHistory parkingHistoryTo) {
        final ParkingHistory parkingFrom = new ParkingHistory(null, rent.getParkingFrom());
        final ParkingHistory parkingTo;
        parkingTo = Objects.requireNonNullElseGet(parkingHistoryTo, () -> new ParkingHistory(null, rent.getParkingTo()));
        final RentHistory rentHistory = new RentHistory(null, rent.getUser(), rent.getCar(), rent.getDateFrom(), rent.getDateTo(), parkingFrom
                , parkingTo, false, false, rent.getReasonForTheLoan(), adminResponse, "");
        parkingHistoryService.addEntityToDB(parkingFrom);
        parkingHistoryService.addEntityToDB(parkingTo);
        this.addEntityToDB(rentHistory);
    }

    @Override
    public RentHistory getEntityById(final Long id) {
        return rentHistoryRepository.findById(id).get();
    }

    @Override
    public RentHistory getEntityByCarAndDateFrom(final Car car, final LocalDateTime dateFrom) {
        return rentHistoryRepository.findByCarAndDateFrom(car, dateFrom);
    }

    @Override
    public RentHistory mapRestModel(final Long id, final RentHistoryDTO rentHistoryDTO, final Long parkingHistoryFromId, final Long parkingHistoryToId, final Boolean isActive, final Boolean isAccepted) {
        return new RentHistory(id, userService.getEntityByLogin(rentHistoryDTO.getUserDTO().getLogin()), carService.getOnCompanyEntityByLicensePlate(rentHistoryDTO.getCarDTO().getLicensePlate())
                , rentHistoryDTO.getDateFrom(), rentHistoryDTO.getDateTo(), parkingHistoryService.getEntityById(parkingHistoryFromId), parkingHistoryService.getEntityById(parkingHistoryToId), isActive, isAccepted, rentHistoryDTO.getReasonForTheLoan(), rentHistoryDTO.getAdminResponseForTheRequest(), rentHistoryDTO.getFaultMessage());
    }

    @Override
    public RentHistoryEndPendingDTO getRentEndById(final Long id) {
        final RentHistory rentHistory = rentHistoryRepository.findById(id).get();
        final User user = rentHistory.getUser();
        final RentHistoryEndPendingDTO temp = new RentHistoryEndPendingDTO();
        temp.setId(rentHistory.getId());
        temp.setDateFrom(rentHistory.getDateFrom());
        temp.setDateTo(rentHistory.getDateTo());
        temp.setCarDTO(carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate()));
        temp.setReasonForTheLoan(rentHistory.getReasonForTheLoan());
        temp.setAdminResponseForTheRequest(rentHistory.getAdminResponseForTheRequest());
        temp.setFaultMessage(rentHistory.getFaultMessage());
        temp.setUserRentInfo(new UserRentInfo(user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail()));
        temp.setParkingFrom(parkingHistoryService.getDTOById(rentHistory.getParkingHistoryFrom().getId()));
        temp.setParkingTo(parkingHistoryService.getDTOById(rentHistory.getParkingHistoryTo().getId()));
        return temp;
    }

    @Override
    public RentHistoryDTO getDTOById(final Long id) {
        final RentHistory rentHistory = rentHistoryRepository.findById(id).get();
        final User user = rentHistory.getUser();
        final Car car = rentHistory.getCar();
        final ParkingHistory parkingHistoryFrom = rentHistory.getParkingHistoryFrom();
        final ParkingHistory parkingHistoryTo = rentHistory.getParkingHistoryTo();

        return new RentHistoryDTO(rentHistory, userService.getDTOByLogin(user.getLogin()), carService.getDTOByLicensePlate(car.getLicensePlate())
                , parkingHistoryService.getDTOById(parkingHistoryFrom.getId()), parkingHistoryService.getDTOById(parkingHistoryTo.getId()));
    }

    @Override
    public RentHistoryDTO getDTOByCarDTOAndDateFrom(final CarDTO carDTO, final LocalDateTime dateFrom) {
        final Car car = carService.getOnCompanyEntityByLicensePlate(carDTO.getLicensePlate());
        final RentHistory rentHistory = rentHistoryRepository.findByCarAndDateFrom(car, dateFrom);

        return new RentHistoryDTO(rentHistory, userService.getDTOByLogin(rentHistory.getUser().getLogin()), carDTO
                , parkingHistoryService.getDTOById(rentHistory.getParkingHistoryFrom().getId()), parkingHistoryService.getDTOById(rentHistory.getParkingHistoryTo().getId()));
    }

    @Override
    public List<RentHistoryDTO> getAllDTOs() {
        final ArrayList<RentHistory> rentHistoryArrayList = new ArrayList<>();
        rentHistoryRepository.findAll().forEach(rentHistoryArrayList::add);
        final ArrayList<RentHistoryDTO> rentHistoryDTOArrayList = new ArrayList<>();

        rentHistoryArrayList.forEach(rentHistory -> {
            final RentHistoryDTO rentHistoryDTO = new RentHistoryDTO(rentHistory, userService.getDTOByLogin(rentHistory.getUser().getLogin()), carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate())
                    , new ParkingHistoryDTO(rentHistory.getParkingHistoryFrom()), new ParkingHistoryDTO((rentHistory.getParkingHistoryTo())));
            rentHistoryDTOArrayList.add(rentHistoryDTO);
        });

        return rentHistoryDTOArrayList;
    }

    @Override
    public List<RentHistoryDTO> getAllDTOsByCar(final Car car) {
        return this.convertRentHistoryListToRentDTOList(rentHistoryRepository.findAllByCar(car));
    }

    @Override
    public List<RentHistoryEndPendingDTO> getAllEndRentPending() {
        return this.convertRentHistoryListToRentHistoryEndPendingDTOList(rentHistoryRepository.findAllByIsActive(false));
    }

    @Override
    public List<RentHistoryDTO> getUserRentHistoryDTOs() {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.convertRentHistoryListToRentDTOList(rentHistoryRepository.findAllByUser(user));
    }

    private List<RentHistoryDTO> convertRentHistoryListToRentDTOList(final List<RentHistory> rentArrayList) {
        final ArrayList<RentHistoryDTO> rentDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {
            for (final RentHistory rentHistory : rentArrayList) {
                final RentHistoryDTO rentDTO = new RentHistoryDTO(rentHistory
                        , userService.getDTOByLogin(rentHistory.getUser().getLogin())
                        , carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate())
                        , new ParkingHistoryDTO(rentHistory.getParkingHistoryFrom())
                        , new ParkingHistoryDTO(rentHistory.getParkingHistoryTo()));
                rentDTOArrayList.add(rentDTO);
            }
        }
        return rentDTOArrayList;
    }

    private List<RentHistoryEndPendingDTO> convertRentHistoryListToRentHistoryEndPendingDTOList(final List<RentHistory> rentArrayList) {
        final ArrayList<RentHistoryEndPendingDTO> rentHistoryEndPendingDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {
            for (final RentHistory rentHistory : rentArrayList) {
                final RentHistoryEndPendingDTO temp = new RentHistoryEndPendingDTO();
                final User user = rentHistory.getUser();
                temp.setId(rentHistory.getId());
                temp.setDateFrom(rentHistory.getDateFrom());
                temp.setDateTo(rentHistory.getDateTo());
                temp.setCarDTO(carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate()));
                temp.setReasonForTheLoan(rentHistory.getReasonForTheLoan());
                temp.setAdminResponseForTheRequest(rentHistory.getAdminResponseForTheRequest());
                temp.setUserRentInfo(new UserRentInfo(user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail()));
                temp.setParkingFrom(parkingHistoryService.getDTOById(rentHistory.getParkingHistoryFrom().getId()));
                temp.setParkingTo(parkingHistoryService.getDTOById(rentHistory.getParkingHistoryTo().getId()));
                temp.setFaultMessage(rentHistory.getFaultMessage());
                rentHistoryEndPendingDTOArrayList.add(temp);
            }
        }
        return rentHistoryEndPendingDTOArrayList;
    }
}
