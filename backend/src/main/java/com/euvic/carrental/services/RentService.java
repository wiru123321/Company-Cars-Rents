package com.euvic.carrental.services;

import com.euvic.carrental.model.*;
import com.euvic.carrental.repositories.RentHistoryRepository;
import com.euvic.carrental.repositories.RentRepository;
import com.euvic.carrental.responses.*;
import com.euvic.carrental.responses.User.UserRentInfo;
import com.euvic.carrental.services.interfaces.RentServiceInterface;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentService implements RentServiceInterface {

    final private RentRepository rentRepository;
    final private RentHistoryRepository rentHistoryRepository;
    final private UserService userService;
    final private CarService carService;
    final private ParkingService parkingService;

    public RentService(final RentRepository rentRepository, final RentHistoryRepository rentHistoryRepository, final UserService userService, final CarService carService, final ParkingService parkingService) {
        this.rentRepository = rentRepository;
        this.rentHistoryRepository = rentHistoryRepository;
        this.userService = userService;
        this.carService = carService;
        this.parkingService = parkingService;
    }

    @Override
    public Long addEntityToDB(final Rent rent) {
        return rentRepository.save(rent).getId();
    }

    @Override
    public Rent getEntityById(final Long id) {
        final Rent rent;

        if (rentRepository.findById(id).isPresent()) {
            rent = rentRepository.findById(id).get();
        } else {
            rent = null;
        }
        return rent;
    }

    @Override
    public Rent getEntityByCarAndDateFrom(final Car car, final LocalDateTime dateFrom) {
        return rentRepository.findByCarAndDateFrom(car, dateFrom);
    }

    @Override
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

    @Override
    public Rent mapRestModel(final Long id, final RentDTO rentDTO, final Long parkingFromId, final Long parkingToId) {

        final Rent rent;
        if (parkingToId != null) {
            rent = new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                    , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), parkingService.getEntityById(parkingToId), rentDTO.getIsActive(), rentDTO.getComment(), rentDTO.getResponse());
        } else {
            rent = new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                    , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), null, rentDTO.getIsActive(), rentDTO.getComment(), rentDTO.getResponse());
        }
        return rent;
    }

    @Override
    public List<RentDTO> getAllDTOs() {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);
        return this.convertRentListToRentDTOList(rentArrayList);
    }

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
                rentPermitDTO.setComment(rent.getComment());
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

    @Override //TODO TEST IT
    public List<RentDTO> getUserRentHistoryDTOs() {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.convertRentHistoryListToRentDTOList(rentHistoryRepository.findAllByUser(user));

    }

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

    private List<RentDTO> convertRentHistoryListToRentDTOList(final List<RentHistory> rentArrayList) {
        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {

            for (final RentHistory rentHistory : rentArrayList) {
                final RentDTO rentDTO = new RentDTO(rentHistory
                        , userService.getDTOByLogin(rentHistory.getUser().getLogin())
                        , carService.getDTOByLicensePlate(rentHistory.getCar().getLicensePlate())
                        , new ParkingDTO(rentHistory.getParkingHistoryFrom())
                        , new ParkingDTO(rentHistory.getParkingHistoryTo()));
                rentDTOArrayList.add(rentDTO);

            }
        }
        return rentDTOArrayList;
    }

    private boolean checkDate(final LocalDateTime dateFrom, final LocalDateTime dateTo, final RentListCarByTime rentListCarByTime) {
        return (rentListCarByTime.getDateFrom().isAfter(dateFrom)
                && rentListCarByTime.getDateFrom().isBefore(dateTo))
                || (rentListCarByTime.getDateTo().isAfter(dateFrom)
                && rentListCarByTime.getDateTo().isBefore(dateTo))

                || (rentListCarByTime.getDateFrom().isEqual(dateFrom) || rentListCarByTime.getDateFrom().isEqual(dateTo))
                || (rentListCarByTime.getDateTo().isEqual(dateFrom) || rentListCarByTime.getDateTo().isEqual(dateTo));
    }

    //TODO TEST IT
    @Override
    public boolean checkMyRentsBeforeAddNewRent(final RentDTO rentDTO) {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        final List<Rent> rentList = rentRepository.findAllByUserAndIsActive(user, true);
        final RentListCarByTime time = new RentListCarByTime();
        time.setDateFrom(rentDTO.getDateFrom());
        time.setDateTo(rentDTO.getDateTo());

        for (final Rent rent : rentList) {
            if (this.checkDate(rent.getDateFrom(), rent.getDateTo(), time)) {
                return false;
            }
        }
        return true;
    }

    @Override //TODO test it
    public void deleteRent(final Rent rent) {
        rentRepository.delete(rent);
    }


}
