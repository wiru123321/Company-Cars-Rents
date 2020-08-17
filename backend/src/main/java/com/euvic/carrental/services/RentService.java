package com.euvic.carrental.services;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.model.Parking;
import com.euvic.carrental.model.Rent;
import com.euvic.carrental.model.User;
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
import java.util.NoSuchElementException;

@Service
public class RentService implements RentServiceInterface {

    final private RentRepository rentRepository;
    final private UserService userService;
    final private CarService carService;
    final private ParkingService parkingService;
    final private ParkingHistoryService parkingHistoryService;

    public RentService(final RentRepository rentRepository, final RentHistoryRepository rentHistoryRepository, final UserService userService, final CarService carService, final ParkingService parkingService, final ParkingHistoryService parkingHistoryService) {
        this.rentRepository = rentRepository;
        this.userService = userService;
        this.carService = carService;
        this.parkingService = parkingService;
        this.parkingHistoryService = parkingHistoryService;
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
    public List<Rent> getRentsByLicensePlate(final String licensePlate) {
        final Car car = carService.getOnCompanyEntityByLicensePlate(licensePlate);
        return rentRepository.findAllByCarAndIsActive(car, true);
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
        final Car car = carService.getOnCompanyEntityByLicensePlate(carDTO.getLicensePlate());
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
        rent = new Rent(id, userService.getEntityByLogin(rentDTO.getUserDTO().getLogin()), carService.getOnCompanyEntityByLicensePlate(rentDTO.getCarDTO().getLicensePlate())
                , rentDTO.getDateFrom(), rentDTO.getDateTo(), parkingService.getEntityById(parkingFromId), parkingService.getEntityById(parkingToId), rentDTO.getIsActive(), rentDTO.getReasonForTheLoan(), rentDTO.getAdminResponseForTheRequest(), rentDTO.getFaultMessage());

        return rent;
    }

    @Override
    public List<RentDTO> getAllDTOs() {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);
        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();

        for (final Rent rent : rentArrayList) {
            rentDTOArrayList.add(new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carService.getDTOByLicensePlate(rent.getCar().getLicensePlate())
                    , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO(rent.getParkingTo())));
        }
        return rentDTOArrayList;
    }

    @Override
    public List<CarDTO> getActiveCarsBetweenDates(final DateFromDateTo dateFromDateTo) {
        final List<RentDTO> rentList = this.getAllDTOsByTimeRange(dateFromDateTo);
        final List<CarDTO> carList = new ArrayList<>();
        final List<CarDTO> carDTOList = carService.getInCompanyActiveCarDTOs();

        for (final RentDTO rentDTO : rentList) {
            carList.add(rentDTO.getCarDTO());
        }

        carDTOList.removeAll(carList);
        return carDTOList;
    }

    @Override
    public List<RentPendingDTO> getAllActiveRents() {
        final List<Rent> rentList = rentRepository.findAllByIsActive(true);
        return this.convertRentListToRentPendingDTOList(rentList);
    }

    @Override
    public List<RentPendingDTO> getAllPendingRents() {
        final List<Rent> rentList = rentRepository.findAllByIsActive(false);
        return this.convertRentListToRentPendingDTOList(rentList);
    }

    @Override
    public List<RentPendingDTO> getUserActiveRentDTOs() {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.convertRentListToRentPendingDTOList(rentRepository.findAllByUserAndIsActive(user, true));
    }

    @Override
    public List<RentPendingDTO> getUserInactiveRentDTOs() {
        final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return this.convertRentListToRentPendingDTOList(rentRepository.findAllByUserAndIsActive(user, false));
    }

    @Override
    public boolean checkMyRentsBeforeAddNewRent(final RentDTO rentDTO) {
        if (this.checkDateTimeChronological(rentDTO.getDateFrom(), rentDTO.getDateTo())) {
            final User user = userService.getEntityByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            final List<Rent> rentList = rentRepository.findAllByUserAndIsActive(user, true);
            final DateFromDateTo time = new DateFromDateTo();
            time.setDateFrom(rentDTO.getDateFrom());
            time.setDateTo(rentDTO.getDateTo());

            for (final Rent rent : rentList) {
                if (this.checkDate(rent.getDateFrom(), rent.getDateTo(), time)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void deleteRent(final Rent rent) {
        rent.setCar(null);
        rent.setParkingFrom(null);
        rent.setParkingTo(null);
        rent.setUser(null);
        rentRepository.delete(rent);
    }

    @Override //TODO test it
    public boolean checkCarAvailability(final Rent rent) {
        boolean toReturn = false;
        final List<Rent> rentList = this.getRentsByLicensePlate(rent.getCar().getLicensePlate());
        if (rentList.size() == 0) {
            toReturn = true;
        } else {
            for (final Rent temp : rentList) {
                if (!this.checkDate(rent.getDateFrom(), rent.getDateTo(), new DateFromDateTo(temp.getDateFrom(), temp.getDateTo())) && !rent.getId().equals(temp.getId())) {
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }

    @Override //TODO test it
    public void updateNextRent(final Rent rent) {
        final Rent nextRent = this.getNearestRent(rent);
        if (nextRent != null) {
            final Long parkingId;
            parkingId = nextRent.getParkingFrom().getId();
            nextRent.setParkingFrom(rent.getCar().getParking());
            parkingService.deleteParkingById(parkingId);
        }
    }

    private Rent getNearestRent(final Rent rent) {

        try {
            final List<Rent> rentList = this.getRentsByLicensePlate(rent.getCar().getLicensePlate());
            rentList.remove(rent);
            LocalDateTime minimum = rent.getDateTo();
            int position = 0;
            if (rentList.size() > 0) {
                for (int i = 0; i < rentList.size(); i++) {
                    if (minimum.isAfter(rent.getDateFrom())) {
                        minimum = rent.getDateFrom();
                        position = i;
                    }
                }
                return rentList.get(position);
            } else {
                return null;
            }
        } catch (final NullPointerException | NoSuchElementException e) {
            return null;
        }

    }

    private boolean checkDate(final LocalDateTime dateFrom, final LocalDateTime dateTo,
                              final DateFromDateTo dateFromDateTo) {
        return (dateFromDateTo.getDateFrom().isAfter(dateFrom)
                && dateFromDateTo.getDateFrom().isBefore(dateTo))
                || (dateFromDateTo.getDateTo().isAfter(dateFrom)
                && dateFromDateTo.getDateTo().isBefore(dateTo))
                || (dateFromDateTo.getDateFrom().isBefore(dateFrom)
                && dateFromDateTo.getDateTo().isAfter(dateTo))

                || (dateFromDateTo.getDateFrom().isEqual(dateFrom) || dateFromDateTo.getDateFrom().isEqual(dateTo))
                || (dateFromDateTo.getDateTo().isEqual(dateFrom) || dateFromDateTo.getDateTo().isEqual(dateTo));
    }

    private List<RentPendingDTO> convertRentListToRentPendingDTOList(final List<Rent> rentArrayList) {
        final ArrayList<RentPendingDTO> rentPendingDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {

            for (final Rent rent : rentArrayList) {
                final RentPendingDTO rentPendingDTO = new RentPendingDTO();
                rentPendingDTO.setId(rent.getId());
                rentPendingDTO.setComment(rent.getReasonForTheLoan());
                rentPendingDTO.setCarDTO(carService.getDTOByLicensePlate(rent.getCar().getLicensePlate()));
                rentPendingDTO.setParkingFrom(new ParkingDTO(rent.getParkingFrom()));
                rentPendingDTO.setParkingTo(new ParkingDTO(rent.getParkingTo()));
                rentPendingDTO.setDateFrom(rent.getDateFrom());
                rentPendingDTO.setDateTo(rent.getDateTo());
                rentPendingDTO.setUserRentInfo(new UserRentInfo(rent.getUser().getName(), rent.getUser().getSurname(), rent.getUser().getPhoneNumber(), rent.getUser().getEmail()));
                rentPendingDTO.setResponse(rent.getAdminResponseForTheRequest());
                rentPendingDTOArrayList.add(rentPendingDTO);
            }
        }
        return rentPendingDTOArrayList;
    }

    private boolean checkDateTimeChronological(final LocalDateTime dateFrom, final LocalDateTime dateTo) {
        return !dateFrom.isAfter(dateTo);
    }

    private List<RentDTO> getAllDTOsByTimeRange(final DateFromDateTo dateFromDateTo) {
        final ArrayList<Rent> rentArrayList = new ArrayList<>();
        rentRepository.findAll().forEach(rentArrayList::add);
        final ArrayList<RentDTO> rentDTOArrayList = new ArrayList<>();

        if (!rentArrayList.isEmpty()) {

            for (final Rent rent : rentArrayList) {
                if (this.checkDate(rent.getDateFrom(), rent.getDateTo(), dateFromDateTo)) {
                    final RentDTO rentDTO = new RentDTO(rent, userService.getDTOByLogin(rent.getUser().getLogin()), carService.getDTOByLicensePlate(rent.getCar().getLicensePlate())
                            , new ParkingDTO(rent.getParkingFrom()), new ParkingDTO((rent.getParkingTo())));
                    rentDTOArrayList.add(rentDTO);
                }
            }
        }
        return rentDTOArrayList;
    }
}
