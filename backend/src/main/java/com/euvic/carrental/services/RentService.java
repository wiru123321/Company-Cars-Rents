package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
import com.euvic.carrental.repositories.RentRepository;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.responses.User.UserRentInfo;
import com.euvic.carrental.services.interfaces.RentServiceInterface;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentService implements RentServiceInterface {

    final private RentRepository rentRepository;
    final private UserService userService;
    final private CarService carService;
    final private ParkingService parkingService;

    public RentService(final RentRepository rentRepository, final UserService userService, final CarService carService, final ParkingService parkingService) {
        this.rentRepository = rentRepository;
        this.userService = userService;
        this.carService = carService;
        this.parkingService = parkingService;
    }

    @Override
    public Long addEntityToDB(final Rent rent) {
        return rentRepository.save(rent).getId();
    }

    @Override //TODO change to isPresent()
    public Rent getEntityById(final Long id) {
        Rent rent;
        try {
            rent = rentRepository.findById(id).get();
        } catch (final NoSuchElementException e) {
            rent = null;
        }
        return rent;
    }

    @Override
    public Rent getEntityByCarAndDateFrom(final Car car, final LocalDateTime dateFrom) {
        return rentRepository.findByCarAndDateFrom(car, dateFrom);
    }

    @Override //TODO add isPresent()
    public RentDTO getDTOById(final Long id) {
        final Rent rent = rentRepository.findById(id).get();
        final User user = rent.getUser();
        final Car car = rent.getCar();
        final Parking parkingFrom = rent.getParkingFrom();
        final Parking parkingTo = rent.getParkingTo();
        final RentDTO rentDTO;
        if (rent.getParkingTo() != null) {
            rentDTO = new RentDTO(rent, userService.getDTOByLogin(user.getLogin()), carService.getDTOByLicensePlate(car.getLicensePlate())
                    , parkingService.getDTOById(parkingFrom.getId()), parkingService.getDTOById(parkingTo.getId()));
        } else {
            rentDTO = new RentDTO(rent, userService.getDTOByLogin(user.getLogin()), carService.getDTOByLicensePlate(car.getLicensePlate())
                    , parkingService.getDTOById(parkingFrom.getId()), null);
        }
        return rentDTO;
    }

    @Override
    public RentDTO getDTOByCarDTOAndDateFrom(final CarDTO carDTO, final LocalDateTime dateFrom) {
        final Car car = carService.getEntityByLicensePlate(carDTO.getLicensePlate());
        final Rent rent = rentRepository.findByCarAndDateFrom(car, dateFrom);
        final RentDTO rentDTO;
        if (rent.getParkingTo() != null) {
            rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carDTO
                    , parkingService.getDTOById(rent.getParkingFrom().getId()), parkingService.getDTOById(rent.getParkingTo().getId()));
        } else {
            rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carDTO
                    , parkingService.getDTOById(rent.getParkingFrom().getId()), null);
        }
        return rentDTO;
    }

    public Rent mapRestModel(final Long id, final RentDTO rentDTO, final Long parkingFromId, final Long parkingToId) {

        final Rent rent;
        if (parkingToId != null) {
            rent = new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                    , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), parkingService.getEntityById(parkingToId), rentDTO.getIsActive(), null);
        } else {
            rent = new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                    , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), null, rentDTO.getIsActive(), null);
        }
        return rent;
    }

    @Override
    public List<RentDTO> getAllDTOs() {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);
        return this.convertRentListToRentDTOList(rentArrayList);
    }

    //TODO TEST IT
    private List<RentDTO> getAllDTOsByTimeRange(final RentListCarByTime rentListCarByTime) {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);
        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {

            for (final Rent rent : rentArrayList) {
                if (this.checkDate(rent.getDateFrom(), rent.getDateTo(), rentListCarByTime)) {
                    final RentDTO rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carService.getDTOByLicensePlate(rent.getCar().getLicensePlate())
                            , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO((rent.getParkingTo())));
                    rentDTOArrayList.add(rentDTO);
                }
            }
        }
        return rentDTOArrayList;
    }

    @Override //TODO TEST IT
    public List<CarDTO> getActiveCarsBetweenDates(final RentListCarByTime rentListCarByTime) {
        final List<RentDTO> rentList = this.getAllDTOsByTimeRange(rentListCarByTime);
        final List<CarDTO> carList = new ArrayList<>();
        final List<CarDTO> carDTOList = carService.getInCompanyActiveCarDTOs();

        for (final RentDTO rentDTO : rentList) {
            carList.add(rentDTO.getCarDTO());
        }

        carDTOList.removeAll(carList);
        return carDTOList;
    }

    @Override //TODO TEST IT
    public List<RentPermitDTO> getAllPendingRents() {
        final List<Rent> rentList = rentRepository.findAllByIsActive(false);
        final List<RentPermitDTO> rentPermitDTOList = new ArrayList<>();

        if (rentList != null) {
            rentList.forEach((rent) -> {
                final RentPermitDTO rentPermitDTO = new RentPermitDTO();
                rentPermitDTO.setId(rent.getId());
                rentPermitDTO.setCarDTO(carService.mapToCarDTO(rent.getCar()));
                rentPermitDTO.setDateFrom(rent.getDateFrom());
                rentPermitDTO.setDateTo(rent.getDateTo());
                rentPermitDTO.setParkingFrom(new ParkingDTO(rent.getParkingFrom()));
                rentPermitDTO.setParkingTo(new ParkingDTO(rent.getParkingTo()));
                rentPermitDTO.setUserRentInfo(new UserRentInfo(rent.getUser().getName(), rent.getUser().getSurname(), rent.getUser().getPhoneNumber(), rent.getUser().getEmail()));
                rentPermitDTOList.add(rentPermitDTO);
            });
        }

        return rentPermitDTOList;
    }

    @Override //TODO TEST IT
    public List<RentDTO> getUserRentDTOs() {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.convertRentListToRentDTOList(rentRepository.findAllByUser(user));

    }

    //TODO TEST IT
    private List<RentDTO> convertRentListToRentDTOList(final List<Rent> rentArrayList) {
        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {

            for (final Rent rent : rentArrayList) {
                final RentDTO rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carService.getDTOByLicensePlate(rent.getCar().getLicensePlate())
                        , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO((rent.getParkingTo())));
                rentDTOArrayList.add(rentDTO);

            }
        }
        return rentDTOArrayList;
    }

    //TODO test it
    private boolean checkDate(final LocalDateTime dateFrom, final LocalDateTime dateTo, final RentListCarByTime rentListCarByTime) {
        return (rentListCarByTime.getDateFrom().isAfter(dateFrom)
                && rentListCarByTime.getDateFrom().isBefore(dateTo))
                || (rentListCarByTime.getDateTo().isAfter(dateFrom)
                && rentListCarByTime.getDateTo().isBefore(dateTo));
    }


}
