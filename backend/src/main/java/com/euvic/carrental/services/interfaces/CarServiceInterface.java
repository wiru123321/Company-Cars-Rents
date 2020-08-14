package com.euvic.carrental.services.interfaces;

import com.euvic.carrental.model.Car;
import com.euvic.carrental.responses.CarDTO;

import java.util.List;

public interface CarServiceInterface {
    Car mapRestModel(Long id, CarDTO carDTO, Long parkingId, Long modelId);

    CarDTO mapToCarDTO(Car car);

    Car getEntityByLicensePlate(String licensePlate);

    CarDTO getDTOByLicensePlate(String licensePlate);

    List<CarDTO> getAllDTOs();

    Long addEntityToDB(Car car);

    Long updateCarInDB(String oldCarLicensePlate, CarDTO newCarDTO);

    Long setCarIsNotInCompany(String licensePlate);

    List<CarDTO> getInCompanyCarDTOs();

    List<CarDTO> getInCompanyActiveCarDTOs();

    List<CarDTO> getInCompanyInactiveCarDTOs();

    Long addExistingImageToExistingCar(String carImagePath, String licensePlate);

    Boolean checkIfCarWithLicensePlateExists(String licensePlate);

    Long setCarActivity(Boolean isActive, String licensePlate);
}
